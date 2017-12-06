package m2.ila.fr.istic.ila.vv.mutation.operator;

import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.target.Target;

public interface MutationOperator {

	public void doMutate();
	public void checkMutate(Target target, String targetPath) throws NotFoundException;
	
}
