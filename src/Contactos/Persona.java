package Contactos;


public class Persona extends CommonData {

	private String apellido;
	private long nroDocumento;
	private String tipoDocumento;

	public Persona(){

	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellido() {
		return apellido;
	}

	public void setNroDocumento(long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public long getNroDocumento() {
		return nroDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	

}