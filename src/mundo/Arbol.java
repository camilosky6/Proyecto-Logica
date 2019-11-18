package mundo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Arbol {

	private Nodo raiz;

	public Arbol() {
		super();
		this.raiz = null;
	}

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	/**
	 * M�todo que agrega un nuevo nodo al arbol.
	 * 
	 * @param nodoNuevo nodo que se va a agregar.
	 */

	public void eliminarNodo(Nodo nodo) {

	}

	/**
	 * M�todo recursivo que agrega un nodo al arbol.
	 * 
	 * @param nodoNuevo Nodo que se va a agregar.
	 * @param raiz      Nodo con el que se va a comparar el nodoNuevo para
	 *                  agregarse.
	 */
	private void insertarNodo(Nodo nodoNuevo, Nodo raiz) {

		if (raiz == null) {
			this.raiz = nodoNuevo;
		} else {
			if (raiz.getIzquierdo() == null) {
				raiz.setIzquierdo(nodoNuevo);
				raiz.getIzquierdo().setPadre(raiz);
			} else {
				if (raiz.getIzquierdo().getValor().contains(nodoNuevo.getValor())) {
					insertarNodo(nodoNuevo, raiz.getIzquierdo());
				} else {
					if (raiz.getDerecho() == null) {
						raiz.setDerecho(nodoNuevo);
						raiz.getDerecho().setPadre(raiz);
					} else {
						insertarNodo(nodoNuevo, raiz.getDerecho());
					}
				}
			}

		}

	}

	public void insertarNodo(String valor) {
		Nodo aux = new Nodo(valor);
		insertarNodo(aux, raiz);
	}

	public void preOrden(Nodo raiz) {
		if (raiz == null) {
			return;
		}
		System.out.println(raiz.getValor() + "======>");
		preOrden(raiz.getIzquierdo());
		preOrden(raiz.getDerecho());
	}

	public void posOrden(Nodo raiz) {
		if (raiz == null) {
			return;
		}
		posOrden(raiz.getIzquierdo());
		posOrden(raiz.getDerecho());
		System.out.println(raiz.getValor() + "======>");
	}

	public void inOrden(Nodo raiz) {
		if (raiz == null) {
			return;
		}
		inOrden(raiz.getIzquierdo());
		System.out.println(raiz.getValor() + "======>");
		inOrden(raiz.getDerecho());
	}

	public String imprimir() {
		String res = "";
		System.out.println("PreOrden: \n");
		preOrden(raiz);
		System.out.println("PosOrden: \n");
		posOrden(raiz);
		System.out.println("InOrden: \n");
		inOrden(raiz);
		return res;
	}

	public void imprimirArbol() {

		Queue<Nodo> colaAuxiliar = new LinkedList<>();
		colaAuxiliar.add(raiz);
		while (colaAuxiliar.size() != 0) {
			Nodo nodo = colaAuxiliar.poll();
			if (nodo != null) {
				System.out.println(nodo.toString() + " =====>");
				colaAuxiliar.add(nodo.getIzquierdo());
				colaAuxiliar.add(nodo.getDerecho());

			}
		}
	}

	/**
	 * M�todo que busca un elemnto dentro del arbol.
	 * 
	 * @param nodo Nodo que se va a buscar.
	 * @return true si halla un Nodo con el mismo valor || false de lo contrario.
	 */
	public boolean buscar(Nodo nodo) {

		Queue<Nodo> colaAuxiliar = new LinkedList<>();
		colaAuxiliar.add(raiz);
		while (colaAuxiliar.size() != 0) {

			Nodo nodoActual = colaAuxiliar.poll();
			if (nodoActual != null) {
				System.out.print(nodoActual.toString() + " =====>");
				if (nodo.getValor() == nodoActual.getValor()) {
					return true;
				}
				colaAuxiliar.add(nodoActual.getIzquierdo());
				colaAuxiliar.add(nodoActual.getDerecho());
			}
		}
		return false;
	}

	public ArrayList<String> getHojas() {
		return getHojas(raiz, new ArrayList<String>());
	}

	public ArrayList<String> getHojas(Nodo raiz, ArrayList<String> hojas) {
		System.out.println(raiz.getValor());
		if (raiz.isHoja()) {
			hojas.add(raiz.getValor());
			return hojas;
		} else {
			if (raiz.getIzquierdo() != null && raiz.getDerecho() != null) {
				hojas = getHojas(raiz.getIzquierdo(), hojas);
				hojas = getHojas(raiz.getDerecho(), hojas);
			} else {
				if (raiz.getIzquierdo() != null) {
					return getHojas(raiz.getIzquierdo(), hojas);
				} else {
					return getHojas(raiz.getDerecho(), hojas);
				}
			}
			return hojas;
		}
	}

	public boolean isEquilibrado() {

		return true;
	}

	public Arbol copyOf() {
		Arbol copia = new Arbol();
		copia.insertarNodo(raiz, null);
		return copia;
	}
}
