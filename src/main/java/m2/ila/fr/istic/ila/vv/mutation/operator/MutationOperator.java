package m2.ila.fr.istic.ila.vv.mutation.operator;

import java.util.List;

import javassist.CtMethod;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.target.Target;

public interface MutationOperator {

	public void doMutate(Mutation mutation);
	public void checkMutate(Target target, CtMethod method) throws NotFoundException;
	public List<Mutation> getMutations();
}
