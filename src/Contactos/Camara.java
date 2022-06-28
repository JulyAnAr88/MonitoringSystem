package Contactos;


public class Camara {

	private String calleEsteOeste;
	private String calleNorteSur;
	private boolean fija;
	private int limiteEste;
	private int limiteNorte;
	private int limiteOeste;
	private int limiteSur;
	private String nombre;
	public Negocio[] misNegocios;

	public Camara(){

	}

	public String getCalleEsteOeste() {
		return calleEsteOeste;
	}

	public void setCalleEsteOeste(String calleEsteOeste) {
		this.calleEsteOeste = calleEsteOeste;
	}

	public String getCalleNorteSur() {
		return calleNorteSur;
	}

	public void setCalleNorteSur(String calleNorteSur) {
		this.calleNorteSur = calleNorteSur;
	}

	public boolean isFija() {
		return fija;
	}

	public void setFija(boolean fija) {
		this.fija = fija;
	}

	public int getLimiteEste() {
		return limiteEste;
	}

	public void setLimiteEste(int limiteEste) {
		this.limiteEste = limiteEste;
	}

	public int getLimiteNorte() {
		return limiteNorte;
	}

	public void setLimiteNorte(int limiteNorte) {
		this.limiteNorte = limiteNorte;
	}

	public int getLimiteOeste() {
		return limiteOeste;
	}

	public void setLimiteOeste(int limiteOeste) {
		this.limiteOeste = limiteOeste;
	}

	public int getLimiteSur() {
		return limiteSur;
	}

	public void setLimiteSur(int limiteSur) {
		this.limiteSur = limiteSur;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Negocio[] getMisNegocios() {
		return misNegocios;
	}

	public void setMisNegocios(Negocio[] misNegocios) {
		this.misNegocios = misNegocios;
	}

	

}