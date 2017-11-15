package m2.ila.fr.istic.ila.vv;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.analysis.FramePrinter;

public class App {
	
	private static final String CLASS_FILES_FOLDER = "somePath";
	private static final String TEST_SUITE_FOLDER = "anotherPath";




	public static void main(String[] args) throws NotFoundException {
//		verifyParams(args);
		
		FramePrinter framePrinter = new FramePrinter(System.out);
		
		// Container for all the class that are be loaded by Javassist
		ClassPool pool = ClassPool.getDefault();
		
		// On indique au pool où trouver les classes
		pool.appendClassPath("target/classes");

		// non complet de la classe BinOp
		CtClass binOpClass = pool.getCtClass("m2.ila.fr.istic.ila.vv.BinOp");
		
		// Impression de toutes les méthodes (avec leur body) de la classe
		FramePrinter.print(binOpClass, System.out);
		
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
