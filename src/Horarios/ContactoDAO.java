package Horarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContactoDAO {

	
	public ContactoDAO(String url){
		/*ContactoDAO.url=url;
		ContactoDAO.user=user;
		ContactoDAO.pass=pass;*/
		crearDB();
		if (!existeTabla("contactos"))
			crearTabla();
	}
	
	/** Funcion para determinar si existe la tabla indicada
	 * @param tableName nombre de tabla a consultar
	 * @return verdadero si existe 
	 */
	public static boolean existeTabla(String tableName){
		boolean b=false;
		Conectarse c1= new Conectarse();
		try{
			Statement stat = c1.getCon().createStatement();
			 ResultSet rs1=stat.executeQuery("use monitoreo");
			 rs1=stat.executeQuery("describe "+tableName);
		    if(rs1.next()){
		    	b=true;
		    	c1.cerrar();
		    	return b;
		    }
		 }catch (SQLException e){
		    	 e.printStackTrace();
		 }
		 	c1.cerrar();
		    return b;
	}

	/**
	 * Crea la Base de Datos 
	 */
	public void crearDB(){
		String accion="create database if not exists monitoreo";
		Conectarse conect= new Conectarse();
		try{
			Statement stmnt=conect.getCon().createStatement();
			stmnt.execute(accion);
			
			conect.cerrar();
		}catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	/** Que mas se puede decir, crea la tabla 
	 */
	public void crearTabla(){
		Conectarse con=new Conectarse();
		String act1="use monitoreo";
		String accion="create table if not exists contactos (id_contacto integer UNSIGNED NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id_contacto`), nombre varchar(35), direccion varchar(35), telefonos varchar(45), email varchar(45), comentario varchar(200))ENGINE = InnoDB;";
		
		try{
			Statement st=con.getCon().createStatement();
			st.execute(act1);
			st.execute(accion);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		con.cerrar();
	}

	public static ResultSet datosCt(){
		 ResultSet rs = null;
		 Conectarse con= new Conectarse("monitoreo");
	        try{ 
	        	Statement s = con.getCon().createStatement();
	        	rs = s.executeQuery("use monitoreo;");
	            rs = s.executeQuery("select * from contactos order by nombre;");
	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return rs;
	}

	
	public static void leerCt(Contacto contacto, String nombre) {
		// TODO Auto-generated method stub
		String accion="select * from contactos where nombre = '"+nombre+"';";
		Conectarse con=new Conectarse("monitoreo");
		ResultSet rs;
		try{
			Statement st=con.getCon().createStatement();
			rs = st.executeQuery("use monitoreo;");
			rs=st.executeQuery(accion);
			while (rs.next()){
				
				contacto.setNombre(rs.getString(2));
				if (contacto.getNombre().indexOf(nombre)!=-1){
					contacto.setDireccion(rs.getString(3));
					contacto.setTelefonos(rs.getString(4));
					contacto.setEmail(rs.getString(5));
					contacto.setComentario(rs.getString(6));

				}
			}

						
			con.cerrar();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static void cargarContacto(Contacto cont) {
		// TODO Auto-generated method stub
		String accion= "insert into contactos values (null, '"+cont.getNombre()+"', '"+cont.getDireccion()+"', '"+cont.getTelefonos()+"', '"+cont.getEmail()+"', '"+cont.getComentario()+"')";
		Conectarse con= new Conectarse("monitoreo");
		try {
			Statement st=con.getCon().createStatement();
			st.execute(accion);
			con.cerrar();
		}catch(SQLException e){
			e.printStackTrace();	
		}
	}

	public static void modificarContacto(Contacto contacto, String columna,
			String newValor) {
		// TODO Auto-generated method stub
		String accion= "update contactos set "+columna+" = '"+newValor+"' where nombre= '"+ contacto.getNombre()+"';";
		Conectarse con=new Conectarse("monitoreo");
		
		try{
			Statement st= con.getCon().createStatement();
			st.executeUpdate(accion);
			con.cerrar();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static void borrarContacto(Contacto contacto) {
		// TODO Auto-generated method stub

		String borrar="delete from contactos where nombre= '"+contacto.getNombre()+"' ;";
		Conectarse con=new Conectarse("monitoreo");

		try{
			Statement st= con.getCon().createStatement();
			st.executeUpdate(borrar);
			con.cerrar();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static ArrayList<Contacto> buscarCt(String text) {
		// TODO Auto-generated method stub
		Contacto encontrado = new Contacto();
		ArrayList<Contacto> agente=new ArrayList<Contacto>();
		ResultSet rs=ContactoDAO.datosCt();

		try {
			while ( rs.next()) {
				if ((rs.getString(2).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(rs.getString(3).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(rs.getString(4).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(rs.getString(5).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(rs.getString(6).toLowerCase().indexOf(text.toLowerCase())!=-1)){
						encontrado.leerCt(rs.getString(2));
						agente.add(encontrado);
						encontrado=new Contacto();
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agente;
	}

}
