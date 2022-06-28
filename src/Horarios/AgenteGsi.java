package Horarios;

import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
* 
*/
public class AgenteGsi {

	//private static final long serialVersionUID = 6201462527221215844L;
	private String nombreYapellido;
	private GregorianCalendar fechaNac=new GregorianCalendar();
	private long legajo;
	private String direccion="";
	private String tipoDni="";
	private long dni;
	private String telefonos;
	private String email="";
	private Grupo myGrupo;
		
	private AgenteGsiDAO agenteD=new AgenteGsiDAO("root");
	private AgenteGsiGUI agenteG=new AgenteGsiGUI();
	

	public AgenteGsi(){

	}
	
	/**
	 * Constructor para settear dichos 4 datos
	 * @param nombreYapellido
	 * @param direccion
	 * @param telefonos
	 * @param nombreGrupo
	 */
	public AgenteGsi(String nombreYapellido, int diaN,int mesN,int anoM,String direccion, String telefonos,String email, String nombreGrupo) {
		super();
		this.nombreYapellido = nombreYapellido;
		this.fechaNac.set(anoM, mesN-1, diaN);
		this.direccion = direccion;
		this.telefonos = telefonos;
		this.email=email;
		setNombreGrupo(nombreGrupo);
	}

	/**   
	 * Panel de consulta de dia de trabajo: permite elegir mediante JRadioButton la 
	 * opci�n Nombre y apellido o Grupo para desplegar combos respectivos y realizar 
	 * la consulta del d�a seleccionado
	 */
	public JPanel trabajoQuest(JPanel panel, JTabbedPane pestania, JTable tabla, JComboBox mes, JComboBox ano){
		return agenteG.trabajoQuest(panel, pestania, tabla,mes, ano);
		
	}
	
	/**
	 * Dialogo para agregar agentes
	 * @param scrollTabla 
	 * @param modeloTablaAgente 
	 * @param tablaAgentes 
	 * @param tabla 
	 * @param modelo 
	 * @param grupoLayout 
	 * @param panelA 
	 * @param scrollP 
	 * 
	 */
	public void agregarDialog( JTable tabla, DefaultTableModel modelo, JScrollPane scrollP ){
		agenteG.agregarDialog(tabla, modelo, scrollP);
	}
	
	public void dialogoModificar(JTable tabla, DefaultTableModel modelo, JScrollPane scrollP)
	  {
	    this.agenteG.dialogoModificar(tabla, modelo, scrollP);
	  }
	
	
	public JPanel ficha(JPanel panelFicha, JTabbedPane pestania){
		return agenteG.ficha(this,panelFicha,pestania);

	}
	
	public JPanel fichaVacia(JPanel panelFicha){
		return agenteG.fichaVacia( panelFicha);

	}
	
	public String getNombreyApellido(){
		return nombreYapellido;
	}
	
	public long getLegajo(){
		return legajo;
	}
	
	public String getDireccion(){
		return direccion;
	}

	public long getDni(){
		return dni;
	}

	public String getTelefono(){
		return telefonos;
	}

	public void setNombreYapellido(String apellido){
		this.nombreYapellido=apellido;

	}

	public void setFechaNac(int dia,int mes,int ano) {
		
		this.fechaNac.set(ano, mes, dia);
	}

	/**
	 * Get con SimpleDateFormat
	 * @return String de la forma "d 'de' MMMM 'de' yyyy"
	 */
	public String getFechaNacFormat() {
		SimpleDateFormat sdf=new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
		String fecha=sdf.format(fechaNac.getTime());
			
		return fecha;
	}
	
	/**
	 * Get con SimpleDateFormat
	 * @return String de la forma "dd/MM/yyyy"
	 */
	public String getFechaNac() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String fecha=sdf.format(fechaNac.getTime());
			
		return fecha;
	}
		
	public java.util.Date getDateNac() {
		return fechaNac.getTime();
		
	}
	
	public void setFechaNac(Date fecha) {
		
		fechaNac.setTimeInMillis(fecha.getTime());
	}

	public void setLegajo(long legajo){
		this.legajo=legajo;
	}

	public void setDireccion(String direccion){
		this.direccion=direccion;
	}

	public void setDni(long dni){
		this.dni=dni;
	}

	public void setGrupo(Grupo grupo){
		this.myGrupo = grupo;
	}

	public void setTelefonos(String fijo){
		this.telefonos=fijo;
	}

	public Grupo getMyGrupo() {
		return myGrupo;
	}

	public void setTipoDni(String tipoDni) {
		this.tipoDni = tipoDni;
	}

	public String getTipoDni() {
		return tipoDni;
	}
	
	public String getNombreGrupo(){
		return myGrupo.getNombre();
	}
	
	public void setNombreGrupo(String nombreGrupo){
		myGrupo=new Grupo(nombreGrupo);

	}
	
	public ResultSet getDatosAgentes(){
		return AgenteGsiDAO.datosAgentes();
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void leerAgente(String nombre){
		nombreYapellido=nombre;
		AgenteGsiDAO.leerAgente(this, nombreYapellido);
	}
	
	public void modificarAgente(String columna,String newValor){
		AgenteGsiDAO.modificarAgente(this, columna, newValor);
	}
	
	public void modificarAgente(String columna,long newValor){
		AgenteGsiDAO.modificarAgente(this, columna, newValor);
	}
	
	public void modificarAgente(String columna,int dia, int mes, int ano){
		AgenteGsiDAO.modificarAgente(this, columna, dia, mes, ano);
	}
	
	public void borrarAgente(String nombre){
		nombreYapellido=nombre;
		AgenteGsiDAO.borrarAgente(this);
	}

	public JPanel diaSeleccionado(JPanel panelDia,JTable tabla) {
		// TODO Auto-generated method stub		
		return agenteG.diaSelect(this,panelDia, tabla);
	}

	public String soloNombres(String grupo) {
		// TODO Auto-generated method stub
		return agenteD.soloNombres(grupo);
	}

	public JPanel diaSeleccionadoVacio(JPanel panelDia, JTable tabla) {
		// TODO Auto-generated method stub
		return agenteG.diaSelectVacio(this, panelDia, tabla);
	}
	
	
	public String toString(){
		String agente;
		agente=legajo+"\n"+nombreYapellido+"\n"+getFechaNac()+"\n"+tipoDni+" "+dni+"\n"+direccion+"\n"+telefonos+"\n"+
		email+"\n"+getNombreGrupo();
		
		return agente;
	}

	public int getDiaNac() {
		// TODO Auto-generated method stub
		return fechaNac.get(5);
	}
	
	public int getMesNac() {
		// TODO Auto-generated method stub
		return fechaNac.get(2)+1;
	}
	
	public int getAnoNac() {
		// TODO Auto-generated method stub
		return fechaNac.get(1);
	}

	public static ArrayList<AgenteGsi> buscarAgente(String text){
		return AgenteGsiDAO.buscarAgente(text);
	}
	

}