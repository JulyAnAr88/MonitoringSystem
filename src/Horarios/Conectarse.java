package Horarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JOptionPane;


public class Conectarse {
	public static String URL;
	public static String USER;
	public static String PASS;
	private Connection con;
	private boolean exito=false;
	
	//Set Look & Feel
	{
		
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Conectarse(/*String url,String user,String pass*/){
		setDatos();
		int n=0;
		/*URL=url;
		USER=user;
		PASS=pass;*/
		try{
			Class.forName("com.mysql.jdbc.Driver" );
			con=DriverManager.getConnection(URL,USER,PASS);
			exito=true;
		}catch( SQLException e ) {
			JOptionPane.showMessageDialog(null,"<html><center><i>Acceso denegado</i><br> Revise los datos ingresados<br> no se pudo lograr la conexión</br></center></html>","Conexión",JOptionPane.ERROR_MESSAGE);
			Object[] options ={"Configurar","Cancelar"};
			n=JOptionPane.showOptionDialog(null,"<html><center>Recuerda configurar los datos de acceso a la Base de Datos</center></html>","Conexión",JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]);

		
		if (n==0){
			@SuppressWarnings("unused")
			Preferences pref=new Preferences();
			//pref.
			e.printStackTrace();
			}
			exito=false;
			/**/File ruta = new File(System.getProperty("user.dir"));

			try {
				File archivo=new File(ruta, "errorConect.log");
				//FileWriter out = new FileWriter(archivo);
				PrintWriter print=new PrintWriter(archivo);
				Calendar cal=Calendar.getInstance();
				SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss", new Locale("ES"));
				String fechaFormat = formateador.format(cal.getTime());
				System.out.println(fechaFormat);
				print.write(fechaFormat+System.getProperty("line.separator"));
				e.printStackTrace(print);
				/*out.write("Salto excepcion en conectar"+System.getProperty("line.separator"));
				out.write(e.toString());
				out.close();*/
				print.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public Conectarse(String nomB){
		setDatos();
		try{
			Class.forName("com.mysql.jdbc.Driver" );
			con=DriverManager.getConnection(URL+"/"+nomB,USER,PASS);
			exito=true;
		}catch( SQLException e ) {
			JOptionPane.showMessageDialog(null,"<html><center><i>Acceso denegado</i><br> Revise los datos ingresados<br> no se pudo lograr la conexión</br></center></html>","Conexión",JOptionPane.ERROR_MESSAGE);
			exito=false;
			/**/File ruta = new File(System.getProperty("user.dir"));

			try {
				File archivo=new File(ruta, "errorConect.log");
				//FileWriter out = new FileWriter(archivo);
				PrintWriter print=new PrintWriter(archivo);
				e.printStackTrace(print);
				/*out.write("Salto excepcion en conectar"+System.getProperty("line.separator"));
				out.write(e.toString());
				out.close();*/
				print.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exito(){
		return exito;
	}
	
	public void cerrar(){
		try{
			con.close();
		}catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	

	public String getUrl() {
		return URL;
	}

	public void setUrl(String url) {
		Conectarse.URL = url;
	}
	

	public String getPass() {
		return PASS;
	}

	public void setPass(String pass) {
		Conectarse.PASS= pass;
	}
	

	public String getUser() {
		return USER;
	}

	public void setUser(String user) {
		Conectarse.USER = user;
	}

	public void setDatos(){

		File ruta;

		try {
			ruta = new File(System.getProperty("user.dir")+"/cfg");
			ruta.mkdirs();

			File archivo=new File(ruta, "cfg.jcdb");
			archivo.createNewFile();

			FileReader in=new FileReader(archivo);
			BufferedReader entrada = new BufferedReader(in );
			if ( archivo.exists() ){
				URL=entrada.readLine();
				USER=entrada.readLine();
				PASS=entrada.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarDatos(String host, String user, String pass){

		File ruta;

		try {
			//ruta = new File(getClass().getResource("/cfg").toURI());
			ruta = new File(System.getProperty("user.dir")+"/cfg");
			ruta.mkdirs();
			File archivo=new File(ruta, "cfg.jcdb");


			FileWriter out = new FileWriter(archivo);

			out.write(host+System.getProperty("line.separator"));
			out.write(user+System.getProperty("line.separator"));
			out.write(pass+System.getProperty("line.separator"));
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
