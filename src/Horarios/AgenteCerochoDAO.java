package Horarios;

import java.awt.Container;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import sun.management.resources.agent;

/**
 * Clase encargada de la relación entre la base de datos y la aplicación
 * @author July_ar
 *
 */
public class AgenteCerochoDAO {
	
	public AgenteCerochoDAO(){
		
	}
	
	 /** Constructor que inicializa los valores estáticos y ya que esta crea la base de datos si no existe
	  * y por qué no también su correspondiente tabla
	 * 
	 * @param URL direccion de host
	 * 
	 */
	public AgenteCerochoDAO(String url/*,String user,String pass*/){
		/*AgenteGsiDAO.url=url;
		AgenteGsiDAO.user=user;
		AgenteGsiDAO.pass=pass;*/
		//crearDB();
		if (!existeTabla("agCero"))
			crearTabla();
	}
	
	public static void cargarAgente(AgenteCerocho agente){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		String fecha;
		if (sdf.format(agente.getDateNac()).equals("0002-11-30")){
			GregorianCalendar aux =new GregorianCalendar(1950,0,1);
			fecha=sdf.format(aux.getTime());
		}else{
			fecha=sdf.format(agente.getDateNac());
		}
		
		String accion= "insert into agCero values (null, '"+agente.getNombreyApellido()+"', '"+fecha+"', '"+
		agente.getDireccion()+"', '"+agente.getLegajo()+"', '"+agente.getTipoDni()+"', '"+
				agente.getDni()+"', '"+agente.getTelefono()+"', '"+agente.getEmail()+"', '"+
		agente.getMyGrupo().getNombre()+"', '"+ agente.getSubGrupo()+"')";
		
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
	            rs = s.executeQuery("select * from agCero order by grupo, nombre;");
	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return rs;
	}
	
	public String soloNombre(Date dia, int subG){
		String agFinal=new String();
		int sg=subG;
		
		String accion="select * from agCero where subgrupo = "+sg+";";
		Conectarse con=new Conectarse();
		ResultSet rs;
		
		try{ 
			
			Statement s = con.getCon().createStatement();
			rs = s.executeQuery("use monitoreo;");
			rs=s.executeQuery(accion);
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();
				System.out.println("in the while");
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[11];
				
				for (int i = 0; i < numeroColumnas; i++){
					datosFila[i] = rs.getObject(i + 1);
					
				}
				DiaLab08 diaL=new DiaLab08();
				
				char dF=datosFila[9].toString().toUpperCase().charAt(0);
				char dL=diaL.calcularGrupo(dia).charAt(0);
				
								
				if (dF==dL) {
					agFinal=((String) datosFila[1]);
				}
				
				/*datosUtiles.add(((String) datosFila[1]));*/
			}
			System.out.println("sale while");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
		return agFinal;
	}
	
	
	public String soloNombres(Date dia){
		String agFinal=new String();
		DiaLab08 diaL=new DiaLab08();
		ArrayList<Object> datosUtiles=new ArrayList<Object>();
				
		String accion="select * from agCero where grupo = '"+diaL.calcularGrupo(dia).toLowerCase()+"';";
		Conectarse con=new Conectarse();
		ResultSet rs;
		
		try{ 
			
			Statement s = con.getCon().createStatement();
			rs = s.executeQuery("use monitoreo;");
			rs=s.executeQuery(accion);
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[11];
				
				for (int i = 0; i < numeroColumnas; i++){
					datosFila[i] = rs.getObject(i + 1);
					
				}				
									
				
				datosUtiles.add(((String) datosFila[1]));
			}
			System.out.println("sale while");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cont=0;
		while (cont<datosUtiles.size()){
			agFinal=new String();
			agFinal=datosUtiles.toString();
			
			cont++;
		}
				
				
		return agFinal;
	}
	
	public String iniciales(String name) {
		
		String[] inis=new String[2];	
		inis[0]=(String) name.substring(0, 1);
		int coma=name.indexOf(',');
		inis[1]=name.substring(coma+2, coma+3);
					
		return (inis[0]+inis[1]);
	}
	
	
	public String dia(Date dia, int subG) {
		String agFinal=new String();
		int sg=subG+1;
		
		String accion="select * from agCero where subgrupo = "+sg+";";
		Conectarse con=new Conectarse();
		ResultSet rs;
		
		try{ 
			
			Statement s = con.getCon().createStatement();
			rs = s.executeQuery("use monitoreo;");
			rs=s.executeQuery(accion);
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();
				System.out.println("in the while");
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[11];
				
				for (int i = 0; i < numeroColumnas; i++){
					datosFila[i] = rs.getObject(i + 1);
					
				}
				DiaLab08 diaL=new DiaLab08();
				
				char dF=datosFila[9].toString().toUpperCase().charAt(0);
				char dL=diaL.calcularGrupo(dia).charAt(0);
				
								
				if (dF==dL) {
					agFinal=(this.iniciales((String) datosFila[1]));
				}
				
				/*datosUtiles.add(((String) datosFila[1]));*/
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
		return agFinal;
		
	}
		
		
	/**
	 * Borra el agente indicado
	 * @param 
	 */
	public static void borrarAgente(AgenteCerocho agente){
		
		String borrar="delete from agCero where nombre= '"+agente.getNombreyApellido()+"' ;";
		Conectarse con=new Conectarse("monitoreo");
		AgenteCerocho agente1=new AgenteCerocho();
		AgenteCerochoDAO.leerAgente(agente1, agente.getNombreyApellido());
		String grupo=agente1.getMyGrupo().getNombre();

		try{
			Statement st= con.getCon().createStatement();
			st.executeUpdate(borrar);
			AgenteCerochoDAO.ordenarSubGrupos(grupo);
			con.cerrar();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Modificara los datos de la <b>columna</b> del <b>agente</b> con <b>newValor</b>
	 * @param String
	 */
	public static void modificarAgente(AgenteCerocho agente,String columna,String newValor){
		String accion= "update agCero set "+columna+ " = '"+newValor+"' where nombre='"+ agente.getNombreyApellido()+"';";
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
	public static void modificarAgente(AgenteCerocho agente,String columna,long newValor){
		String accion= "update agCero set "+columna+" = "+newValor+" where nombre= '"+ agente.getNombreyApellido()+"';";
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
	 * Modificara los datos de la <b>columna</b> del <b>agente</b> con el date formado por <b>dia</b>, <b>mes</b>, <b>año</b>
	 * @param agente
	 * @param columna
	 * @param dia
	 * @param mes
	 * @param año
	 */
	public static void modificarAgente(AgenteCerocho agente,String columna,int dia, int mes, int año){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar aux=new GregorianCalendar(año,mes,dia);
		String newValor=sdf.format(aux.getTime());
		String accion= "update agCero set "+columna+" = '"+newValor+"' where nombre= '"+ agente.getNombreyApellido()+"';";
		Conectarse con=new Conectarse("monitoreo");
		
		try{
			Statement st= con.getCon().createStatement();
			st.executeUpdate(accion);
			con.cerrar();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void leerAgente(AgenteCerocho agente,String nombre){
		String accion="select * from agCero where nombre = '"+nombre+"';";
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
					int subg=rs.getInt(11);
					agente.setNombreGrupo(nomG,subg);

				}
			}

						
			con.cerrar();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static int asignSubGrupo(String grupo) {
		int subGrupo=0;

		ArrayList<Integer> agentes=new ArrayList<Integer>();
		
		String accion="select * from agCero where grupo = '"+grupo+"';";
		Conectarse con=new Conectarse();
		ResultSet rs;

		ArrayList<Object> datosUtiles=new ArrayList<Object>();

		try{ 
			Statement s = con.getCon().createStatement();
			rs = s.executeQuery("use monitoreo;");
			rs=s.executeQuery(accion);
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();
				
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[11];
				
				for (int i = 0; i < numeroColumnas; i++){
					datosFila[i] = rs.getObject(i + 1);					
				}
				datosUtiles.add(((Integer) datosFila[10]));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Object> iterador;
		
		
		int i=0;
		for (iterador=datosUtiles.iterator(); iterador.hasNext();i++) {
			agentes.add((Integer) (iterador.next()));			
		}
		
		Collections.sort(agentes);
		
		int cont=0;

		if (agentes.size()==0) { 
			subGrupo=1;
		}
		while (cont<agentes.size()){
		
			int c=cont+1;
			
			if (agentes.get(cont)!=c) {

				subGrupo= c;
				break;
			} else {
				subGrupo=c+1;

			}
			/*
			
			if (agentes.get(cont)>c) {
				System.out.println("iterator: "+agentes);
				System.out.println("cont: "+cont);
				System.out.println(agentes.get(cont));
				subGrupo=agentes.get(cont)-1;
				break;
			} else {
				
				subGrupo=agentes.get(cont);
				if (cont+1==agentes.size()) {
					System.out.println("iterator: "+agentes);
				subGrupo=agentes.size()+1;
				System.out.println("if: "+subGrupo);
			}
			}*/
			/*if (agentes.get(cont)<iteradorAgentes.next()) {
				System.out.println("cont: "+cont);
				
				subGrupo=cont;
				System.out.println("if else: "+subGrupo);
			}
			
				
				//subGrupo=cont+1;
				System.out.println("agente.get: "+agentes.get(cont));
				
			} */
			
			cont++;
		}			
		
		return subGrupo;
		
	}
	
	static void ordenarSubGrupos(String grupo) {
		int subGrupo=0;

		ArrayList<Integer> agentes=new ArrayList<Integer>();
		
		AgenteCerocho ag=new AgenteCerocho();
		
		String accion="select * from agCero where grupo = '"+grupo+"';";
		Conectarse con=new Conectarse();
		ResultSet rs;
		
		ArrayList<Object> datosUtiles=new ArrayList<Object>();

		try{ 
			Statement s = con.getCon().createStatement();
			rs = s.executeQuery("use monitoreo;");
			rs=s.executeQuery(accion);
			ResultSetMetaData metaDatos = rs.getMetaData();
			
			int k=1;

			while (rs.next())
			{
				
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[11];
				
				for (int i = 0; i < numeroColumnas; i++){
					datosFila[i] = rs.getObject(i + 1);					
				}
				datosUtiles.add(((String) datosFila[1]));
				
			}
			for (int j = 0; j < datosUtiles.size(); j++,k++) {
				leerAgente(ag, (String) datosUtiles.get(j));
				modificarAgente(ag, "subgrupo", k);
				String actualizar= "update agCero set subgrupo = "+k+" where nombre= '"+ ag.getNombreyApellido()+"';";
				System.out.println(actualizar);	
			}
						
				
				//
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		String accion="create table if not exists agCero (id_agente integer UNSIGNED NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id_agente`), "
				+ "nombre varchar(35), fecha_nac DATE default null, direccion varchar(35), nro_legajo int(20), "
				+ "tipo_dni varchar(5), dni integer(20), telefonos varchar(45), email varchar(45), grupo char(1), subgrupo int(1))ENGINE = InnoDB;";
		
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
				System.out.println("Base de datos borrado con éxito!!");
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
				System.out.println("Tabla borrada con éxito!!");
			}catch( SQLException e ) {
				e.printStackTrace();
			}
		}else {
			con.cerrar();
			System.out.println("Nada que borrar, la tabla no existe");
		}
		
	}
	

	public static ArrayList<AgenteCerocho> buscarAgente(String text) {
		// TODO Auto-generated method stub
		//boolean positivo=false;
		AgenteCerocho encontrado = new AgenteCerocho();
		ArrayList<AgenteCerocho> agente=new ArrayList<AgenteCerocho>();
		ResultSet rs=AgenteCerochoDAO.datosAgentes();

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
						encontrado=new AgenteCerocho();
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
