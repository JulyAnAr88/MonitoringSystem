package Horarios;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Encrip {
	
	/*public Encrip(){
		super();
	}*/
	
	public static String encriptar(String texto) throws IllegalStateException {
		MessageDigest md = null;

		try {
		md = MessageDigest.getInstance("SHA"); // Instancia de generador SHA-1
		}
		catch(NoSuchAlgorithmException e) {
		throw new IllegalStateException(e.getMessage());
		}

		try {
		md.update(texto.getBytes("UTF-8")); // Generaci�n de resumen de mensaje
		}
		catch(UnsupportedEncodingException e) {
		throw new IllegalStateException(e.getMessage());
		}

		byte raw[] = md.digest(); // Obtenci�n del resumen de mensaje
		String hash = (new BASE64Encoder()).encode(raw); // Traducci�n a BASE64

		return hash;
		}

}
