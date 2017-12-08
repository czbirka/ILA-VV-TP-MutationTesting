package m2.ila.fr.istic.ila.vv;

import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.mutation.MutationController;

public class MutationTestingApp {

	public static void main(String[] args) throws NotFoundException {

		MutationController mutationController = new MutationController(Properties.CLASS_FILES_FOLDER, Properties.TEST_SUITE_FOLDER);

		mutationController.checkMutations();

		mutationController.doMutations();

	}

}
