package master2.binop;

public class BinOp {

	public static void main(String[] args) {

		double param1 = 5.63;
		double param2 = 2.58;

		affiche("+", param1, param2);
		affiche("<", param1, param2);

	}

	public static double addition(double param1, double param2) {
		return param1 + param2;
	}

	public static double soustraction(double param1, double param2) {
		return param1 - param2;
	}

	public static double multiplication(double param1, double param2) {
		return param1 * param2;
	}

	public static double division(double param1, double param2) {
		return param1 / param2;
	}

	public static boolean estEgal(double param1, double param2) {
		return (param1 == param2);
	}

	public static boolean estSuperieur(double param1, double param2) {
		return (param1 > param2);
	}

	public static boolean estInferieur(double param1, double param2) {
		return (param1 < param2);
	}

	public static void affiche(String op, double param1, double param2) {

		if (op.equals("+")) {
			System.out.println("Addition de " + param1 + " et de " + param2);
			System.out.println("Résultat = " + addition(param1, param2));
		} else if (op.equals("-")) {
			System.out.println("Soustraction de " + param1 + " et de " + param2);
			System.out.println("Résultat = " + soustraction(param1, param2));
		} else if (op.equals("*")) {
			System.out.println("Multiplication de " + param1 + " et de " + param2);
			System.out.println("Résultat = " + multiplication(param1, param2));
		} else if (op.equals("/")) {
			System.out.println("Division de " + param1 + " et de " + param2);
			System.out.println("Résultat = " + division(param1, param2));
		} else if (op.equals("<")) {
			System.out.println("Test " + param1 + " supérieur à " + param2);
			System.out.println("Résultat = " + estSuperieur(param1, param2));
		} else if (op.equals(">")) {
			System.out.println("Test " + param1 + " inférieur à " + param2);
			System.out.println("Résultat = " + estInferieur(param1, param2));
		} else if (op.equals("=")) {
			System.out.println("Test " + param1 + " égal à " + param2);
			System.out.println("Résultat = " + estEgal(param1, param2));
		} else {
			System.out.println("Opération impossible");
		}
	}
}
