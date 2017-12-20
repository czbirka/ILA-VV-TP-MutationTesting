package m2.ila.fr.istic.ila.vv.mutation.operator;

import java.io.IOException;
import java.util.List;

import org.apache.maven.shared.invoker.MavenInvocationException;

import javassist.CannotCompileException;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.target.Target;

public interface MutationOperator {

	public void doMutate(Mutation mutation);
	public void checkMutate(Target target, CtMethod method) throws NotFoundException, CannotCompileException, IOException, BadBytecode, MavenInvocationException;
	public List<Mutation> getMutations();
}
