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
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public int getPosicion() {
		return posicion;
	}
	

	public void eliminarAtomosComplementariosDe(String p) {
		
	}
	
	private Atomo obtenerAtomo(boolean negacion, String p) {
		for (int i = 0; i < literales.size(); i++) {
			if (literales.get(i).getLiteral() == p && literales.get(i).getNegacion() == negacion) {
				return literales.get(i);
			}
		}
		return null;
	}


	public String toString() {
		return literales + comentario;
	}

}
