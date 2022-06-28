package Horarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("restriction")
public class ExportacionPdf08 {
	
	private int mes;
	private int a�o;
	
	private static final String RUTA_PATH = System.getProperty( "user.home")+"\\Mis documentos\\Planillas de horarios";
	
	private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 24,	Font.BOLD);
	//private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	//private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,	Font.BOLD);
	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	
	private Document documento;
	private Rectangle objRectangulo = null;

	private int ancho = 950;
	private int alto = 600;
	
	private File archivoSalida;
	//private GregorianCalendar cal=(GregorianCalendar) Calendar.getInstance();
	private GregorianCalendar calendar;
	private final static int daysMonth[] = {
	        31, 28, 31, 30, 31, 30, 
	        31, 31, 30, 31, 30, 31
	    };
	
	public ExportacionPdf08(){
		
	}
		
	public ExportacionPdf08(int mes,int a�o){
		try {	
			this.mes=mes;
			this.a�o=a�o;
			objRectangulo = new Rectangle( ancho, alto );
			documento=new Document(objRectangulo);
			File ruta=new File(RUTA_PATH);
			ruta.mkdir();
			archivoSalida=new File(ruta,"0800 - Planilla horarios mes "+CalendarioGrafic.nombreMes(this.mes)+" "+this.a�o+".pdf");
			
			FileOutputStream fileOut=new FileOutputStream(archivoSalida);
			
			PdfWriter.getInstance(documento,fileOut );
			documento.open();
			addMetaData(documento);
			agregarContenido(documento);
			documento.close();
			Runtime.getRuntime().exec( "rundll32 url.dll, FileProtocolHandler " + archivoSalida );
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"<html><center><i>Acceso denegado</i><br> El archivo est� siendo usado por otra aplicaci�n</br></center></html>","Exportaci�n de planilla de horarios",JOptionPane.ERROR_MESSAGE);
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void exportarPdf(int mes, int a�o){
		new ExportacionPdf08(mes, a�o);
	}
	
	private void agregarContenido(Document document)	throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Sala 0800", catFont));

		addEmptyLine(preface, 1);
		preface.add(new Paragraph(
				"Planilla de horarios mes de " + CalendarioGrafic.nombreMes(mes)+" de "+a�o, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				smallBold));
		addEmptyLine(preface, 3);
		
		crearTablas(preface);
		
		addEmptyLine(preface, 2);
		
		agregarAgentes(preface);

		document.add(preface);
		
		// Start a new page
		document.newPage();
	}
	
	public void agregarAgentes(Paragraph preface)throws BadElementException{
		
		for (int i = 0; i < 3; i++) {
			int j=97+i;
			String aChar = new Character((char)j).toString();
			preface.add(new Paragraph(integrantesGrupo(aChar)));
			addEmptyLine(preface, 1);
		}
		
	}
	
	
	private void crearTablas(Paragraph preface/*Section subCatPart*/)	throws BadElementException {
		
		int nroDays=daysMonth[mes];
		calendar=new GregorianCalendar(a�o,mes,1);
		if (mes == Calendar.FEBRUARY && calendar.isLeapYear(a�o)) {
            nroDays++;
        }
		
		PdfPTable table = new PdfPTable(nroDays+2);
		//setea medidas de celdas
		
		float[] medidas=new float[nroDays+2];
		for(int j=0; j<(nroDays+2);j++)
		{
			medidas[j] =30.30f;
		} 
		try {
			table.setWidths(medidas);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PdfPCell celdaB=new PdfPCell();
		celdaB.setColspan(2);
		table.addCell(celdaB);
		
		//agrega nombre de d�a y n�mero 
		for ( int i= 0; i < nroDays; i++) {
			calendar=new GregorianCalendar(a�o, mes,i);
			String nomDia=CalendarioGrafic.letraDia(calendar.get(Calendar.DAY_OF_WEEK));
			PdfPCell c1 = new PdfPCell(new Phrase(nomDia+"  "+(i+1)));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
		}
		//agrega celdas vacias
			PdfPCell ce1 = new PdfPCell();
			ce1.setColspan(nroDays+2);
			table.addCell(ce1);		
				
			
			//agrega celda con inscripci�n "Grupo"
			PdfPCell celda0=new PdfPCell(new Paragraph("Grupo", FontFactory.getFont("arial",   // fuente
					 11,                            // tama�o
					Font.BOLD,                   // estilo
					BaseColor.BLACK)));
			celda0.setBackgroundColor(BaseColor.GRAY);
			celda0.setColspan(2);
			celda0.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda0);
			
			//calcula y agrega el grupo que trabaja tal dia en el turno de 00 a 06 
			addTurnoInCell(nroDays,table, 0);
			
			//agrega celdas vacias
			table.addCell(ce1);	
			
			//agrega celda con inscripci�n "Ag 1"
		PdfPCell celda=new PdfPCell(new Paragraph("Agente", FontFactory.getFont("arial",   // fuente
				 11,                            // tama�o
				Font.BOLD,                   // estilo
				BaseColor.BLACK)));
		celda.setBackgroundColor(BaseColor.GRAY);
		celda.setColspan(2);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);
		
		//calcula y agrega el grupo que trabaja tal dia en el turno de 00 a 06 
		addTurnosInCell(nroDays,table, 0);
		
		//agrega celdas vacias
		/*table.addCell(ce1);			
		
		
		 * //agrega celda con inscripci�n "06 a 12" PdfPCell celda2=new PdfPCell(new
		 * Paragraph("Ag 2", FontFactory.getFont("arial", // fuente 11, // tama�o
		 * Font.BOLD, // estilo BaseColor.BLACK)));
		 * celda2.setBackgroundColor(BaseColor.GRAY); celda2.setColspan(2);
		 * celda2.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(celda2);
		 * 
		 * //calcula y agrega el grupo que trabaja tal dia en el turno de 06 a 12
		 * addTurnosInCell(nroDays,table, 1);
		 */
			//table.addCell(ce1);				
		
		

		preface.add(table);

	}
	
	private void addMetaData(Document document) {
		document.addTitle("Monitoring System");
		document.addSubject("Mes laboral");
		document.addKeywords("Centro de atenci�n ciudadana, Municipalidad");
		document.addAuthor("Juli�n Arag�n");
		document.addCreator("Juli�n Arag�n");
	}
	
	
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	/**
	 * Agrega en las celdas <b>nroDias</b> el grupo correspondiente al <b>turno</b> 
	 * que trabaja cada dia del <b>mes</b> del <b>a�o</b> indicado dentro de la <b>tabla</b> 
	 * @param nroDias
	 * @param tabla
	 * @param turno
	 */
	public void addTurnosInCell(int nroDias,PdfPTable tabla,int turno){
		for ( int i= 0; i < nroDias; i++) {
			calendar=new GregorianCalendar(a�o, mes,i+1);
			Date fecha=calendar.getTime();
			DiaLab08 diaL=new DiaLab08();
			PdfPCell c1 = new PdfPCell(new Phrase(diaL.calcularSubGrupo(fecha, turno)));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla.addCell(c1);			
		}
	}
	
	/**
	 * Agrega en las celdas <b>nroDias</b> el grupo correspondiente al <b>turno</b> 
	 * que trabaja cada dia del <b>mes</b> del <b>a�o</b> indicado dentro de la <b>tabla</b> 
	 * @param nroDias
	 * @param tabla
	 * @param turno
	 */
	public void addTurnoInCell(int nroDias,PdfPTable tabla,int turno){
		for ( int i= 0; i < nroDias; i++) {
			calendar=new GregorianCalendar(a�o, mes,i+1);
			Date fecha=calendar.getTime();
			DiaLab08 diaL=new DiaLab08();
			PdfPCell c1 = new PdfPCell(new Phrase(diaL.calcularGrupo(fecha)));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla.addCell(c1);			
		}
	}
	
	/**
	 * Saca los integrantes de la base de datos
	 * @param grupo
	 * @return
	 */
	public String integrantesGrupo(String grupo){
		String lineaReturn;
		ArrayList<String> agentes=new ArrayList<String>();
		String grupoFinal=new String();
		//ArrayList<String> integrantes=null;
		
		String accion="select * from agCero where grupo = '"+grupo+"';";
		Conectarse con=new Conectarse();
		ResultSet rs;
		//datosUtiles guarda solo eso, que en este caso son los nombres
		ArrayList<Object> datosUtiles=new ArrayList<Object>();

		try{ 
			Statement s = con.getCon().createStatement();
			rs = s.executeQuery("use monitoreo;");
			rs=s.executeQuery(accion);
			//int cont=0;
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();
				
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[11];
				
				for (int i = 0; i < numeroColumnas; i++){
					datosFila[i] = rs.getObject(i + 1);
					
				}

				datosUtiles.add(((String) datosFila[1]));
				//System.out.println(datosUtiles);
				//cont++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Object> iterador;
		
		int i=0;
		for (iterador=datosUtiles.iterator(); iterador.hasNext();i++) {
			agentes.add((String) (iterador.next()));
			
		}
		Iterator<String> iteradorAgentes;
		/*for (iteradorAgentes=agentes.iterator(); iteradorAgentes.hasNext();) {
			System.out.println(iteradorAgentes.next());
		}*/
		
		//integrantes.add(((String) datosUtiles[0]));
		//segundoInt=((String) datosUtiles[1]).split(",");
		int cont=0;
		ArrayList<String> nombres=new ArrayList<String>();
		String[] nombresFinales;
		iteradorAgentes=agentes.iterator();
		while (cont<agentes.size()){
			grupoFinal=new String();
			grupoFinal+=iteradorAgentes.next();
			//if (cont!=(agentes.size()-1)){
				nombresFinales=new String[50];
				nombresFinales=grupoFinal.split(",");
				nombres.add(nombresFinales[0])/*+" - "*/;
				/*nombres[cont]=nombres+" - ";
				System.out.println(nombres);*/
			//}
			
			cont++;
		}
		/*nombresFinales=nombres[0]+" - "+nombres[2]+" - "+nombres[4];
			System.out.println(nombresFinales);*/
		lineaReturn="Grupo "+grupo.toUpperCase()+": "+nombres;
		return lineaReturn;
		
	}
	
	/*
	public static void main(String Args[]){
		ExportacionPdf.exportarPdf(5, 2011);
		
	}
	*/
	

}
