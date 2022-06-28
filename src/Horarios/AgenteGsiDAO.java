package Horarios;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Clase encargada de la relaci�n entre la base de datos y la aplicaci�n
 * @author July_ar
 *
 */
public class AgenteGsiDAO {
	
	public AgenteGsiDAO(){
		
	}
	
	 /** Constructor que inicializa los valores est�ticos y ya que esta crea la base de datos si no existe
	  * y por qu� no tambi�n su correspondiente tabla
	 * 
	 * @param URL direccion de host
	 * @param USER usuario de base de datos
	 * @param PASS contrase�a de la base de datos
	 * 
	 */
	public AgenteGsiDAO(String url/*,String user,String pass*/){
		/*AgenteGsiDAO.url=url;
		AgenteGsiDAO.user=user;
		AgenteGsiDAO.pass=pass;*/
		//crearDB();
		if (!existeTabla("agentes"))
			crearTabla();
	}
	
	public static void cargarAgente(AgenteGsi agente){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(agente.getDateNac());
		System.out.println(sdf.format(agente.getDateNac()));
		String fecha;
		if (sdf.format(agente.getDateNac()).equals("0002-11-30")){
			GregorianCalendar aux =new GregorianCalendar(1950,0,1);
			fecha=sdf.format(aux.getTime());
		}else{
			fecha=sdf.format(agente.getDateNac());
		}
		
		String accion= "insert into agentes values (null, '"+agente.getNombreyApellido()+"', '"+fecha+"', '"+agente.getDireccion()+"', '"
		+agente.getLegajo()+"', '"+agente.getTipoDni()+"', '"+agente.getDni()+"', '"+agente.getTelefono()+"', '"
				+agente.getEmail()+"', '"+agente.getMyGrupo().getNombre()+"')";
		Conectarse con= new Conectarse("monitoreo");
		try {
			Statement st=con.getCon().createStatement();
			st.execute(accion);
			con.cerrar();
		}catch(SQLException e){
			e.printStackTrace();	
		}
	}
	
	/**
	 * Carga en un ResulSet los datos de la tabla <i>agentes</i>
	 * @return
	 */
	public static ResultSet datosAgentes(){
		 ResultSet rs = null;
		 Conectarse con= new Conectarse("monitoreo");
	        try{ 
	        	Statement s = con.getCon().createStatement();
	        	rs = s.executeQuery("use monitoreo;");
	            rs = s.executeQuery("select * from agentes order by grupo, nombre;");
	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return rs;
	}
	
	public String soloNombres(String grupo){
		String grupoFinal=new String();
		
		String accion="select * from agentes where grupo = '"+grupo+"';";
		Conectarse con=new Conectarse();
		ResultSet rs;
		//datosUtiles guarda solo eso, que en este caso son los nombres
		ArrayList<Object> datosUtiles=new ArrayList<Object>();

		try{ 
			Statement s = con.getCon().createStatement();
			rs = s.executeQuery("use monitoreo;");
			rs=s.executeQuery(accion);
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();
				
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[10];
				
				for (int i = 0; i < numeroColumnas; i++){
					datosFila[i] = rs.getObject(i + 1);
					
				}

				datosUtiles.add(((String) datosFila[1]));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		int cont=0;
		while (cont<datosUtiles.size()){
			grupoFinal=new String();
			grupoFinal=datosUtiles.toString()/*iteradorAgentes.next()+System.getProperty("line.separator")*/;
			
			cont++;
		}
		
		return grupoFinal;
	}
		
	/**
	 * Borra el agente indicado
	 * @param 
	 */
	public static void borrarAgente(AgenteGsi agente){
		
		String borrar="delete from agentes where nombre= '"+agente.getNombreyApellido()+"' ;";
		Conectarse con=new Conectarse("monitoreo");

		try{
			Statement st= con.getCon().createStatement();
			st.executeUpdate(borrar);
			con.cerrar();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Modificara los datos de la <b>columna</b> del <b>agente</b> con <b>newValor</b>
	 * @param String
	 */
	public static void modificarAgente(AgenteGsi agente,String columna,String newValor){
		String accion= "update agentes set "+columna+ " = '"+newValor+"' where nombre='"+ agente.getNombreyApellido()+"';";
		Conectarse con=new Conectarse("monitoreo");
		
		try{
			Statement st= con.getCon().createStatement();
			st.executeUpdate(accion);
			con.cerrar();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Modificara los datos de la <b>columna</b> del <b>agente</b> con <b>newValor</b> de tipo long
	 * @param agente
	 * @param columna
	 * @param newValor
	 */
	public static void modificarAgente(AgenteGsi agente,String columna,long newValor){
		String accion= "update agentes set "+columna+" = "+newValor+" where nombre= '"+ agente.getNombreyApellido()+"';";
		Conectarse con=new Conectarse("monitoreo");
		
		try{
			Statement st= con.getCon().createStatement();
			st.executeUpdate(accion);
			con.cerrar();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Modificara los datos de la <b>columna</b> del <b>agente</b> con el date formado por <b>dia</b>, <b>mes</b>, <b>a�o</b>
	 * @param agente
	 * @param columna
	 * @param dia
	 * @param mes
	 * @param a�o
	 */
	public static void modificarAgente(AgenteGsi agente,String columna,int dia, int mes, int a�o){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar aux=new GregorianCalendar(a�o,mes,dia);
		String newValor=sdf.format(aux.getTime());
		String accion= "update agentes set "+columna+" = '"+newValor+"' where nombre= '"+ agente.getNombreyApellido()+"';";
		Conectarse con=new Conectarse("monitoreo");
		
		try{
			Statement st= con.getCon().createStatement();
			st.executeUpdate(accion);
			con.cerrar();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void leerAgente(AgenteGsi agente,String nombre){
		String accion="select * from agentes where nombre = '"+nombre+"';";
		Conectarse con=new Conectarse("monitoreo");
		ResultSet rs;
		try{
			Statement st=con.getCon().createStatement();
			rs = st.executeQuery("use monitoreo;");
			rs=st.executeQuery(accion);
			while (rs.next()){
				
				agente.setNombreYapellido(rs.getString(2));
				if (agente.getNombreyApellido().indexOf(nombre)!=-1){
					agente.setFechaNac(rs.getDate(3));
					agente.setDireccion(rs.getString(4));
					agente.setLegajo(rs.getLong(5));
					agente.setTipoDni(rs.getString(6));
					agente.setDni(rs.getLong(7));
					agente.setTelefonos(rs.getString(8));
					agente.setEmail(rs.getString(9));
					String nomG=rs.getString(10);
					agente.setNombreGrupo(nomG);

				}
			}

						
			con.cerrar();
		}catch(SQLException e){
			e.printStackTrace();
		}
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
	
	/**
	 * Que mas se puede decir, crea la tabla 
	 */
	public void crearTabla(){
		Conectarse con=new Conectarse();
		String act1="use monitoreo";
		String accion="create table if not exists agentes (id_agente integer UNSIGNED NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id_agente`), nombre varchar(35), fecha_nac DATE default null, direccion varchar(35), nro_legajo int(20), tipo_dni varchar(5), dni integer(20), telefonos varchar(45), email varchar(45), grupo char(1))ENGINE = InnoDB;";
		
		try{
			Statement st=con.getCon().createStatement();
			st.execute(act1);
			st.execute(accion);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		con.cerrar();
	}
	
	/**
	 * Borra la Base de Datos indicada
	 * @param nameDb nombre de base de datos a eliminar
	 */
	public static void borrarDB(String nameDb){
		String accion="DROP DATABASE IF EXISTS "+nameDb;
		Conectarse conect=new Conectarse(nameDb);
		boolean exist=false;

		if (exist){
			try{
				Statement stmnt=conect.getCon().createStatement();
				stmnt.execute(accion);
				conect.cerrar();
				System.out.println("Base de datos borrado con �xito!!");
			}catch( SQLException e ) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Funcion para determinar si existe la tabla indicada
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
	 * Borra la tabla indicada
	 * @param nombreTabla nombre de tabla a eliminar
	 */
	public static void borrarTabla(String nombreTabla){
		String acc="use monitoreo";
		String accion="drop table if exists "+nombreTabla;
		Conectarse con=new Conectarse("monitoreo");

		if (existeTabla(nombreTabla)){
			try{
				Statement stmnt=con.getCon().createStatement();
				stmnt.execute(acc);
				stmnt.execute(accion);
				con.cerrar();
				System.out.println("Tabla borrada con �xito!!");
			}catch( SQLException e ) {
				e.printStackTrace();
			}
		}else {
			con.cerrar();
			System.out.println("Nada que borrar la tabla no existe");
		}
		
	}
	

	public static ArrayList<AgenteGsi> buscarAgente(String text) {
		// TODO Auto-generated method stub
		//boolean positivo=false;
		AgenteGsi encontrado = new AgenteGsi();
		ArrayList<AgenteGsi> agente=new ArrayList<AgenteGsi>();
		ResultSet rs=AgenteGsiDAO.datosAgentes();

		try {
			while ( rs.next()) {
				encontrado.setFechaNac(rs.getDate(3));
				String fechaN=encontrado.getFechaNac();
				String legajo=String.valueOf(rs.getLong(5));
				String dni=String.valueOf(rs.getLong(7));
				String nomG=rs.getString(10);
				if ((rs.getString(2).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(fechaN.toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(rs.getString(4).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(legajo.toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(rs.getString(6).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(dni.toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(rs.getString(8).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(rs.getString(9).toLowerCase().indexOf(text.toLowerCase())!=-1)
					||(nomG.toLowerCase().indexOf(text.toLowerCase())!=-1)){
						encontrado.leerAgente(rs.getString(2));
						agente.add(encontrado);
						encontrado=new AgenteGsi();
						//positivo=true;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return agente;
	}

	

}
