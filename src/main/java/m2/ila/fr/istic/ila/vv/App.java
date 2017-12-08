package m2.ila.fr.istic.ila.vv;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.analysis.FramePrinter;

public class App {

	public static void main(String[] args) throws NotFoundException, CannotCompileException {
		// verifyParams(args);

		FramePrinter framePrinter = new FramePrinter(System.out);

		// Container for all the class that are be loaded by Javassist
		ClassPool pool = ClassPool.getDefault();

		// On indique au pool où trouver les classes
		pool.appendClassPath(Properties.TARGET_CLASSE_CLASSPATH);

		// non complet de la classe à modifier
		CtClass binOpClass = pool.getCtClass(Properties.JAVA_DOT_CLASS_TO_MUTATE);
		final String className = binOpClass.getName();
		System.err.println("Nom de la classe à modifier " + className);

		// Liste des méthodes
		// CtMethod[] methods = binOpClass.getMethods();
		CtMethod[] methods = binOpClass.getDeclaredMethods(); // Celles Déclarées

		for (CtMethod method : methods) { // Boucle sur les methodes
			final CtClass returnType = method.getReturnType();

			if (returnType.equals(CtClass.voidType)) {
				// VoidOperator
				System.out.println("name: " + method.getName() + Constants.VOID_TYPE_METHOD);

			} else if (returnType.equals(CtClass.doubleType)) {
				// DoubleOperator
				System.out.println("name: " + method.getName() + Constants.DOUBLE_TYPE_METHOD);
				performDoubleMutation(binOpClass, method, Properties.JAVA_DOT_CLASS_TO_MUTATE);

			} else if (returnType.equals(CtClass.booleanType)) {
				// BooleanOperator
				System.out.println("name: " + method.getName() + Constants.BOOLEAN_TYPE_METHOD);

			}
			// ArithmeticOperator

			// ExtraOperatpr

			// EtcOperator
		} // End For

		binOpClass.detach();
		binOpClass.debugWriteFile(Properties.OUTPUT_DIRECTORY);
	}

	private static void performDoubleMutation(CtClass original, CtMethod method, String className)
			throws NotFoundException, CannotCompileException {
		double arbirtraryValue = 123456.789;
		String mutateCode = "{ return " + arbirtraryValue + "; }";
		method.setBody(mutateCode);

		// TODO Auto-generated method stub
		System.out.println("method was mutated => " + method.getName());
		RunAClass.main(null);
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
