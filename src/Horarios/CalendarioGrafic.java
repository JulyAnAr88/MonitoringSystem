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
public class CalendarioGrafic extends JFrame{
	
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCalendar;
    private JPanel pnlDays;
    private JLabel lblDays[];
    private JTabbedPane pestania;
    
    /**
     * Paso como referencia la pestaña adonde va a ir el calendario para poder actualizarla
     * @param pestaña
     */
    public CalendarioGrafic(JTabbedPane pestaña){
    	pestania=pestaña;
    }
    
    private GregorianCalendar calendar;    
	private GregorianCalendar select=(GregorianCalendar) Calendar.getInstance();
	
    private static int mees;
	private static int anio;
	
	private JTable tabla, tablaFija, tabla08, tablaFija08;
    
    private AgenteGsi agente=new AgenteGsi();
    private AgenteCerocho ag08=new AgenteCerocho();

    private final String days[] = { 
    		"Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"
    };
    
    private final String months[] = {
            "Enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", 
            "agosto", "septiembre", "octubre", "noviembre", "diciembre"
    };

    public final static int daysMonth[] = {
    		31, 28, 31, 30, 31, 30, 
    		31, 31, 30, 31, 30, 31
    };
    
    private final String columFija08[] = {
    	"Guardia", "Agente"	
    };
    
    
    private final String[][] data= new String[4][40];
    Object [] column;
	private JLabel labelMesEx;
	private JComboBox mesExport;
	private JComboBox añoExport;
	private JComboBox jComboBoxGuardia;
	private JLabel labelAñoExp;
	private JLabel labelSelTab;
	
	

	/**
	 * Panel que contiene las tablas del calendario y la posibilidad de consulta de turno
	 * @param mes
	 * @param año
	 * @param panelGral
	 * @return
	 */
    public JPanel /*void*/ calendario(final JPanel panelGral){

    	CalendarioGrafic.mees=select.get(Calendar.MONTH);
    	CalendarioGrafic.anio=select.get(Calendar.YEAR);
    	Calendar cal=Calendar.getInstance();
    	//System.out.println(cal.getTime());

    	//final JPanel panelGral= new JPanel();
    	//JPanel panelTrabajo= new JPanel();
    	final JPanel panelGrupo= new JPanel();
    	JPanel resultado=new JPanel();
    	
    	AnchorLayout panelLayout = new AnchorLayout();
    	panelGral.setLayout(panelLayout);
    	panelGral.setBorder(BorderFactory.createTitledBorder(
    			BorderFactory.createTitledBorder("Calendario laboral y consultas relacionadas")));
    	panelGral.setPreferredSize(new java.awt.Dimension(484, 80));
    	/*panelTrabajo.setPreferredSize(new java.awt.Dimension(356, 174));
    	panelTrabajo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder(Calendario.nombreMes(select.get(Calendar.MONTH)))));
    	panelTrabajo.add(agente.trabajoQuest(resultado),BorderLayout.CENTER);*/
    	
    	//panelGrupo.setPreferredSize(new java.awt.Dimension(122, 85));
    	
    	llenarData();    	

    	AbstractTableModel fixedModel = new AbstractTableModel() {

    		private static final long serialVersionUID = 1L;
    		public int getColumnCount() {
    			return 1;
    		}

    		public int getRowCount() {
    			return 4;
    		}

    		public String getColumnName(int col) {
    			return " ";
    		}

    		public Object getValueAt(int row, int col) {
    			Turno tur=new Turno(row+1);
    			return tur;
    		}
    	};
    	
    	
    	AbstractTableModel fixedModel08 = new AbstractTableModel() {

    		private static final long serialVersionUID = 1L;
    		public int getColumnCount() {
    			return 1;
    		}

    		public int getRowCount() {
    			return 2;
    		}

    		public String getColumnName(int col) {
    			return " ";
    		}

    		public Object getValueAt(int row, int col) {
    			String tur=new String();
    			tur=columFija08[row];
    			return tur;
    		}
    	};

    	final AbstractTableModel model = new AbstractTableModel() {
    		private static final long serialVersionUID = 1L;

    		public int getColumnCount() {
    			int nroDays=daysMonth[mees];
    			calendar=new GregorianCalendar(anio, mees,1);
    			if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
    				nroDays++;
    			}
    			return nroDays;
    		}

    		public int getRowCount() {
    			return 4;
    		}

    		public String getColumnName(int col) {
    			int nroDays=daysMonth[mees];

    			//agrega nombre de dia y numero 
    			calendar=new GregorianCalendar(anio, mees,col);
    			if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
    				nroDays++;
    			}
    			String nomDia=CalendarioGrafic.letraDia(calendar.get(Calendar.DAY_OF_WEEK));

    			return nomDia+" "+(col+1);
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
    	
    	final AbstractTableModel model08 = new AbstractTableModel() {
    		private static final long serialVersionUID = 1L;

    		public int getColumnCount() {
    			int nroDays=daysMonth[mees];
    			calendar=new GregorianCalendar(anio, mees,1);
    			if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
    				nroDays++;
    			}
    			return nroDays;
    		}

    		public int getRowCount() {
    			return 2;
    		}

    		public String getColumnName(int col) {
    			int nroDays=daysMonth[mees];

    			//agrega nombre de dia y numero 
    			calendar=new GregorianCalendar(anio, mees,col);
    			if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
    				nroDays++;
    			}
    			String nomDia=CalendarioGrafic.letraDia(calendar.get(Calendar.DAY_OF_WEEK));

    			return nomDia+" "+(col+1);
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

   	    tabla=new JTable(model){
   	    	private static final long serialVersionUID = 1L;

   	    	public void valueChanged(ListSelectionEvent e) {
   	    		super.valueChanged(e);
   	    		checkSelection(false);
   	    	}
   	    };
   	    
   	    tabla08=new JTable(model08){
   	    	private static final long serialVersionUID = 1L;

   	    	public void valueChanged(ListSelectionEvent e) {
   	    		super.valueChanged(e);
   	    		checkSelection(false);
   	    	}
   	    };
   	    
   	    tablaFija=new JTable(fixedModel){
   	    	private static final long serialVersionUID = 1L;

   	    	public void valueChanged(ListSelectionEvent e) {
   	    		super.valueChanged(e);
  	        checkSelection(true);
  	      }
  	    };
  	    
  	    tablaFija08=new JTable(fixedModel08){
   	    	private static final long serialVersionUID = 1L;

   	    	public void valueChanged(ListSelectionEvent e) {
   	    		super.valueChanged(e);
  	        checkSelection(true);
  	      }
  	    };
    	
    	TableColumn columnaNom=tablaFija.getColumn(" ");
		columnaNom.setPreferredWidth(100);
		
		TableColumn columnaNom08=tablaFija08.getColumn(" ");
		columnaNom08.setPreferredWidth(100);
		
    	//tabla.setIntercellSpacing(new Dimension (10,20));
    	
    	//int nroDays=daysMonth[mees];
		tablaFija.setName("Fija");
		final RenderTabla miRender = new RenderTabla();
    	final JScrollPane scroll=new JScrollPane();
    	JViewport viewport = new JViewport();
    	tablaFija.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	//tablaFija.setOpaque(true);
    	tablaFija.setDefaultRenderer(Object.class, miRender);
	    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tabla.setDefaultRenderer(Object.class, miRender);//
	    
	    viewport.setView(tablaFija);
	    viewport.setPreferredSize(new java.awt.Dimension(65, 83));
    	scroll.setViewportView(tabla);
    	panelGrupo.add(agente.diaSeleccionadoVacio(panelGrupo,tabla),BorderLayout.CENTER);

    	tablaFija.setPreferredSize(new java.awt.Dimension(65, 66));
    	tabla.setPreferredSize(new java.awt.Dimension(1080, 66));
    	panelGral.add(scroll, new AnchorConstraint(100, 989, 390, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
    	scroll.setPreferredSize(new java.awt.Dimension(885, 144));
    	scroll.setRowHeaderView(viewport);
	    scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, tablaFija.getTableHeader());
    	panelGral.add(resultado, new AnchorConstraint(400, 652, 920, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
    	resultado.setPreferredSize(new java.awt.Dimension(178, 197));
    	panelGral.add(agente.diaSeleccionadoVacio(panelGrupo,tabla), new AnchorConstraint(400, 480, 960, 95, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
    	
    	
    	{
			labelSelTab = new JLabel();
			panelGral.add(labelSelTab, new AnchorConstraint(32, 64, 93, 15, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
	    	labelSelTab.setText("Guardia:");
	    	labelSelTab.setPreferredSize(new java.awt.Dimension(28, 25));
		}
		
		
		{
			jComboBoxGuardia = new JComboBox();
			panelGral.add(jComboBoxGuardia, new AnchorConstraint(41, 202, 95, 74, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
			jComboBoxGuardia.addItem("Monitoreo");
			jComboBoxGuardia.addItem("0800");

			//jComboBoxGuardia.setSelectedItem(agentesillo.getNombreGrupo().toUpperCase());
			//jComboBoxGuardia.setPreferredSize(new java.awt.Dimension(125, 17));
			jComboBoxGuardia.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println(jComboBoxGuardia.getSelectedIndex());
					switch (jComboBoxGuardia.getSelectedIndex()) {
					case 0:
						actualizarTablaM(model, tabla, tablaFija,scroll, miRender,panelGral, panelGrupo);
						break;
					case 1:
						actualizarTabla08(model08, tabla08, tablaFija08,scroll, miRender,panelGral, panelGrupo);
					default:
						break;
					}
				}
			});
			
		}
		
    	    	
    	{
			labelMesEx = new JLabel();
			panelGral.add(labelMesEx, new AnchorConstraint(32, 679, 93, 637, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
	    	labelMesEx.setText("Mes:");
	    	labelMesEx.setPreferredSize(new java.awt.Dimension(33, 23));
		}
		{
			mesExport=new JComboBox();
			mesExport.setPreferredSize(new java.awt.Dimension(115, 17));
			panelGral.add(mesExport, new AnchorConstraint(40, 841, 85, 694, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
	    	for (int i = 0; i < 12; i++) {
				mesExport.addItem(CalendarioGrafic.nombreMesM(i));
			}
			mesExport.setSelectedItem(CalendarioGrafic.nombreMesM(select.get(Calendar.MONTH)));
			mesExport.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					mees=mesExport.getSelectedIndex();
					switch (jComboBoxGuardia.getSelectedIndex()) {
					case 0:
						actualizarTablaM(model, tabla, tablaFija,scroll, miRender,panelGral, panelGrupo);
						break;
					case 1:
						actualizarTabla08(model08, tabla08, tablaFija08,scroll, miRender,panelGral, panelGrupo);
					default:
						break;

					}
				}
			});
			
		}
		{
			añoExport = new JComboBox();
			añoExport.setPreferredSize(new java.awt.Dimension(53, 17));
			panelGral.add(añoExport, new AnchorConstraint(40, 972, 85, 904, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
	    	for (int i = 2000; i < 2050; i++) {
				añoExport.addItem(i);
			}
			añoExport.setSelectedItem(select.get(Calendar.YEAR));
			añoExport.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(añoExport.getSelectedIndex());
					anio=añoExport.getSelectedIndex()+2000;

					switch (jComboBoxGuardia.getSelectedIndex()) {
					case 0:
						actualizarTablaM(model, tabla, tablaFija,scroll, miRender,panelGral, panelGrupo);
						break;
					case 1:
						actualizarTabla08(model08, tabla08, tablaFija08,scroll, miRender,panelGral, panelGrupo);
					default:
						break;

					}
				}
			});
		}
		{
			labelAñoExp = new JLabel();
			panelGral.add(labelAñoExp, new AnchorConstraint(32, 897, 93, 856, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
	    	labelAñoExp.setText("Año:");
	    	labelAñoExp.setPreferredSize(new java.awt.Dimension(32, 23));
		}
		
		
		tabla.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) 
		      {
	    		AgenteGsi agentesillo=new AgenteGsi();
				panelGral.add(agentesillo.diaSeleccionado(panelGrupo, tabla), new AnchorConstraint(400, 480, 960, 95, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		    	panelGral.repaint();
		    	pestania.repaint();
		      }
		});
		//panelGral.add(agente.trabajoQuest(resultado,pestania, tabla,mesExport,añoExport), new AnchorConstraint(400, 420, 920, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
    	


    	/* getContentPane().add(panelGral, BorderLayout.CENTER);
    	this.setSize(912, 619);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
    	pack();*/
    	return panelGral;
    }

    
    
   
    
    private void checkSelection(boolean isFixedTable) {
	    int fixedSelectedIndex = tablaFija.getSelectedRow();
	    int selectedIndex = tabla.getSelectedRow();
	    System.out.println(tabla.getSelectedRow());
	    if (fixedSelectedIndex != selectedIndex) {
	      if (isFixedTable) {
	        tabla.setRowSelectionInterval(fixedSelectedIndex,
	            fixedSelectedIndex);
	      } else {
	       // tablaFija.setRowSelectionInterval(selectedIndex, selectedIndex);
	      }
	    }
	  }
    
    public void llenarData(){
    	int nroDays=daysMonth[mees];

		calendar=new GregorianCalendar(anio, mees,1);
    	
    	if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
            nroDays++;
        }
    	for ( int i= 0; i < nroDays; i++) {
    		for (int turno=0; turno < 4; turno++) {
    			calendar=new GregorianCalendar(anio, mees,i+1);
    			Date fecha=calendar.getTime();
    			DiaLaboral diaL=new DiaLaboral();
    			data[turno][i]=diaL.calcularGrupo(fecha, turno);
    		}
    	}
    }
    
    public void llenarData08(){
    	int nroDays=daysMonth[mees];

		calendar=new GregorianCalendar(anio, mees,1);
    	
    	if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
            nroDays++;
        }
    	
    	for ( int i= 0; i < nroDays; i++) {
    		for (int turno=0; turno < 1; turno++) {
    			calendar=new GregorianCalendar(anio, mees,i+1);
    			Date fecha=calendar.getTime();
    			DiaLab08 diaL=new DiaLab08();
    			data[turno][i]=diaL.calcularGrupo(fecha);
    		}
    	}    	
    	
    	for ( int i= 0; i < nroDays; i++) {
    		for (int j=1; j < 3; j++) {
    			calendar=new GregorianCalendar(anio, mees,i+1);
    			Date fecha=calendar.getTime();
    			DiaLab08 diaL=new DiaLab08();
    			data[j][i]=diaL.calcularSubGrupo(fecha, j-1);
    		}
    	}
    }
    
    
public void actualizarTabla08(AbstractTableModel modelo,final JTable tabla,JTable tablaFija,JScrollPane scroll,RenderTabla miRender,final JPanel panelGral, final JPanel panelGrupo){
    	
    	llenarData08();  
    	miRender = new RenderTabla();
    	
    	modelo = new AbstractTableModel() {
    		private static final long serialVersionUID = 1L;

    		public int getColumnCount() {
    			int nroDays=daysMonth[mees];
    			calendar=new GregorianCalendar(anio, mees,1);
    			if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
    				nroDays++;
    			}
    			return nroDays;
    		}

    		public int getRowCount() {
    			return 2;
    		}

    		public String getColumnName(int col) {
    			int nroDays=daysMonth[mees];

    			//agrega nombre de dia y numero 
    			calendar=new GregorianCalendar(anio, mees,col);
    			if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
    				nroDays++;
    			}
    			String nomDia=CalendarioGrafic.letraDia(calendar.get(Calendar.DAY_OF_WEEK));

    			return nomDia+" "+(col+1);
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
	    		AgenteCerocho agentesillo=new AgenteCerocho();
				panelGral.add(agentesillo.diaSeleccionado(panelGrupo, tabla), new AnchorConstraint(400, 480, 1000, 95, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		    	panelGral.repaint();
		    	pestania.repaint();
		      }
		});

    	tabla.setPreferredSize(new java.awt.Dimension(1080, 66));
    	scroll.setViewportView(tabla);
    	JViewport viewport = new JViewport();

    	tablaFija.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	//tablaFija.setOpaque(true);
    	tablaFija.setDefaultRenderer(Object.class, miRender);
    	viewport.setView(tablaFija);
	    viewport.setPreferredSize(new java.awt.Dimension(65, 83));
	    scroll.setRowHeaderView(viewport);
	    scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, tablaFija.getTableHeader());

    	//vaciaFilasModelo(modelo);

    }
    
    
    
    public void actualizarTablaM(AbstractTableModel modelo,final JTable tabla,JTable tablaFija,JScrollPane scroll,RenderTabla miRender,final JPanel panelGral, final JPanel panelGrupo){
    	
    	llenarData();  
    	miRender = new RenderTabla();
    	
    	modelo = new AbstractTableModel() {
    		private static final long serialVersionUID = 1L;

    		public int getColumnCount() {
    			int nroDays=daysMonth[mees];
    			calendar=new GregorianCalendar(anio, mees,1);
    			if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
    				nroDays++;
    			}
    			return nroDays;
    		}

    		public int getRowCount() {
    			return 4;
    		}

    		public String getColumnName(int col) {
    			int nroDays=daysMonth[mees];

    			//agrega nombre de dia y numero 
    			calendar=new GregorianCalendar(anio, mees,col);
    			if (mees == Calendar.FEBRUARY && calendar.isLeapYear(anio)) {
    				nroDays++;
    			}
    			String nomDia=CalendarioGrafic.letraDia(calendar.get(Calendar.DAY_OF_WEEK));

    			return nomDia+" "+(col+1);
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
				panelGral.add(agentesillo.diaSeleccionado(panelGrupo, tabla), new AnchorConstraint(400, 480, 1000, 95, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		    	panelGral.repaint();
		    	pestania.repaint();
		      }
		});

    	tabla.setPreferredSize(new java.awt.Dimension(1080, 66));
    	scroll.setViewportView(tabla);
    	JViewport viewport = new JViewport();

    	tablaFija.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	//tablaFija.setOpaque(true);
    	tablaFija.setDefaultRenderer(Object.class, miRender);
    	viewport.setView(tablaFija);
	    viewport.setPreferredSize(new java.awt.Dimension(65, 83));
	    scroll.setRowHeaderView(viewport);
	    scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, tablaFija.getTableHeader());

    	

    }


	public static int getMes(){
		return mees;
	}
	
	public static int getAño(){
		return anio;
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
	
	public static String nombreMes(int nroMes){
		switch (nroMes){
		case (0):
			return "enero";
		case (1):
			return "febrero";
		case (2):
			return "marzo";
		case (3):
			return "abril";
		case (4):
			return "mayo";
		case (5):
			return "junio";
		case (6):
			return "julio";
		case (7):
			return "agosto";
		case (8):
			return "septiembre";
		case (9):
			return "octubre";
		case (10):
			return "noviembre";
		case (11):
			return "diciembre";
		default:
			return " ";
		}		
	}
	
	public static String nombreMesM(int nroMes){
		switch (nroMes){
		case (0):
			return "Enero";
		case (1):
			return "Febrero";
		case (2):
			return "Marzo";
		case (3):
			return "Abril";
		case (4):
			return "Mayo";
		case (5):
			return "Junio";
		case (6):
			return "Julio";
		case (7):
			return "Agosto";
		case (8):
			return "Septiembre";
		case (9):
			return "Octubre";
		case (10):
			return "Noviembre";
		case (11):
			return "Diciembre";
		default:
			return " ";
		}		
	}
	
	
	public static String letraDia(int nroDia){
		switch (nroDia){
		case (1):
			return "L";
		case (2):
			return "M";
		case (3):
			return "M";
		case (4):
			return "J";
		case (5):
			return "V";
		case (6):
			return "S";
		case (7):
			return "D";
		default:
			return " ";
		}		
	}
	
	
	
	/*public static void main(String[] args) {
    	String arg0=(String)JOptionPane.showInputDialog(null,"ingrese el mes","Set Calendar",
				JOptionPane.INFORMATION_MESSAGE,null,null,null);
    	int mes= (Integer.parseInt(arg0)-1);
    	String arg1=(String)JOptionPane.showInputDialog(null,"ingrese el año","Set Calendar",
				JOptionPane.INFORMATION_MESSAGE,null,null,null);
    	int anio= Integer.parseInt(arg1);
    	JTabbedPane panel=new JTabbedPane();
        CalendarioGrafic cal=new CalendarioGrafic(panel);
        cal.calendario(11,2011);
    }*/
    
	
	/* public void calendario(int month, int year) {
    int offset = 0;
    int numDays = 0;
    int today = 0;
    
    *//**
     * GregorianCalendar es una subclase de Calendar, por lo que nos 
     * proporciona toda la funcionalidad de esta clase y ademas nos brinda 
     * algunos metodos mas. En este caso estamos iniciando dos 
     * GregorianCalendar utilizando diferentes constructores. 
     *
     * currCal se inicia con Calendar.getInstance() que nos devuelve un 
     * Calendar fijado en el dia de hoy. 
     * cal se inicia con el constructor que recibe la fecha 
     *//*
    GregorianCalendar currCal = (GregorianCalendar) Calendar.getInstance();
    GregorianCalendar cal = new GregorianCalendar(year, month, 1);
    
    *//** 
     * En caso de que el mes del calendario que iniciamos con la fecha 
     * actual sea el mismo que el que iniciamos con la fecha pedida 
     * entonces cambiamos el dia de hoy
     *//*
    if (currCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH) && 
        currCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) {
        
        today = currCal.get(Calendar.DAY_OF_MONTH);
    }
    
    *//** 
     * Obtengo el numero de dias del arreglo 
     *//*
    numDays = daysMonth[month];
    
    *//** Si es biciesto y es febrero le sumo uno al numero de dias *//*
    if (month == Calendar.FEBRUARY && cal.isLeapYear(year)) {
        numDays++;
    }
    
    *//** 
     * Obtengo el numero de dias que van a ir en blanco.
     * Al llamar el metodo get de la siguiente manera 
     * get(Calendar.DAY_OF_WEEK) nos va a devolver un 0 para domingo, un 1 
     * para lunes y un 6 para sabado, por lo que es el numero de dias que 
     * debemos dejar en blanco directamente
     *//*
    offset = cal.get(Calendar.DAY_OF_WEEK);
    
    *//** 
     * El GridLayout en realidad no le hace caso mas que a uno de los dos 
     * parametros que recibe y en el otro se expande hasta que entren todos 
     * los controles que se agreguen. Para indicarle cual de los dos debe 
     * despreciar ponemos un cero, lo que interpreta Java como expande este 
     * lado hasta que entren todos los controles.
     *//*
    this.setLayout(new BorderLayout());
    pnlDays = new JPanel(new GridLayout(0, 7));
    
    lblCalendar = new JLabel(months[month] + " " + year);
    lblCalendar.setIcon(new ImageIcon(Calendario.class.getResource("C:\\Documents and Settings\\Administrador\\Mis documentos\\Documentos del Lic. Aragón\\Java\\calendario\\date.png")));
    lblCalendar.setFont(new Font("Arial", Font.BOLD, 32));
    getContentPane().add(lblCalendar, BorderLayout.NORTH);
    
    lblDays = new JLabel[numDays];
    
    *//** 
     * Utilizo n para que nuestros amigos franceses vean que la semana 
     * empieza en lunes y nosotros veamos que empieza en domingo.
     *//*
    int n = 0;
    if (cal.getFirstDayOfWeek() == Calendar.SUNDAY) 
        n++;
    
    for (int i = 0; i < days.length; i++) {
        *//** 
         * Utilizo mod para que me regrese numeros del 0 al 6 que esten 
         * dentro del arreglo de dias 
         *//*
        JLabel lblDayName = new JLabel( days[n++ % 7] );
        lblDayName.setFont( new Font("Arial", Font.BOLD, 13) );
        lblDayName.setHorizontalAlignment(JLabel.CENTER);
        pnlDays.add(lblDayName);
    }
    
    *//** Por cada dia en que todavia no empieza el mes *//*
    for (int i = 0; i < offset - 1; i++) {
        *//** Agrego un JLabel en blanco *//*
        pnlDays.add(new JLabel());
    }
    
    *//* Por cada dia del mes *//*
    for (int i = 0; i < numDays; i++) {
        *//*Creo un nuevo JLabel que tenga como texto el valor de i + 1, 
         * como i va desde cero hasta el numero de dias menos uno al 
         * sumarle uno a i obtenemos el numero de dia real.
         *//*
    	String diaD=("00 a 06 | A           ");
    	String diaD1=("06 a 12 | B			" +
    			"12 a 18 | C"
    			+"18 a 24 | D");
        lblDays[i] = new JLabel( diaD+ String.valueOf(i + 1));
        
        
		
		display[2]= new JLabel();
		
		display[3]= new JLabel("B");
		
		display[4]= new JLabel("12 a 18");
		
		display[5]= new JLabel("C");
		
		display[6]= new JLabel("18 a 24");
		
		display[7]= new JLabel("D");
		
        lblDays[i].setHorizontalAlignment(JLabel.CENTER);
        
        *//*
         * Podemos agregar un borde utilizando el metodo setBorder y la 
         * clase BorderFactory, que ademas de este metodo tiene varios 
         * otros metodos que crean bordes diferentes.
         *//*
        lblDays[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
        *//* Si es el dia de hoy *//*
        if (i + 1 == today) {
            *//* Cambio el color a rojo *//*
            lblDays[i].setOpaque(true);
            lblDays[i].setBackground(new Color(255, 200, 200));
        }
        
        *//* Si es mi cumple *//*
        if (i + 1 == 23 && month == Calendar.SEPTEMBER) {
            
            *//* Pongo un pastel y cambio el color a verde *//*
            lblDays[i].setIcon(new ImageIcon(Calendario.class.getResource("C:\\Documents and Settings\\Administrador\\Mis documentos\\Documentos del Lic. Aragón\\Java\\calendario\\cake.png")));
            
            lblDays[i].setOpaque(true);
            lblDays[i].setBackground(new Color(200, 255, 200));
        }
        
        pnlDays.add(lblDays[i]);
    }
    *//* Cambio el color del panel a blanco para que parezca calendario *//*
    pnlDays.setBackground(Color.WHITE);
    getContentPane().add(pnlDays, BorderLayout.CENTER);
    
    this.setSize(950, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
}
*/


}
