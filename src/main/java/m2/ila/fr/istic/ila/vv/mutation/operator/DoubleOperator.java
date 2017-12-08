package m2.ila.fr.istic.ila.vv.mutation.operator;

import java.util.ArrayList;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation1;
import m2.ila.fr.istic.ila.vv.target.Target;

public class DoubleOperator implements MutationOperator {

	private List<Mutation> mutations;
	
	public DoubleOperator() {
		mutations = new ArrayList<Mutation>();
	}
	
	public void doMutate(Mutation mutation) {
		// TODO Auto-generated method stub

	}

	public void checkMutate(Target target, CtMethod method) throws NotFoundException {
		CtClass returnType = method.getReturnType();
		if (returnType.equals(CtClass.doubleType)) {
			System.out.println("name: " + method.getName() + "::double");
			Mutation mutation = new Mutation1(target, method);
			mutations.add(mutation);
		}
	}

	public List<Mutation> getMutations() {
		return this.mutations;
	}

}
