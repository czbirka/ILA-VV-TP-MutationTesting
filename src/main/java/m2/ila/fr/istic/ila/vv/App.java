package m2.ila.fr.istic.ila.vv;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.analysis.FramePrinter;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class App {

	private static final String CLASS_FILES_FOLDER = "somePath";
	private static final String TEST_SUITE_FOLDER = "anotherPath";

	public static void main(String[] args) throws NotFoundException, CannotCompileException {
		// verifyParams(args);

		FramePrinter framePrinter = new FramePrinter(System.out);

		// Container for all the class that are be loaded by Javassist
		ClassPool pool = ClassPool.getDefault();

		// On indique au pool où trouver les classes
		pool.appendClassPath("target/classes");

		// non complet de la classe à modifier
		CtClass binOpClass = pool.getCtClass("m2.ila.fr.istic.ila.vv.BinOp");
		final String className = binOpClass.getName();
		System.err.println("Nom de la classe à modifier " + className);

		// Liste des méthodes
		// CtMethod[] methods = binOpClass.getMethods();
		CtMethod[] methods = binOpClass.getDeclaredMethods(); // Celles Déclarées

		for (CtMethod method : methods) {
			final CtClass returnType = method.getReturnType();

			if (returnType.equals(CtClass.voidType)) {
				System.out.println("name: " + method.getName() + ":: void");
			} else if (returnType.equals(CtClass.doubleType)) {
				System.out.println("name: " + method.getName() + "::double");
			} else if (returnType.equals(CtClass.booleanType)) {
				System.out.println("name: " + method.getName() + "::boolean");
			}
		} // End For
	}

	private static void verifyParams(String[] args) {
		if (args.length < 2) {
			System.err.println("Mutation tool need at least 2 params :\n" + "1) location of class file(s)\n"
					+ "2) location of test case(s) to perform");
			System.exit(-1);
		}

		for (String input : args) {
			System.err.println("param : " + input);
		}
	}
}
