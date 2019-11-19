package mundo;

import java.util.ArrayList;

import excepciones.ContinuasException;
import excepciones.ParentesisException;
import excepciones.ParentesisVacioException;
import excepciones.PremisaException;

public class Validaciones {

	public static void validacionLetrasContiguas(String formula) throws ContinuasException {
		for (int i = 1; i < formula.length(); i++) {
			char actual = formula.charAt(i);
			if (Character.isLetter(actual)) {
				boolean encontrado = false;
				for (int j = i - 1; j > 0 || encontrado == true; j--) {
					char anterior = formula.charAt(j);
					if (anterior == '(' || anterior == ')') {
						encontrado = true;
					} else {
						if (Character.isLetter(anterior)) {
							throw new ContinuasException("Hay atomos contigüos en la premisa");
						}
					}
				}
			}
		}
	}

	public static boolean verificarParentesis(String formula) throws ParentesisException {
		int sum = 0;
		for (int i = 0; i < formula.length(); i++) {
			char actual = formula.charAt(i);
			if (actual == '(') {
				sum++;
			} else if (actual == ')') {
				sum--;
			}
		}

		if (sum == 0) {
			return true;
		} else {
			throw new ParentesisException("No hay el mismo número de parentesis izquierdos y derechos en la fórmula");
		}
	}

	public static boolean verificarAtomos(String formula) {
		for (int i = 0; i < formula.length(); i++) {
			char actual = formula.charAt(i);
			if (!Character.isLetter(actual) && !Character.isWhitespace(actual)) {
				if (actual != '(' && actual != ')' && actual != '¬' && actual != '∨' && actual != '∧' && actual != '→'
						&& actual != '↔') {
					return false;
				}
			}
		}
		return true;
	}

	public static void verificarPremisa(String formula)
			throws ContinuasException, PremisaException, ParentesisVacioException {

		if (formula.isEmpty()) {
			throw new PremisaException("La premisa no puede ser vacia");
		}
		System.out.println("Formula: + " + formula);
		Proposiciones proposiciones = new Proposiciones();
		proposiciones.generarArbolSubformula(formula);
		ArrayList<String> hojas = proposiciones.getArbolSubformula().getHojas();

		for (int i = 0; i < hojas.size(); i++) {
			validacionLetrasContiguas(hojas.get(i));
			verificarParentesisVacios(formula);
			if (hojas.get(i).length() > 1) {
				throw new PremisaException("La premisa no es valida");
			}
		}

	}

	public static void verificarParentesisVacios(String formula) throws ParentesisVacioException {

		for (int i = 0; i < formula.length(); i++) {
			char actual = formula.charAt(i);
			if (actual == '(') {
				if (!isVacio(formula, i + 1)) {
					throw new ParentesisVacioException("Hay parentesis vacíos en la premisa");
				}
			}
		}
	}

	private static boolean isVacio(String formula, int pos) {

		boolean encontrado = false;
		for (int i = pos; i < formula.length() && encontrado == false; i++) {
			char actual = formula.charAt(i);
			if (actual == '(') {
				encontrado = true;
			} else {
				if (Character.isLetter(actual)) {
					encontrado = true;
				}

				if (actual == ')') {
					return false;
				}
			}
		}
		return encontrado;
	}

}
