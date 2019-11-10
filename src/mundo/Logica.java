package mundo;

import java.util.ArrayList;

public class Logica {

	/**
	 * Metodo para verificar si es un operador
	 * @param caracter
	 * @return
	 */
	public boolean esOperador(char caracter) {
		if (caracter == '¬' || caracter == '∨' || caracter == '∧' || caracter == '→' || caracter == '↔') {
			return true;
		}
		return false;
	}
	
	/********************************************************************************
	                                  Resolucion
	 ******************************************************************************/
	private static void resolucion(ArrayList<Clausula> clausulas,int j) {
		if (j< clausulas.size() && clausulas.get(j).estaUsada()) {
			for (int i = 0; i < clausulas.size(); i++) {
				if (tienenParComplementario(clausulas.get(j),clausulas.get(i))) {
					Clausula aux = comprobarRes(clausulas.get(j),clausulas.get(i));
					//hacer que las clausulas usadas en la resolicion esten ya usadas
					clausulas.add(aux);
					resolucion(clausulas,j+1);
				}
			}
		}
	}
	
	private static Clausula comprobarRes(Clausula token, Clausula token2) {
		// TODO Auto-generated method stub
		return null;
	}


	private static boolean tienenParComplementario(Clausula token, Clausula token2) {
		// TODO Auto-generated method stub
		return false;
	}


	private static ArrayList<Clausula> obtenerToken(String formula) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Sacar clausales de la formula	
	 * @param formula
	 * @return
	 */
	private static ArrayList<Clausula> obtenerClausulas(String formula) {
		//cambiar a O
		ArrayList<Clausula> aux = new ArrayList<>();
		String[] arreglo = formula.split("v");
		for (int i = 0; i < arreglo.length; i++) {
			ArrayList<Atomo> literales = new ArrayList<>();
			Clausula clausula = new Clausula(false, literales, Clausula.PREMISA, i);
			for (int j = 0; j < arreglo[i].length(); j++) {
				if (esAtomo(arreglo[i].charAt(j))) {
					Atomo p = new Atomo(false, arreglo[i]);
				}
			}
		}
		
		return null;
	}
	
	private static boolean esAtomo(char p) {
		// TODO Auto-generated method stub
		return false;
	}

}
