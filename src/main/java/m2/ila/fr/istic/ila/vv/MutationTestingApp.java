package m2.ila.fr.istic.ila.vv;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.mutation.MutationController;

public class MutationTestingApp {
	
	private static final String CLASS_FILES_FOLDER = "targetFolder";
	private static final String TEST_SUITE_FOLDER = "testFolder";
	
	
	
	public static void main(String[] args) throws NotFoundException {
		
		MutationController mutationController = new MutationController(CLASS_FILES_FOLDER, TEST_SUITE_FOLDER);
		
		mutationController.checkMutations();
		
		mutationController.doMutations();
		
		
	}

}
