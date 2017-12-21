package m2.ila.fr.istic.ila.vv.mutation.operator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.shared.invoker.MavenInvocationException;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import m2.ila.fr.istic.ila.vv.Constants;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation1;
import m2.ila.fr.istic.ila.vv.target.Target;

public class DoubleOperator implements MutationOperator {

	private List<Mutation> mutations;
	
	public DoubleOperator() {
		mutations = new ArrayList<Mutation>();
	}
	
	@Override
	public void doMutate(Mutation mutation) {
		System.out.println(mutation.toString());
	}

	@Override
	public void checkMutate(Target target, CtMethod method)
			throws NotFoundException, CannotCompileException, IOException, BadBytecode, MavenInvocationException {
		CtClass returnType = method.getReturnType();
		if (returnType.equals(CtClass.doubleType)) {
			System.out.println("name: " + method.getName() + Constants.DOUBLE_TYPE_METHOD);
			mutations.add(new Mutation1(target, method, "{return 1234546.789;}"));
		}
	}

	@Override
	public List<Mutation> getMutations() {
		return mutations;
	}

}
