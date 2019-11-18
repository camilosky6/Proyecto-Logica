package mundo;

import java.io.IOException;

//Esto e sun ejemplo del push y el pull
public class Proposiciones {

	private Arbol arbolSubformula = new Arbol();

	/**
	 * Método recursivo que retorna un string con las diferentes subformulas de la
	 * subformula original.
	 * 
	 * @param formula Formula que se va a evaluar.
	 * @return Un texto con las diferentes subformulas separadas con comas.
	 * @throws IOException Si no encuentra la carpeta contenedora de los operadores.
	 */
	public String funcionSubformula(String formula) throws IOException {

		if (!formula.isEmpty()) {
			arbolSubformula.insertarNodo(formula);
			int pos = getPosicionOperadorPrincipal(formula);
			String[] aux = getOperadores();
			String nuevaFormula = "";
			String nuevaFormula2 = "";
			if (formula.charAt(pos) == aux[0].charAt(1)) {
				nuevaFormula = eliminarParentesisUnario(formula, pos);
				return formula + " , " + funcionSubformula(nuevaFormula);
			} else {
				nuevaFormula = eliminarParentesisBinarios(formula, pos);
				nuevaFormula2 = eliminarParentesisBinarios2(formula, pos);
				nuevaFormula = funcionSubformula(nuevaFormula);
				nuevaFormula2 = funcionSubformula(nuevaFormula2);

				if (nuevaFormula.isEmpty() || nuevaFormula2.isEmpty()) {
					if (nuevaFormula.isEmpty() && nuevaFormula2.isEmpty()) {
						return formula;
					}
					if (nuevaFormula.isEmpty()) {
						return formula + " , " + nuevaFormula2;
					}
					if (nuevaFormula2.isEmpty()) {
						return formula + " , " + nuevaFormula;
					}
				}
				return formula + " , " + nuevaFormula + " , " + nuevaFormula2;
			}
		} else {
			return "";
		}
	}

	/**
	 * Método que crea el arbol subformula dada una formula.
	 * 
	 * @param formula String que se va a evaluar.
	 * @throws IOException Si no se halla el txt con los operadores.
	 */
	public void generarArbolSubformula(String formula) {
		arbolSubformula.setRaiz(modificarArbol(formula));
	}

	/**
	 * Método recursivo que retorna un nodo con los operadores principales de un
	 * formula que llega como parametro. Ademas va a crear hijos al nodo que va a
	 * retornar dependiendo si el operador es unario (creando un solo hijo a la
	 * izquierda) o binario (creando un hijo izquierdo y otro derecho).
	 * 
	 * @param formula Formula a la cual se le va a obtener el operador principal y
	 *                que se va a agregar al Nodo que se va a retornar.
	 * @return Nodo con un operador principal y a su vez con hijos que tienen los
	 *         diferentes operadores principales de las subformulas de la formula
	 *         que llega como parametro.
	 * @throws IOException Si no se halla el txt con los operadores.
	 */
	public Nodo modificarArbol(String formula) {
		if (!formula.isEmpty()) {
			int pos = getPosicionOperadorPrincipal(formula);
			char aux = formula.charAt(pos);
			String[] operadores = getOperadores();
			Nodo nodo = new Nodo(formula);
			if (isOperador(aux) == false) {
				return nodo;
			}
			if (aux == operadores[0].charAt(0)) {
				nodo.setIzquierdo(modificarArbol(eliminarParentesisUnario(formula, pos)));
			} else {
				nodo.setIzquierdo(modificarArbol(eliminarParentesisBinarios(formula, pos)));
				nodo.setDerecho(modificarArbol(eliminarParentesisBinarios2(formula, pos)));
			}
			return nodo;
		}
		return null;
	}

	/**
	 * Método que retorna un entero, que es la posicion del operador principal de la
	 * formula que llega como parametro.
	 * 
	 * @param formula Formula que se va a evaluar.
	 * @return La posicion del operador principal
	 * @throws IOException Si no encuentra la carpeta contenedora de los operadores.
	 */
	public int getPosicionOperadorPrincipal(String formula) {
		formula = formula.trim();
		String[] aux = getOperadores();
		if (formula.charAt(0) == aux[0].charAt(0)) {
			return 0;
		}
		int cont = 0;
		boolean encontrado = false;
		for (int i = 0; i < formula.length(); i++) {
			if (formula.charAt(i) == '(') {
				cont++;
				if (encontrado == false) {
					encontrado = true;
				}
			}

			if (formula.charAt(i) == ')') {
				cont--;
			}

			if (encontrado == true && cont == 0) {
				for (int j = i + 1; j < formula.length(); j++) {
					if (isOperador(formula.charAt(j))) {
						return j;
					}
				}
			}
		}
		return 0;
	}

	private String eliminarParentesisUnario(String formula, int pos) {
		int cont = 0;
		boolean primerEncuentro = false;
		String res = "";
		for (int i = pos; i < formula.length(); i++) {
			if (formula.charAt(i) == '(') {
				cont++;
				if (primerEncuentro == false) {
					primerEncuentro = true;
					res = formula.substring(0, i - 1);
				}
			}

			if (formula.charAt(i) == ')') {
				cont--;
			}

			if (primerEncuentro == true && cont == 0) {
				res += formula.substring(res.length() + 2, i) + formula.substring(i + 1);
				return res.trim();
			}
		}
		return "";
	}

	/**
	 * M�todo que elimina los parentesis generados al lado derecho de un operador
	 * binario y retorna ese String resultante.
	 * 
	 * @param formula Formula que se va a evaluar.
	 * @param pos,    Entero que indica la posicion del operador binario.
	 * @return El string que se encuentra a la derecha del operador binario al
	 *         eliminar los parent�sis que �ste genera
	 */
	private String eliminarParentesisBinarios2(String formula, int pos) {
		int cont = 0;
		boolean primerEncuentro = false;
		String res = "";
		int posInicio = 0;
		int posFinal = 0;
		for (int i = pos; i < formula.length(); i++) {
			if (formula.charAt(i) == '(') {
				cont++;
				if (primerEncuentro == false) {
					primerEncuentro = true;
					posInicio = i;
				}
			}

			if (formula.charAt(i) == ')') {
				cont--;
			}

			if (primerEncuentro == true && cont == 0) {
				posFinal = i;
				res = formula.substring(posInicio + 1, posFinal) + formula.substring(posFinal + 1, formula.length());
				return res.trim();
			}
		}
		return "";
	}

	/**
	 * M�todo que elimina los parentesis generados al lado izquierdo de un operador
	 * binario y retorna ese String resultante.
	 * 
	 * @param formula Formula que se va a evaluar.
	 * @param pos     Entero que indica la posicion del operador binario.
	 * @return El string que se encuentra a la izquierda del operador binario al
	 *         eliminar los parent�sis que �ste genera.
	 */
	private String eliminarParentesisBinarios(String formula, int pos) {
		int cont = 0;
		boolean primerEncuentro = false;
		String res = "";
		int pos2 = 0;
		for (int i = pos; i >= 0; i--) {
			if (formula.charAt(i) == ')') {
				cont++;
				if (primerEncuentro == false) {
					primerEncuentro = true;
					pos2 = i;
				}
			}

			if (formula.charAt(i) == '(') {
				cont--;
			}

			if (primerEncuentro == true && cont == 0) {
				res = formula.substring(0, i) + formula.substring(i + 1, pos2);
				return res.trim();
			}
		}
		return "";
	}

	private boolean isOperador(char caracter) {

		for (int i = 0; i < 5; i++) {
			if (caracter != '¬' && caracter != '∨' && caracter != '∧' && caracter != '→' && caracter != '↔') {
				return true;
			}
		}
		return false;
	}

	private String[] getOperadores() {
		String[] aux = { "¬", "∨", "∧", "→", "↔" };
		return aux;
	}

	public Arbol getArbolSubformula() {
		return arbolSubformula;
	}

	public void setArbolSubformula(Arbol arbolSubformula) {
		this.arbolSubformula = arbolSubformula;
	}

}
