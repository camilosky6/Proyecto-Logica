package mundo;

import java.util.ArrayList;


public class Clausula {

	public static final String PREMISA = "... h";
	private boolean usado = false;
	private ArrayList<Atomo> literales;
	private String comentario;
	private int posicion;
	
	public Clausula(boolean usado, ArrayList<Atomo> literales, String comentario, int posicion) {
		this.usado = usado;
		this.literales = literales;
		this.comentario = comentario;
		this.posicion = posicion;
	}
	
	
	public boolean esParComplementarioDe(Atomo p) {
		for (int i = 0; i < literales.size(); i++) {
			if (literales.get(i).esComplementario(p)) {
				return true;
			}
		}
		return false;
	}
	
	public void darAtomos(Clausula list) {
		for (int i = 0; i < literales.size(); i++) {
			if (!list.contiene(literales.get(i))) {
				list.getLiterales().add(literales.get(i));
			}
		}
	}
	
	private boolean contiene(Atomo atomo) {
		for (int i = 0; i < literales.size(); i++) {
			if (atomo.esIgual(literales.get(i))) {
				return true;
			}
		}
		return false;
	}


	public void esResolucionDe(String atomo,int pos,int pos2) {
		String salida = "Res "+atomo+"("+"C"+pos+",C"+pos2+")";
		setComentario(salida);
	}
	
	public void setEstado(boolean aux) {
		usado = aux;
	}
	
	public boolean estaUsada() {
		return usado;
	}
	
	public ArrayList<Atomo> getLiterales(){
		return literales;
	}
	
	public void setAtomo(Atomo p) {
		literales.add(p);
	}
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public int getPosicion() {
		return posicion;
	}
	
	public void darAtomos(Clausula list,String p) {
		for (int i = 0; i < literales.size(); i++) {
			if (!list.contiene(literales.get(i)) && !literales.get(i).getLiteral().equals(p)) {
				list.getLiterales().add(literales.get(i));
			}
		}
	}
	/**
	public void eliminarAtomosComplementariosDe(String p) {
		Atomo token = new Atomo(true, p);
		Atomo token2 = new Atomo(false, p);
		for (Atomo atomo : literales) {
			if (atomo.esComplementario(token)) {
				literales.remove(atomo);
			}
		}
		for (Atomo atomo : literales) {
			if (atomo.esComplementario(token2)) {
				literales.remove(atomo);
			}
		}
	}
	**/
	
	
	private Atomo obtenerAtomo(boolean negacion, String p) {
		for (Atomo atomo : literales) {
			System.out.println(atomo);
			if (atomo.getLiteral() == p && atomo.getNegacion() == negacion) {
				return atomo;
			}
		}
		return null;
	}
	
	public boolean estaVacia() {
		if (literales.isEmpty()) {
			return true;
		}
		return false;
	}
	public void setPosicion(int n) {
		posicion = n;
	}


	public String toString() {
		String salida = "";
		for (Atomo atomo : literales) {
			salida+= atomo;
		}
		return salida;
	}

}
