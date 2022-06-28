package Horarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.FocusManager;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;



@SuppressWarnings("unused")
/**
* Panel que muestra tabla con calendario laboral y permite multiples consultas
*/
public class TablaPrueba extends JFrame{
	
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCalendar;
    private JPanel pnlDays;
    private JLabel lblDays[];
    private JTabbedPane pestania;
    
    ResultSet rs;
    
    /**
     * Paso como referencia la pestaña adonde va a ir el calendario para poder actualizarla
     * @param pestaña
     */
    public TablaPrueba(JTabbedPane pestaña){
    	pestania=pestaña;
    }
    
	
	private JTable tabla;
    
    private AgenteGsi agente=new AgenteGsi();
    private AgenteCerocho ag08=new AgenteCerocho();

    private final String nomCol[] = { 
    	"Nombre y Apellido","Dirección", "Teléfono", "E-mail", "Grupo"
    };
    
    private final String months[] = {
            "Enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", 
            "agosto", "septiembre", "octubre", "noviembre", "diciembre"
    };

        
    private final String columFija08[] = {
    	"Guardia", "Ag 1", "Ag 2"	
    };
    
    
    private final String[][] data= new String[100][5];
    Object [] column;
	private JLabel labelMesEx;
	private JComboBox mesExport;
	private JComboBox añoExport;
	private JComboBox jComboBoxGuardia;
	private JLabel labelAñoExp;
	private JLabel labelSelTab;
	
	final AbstractTableModel model = new AbstractTableModel() {
		private static final long serialVersionUID = 1L;

		public int getColumnCount() {
			
			return 5;
		}

		public int getRowCount() {
			int numeroColumnas=0;
			try {
				AgenteGsi agente=new AgenteGsi();
	    		rs = agente.getDatosAgentes();
	    		ResultSetMetaData metaDatos = rs.getMetaData();

    			// Se obtiene el numero de columnas.
    			numeroColumnas = metaDatos.getColumnCount();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
			return numeroColumnas;
		}

		public String getColumnName(int col) {
			

			return nomCol[col];
		}

		public Object getValueAt(int row, int col) {
			/*
			 * poner el grupo de la celda seleccionada
			 * 
			 * */
			
			return data[row][col];

		}

		public void setValueAt(Object obj, int row, int col) {
			data[row][col]=(String) obj;
			fireTableCellUpdated(row, col); 
		}

		public boolean CellEditable(int row, int col) {
			return true;
		}
		public void addRow(int row, int col) {  

			//Don't know what to do here  
			this.fireTableRowsInserted(row, col);  

		}  

		public void delRow(int row, int col) {  
			//Don't know what to do here  
			this.fireTableRowsDeleted(row, col);  

		}  
	};
	
	
	

	/**
	 * Panel que contiene las tablas del calendario y la posibilidad de consulta de turno
	 * @param mes
	 * @param año
	 * @param panelGral
	 * @return
	 */
    public JPanel /*void*/ crearTabla(final JPanel panelGral){

    	
    	final JPanel panelGrupo= new JPanel();
    	JPanel resultado=new JPanel();
    	
    	AnchorLayout panelLayout = new AnchorLayout();
    	panelGral.setLayout(panelLayout);
    	
    	llenarData();    	    	
    	

		tabla = new JTable(model)/*
									 * { private static final long serialVersionUID = 1L;
									 * 
									 * public void valueChanged(ListSelectionEvent e) { super.valueChanged(e);
									 * checkSelection(false); } }
									 */;
   	    
   	   
    	
    			
    	//tabla.setIntercellSpacing(new Dimension (10,20));
    	
    	//int nroDays=daysMonth[mees];
		
		final RenderTabla miRender = new RenderTabla();
    	final JScrollPane scroll=new JScrollPane();
    	JViewport viewport = new JViewport();
	    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tabla.setDefaultRenderer(Object.class, miRender);//
	    
	    viewport.setPreferredSize(new java.awt.Dimension(65, 83));
    	scroll.setViewportView(tabla);
    	panelGrupo.add(agente.diaSeleccionadoVacio(panelGrupo,tabla),BorderLayout.CENTER);


    	tabla.setPreferredSize(new java.awt.Dimension(1080, 66));
    	panelGral.add(scroll, new AnchorConstraint(100, 989, 390, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
    	scroll.setPreferredSize(new java.awt.Dimension(885, 144));
    	scroll.setRowHeaderView(viewport);
    	panelGral.add(resultado, new AnchorConstraint(400, 652, 920, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
    	resultado.setPreferredSize(new java.awt.Dimension(178, 197));
    	panelGral.add(agente.diaSeleccionadoVacio(panelGrupo,tabla), new AnchorConstraint(400, 480, 960, 95, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
    	
    	return panelGral;
    }

       
    
    
    public void llenarData(){

    	tabla=new JTable(model);
    	
    	try{
    		AgenteGsi agente=new AgenteGsi();
    		rs = agente.getDatosAgentes();
    		int numeroFila = 0;
    		// Para cada registro de resultado en la consulta 
    		while (rs.next())
    		{
    			// Se crea y rellena la fila para el modelo de la tabla.
    			ResultSetMetaData metaDatos = rs.getMetaData();

    			// Se obtiene el numero de columnas.
    			int numeroColumnas = metaDatos.getColumnCount();
    			Object[] datosFila = new Object[10];


    			for (int i = 0; i < numeroColumnas; i++){
    				datosFila[i] = rs.getObject(i + 1);

    			}

    			Object[] datosUtiles={datosFila[1],datosFila[3],datosFila[7],datosFila[8],datosFila[9]};


    			


    			for (int i = 0; i < 5; i++){

    				//data[numeroFila][i]=datosUtiles.toString(); 
    				//tabla.setValueAt((String)datosUtiles, numeroFila, i);

    				System.out.println(tabla.getValueAt(numeroFila, i));
    				}


    			numeroFila++;
    		}
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}


    }
    
    
    
    public void actualizarTablaM(AbstractTableModel modelo,final JTable tabla,JScrollPane scroll,RenderTabla miRender,final JPanel panelGral, final JPanel panelGrupo ){
    	
    	llenarData();  
    	miRender = new RenderTabla();
    	
    	modelo = new AbstractTableModel() {
    		private static final long serialVersionUID = 1L;

    		public int getColumnCount() {
    			
    			return 5;
    		}

    		public int getRowCount() {
    			int numeroColumnas=0;
    			try {
    				AgenteGsi agente=new AgenteGsi();
    	    		rs = agente.getDatosAgentes();
    	    		ResultSetMetaData metaDatos = rs.getMetaData();

        			// Se obtiene el numero de columnas.
        			numeroColumnas = metaDatos.getColumnCount();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
    			
    			
    			
    			return numeroColumnas;
    		}

    		public String getColumnName(int col) {
    			

    			return nomCol[col];
    		}

    		public Object getValueAt(int row, int col) {
    			/*
    			 * poner el grupo de la celda seleccionada
    			 * 
    			 * */
    			
    			return data[row][col];

    		}

    		public void setValueAt(Object obj, int row, int col) {
    			data[row][col]=(String) obj;
    			fireTableCellUpdated(row, col); 
    		}

			public boolean CellEditable(int row, int col) {
    			return true;
    		}
    		public void addRow(int row, int col) {  

    			//Don't know what to do here  
    			this.fireTableRowsInserted(row, col);  

    		}  

    		public void delRow(int row, int col) {  
    			//Don't know what to do here  
    			this.fireTableRowsDeleted(row, col);  

    		}  
    	};
    	
    	tabla.setModel(modelo);/*{
   	    	private static final long serialVersionUID = 1L;

   	    	public void valueChanged(ListSelectionEvent e) {
   	    		super.valueChanged(e);
   	    		checkSelection(false);
   	    	}
   	    };*/
    	tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    	tabla.setDefaultRenderer(Object.class, miRender);
    	
    	tabla.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) 
		      {
	    		AgenteGsi agentesillo=new AgenteGsi();
	    		agentesillo.leerAgente((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
				panelGral.add(agentesillo.ficha(panelGrupo, pestania), new AnchorConstraint(400, 480, 1000, 95, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		    	panelGral.repaint();
		    	pestania.repaint();
		      }
		});

    	tabla.setPreferredSize(new java.awt.Dimension(1080, 66));
    	scroll.setViewportView(tabla);
    	JViewport viewport = new JViewport();

	    viewport.setPreferredSize(new java.awt.Dimension(65, 83));
	    scroll.setRowHeaderView(viewport);
    	

    }


	
	/**
	 * Pinta la celda seleccionada en el calendario laboral
	 * @author July_ar
	 *
	 */
	class RenderTabla implements TableCellRenderer{
		public Component getTableCellRendererComponent(JTable tabla, Object value,boolean isSelected, boolean hasFocus, int row, int col) {
			// TODO Auto-generated method stub
			JLabel etiqueta = new JLabel();

			if (tabla.getColumnName(0).equals(" ")&& col==0)//Name().equals("Fija")Count()==1
			{
				// Para que el JLabel haga caso al color de fondo, tiene que ser opaco.
				etiqueta.setOpaque(true);
				etiqueta.setText(value.toString());
				etiqueta.setBackground(Color.LIGHT_GRAY);
			} else{
				/*etiqueta.setOpaque(true);
				etiqueta.setBackground(Color.WHITE);*/
				etiqueta.setHorizontalAlignment(JLabel.CENTER);
				etiqueta.setText(value.toString());
			}

			if ((isSelected && hasFocus)/*||tabla.isCellSelected(row, col)row==2*/){
				//System.out.println("row: "+row+ " col:" +col);
				
				etiqueta.setOpaque(true);
				etiqueta.setText(value.toString());
				etiqueta.setBackground(Color.LIGHT_GRAY);
				/*RenderResultadoTabla otroR=new RenderResultadoTabla();
				otroR.getTableCellRendererComponent(tabla, value, isSelected, hasFocus, row+1, col+1);
				tabla.setDefaultRenderer(Object.class, otroR);/**/
			}

			return etiqueta;
		}
	}
	
	/**
	 * Pinta el dia consultado
	 * @author July_ar
	 *
	 */
	class RenderResultadoTabla implements TableCellRenderer{
		public Component getTableCellRendererComponent(JTable tabla, Object value,boolean isSelected, boolean hasFocus, int row, int col) {
			// TODO Auto-generated method stub
			row+=1;
			col+=1;
			JLabel etiqueta = new JLabel();

			//Tabla fija, la que contiene los horarios
			if (tabla.getColumnName(0).equals(" ")&& col==0)//Name().equals("Fija")Count()==1
			{
				// Para que el JLabel haga caso al color de fondo, tiene que ser opaco.
				etiqueta.setOpaque(true);
				etiqueta.setText(value.toString());
				etiqueta.setBackground(Color.LIGHT_GRAY);
			} else{
				/*etiqueta.setOpaque(true);
				etiqueta.setBackground(Color.WHITE);*/
				etiqueta.setHorizontalAlignment(JLabel.CENTER);
				etiqueta.setText(value.toString());
			}

			//Tabla que muestra los dias
			if (isSelected && hasFocus){//
				System.out.println("row: "+row+ " col:" +col);
				etiqueta.setOpaque(true);
				etiqueta.setText(value.toString());
				etiqueta.setBackground(Color.YELLOW);
			}
			//if (tabla.)

			return etiqueta;
		}
	}
	
	


}
