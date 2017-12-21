package m2.ila.fr.istic.ila.vv.mutation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.shared.invoker.MavenInvocationException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
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
	
	public MutationController(String targetPath, String testPath) throws IOException {
		this.targetPath = targetPath;
		this.testPath = testPath;
		
		targetsLoader = new TargetsLoader();
		this.targets = targetsLoader.getTargets();
		
		
//		this.targets = new ArrayList<Target>();
		this.mutators = new ArrayList<MutationOperator>();
		
		//pour test avant mise en place du loader
//		Test test1 = new Test("BinOpTest");
//		Target target1 = new Target("BinOp", test1);
//		Test test2 = new Test("BinOpTest2");
//		target1.getTests().add(test1);
//		target1.getTests().add(test2);
//		target1.setTest(test1);
//		
//		targets.add(target1);
		
		MutationOperator operator1 = new BooleanOperator();
		MutationOperator operator2 = new ArithmeticOperator();
		
		mutators.add(operator1);
		mutators.add(operator2);
		
	}
	
	public void checkMutations() throws NotFoundException, CannotCompileException, IOException, BadBytecode, MavenInvocationException {
		
		ClassPool pool = ClassPool.getDefault();
		pool.appendClassPath(targetPath + "/target/classes");		
		ClassLoader classLoader = new ClassLoader();
		List<Class<?>> classes = classLoader
				.getClassesFromDirectory(targetPath + "/target/classes");
		
		//on passe en revue tous les fichiers cible
		for(Class<?> c : classes) {
			System.out.println(c.getName());
			CtClass targetClass = pool.getCtClass(c.getName());
			final String targetClassName = targetClass.getName();
			System.err.println("Nom de la classe à modifier :" + targetClassName);
			
//			// On crée la classe
//			Target target = new Target(c.getName(), null /* TODO a redeffini*/);
//			
//			// Liste des méthodes
//			CtMethod[] methods = targetClass.getDeclaredMethods();
//			
//			// Boucle sur les methodes
//			for (CtMethod method : methods) {
//				System.out.println("Method name: " + method.getName());
//				
//				//on passe les méthodes aux mutateurs pour vérification
//				for (MutationOperator mutator : mutators) {
//					mutator.checkMutate(target, method);
//				}
//				
//			}
			
		}

		

	}
	
	public void doMutations() {
		for(MutationOperator mutator : mutators) {
			for(Mutation mutation : mutator.getMutations()) {
				mutator.doMutate(mutation);
				doTests();
			}
		}
	}
	
	public void doTests() {
		
	}
	
}
