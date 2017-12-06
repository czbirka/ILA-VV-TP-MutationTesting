package m2.ila.fr.istic.ila.vv.mutation.operator;

import javassist.ClassPool;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.target.Target;

public class DoubleOperator implements MutationOperator {

	public void doMutate() {
		// TODO Auto-generated method stub

	}

	public void checkMutate(Target target, String targetPath) throws NotFoundException {
		

		ClassPool pool = ClassPool.getDefault();
		pool.appendClassPath(targetPath);
		
//		
//		CtClass binOpClass = pool.getCtClass("BinOp");
//		final String className = binOpClass.getName();
//		System.err.println("Nom de la classe à modifier " + className);
		
	}

}
