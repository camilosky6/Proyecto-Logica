package mundo;

import java.util.ArrayList;

public class Logica {

	public static final String NEGACION = "¬";
	public static final String CONJUNCION = "∧";
	public static final String DISYUNCION = "∨";
	public static final String CONDICIONAL = "→";
	public static final String EQUIVALENCIA = "↔";
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
	/*********************************************************************************
	                                    FNC
	 *********************************************************************************/
	/**
     * dada una formula la lleva a FNC
     */
    private String fnc(String entrada) {
        int contador1 = 0;
        String derecha = "", izquierda = "", tmp1 = "", iz, der;
        for (int i = 0; i < entrada.length(); i++) {
            if (entrada.charAt(i) == ')')
                contador1--;
            else if (entrada.charAt(i) == '(')
                contador1++;
            if (contador1 == 0) {
                izquierda = iz = entrada.substring(1, i);
                derecha = der = entrada.substring(i + 3, entrada.length() - 1);

                String tmpIzquierda = "", tmpDerecho = "", f = (entrada.charAt(i + 1) + "");
                int hf = 1;
                if (f.equals("(") || f.equals(")"))
                    hf = 2;
                if ((entrada.charAt(i + hf) + "").equals(DISYUNCION)) {
                    String opDerecha = getOperadorPrincipal(derecha), opIzquierda = getOperadorPrincipal(izquierda);
                    if ((opDerecha.equals("") || opDerecha.equals(DISYUNCION)) && (opIzquierda.equals(CONJUNCION))) {
                        tmpIzquierda = getLados(izquierda).get(0);
                        tmpDerecho = getLados(izquierda).get(1);

                        izquierda = "(" + derecha + ")" + DISYUNCION + "(" + tmpIzquierda + ")";
                        derecha = "(" + derecha + ")" + DISYUNCION + "(" + tmpDerecho + ")";


                    } else if ((opIzquierda.equals("") || opIzquierda.equals(DISYUNCION)) && (opDerecha.equals(CONJUNCION))) {
                        tmpIzquierda = getLados(derecha).get(0);
                        tmpDerecho = getLados(derecha).get(1);
                        derecha = "(" + izquierda + ")" + DISYUNCION + "(" + tmpDerecho + ")";
                        izquierda = "(" + izquierda + ")" + DISYUNCION + "(" + tmpIzquierda + ")";

                    } else if ((opIzquierda.equals(CONJUNCION)) && (opDerecha.equals(CONJUNCION))) {
                        tmpIzquierda = getLados(derecha).get(0);
                        tmpDerecho = getLados(derecha).get(1);
                        derecha = "(" + izquierda + ")" + DISYUNCION + "(" + tmpDerecho + ")";
                        izquierda = "(" + izquierda + ")" + DISYUNCION + "(" + tmpIzquierda + ")";

                    }
                    if (getOperadorPrincipal(izquierda).equals(DISYUNCION) && izquierda.contains(CONJUNCION))
                        izquierda = fnc(izquierda);

                    if (getOperadorPrincipal(derecha).equals(DISYUNCION) && derecha.contains(CONJUNCION))
                        derecha = fnc(derecha);

                    if (iz.equals(izquierda) && der.equals(derecha)) {
                        tmp1 = entrada;
                        break;
                    } else {
                        if (iz.equals(izquierda) || der.equals(derecha))
                            tmp1 = fnc("(" + izquierda + ")" + DISYUNCION + "(" + derecha + ")");
                        else
                            tmp1 = "(" + izquierda + ")" + CONJUNCION + "(" + derecha + ")";
                    }
                } else {
                    if (derecha.contains(CONJUNCION))
                        derecha = fnc(derecha);
                    if (izquierda.contains(CONJUNCION))
                        izquierda = fnc(izquierda);

                    tmp1 = "(" + izquierda + ")" + CONJUNCION + "(" + derecha + ")";


                }

                break;
            }

        }

        System.out.println(tmp1);
        return tmp1;
    }
    
    /**
     * retorna los lados de una ecuacion
     */
    private ArrayList<String> getLados(String entrada) {

        if (entrada.charAt(0) == NEGACION.charAt(0))
            entrada = entrada.substring(2, entrada.length() - 1);
        int contador1 = 0;
        String derecha = "", izquierda = "";
        ArrayList<String> retorno = new ArrayList<>();
        for (int i = 0; i < entrada.length(); i++) {
            if (entrada.charAt(i) == ')')
                contador1--;
            else if (entrada.charAt(i) == '(')
                contador1++;
            if (contador1 == 0) {
                derecha = entrada.substring(1, i);
                izquierda = entrada.substring(i + 3, entrada.length() - 1);
                retorno.add(izquierda);
                retorno.add(derecha);
                break;
            }
        }
        return retorno;
    }
    
    /**
     * obtiene el operador principal de una ecuacion
     */
    private String getOperadorPrincipal(String entrada) {
        String t = "";
        if (!entrada.contains(DISYUNCION) && !entrada.contains(CONJUNCION))
            return "";
        int contador1 = 0;
        for (int i = 0; i < entrada.length() - 1; i++) {
            if (entrada.charAt(i) == ')')
                contador1--;
            else if (entrada.charAt(i) == '(')
                contador1++;
            if (contador1 == 0) {
                t = entrada.charAt(i + 1) + "";
                break;
            }
        }
        return t;
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
