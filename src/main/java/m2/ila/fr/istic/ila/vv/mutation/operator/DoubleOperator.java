package m2.ila.fr.istic.ila.vv.mutation.operator;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.target.Target;

public class DoubleOperator implements MutationOperator {

	public void doMutate() {
		// TODO Auto-generated method stub

	}

	public void checkMutate(CtMethod method) throws NotFoundException {
		final CtClass returnType = method.getReturnType();
		
	}

}
