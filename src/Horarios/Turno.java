package Horarios;

public class Turno {
	private String []horas={"00 a 06", "06 a 12", "12 a 18","18 a 24" };
	private int myTurno;
	
	public Turno(){}
	
	public Turno(int turno){
		myTurno=turno;
	}
	
	public String getHoras(){
		
		return horas[myTurno-1];
	}
	
	public int getTurno(){
		return myTurno;
	}
	
	public String toString(){
		return getHoras()+" hs";
	}
	
	public static String Primero(){
		return "00 a 06";
	}
	
	public static String Segundo(){
		return "06 a 12";
	}
	
	public static String Tercero(){
		return "12 a 18";
	}
	
	public static String Cuartoo(){
		return "18 a 24";
	}
	

}
