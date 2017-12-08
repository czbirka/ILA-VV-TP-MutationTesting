package m2.ila.fr.istic.ila.vv.mutation.operator;

import java.util.ArrayList;
import java.util.List;

import javassist.CtMethod;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.target.Target;

public class BooleanOperator implements MutationOperator {

	private List<Mutation> mutations;
	
	public BooleanOperator() {
		mutations = new ArrayList<Mutation>();
	}
	
	public void checkMutate(Target target, CtMethod method) {
		// TODO Auto-generated method stub
		
	}

	public List<Mutation> getMutations() {
		return mutations;
	}

	public void doMutate(Mutation mutation) {
		// TODO Auto-generated method stub
		
	}

}
