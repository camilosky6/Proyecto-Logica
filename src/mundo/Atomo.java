package mundo;

public class Atomo {

	boolean negacion;
	String literal;
	
	public Atomo(boolean negacion,String literal) {
		this.negacion = negacion;
		this.literal = literal;
	}

	public boolean esComplementario(Atomo p) {
		if (literal.equals(p.getLiteral()) && (negacion!= p.getNegacion())) {
			return true;
		}
		return false;
	}
	
	public String getLiteral() {
		return literal;
	}
	
	public boolean getNegacion() {
		return negacion;
	}
	
	public String toString() {
		if (negacion) {
			return "¬"+literal;
		}else {
			return literal;
		}
	}

	public boolean esIgual(Atomo atomo) {
		if (atomo.getNegacion() == negacion && atomo.getLiteral()==(literal)) {
			return true;
		}
		return false;
	}

}
