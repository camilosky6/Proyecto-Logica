package mundo;

public class Nodo {

	private Nodo padre;
	private Nodo izquierdo;
	private Nodo derecho;
	private String valor;

	public Nodo(String valor) {
		super();
		this.valor = valor;
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public Nodo getIzquierdo() {
		return izquierdo;
	}

	public void setIzquierdo(Nodo izquierdo) {
		this.izquierdo = izquierdo;
	}

	public Nodo getDerecho() {
		return derecho;
	}

	public void setDerecho(Nodo derecho) {
		this.derecho = derecho;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isHoja() {
		if (derecho == null && izquierdo == null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		if (izquierdo == null && izquierdo == null) {
			return "Nodo [valor=" + valor + " izquierdo: null" + " derecho: null" + "]";
		}
		if (izquierdo == null) {
			return "Nodo [valor=" + valor + " izquierdo: null" + " derecho: " + derecho.getValor() + "]";
		}
		if (derecho == null) {
			return "Nodo [valor=" + valor + " izquierdo: " + izquierdo.getValor() + " derecho: null" + "]";
		}
		return "Nodo [valor=" + valor + " izquierdo: " + izquierdo.getValor() + " derecho: " + derecho.getValor() + "]";
	}

}
