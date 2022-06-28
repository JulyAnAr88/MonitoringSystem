package Horarios;

public class Grupo {

	private String nombre;
	private AgenteGsi[] misAgenteGsi;
	private AgenteCerocho[] misAgenteCero;
	private CalendarioGrafic myCalendario;
	private Turno myTurno; 
	private int subGrupo;

	public Grupo(){	}
	
	/** Recibe el nombre del grupo 
	 */
	public Grupo(String nombre){
		this.nombre=nombre;

	}
	
	/** Recibe el nombre del grupo ademas de los agentes 
	 */
	public Grupo(String nombre, AgenteGsi[] agentes){
		this.nombre=nombre;
		setAgente(agentes);
	}
	
	public Grupo(String nombre, AgenteCerocho[] agentes){
		this.nombre=nombre;
		setAgente(agentes);
	}


	public void getAgentes(){

	}

	public String getNombre(){
		return nombre;
	}
	
	public void setAgente(AgenteGsi[] agente){
		for (int j = 0; j < agente.length; j++) {
			this.misAgenteGsi[j]=agente[j];
		}
	}
	
	public void setAgente(AgenteCerocho[] agente){
		for (int j = 0; j < agente.length; j++) {
			this.misAgenteCero[j]=agente[j];
		}
	}

	public void setNombre(String nombre){
		this.nombre=nombre;
	}

	public CalendarioGrafic getMyCalendario() {
		return myCalendario;
	}

	public void setMyCalendario(CalendarioGrafic myCalendario) {
		this.myCalendario = myCalendario;
	}

	public int getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(int subGrupo) {
		this.subGrupo = subGrupo;
	}

	public Turno getMyTurno() {
		return myTurno;
	}

	public void setMyTurno(Turno myTurno) {
		this.myTurno = myTurno;
	}

}