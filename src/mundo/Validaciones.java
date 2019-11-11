package mundo;

import java.util.ArrayList;

public class Validaciones {

	public boolean validacionLetrasContiguas(String formula) {
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
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean verificarParentesis(String formula) {
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
			return false;
		}
	}

	public boolean verificarAtomos(String formula) {
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

	public boolean verificarPremisa(String formula) {
		Proposiciones proposiciones = new Proposiciones();
		proposiciones.modificarArbol(formula);
		ArrayList<String> hojas = proposiciones.getArbolSubformula().getHojas();

		for (int i = 0; i < hojas.size(); i++) {
			if (!validacionLetrasContiguas(hojas.get(i)) && hojas.get(i).length() > 1) {
				return false;
			}
		}

		return true;
	}

	public boolean verificarParentesisVacios(String formula) {

		for (int i = 0; i < formula.length(); i++) {
			char actual = formula.charAt(i);
			if (actual == '(') {
				if (isVacio(formula, i + 1)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isVacio(String formula, int pos) {

		boolean encontrado = false;
		for (int i = pos; i < formula.length() || encontrado == false; i++) {
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
