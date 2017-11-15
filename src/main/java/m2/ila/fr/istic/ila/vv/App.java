package m2.ila.fr.istic.ila.vv;

public class App {
	
	private static final String CLASS_FILES_FOLDER = "somePath";
	private static final String TEST_SUITE_FOLDER = "anotherPath";




	public static void main(String[] args) {
//		verifyParams(args);
		
	}
	
	private static void verifyParams(String[] args) {
		if (args.length < 2) {
			System.err.println(
				"Mutation tool need at least 2 params :\n" + 
				"1) location of class file(s)\n" + 
				"2) location of test case(s) to perform" 				
			);
			System.exit(-1);
		}

		for (String input : args) {
			System.err.println("param : " + input);
		}
	}
}
