package Horarios;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Organiza el día laboral asignando agentes a trabajar en los distintos turnos,
 * permitiendo realizar respectivas consultas
 * 
 */

public class DiaLab08 extends JFrame {
	
	private static final long serialVersionUID = -3536504457190499330L;
	private int nroDiaLaboral;
	private Grupo group;
	private long horaInicio;
	private long horaFin;
	private Turno turno;
	private String[][] dia={
			{"c"},//dia 7
			{"a"},//dia 8
			{"a"},//dia 9
			{"b"},//dia 4
			{"b"},//dia 5
			{"c"},//dia 6
			
			
			};
	
	private JLabel labelResp=new JLabel();
	SimpleDateFormat formateador = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", new Locale("ES"));
	
	
	public DiaLab08(){
		
	}
	
	public DiaLab08(Calendar dia){
		//fecha=dia; 
	}
	
	/**Cada grupo trabajará 2 días.
	 *<p>Por ende habrá 6 dias posibles, los cuales se calcularan comparando el modulo 6
	 *del dia con una lista prediseñada
	 */
	public void programarDia(/*Grupo grupo, Turno turno, Calendar dia*/){
		//group=grupo;
		//group.setMyCalendario(dia);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(dia[j][i]+" ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Responde si el grupo consultado se encuentra en algún turno 
	 * @param dia
	 * @param grupo
	 */
	public JLabel calcularDia(Date dia, Grupo grupo){
		long aux;
		aux=dia.getTime()/(3600*24*1000);
		long tur=aux%12;
		int turnoI=(int)tur;
		boolean esta=false;
		int t=0;
		for (int i = 0; i < 2; i++) {
			if (this.dia[turnoI][i].indexOf(grupo.getNombre())!=-1){
				esta=true;
				t=i;
				break;
			}
							
		}
		String fechaFormat = formateador.format(dia.getTime());
		
		if (esta){
			
			this.turno=new Turno(t+1);
			labelResp.setText("<html><center>El día "+fechaFormat+" el grupo <b>"+grupo.getNombre().toUpperCase()+"</b><br>\t se encuentra en el turno "
					+(t+1)+"; de "+this.turno+"</center></html>");
		}else{
			labelResp.setText("<html><center>El día "+fechaFormat+" el grupo <b>"+grupo.getNombre().toUpperCase()+"</b><br> no se encuentra en ningún turno</center></html>");
			
		}
		return labelResp;
	}
	
	
	
	/**
	 * Responde si determinado agente trabajó o trabajará tal dia ejecutando 
	 * la consulta de la siguiente manera:
	 * a cada combinacion de turno se le asigna un nro de 0 a 5,
	 * sea<p>0= e, f, a, b;</p> 
	 * 	   1= d, e, f, a; </p>
	 * 	   2= c, d, e, f; </p>
	 * 	   3= b, c, d, e; </p>
	 *     4= a, b, c, d y </p>
	 *     5= f, a, b, c;</p>
	 * este número se compara con el módulo del día consultado y 6 
	 * @param dia
	 * @param agente
	 */
	public JLabel calcularDia(Date dia, AgenteGsi agente){
		long aux;
		aux=dia.getTime()/(3600*24*1000);
		long tur=aux%12;
		int turnoI=(int)tur;
		boolean esta=false;
		int t=0;
		for (int i = 0; i < 2; i++) {
			if (this.dia[turnoI][i].indexOf(agente.getNombreGrupo())!=-1){
				esta=true;
				t=i;
				break;
			}
							
		}
		String fechaFormat = formateador.format(dia.getTime());
		
		if (esta){
			
			this.turno=new Turno(t+1);
			labelResp.setText("<html><center>El día "+fechaFormat+" <br><i> "+agente.getNombreyApellido()+"</i> se encuentra en el turno "
					+(t+1)+"; de "+this.turno+"</center></html>");
			
		}else{
			labelResp.setText("<html><center>El día "+fechaFormat+"<br> <i>"+agente.getNombreyApellido()+"</i> no se encuentra en ningún turno</center></html>");
			
		}
		return labelResp;
	}
	
	/** Responde si el grupo consultado se encuentra en algún turno 
	 * @param dia
	 * @param grupo
	 */
	public Turno calcularTurno(Date dia, Grupo grupo){
		long aux;
		aux=dia.getTime()/(3600*24*1000);
		long tur=aux%6;
		int turnoI=(int)tur;
		boolean esta=false;
		int t=0;
		for (int i = 0; i < 2; i++) {
			if (this.dia[turnoI][i].indexOf(grupo.getNombre())!=-1){
				esta=true;
				t=i;
				break;
			}
							
		}
		String fechaFormat = formateador.format(dia.getTime());
		
		if (esta){
			
			this.turno=new Turno(t+1);
			labelResp.setText("<html><center>El día "+fechaFormat+" el grupo <b>"+grupo.getNombre().toUpperCase()+"</b><br>\t se encuentra en el turno "
					+(t+1)+"; de "+this.turno+"</center></html>");
		}else{
			labelResp.setText("<html><center>El día "+fechaFormat+" el grupo <b>"+grupo.getNombre().toUpperCase()+"</b><br> no se encuentra en ningún turno</center></html>");
			
		}
		return turno;
	}
	
	
	
	/**
	 * Responde si determinado agente trabajó o trabajará tal dia ejecutando 
	 * la consulta de la siguiente manera:
	 * a cada combinacion de turno se le asigna un nro de 0 a 5,
	 * sea<p>0= e, f, a, b;</p> 
	 * 	   1= d, e, f, a; </p>
	 * 	   2= c, d, e, f; </p>
	 * 	   3= b, c, d, e; </p>
	 *     4= a, b, c, d y </p>
	 *     5= f, a, b, c;</p>
	 * este número se compara con el módulo del día consultado y 6 
	 * @param dia
	 * @param agente
	 */
	public Turno calcularTurno(Date dia, AgenteGsi agente){
		long aux;
		aux=dia.getTime()/(3600*24*1000);
		long tur=aux%12;
		int turnoI=(int)tur;
		boolean esta=false;
		int t=0;
		for (int i = 0; i < 4; i++) {
			if (this.dia[turnoI][i].indexOf(agente.getNombreGrupo())!=-1){
				esta=true;
				t=i;
				break;
			}
							
		}
		String fechaFormat = formateador.format(dia.getTime());
		
		if (esta){
			
			this.turno=new Turno(t+1);
			labelResp.setText("<html><center>El día "+fechaFormat+" <br><i> "+agente.getNombreyApellido()+"</i> se encuentra en el turno "
					+(t+1)+"; de "+this.turno+"</center></html>");
			
		}else{
			labelResp.setText("<html><center>El día "+fechaFormat+"<br> <i>"+agente.getNombreyApellido()+"</i> no se encuentra en ningún turno</center></html>");
			
		}
		return turno;
	}
	
	/**
	 * Responde qué grupo trabaja tal dia en tal turno
	 * @param dia
	 * @param turno
	 * @return String: nombre del grupo
	 */
	public String calcularGrupo(Date dia){
		String grupo;
		long aux;
		aux=dia.getTime()/(3600*24*1000);
				
		long turn=aux%6;
		int turnoI=(int)turn;
		grupo=this.dia[turnoI][0];
		System.out.println(grupo);
		return grupo.toUpperCase();
	}
	
	public String calcularSubGrupo(Date dia, int turno){
		
		AgenteCerochoDAO ag=new AgenteCerochoDAO();
		
		return ag.dia(dia, turno);


	}
	
	

	public void setHoraInicio(long horaInicio) {
		this.horaInicio = horaInicio;
	}
	public long getHoraInicio() {
		return horaInicio;
	}

	public void setGroup(Grupo group) {
		this.group = group;
	}
	public Grupo getGroup() {
		return group;
	}

	public void setHoraFin(long horaFin) {
		this.horaFin = horaFin;
	}
	public long getHoraFin() {
		return horaFin;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Turno getTurno() {
		return turno;
	}
	
	/*public void displayDateLabel(int forma){
		JLabel display[];
		JPanel panel=new JPanel(new GridLayout(0, 2));
		
		display=new JLabel[8];
		
		switch (forma){
		case (1):
			display[0]= new JLabel("00 a 06");
		
			display[1]= new JLabel("A");
			
			display[2]= new JLabel("06 a 12");
			
			display[3]= new JLabel("B");
			
			display[4]= new JLabel("12 a 18");
			
			display[5]= new JLabel("C");
			
			display[6]= new JLabel("18 a 24");
			
			display[7]= new JLabel("D");
				
			
				
			for (int j = 0; j < display.length; j++) {
				display[j].setHorizontalAlignment(JLabel.CENTER);
				display[j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(display[j]);
			}
			
			break;
						
		}
		getContentPane().add(panel, BorderLayout.CENTER);
        
        this.setSize(150, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
		
	}*/

	public void setNroDiaLaboral(int nroDiaLaboral) {
		this.nroDiaLaboral = nroDiaLaboral;
	}
	public int getNroDiaLaboral() {
		return nroDiaLaboral;
	}
	
	public void crearTabla(){
		Conectarse con=new Conectarse("monitoreo");
		String accion="CREATE TABLE if not exists`turnos` (`id_turno` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
				"`horas` VARCHAR(10) NOT NULL,`dia_0` CHAR NOT NULL,`dia_1` CHAR NOT NULL,`dia_2` CHAR NOT NULL," +
				"`dia_3` CHAR NOT NULL,`dia_4` CHAR NOT NULL,`dia_5` CHAR NOT NULL,PRIMARY KEY (`id_turno`))ENGINE = InnoDB;";
		
		try{
			Statement st=con.getCon().createStatement();
			st.execute(accion);
			System.out.println("Tabla turnos creada con éxito!!");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		con.cerrar();
	}
	public void cargarTabla(){
		String accion= "insert into turnos values (null, '00 a 06','e', 'd', 'c', 'b','a','f');";
		String accion1= "insert into turnos values (null, '06 a 12','f', 'e', 'd', 'c','b','a');";
		String accion2= "insert into turnos values (null, '12 a 18','a', 'f', 'e', 'd','c','b');";
		String accion3= "insert into turnos values (null, '18 a 24','b', 'a', 'f', 'e','d','c');";
		
		Conectarse con= new Conectarse("monitoreo");
		try {
			Statement st=con.getCon().createStatement();
			st.execute(accion);
			st.execute(accion1);
			st.execute(accion2);
			st.execute(accion3);
			con.cerrar();
		}catch(SQLException e){
			e.printStackTrace();	
		}
		
	}
	

}
