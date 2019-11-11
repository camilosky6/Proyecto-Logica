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
	
	public void esResolucionDe(String atomo,int pos,int pos2) {
		String salida = "Res "+atomo+"("+"C"+pos+",C"+pos2+")";
		setComentario(salida);
	}
	
	public boolean estaUsada() {
		return usado;
	}
	
	public void setAtomo(Atomo p) {
		literales.add(p);
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
