package m2.ila.fr.istic.ila.vv.mutation;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import m2.ila.fr.istic.ila.vv.RunAClass;
import m2.ila.fr.istic.ila.vv.mutation.loader.OperatorsLoader;
import m2.ila.fr.istic.ila.vv.mutation.loader.TargetsLoader;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.mutation.operator.BooleanOperator;
import m2.ila.fr.istic.ila.vv.mutation.operator.ArithmeticOperator;
import m2.ila.fr.istic.ila.vv.mutation.operator.MutationOperator;
import m2.ila.fr.istic.ila.vv.target.Target;
import m2.ila.fr.istic.ila.vv.test.Test;

public class MutationController {

	private String targetPath;
	private String testPath;
	
	private List<Target> targets;
	private List<Test> tests;
	private List<MutationOperator> mutators;
	private ClassLoader classLoader;
	private TargetsLoader targetsLoader;
	private OperatorsLoader operatorsLoader;
	
	public MutationController(String targetPath, String testPath) throws IOException {
		
		this.targetPath = targetPath;
		this.testPath = testPath;
		
		targetsLoader = new TargetsLoader();
		this.targets = targetsLoader.getTargets();
		
		operatorsLoader = new OperatorsLoader();
		this.mutators = operatorsLoader.getOperators();
		
	}
	
	public void checkMutations() throws NotFoundException, CannotCompileException, IOException, BadBytecode, MavenInvocationException {
		
		ClassPool pool = ClassPool.getDefault();
		//pool.appendClassPath(targetPath + "/target/classes");		
		pool.appendClassPath(targetPath);
		ClassLoader classLoader = new ClassLoader();
		//List<Class<?>> classes = classLoader
		//		.getClassesFromDirectory(targetPath + "/target/classes");
		List<Class<?>> classes = classLoader
				.getClassesFromDirectory(targetPath);
		
		//on passe en revue tous les fichiers cible
		for(Class<?> c : classes) {
			
			CtClass targetClass = pool.getCtClass(c.getName());
			final String targetClassName = targetClass.getName();
			for(Target target : targets) {
				if(target.getName().equals(c.getSimpleName())) {
					
					System.err.println("Nom de la classe à modifier :" + targetClassName);
					
					// Liste des méthodes
					CtMethod[] methods = targetClass.getDeclaredMethods();
					
					// Boucle sur les methodes
					for (CtMethod method : methods) {
						System.out.println("Method name: " + method.getName());
						
//						//on passe les méthodes aux mutateurs pour vérification
						for (MutationOperator mutator : mutators) {
							mutator.checkMutate(target, method);
						}
						
					}

				}
			}
			
			
			//System.out.println(c.getName());
			//System.out.println(c.getSimpleName());
			
			
			
			
//			// On crée la classe
//			Target target = new Target(c.getName(), null /* TODO a redeffini*/);
//			
//			
			
		}

		

	}
	
	public void doMutations() throws NotFoundException, CannotCompileException {
		
		ClassPool pool = ClassPool.getDefault();
		pool.appendClassPath(targetPath);
		ClassLoader classLoader = new ClassLoader();
		List<Class<?>> classes = classLoader
				.getClassesFromDirectory(targetPath);
		
		for(Class<?> c : classes) {
			
			CtClass targetClass = pool.getCtClass(c.getName());
			final String targetClassName = targetClass.getName();
			
			for(MutationOperator mutator : mutators) {
				
				for(Mutation mutation : mutator.getMutations()) {
					
					if(mutation.getTarget().getName().equals(c.getSimpleName())) {
						
						System.out.println("pouet");
						if (targetClass.isFrozen()) {
							targetClass.defrost();
						}
						
						CtMethod[] targetMethods = targetClass.getDeclaredMethods();
						// Boucle sur les methodes
						for (CtMethod method : targetMethods) {
							if(method.getName().equals(mutation.getMethod().getName())) {
								method.setBody(mutation.getMutateCode());
							}
							
						}
						
						
						//mutation.getMethod().setBody(mutation.getMutateCode());

						System.out.println("method was mutated => " + mutation.getMethod().getName());
						
						ClassPool pooltest = ClassPool.getDefault();	
						pool.appendClassPath(testPath);
						ClassLoader classLoadertest = new ClassLoader();
						List<Class<?>> tests = classLoadertest
								.getClassesFromDirectory(testPath);
						
						for(Class<?> t : tests) {
							
							if(mutation.getTarget().contientTest(t.getSimpleName())) {
								
								System.out.println("Test : "+t.getName());
						        
						        JUnitCore core = new JUnitCore();
						         Result result = core.run(t);
						         System.out.println("FINISHED");
						         System.out.println(String.format("| IGNORED: %d", result.getIgnoreCount()));
						         System.out.println(String.format("| FAILURES: %d", result.getFailureCount()));
						         System.out.println(String.format("| RUN: %d", result.getRunCount()));
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
			
	}
	
	public void doTests() {
		
	}
	
}
