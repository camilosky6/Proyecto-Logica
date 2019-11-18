package interfaz;

import java.io.File;

import excepciones.ParentesisVacioException;
import mundo.Validaciones;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = " (a) + (a)";
		Validaciones val = new Validaciones();
		try {
			System.out.println(val.verificarParentesisVacios(a));
		} catch (ParentesisVacioException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	private static String[] getOperadores() {
		String[] aux = { "¬", "∨", "∧", "→", "↔" };
		return aux;
	}

}
