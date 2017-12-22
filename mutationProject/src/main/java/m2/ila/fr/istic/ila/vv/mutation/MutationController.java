package m2.ila.fr.istic.ila.vv.mutation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.maven.shared.invoker.MavenInvocationException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import m2.ila.fr.istic.ila.vv.mutation.loader.OperatorsLoader;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.mutation.operator.MutationOperator;

public class MutationController {

	private String targetPath;
	@SuppressWarnings("unused")
	private String testPath;
	
	private List<MutationOperator> mutators;
	private OperatorsLoader operatorsLoader;
	
	public MutationController(String projectPath) throws IOException {
		
		this.targetPath = projectPath + "/target/classes";
		this.testPath = projectPath + "/target/test-classes";
		
		operatorsLoader = new OperatorsLoader();
		this.mutators = operatorsLoader.getOperators();
		
	}
	
	public void checkMutations() throws NotFoundException, CannotCompileException, IOException, BadBytecode, MavenInvocationException {
		
		ClassPool pool = ClassPool.getDefault();	
		pool.appendClassPath(targetPath);
		ClassLoader classLoader = new ClassLoader();
		List<Class<?>> classes = classLoader
				.getClassesFromDirectory(targetPath);
		
		//on passe en revue tous les fichiers cible
		for(Class<?> c : classes) {
			
			CtClass targetClass = pool.getCtClass(c.getName());
			final String targetClassName = targetClass.getName();
					
					System.err.println("Nom de la classe à modifier :" + targetClassName);
					
					// Liste des méthodes
					CtMethod[] methods = targetClass.getDeclaredMethods();
					
					// Boucle sur les methodes
					for (CtMethod method : methods) {
						System.out.println("Method name: " + method.getName());
						
						//on passe les méthodes aux mutateurs pour vérification
						for (MutationOperator mutator : mutators) {
							//mutator.checkMutate(target, method);
							mutator.checkMutate(method);
						}
						
					}
	
		}

	}
	
	public void redactionBilan() throws FileNotFoundException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
		Date date = new Date();

		String nom = "bilan mutations "+dateFormat.format(date);
		PrintWriter writer = new PrintWriter(nom);
		writer.println("BILAN MUTATIONS");
		writer.println();
		writer.println(date);
		writer.println();
		writer.println();
		
		for(MutationOperator mutator : mutators) {
			writer.println(mutator.getClass());
			writer.println();
			for(Mutation mutation : mutator.getMutations()) {
				writer.println(mutation.toString());
				writer.println();
			}
			writer.println();
			writer.println();
		}
		
		writer.close();
		
	}
}
