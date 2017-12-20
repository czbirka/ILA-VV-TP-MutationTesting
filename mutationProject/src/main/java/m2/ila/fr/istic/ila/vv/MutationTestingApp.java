package m2.ila.fr.istic.ila.vv;

import java.io.IOException;

import org.apache.maven.shared.invoker.MavenInvocationException;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import m2.ila.fr.istic.ila.vv.mutation.MutationController;

public class MutationTestingApp {

	public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, BadBytecode, MavenInvocationException {

		MutationController mutationController = new MutationController(Properties.TARGET_DIRECTORY, Properties.TEST_SUITE_FOLDER);

		mutationController.checkMutations();

		mutationController.doMutations();

	}

}
