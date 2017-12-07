package m2.ila.fr.istic.ila.vv.mutation.operator;

import javassist.CtMethod;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.target.Target;

public interface MutationOperator {

	public void doMutate();
	public void checkMutate(CtMethod method) throws NotFoundException;
	
}
