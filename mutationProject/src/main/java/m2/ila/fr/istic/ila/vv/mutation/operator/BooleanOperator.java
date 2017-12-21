package m2.ila.fr.istic.ila.vv.mutation.operator;

import java.util.ArrayList;
import java.util.List;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.Constants;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation1;
import m2.ila.fr.istic.ila.vv.target.Target;

public class BooleanOperator implements MutationOperator {

	private List<Mutation> mutations;
	
	public BooleanOperator() {
		mutations = new ArrayList<Mutation>();
	}
	
	public void checkMutate(Target target, CtMethod method) throws NotFoundException {
		CtClass returnType = method.getReturnType();
		if (returnType.equals(CtClass.booleanType)) {
			System.out.println("name: " + method.getName() + Constants.BOOLEAN_TYPE_METHOD);
			mutations.add(new Mutation1(target, method, "{return true;}"));
			mutations.add(new Mutation1(target, method, "{return false;}"));
		}
	}

	public List<Mutation> getMutations() {
		return mutations;
	}

	public void doMutate(Mutation mutation) {
		System.out.println(mutation.toString());
		
	}

}
