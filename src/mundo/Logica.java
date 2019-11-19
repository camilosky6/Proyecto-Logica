package mundo;

import java.util.ArrayList;

public class Logica {

	public static final String NEGACION = "¬";
	public static final String CONJUNCION = "∧";
	public static final String DISYUNCION = "∨";
	public static final String CONDICIONAL = "→";
	public static final String EQUIVALENCIA = "↔";
	public static void main(String[] args) {
		//( ( ( (r) ∨ (¬(s)) ) ∧ ( ( (r) ∨ (¬(s)) ) ∨ (s) ) ) ∧ ( (¬(r)) ∨ (¬(s)) ) ) ∧ ( ( (¬(r)) ∨ (¬(s)) ) ∨ (s) )
		//¬((p)∧((q)→(r)))
		//((¬(p))∨(q))∧((¬(r))∨(s))∧((p)∨(r))∧(¬(q))∧(¬(s))
		String prueba = "((¬(p))∨(q))∧((¬(r))∨(s))∧(¬(s))∧((p)∨(r))∧(¬(q))";
		System.out.println(prueba);
		prueba = realizarFNC(prueba);
		System.out.println(prueba);
		
		ArrayList<Clausula> clausulas = obtenerClausulas(prueba);
		ArrayList<Clausula> c2 = obtenerClausulas(prueba);
		System.out.println(clausulas);
		resolucion(clausulas);
		System.out.println(esSatisfacible(clausulas));
		
		System.out.println(c2);
	}
	/*********************************************************************************
	                                    FNC
	 *********************************************************************************/
	/**
     * dada una formula la lleva a FNC
     */
    private static String fnc(String entrada) {
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

       // System.out.println(tmp1);
        return tmp1;
    }
    
    /**
     * remueve los conectores si y solo si y el entonces
     */
    public static String realizarFNC(String entrada) {
        try {
            String izquierdo = "", derecho = "", completo = "";
            for (int i = 0; i < entrada.length(); i++) {
                if (entrada.charAt(i) == EQUIVALENCIA.charAt(0)) {
                    int contador1 = 0;
                    int contador2 = 0;
                    for (int j = i - 1; j >= 0; j--) {
                        if (entrada.charAt(j) == ')')
                            contador1++;
                        else if (entrada.charAt(j) == '(')
                            contador1--;
                        if (contador1 == 0) {
                            izquierdo = entrada.substring(j, i);
                            contador1 = j;
                            break;
                        }
                    }
                    for (int j = i + 1; j < entrada.length(); j++) {
                        if (entrada.charAt(j) == '(')
                            contador2++;
                        else if (entrada.charAt(j) == ')')
                            contador2--;
                        if (contador2 == 0) {
                            derecho = entrada.substring(i + 1, j + 1);
                            contador2 = j;
                            break;

                        }
                    }

                    completo = "(" + izquierdo + DISYUNCION.charAt(0) + "(" + NEGACION + derecho + ")" + ")" +
                            CONJUNCION + "(" + "(" + NEGACION + izquierdo + ")" +
                            DISYUNCION + derecho + ")";
                    entrada = entrada.substring(0, contador1) + completo + entrada.substring(contador2 + 1, entrada.length());
                } else if (entrada.charAt(i) == CONDICIONAL.charAt(0)) {

                    int contador1 = 0, contador2 = 0;

                    for (int j = i - 1; j >= 0; j--) {
                        if (entrada.charAt(j) == ')')
                            contador1++;
                        else if (entrada.charAt(j) == '(')
                            contador1--;
                        if (contador1 == 0) {
                            izquierdo = entrada.substring(j, i);
                            contador1 = j;
                            break;
                        }
                    }
                    for (int j = i + 1; j < entrada.length(); j++) {
                        if (entrada.charAt(j) == '(')
                            contador2++;
                        else if (entrada.charAt(j) == ')')
                            contador2--;
                        if (contador2 == 0) {
                            derecho = entrada.substring(i + 1, j + 1);
                            contador2 = j;
                            break;

                        }
                    }


                    completo = "(" + NEGACION + izquierdo + ")" + DISYUNCION + derecho;
                    entrada = entrada.substring(0, contador1) + completo + entrada.substring(contador2 + 1, entrada.length());


                }
            }
            //salida
            System.out.println(fnc(axiomas7y4(entrada)));
            return fnc(axiomas7y4(entrada));
        }catch (Exception g){
            return null;
        }
    }
    
    /**
     * retorna los lados de una ecuacion
     */
    private static ArrayList<String> getLados(String entrada) {

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
    private static String getOperadorPrincipal(String entrada) {
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
    /**
     * aplica los axiomas 7 y 4 hasta que la formula quede libre de dobles neggaciones
     */
    private static String axiomas7y4(String entrada) {
        String izquierdo = "", derecho = "", completo = "", tmp = "";

        while (comprobar(entrada)) {
            for (int i = 0; i < entrada.length() - 2; i++) {
                if (entrada.charAt(i + 2) != 'p' && entrada.charAt(i + 2) != 'q' && entrada.charAt(i + 2) != 'r' &&
                        entrada.charAt(i + 2) != 's' && entrada.charAt(i + 2) != 't' &&
                        entrada.charAt(i + 2) != NEGACION.charAt(0) &&
                        entrada.charAt(i + 2) != 'v' && entrada.charAt(i) == NEGACION.charAt(0)) {
                    int contador2 = 0;

                    tmp = entrarNegacion(entrada, i);
                    int c = 0;
                    for (int j = 0; j < tmp.length(); j++) {
                        if (tmp.charAt(j) == '(')
                            contador2++;
                        else if (tmp.charAt(j) == ')')
                            contador2--;
                        if (contador2 == 0) {
                            izquierdo = tmp.substring(0, j + 1);
                            contador2 = j;
                            c = j;
                            break;

                        }
                    }

                    derecho = tmp.substring(c + 2, tmp.length());


                    if (tmp.charAt(c + 1) == CONJUNCION.charAt(0)) {
                        completo = "(" + NEGACION + izquierdo + ")" + DISYUNCION.charAt(0) +
                                "(" + NEGACION + derecho + ")";
                    } else
                        completo = "(" + NEGACION + izquierdo + ")" + CONJUNCION.charAt(0) +
                                "(" + NEGACION + derecho + ")";


                    entrada = entrada.substring(0, i) + completo + entrada.substring(i + tmp.length() + 3, entrada.length());
                } else if (entrada.charAt(i + 2) == NEGACION.charAt(0) &&
                        entrada.charAt(i) == NEGACION.charAt(0)) {
                    tmp = entrarNegacion(entrada, i);
                    completo = tmp.substring(1, tmp.length());
                    entrada = entrada.substring(0, i) + completo + entrada.substring(i + tmp.length() + 3, entrada.length());
                }
            }
        }

        return entrada;
    }
    
    /**
     * retorna la formula que se encuentra dentro de una negacion
     */
    private static String entrarNegacion(String entrada, int i) {
        int contador2 = 0;
        for (int j = i + 1; j < entrada.length(); j++) {
            if (entrada.charAt(j) == '(')
                contador2++;
            else if (entrada.charAt(j) == ')')
                contador2--;
            if (contador2 == 0) {
                entrada = entrada.substring(i + 2, j);
                break;
            }
        }
        return entrada;
    }
    /**
     * comprueba que no hallan formas atomicas para poder poner una nueva formula
     */
    private static boolean comprobar(String entrada) {
        boolean retorno = false;
        for (int i = 0; i < entrada.length() - 2; i++) {
            if (!esAtomo(entrada.charAt(i + 2))  && entrada.charAt(i) == NEGACION.charAt(0)) {
                retorno = true;
            }
        }
        return retorno;
    }
	
	/********************************************************************************
	                                  Resolucion
	 ******************************************************************************/
    public static void resolucion(ArrayList<Clausula> clausulas) {
		 for(int j =0; j<clausulas.size(); j++){
			for (int i = 0; i < clausulas.size(); i++) {
				if (j< clausulas.size() && !(clausulas.get(j).estaUsada())) {
					if (i!=j && tienenParComplementario(clausulas.get(j),clausulas.get(i))) {
						Clausula aux = comprobarRes(clausulas.get(j),clausulas.get(i),clausulas.size());
						clausulas.get(j).setEstado(true);
						clausulas.get(i).setEstado(true);
						clausulas.add(aux);
					}
				}
			}
		}
	}
	
    //Metodo que genera la Clausula resultado de 2 formulas
	private static Clausula comprobarRes(Clausula token, Clausula token2,int pos) {
		String p = obtenerParComplementario(token, token2);
		ArrayList<Atomo> aux = new ArrayList<>();
		Clausula salida = new Clausula(false, aux, "", pos);
		token.darAtomos(salida,p);
		token2.darAtomos(salida,p);
		System.out.println(salida);
		//TODO: modificar posicion despues, ver si es util o no
		salida.esResolucionDe(p, token.getPosicion()+1, token2.getPosicion()+1);
		System.out.println(salida);
		return salida;
	}

	//Metodo que verifica si 2 clausulas tienen par complementario
	private static boolean tienenParComplementario(Clausula token, Clausula token2) {
		if (!token.estaUsada() && !token2.estaUsada()) {
			for (int i = 0; i < token.getLiterales().size(); i++) {
				if (token2.esParComplementarioDe(token.getLiterales().get(i))) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Metodo que devuelve el atomo complementario de dos formulas
	private static String obtenerParComplementario(Clausula token, Clausula token2) {
		for (int i = 0; i < token.getLiterales().size(); i++) {
			if (token2.esParComplementarioDe(token.getLiterales().get(i))) {
				return token.getLiterales().get(i).getLiteral();
			}
		}
		return null;
	}

	/**
	 * Sacar una lista de clausulas atravez de una formula	
	 * @param String formula
	 * @return un ArrayList de clausulas
	 */
	public static ArrayList<Clausula> obtenerClausulas(String formula) {
		ArrayList<Clausula> aux = new ArrayList<>();
		String[] arreglo;
		arreglo = formula.trim().split(CONJUNCION);
		if (arreglo != null) {
			for (int i = 0; i < arreglo.length; i++) {
				ArrayList<Atomo> literales = new ArrayList<>();
				Clausula clausula = new Clausula(false, literales, Clausula.PREMISA, i);
				for (int j = 0; j < arreglo[i].length(); j++) {
					char letra = arreglo[i].charAt(j);
					if (esAtomo(letra)) {
						Atomo p;
						if (j-2 >= 0 && arreglo[i].charAt(j-2) == NEGACION.charAt(0)) {
							p = new Atomo(true, letra+"");
						} else {
							p = new Atomo(false, letra+"");
						}
						literales.add(p);
					}
				}
				aux.add(clausula);
			}
		}else {
			ArrayList<Atomo> literales = new ArrayList<>();
			Clausula clausula = new Clausula(false, literales, Clausula.PREMISA, 0);
			for (int j = 0; j < formula.length(); j++) {
				char letra = formula.charAt(j);
				if (esAtomo(letra)) {
					Atomo p;
					if (j-2 >= 0 && formula.charAt(j-2) == NEGACION.charAt(0)) {
						p = new Atomo(true, letra+"");
					} else {
						p = new Atomo(false, letra+"");
					}
					literales.add(p);
				}
			}
			aux.add(clausula);
			
		}
		
		return aux;
	}
	
	/**
	 * Sacar una lista de clausulas atravez de una formula	
	 * @param String formula
	 * @return un ArrayList de clausulas
	 */
	public static void ingresarClausulas(ArrayList<Clausula> list,ArrayList<Clausula> ingresa) {
		for (Clausula clausula : ingresa) {
			list.add(clausula);
		}
	}
	
	public static boolean esSatisfacible(ArrayList<Clausula> list){
		if(list.get(list.size()-1).estaVacia()){
		return false;
		}
		return true;
	}
	
	//Metodo para verificar si un caracter es un atomo
	private static boolean esAtomo(char p) {
		if (p>96 && p<123) {
			return true;
		}
		return false;
	}

}
