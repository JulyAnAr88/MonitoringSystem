package Horarios;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
* This code was edited or generated using CloudGarden's Jigloo
*/
public class Contacto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String direccion="";
	private String telefonos;
	private String email="";
	private String comentario;
	
	private ContactoGUI conG=new ContactoGUI();
	@SuppressWarnings("unused")
	private ContactoDAO conD=new ContactoDAO("java");
	
	public Contacto(){
		
	}
	
	public Contacto(String nombre, String direccion, String telefonos,String email, String comentario){
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefonos = telefonos;
		this.email=email;
		this.comentario=comentario;
	}

	/**
	 * Dialogo para agregar contactos
	 * 
	 */
	public void agregarDialog(JTable tabla, DefaultTableModel modelo,  JScrollPane scrollP){
		conG.agregarDialog(tabla, scrollP, modelo);
	}

	public ResultSet getDatosCt() {
		// TODO Auto-generated method stub
		return ContactoDAO.datosCt();
	}
	
	
	public void dialogoModificar(TablaCt tabla){
		conG.dialogoModificar(tabla);
	}
	
	public void leerCt(String nombre){
		this.nombre=nombre;
		ContactoDAO.leerCt(this, nombre);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void modificarCt(String columna, String newValor) {
		// TODO Auto-generated method stub
		ContactoDAO.modificarContacto(this, columna, newValor);
	}
	
	public void borrarContacto(String nombre){
		this.nombre=nombre;
		ContactoDAO.borrarContacto(this);
	}

	public JPanel fichaVacia(JPanel panelFicha) {
		// TODO Auto-generated method stub
		return conG.fichaVacia(this,panelFicha);
	}
	
	public JPanel ficha(JPanel panelFicha,JTabbedPane pestaña) {
		// TODO Auto-generated method stub
		return conG.ficha(this,panelFicha,pestaña);
	}
	
	public static ArrayList<Contacto> buscarCt(String text){
		return ContactoDAO.buscarCt(text);
	}
	

}
