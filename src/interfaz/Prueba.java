package interfaz;

import java.io.File;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] a = getOperadores();
		System.out.println(a[0].charAt(0));
		try {
			File file = new File("");
			String helper = file.getAbsolutePath() + "\\src\\";
			String currentDir = helper + "NOMBRE PDF.png";
			Runtime.getRuntime().exec(" rundll32 url.dll, FileProtocolHandler " + currentDir);
			System.out.println(currentDir);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error :c");
		}
	}

	private static String[] getOperadores() {
		String[] aux = { "¬", "∨", "∧", "→", "↔" };
		return aux;
	}

}
