package m2.ila.fr.istic.ila.vv.mutation;

import java.util.ArrayList;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.mutation.operator.BooleanOperator;
import m2.ila.fr.istic.ila.vv.mutation.operator.DoubleOperator;
import m2.ila.fr.istic.ila.vv.mutation.operator.MutationOperator;
import m2.ila.fr.istic.ila.vv.target.Target;
import m2.ila.fr.istic.ila.vv.test.Test;

public class MutationController {

	private String targetPath;
	private String testPath;
	
	private List<Target> targets;
	private List<MutationOperator> mutators;
	private ClassLoader classLoader;
	
	public MutationController(String targetPath, String testPath) {
		this.targetPath = targetPath;
		this.testPath = testPath;
		this.targets = new ArrayList<Target>();
		this.mutators = new ArrayList<MutationOperator>();
		
		//pour test avant mise en place du loader
		Test test1 = new Test("BinOpTest");
		Test test2 = new Test("BinOpTest2");
		Target target1 = new Target("BinOp");
		target1.getTests().add(test1);
		target1.getTests().add(test2);
		
		targets.add(target1);
		
		MutationOperator operator1 = new BooleanOperator();
		MutationOperator operator2 = new DoubleOperator();
		
		mutators.add(operator1);
		mutators.add(operator2);
		
	}
	
	public void checkMutations() throws NotFoundException {
		
		ClassPool pool = ClassPool.getDefault();
		pool.appendClassPath(targetPath);
		
		//on passe en revue tous les fichiers cible
		for(Target target : targets) {
			
			CtClass targetClass = pool.getCtClass("m2.ila.fr.istic.ila.vv."+target.getName());
			final String targetClassName = targetClass.getName();
			System.err.println("Nom de la classe à modifier :" + targetClassName);
			
			
			// Liste des méthodes
			CtMethod[] methods = targetClass.getDeclaredMethods();
			
			// Boucle sur les methodes
			for (CtMethod method : methods) {
				System.out.println("Method name: " + method.getName());
				
				//on passe les méthodes aux mutateurs pour vérification
				for (MutationOperator mutator : mutators) {
					mutator.checkMutate(method);
				}
				
			}
			
		}

		

	}
	
	public void doMutations() {
		for(MutationOperator mutator : mutators) {
			mutator.doMutate();
		}
	}
	
}
