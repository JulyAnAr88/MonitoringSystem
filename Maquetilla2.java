package Horarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
* Interfaz del Monitoring System
* 
* @author July_Ar
*/

public class Maquetilla extends JFrame{

	private static final long serialVersionUID = 1L;
	final static boolean shouldFill = false;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	
	private JPanel panel;
	
	private JMenuBar menuBarra= new JMenuBar();
	
	private JMenu menuArchivo;
	private JMenuItem nuevo;
	private JMenuItem cerrar;
	
	private JMenu menuAyuda;
	private JMenuItem itemAcerca;
	
	private JMenu menuHerramientas;
	private JMenuItem configurar;
	
	private JMenu menuVer;
	private JMenuItem itemHorario;
	private JMenuItem itemAgente;
	private JMenuItem itemContactos;
	
	private JTabbedPane contenedorPestanias;
	
	private JTabbedPane pestaniaHorarios;
	private JLabel labelAñoExp;
	private JLabel labelMesEx;
	private JPanel panleConsultaH;
	private JComboBox mesExport;
	private JComboBox añoExport;
	private JButton exportMes;
	
	private JTabbedPane pestaniaAgentes;
	private JTable tablaAgentes;
	private ModeloNoEditable modeloTablaAgente = new ModeloNoEditable();
	private JScrollPane scrollTabla;
	private JTextField textBuscarAg;
	private JButton botonBuscarAg;
	private JButton agregarAg;
	private JButton botonTrabajaAg;
	private JButton botonModificarAg;
	private JButton botonBorrarAg;
	private JButton botonVer;
		
	private JTabbedPane pestaniaContactos;
	private JButton botonBuscarCt;
	private JTextField textBuscarCt;
	private JButton agregarCt;
	private JButton botonModificarCt;
	private JButton botonBorrarCt;	
	private JTable tablaCt;
	private ModeloNoEditable modeloTablaCt = new ModeloNoEditable();
	private JScrollPane scrollCt;
	
	GregorianCalendar select=(GregorianCalendar) Calendar.getInstance();
	
	private ResultSet rs;

	AgenteGsi agente=new AgenteGsi();
	Contacto contacto=new Contacto();
	
	//Set Look & Feel
	{
		
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Maquetilla(){
		super("Monitoring System - Beta 0.5");
		correGUI();
	}
	
	
	
	public DefaultTableModel getModelAgentes(){
        
		 modeloTablaAgente.addColumn("Nombre y Apellido");
	        modeloTablaAgente.addColumn("Dirección");
	        modeloTablaAgente.addColumn("Teléfono");
	        modeloTablaAgente.addColumn("E-mail");
	        modeloTablaAgente.addColumn("Grupo");
        
        return modeloTablaAgente;
    }
	
	/*public void cargarTablaAgente(AgenteGsi agente,Grupo grupo){
		AgenteGsiDAO.leerAgente(agente,grupo,"Julian");
		agente.setGrupo(grupo);
		String[] datos1 = new String[]{agente.getNombreyApellido(), agente.getDireccion(),agente.getTelefono(), agente.getNombreGrupo()};
		modeloTablaAgente.addRow(datos1);
		
	}
	
	public void cargarTablaAgente(AgenteGsi agente,Grupo grupo,int idAg){
		for (int i = 0; i < 12; i++) {
			AgenteGsiDAO.leerAgente(agente,grupo,"Julian");
			agente.setGrupo(grupo);
			String[] datos1 = new String[]{agente.getNombreyApellido(), agente.getDireccion(),agente.getTelefono(), agente.getNombreGrupo()};
			modeloTablaAgente.addRow(datos1);
			
		
		}
	}*/
	
	DefaultTableModel modelo = new DefaultTableModel(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private CalendarioGrafic calendario;
	private JPanel panelFicha;
	
	public DefaultTableModel getModelContactos(){
		 
        modeloTablaCt.addColumn("Nombre");
        modeloTablaCt.addColumn("Dirección");
        modeloTablaCt.addColumn("Teléfono");
        modeloTablaCt.addColumn("Comentario");

        return modeloTablaCt;
    }
	
	public void correGUI(){
		try{
			panel= new JPanel(new GridLayout());
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setPreferredSize(new java.awt.Dimension(864, 450));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			
			contenedorPestanias=new JTabbedPane();
			//contenedorPestanias.setPreferredSize(new java.awt.Dimension(504, 564));
			//pestaña Horarios
			pestaniaHorarios =new JTabbedPane();
			calendario=new CalendarioGrafic(pestaniaHorarios);
			pestaniaHorarios.setPreferredSize(new java.awt.Dimension(843, 530));
			pestaniaHorarios.setLayout(new BorderLayout());

			contenedorPestanias.addTab("Horarios", null, pestaniaHorarios, null);		
			{
				AnchorLayout panelLayout = new AnchorLayout();
				JPanel panelH=new JPanel();
				panelH.setLayout(panelLayout);
				{
					JPanel panelExportMes=new JPanel();
					panelExportMes.setLayout(new AnchorLayout());
					panelExportMes.setBorder(BorderFactory.createTitledBorder(
		                    BorderFactory.createTitledBorder("Planillas:")));
					panelH.add(panelExportMes, new AnchorConstraint(400, 920, 955, 490, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					//panelExportMes.setPreferredSize(new java.awt.Dimension(326, 159));
					
					JPanel panExpMon=new JPanel();
					panExpMon.setLayout(new AnchorLayout());
					panExpMon.setBorder(BorderFactory.createTitledBorder(
		                    BorderFactory.createTitledBorder("Monitoreo:")));
					panelExportMes.add(panExpMon, new AnchorConstraint(70, 970, 510, 40, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					
															
					{
						labelMesEx = new JLabel();
						panExpMon.add(labelMesEx, new AnchorConstraint(245, 174, 355, 100, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				    	labelMesEx.setText("Mes:");
				    	labelMesEx.setPreferredSize(new java.awt.Dimension(28, 25));
					}
					{
						mesExport=new JComboBox();
						mesExport.setPreferredSize(new java.awt.Dimension(100, 22));
						panExpMon.add(mesExport, new AnchorConstraint(210, 454, 410, 199, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				    	for (int i = 0; i < 12; i++) {
							mesExport.addItem(CalendarioGrafic.nombreMesM(i));
						}
						
						mesExport.setSelectedItem(CalendarioGrafic.nombreMesM(select.get(Calendar.MONTH)));
					}
					{
						exportMes=new JButton("Exportar");
						panExpMon.add(exportMes, new AnchorConstraint(500, 770, 745, 500, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						exportMes.setPreferredSize(new java.awt.Dimension(96, 20));
				    	exportMes.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								try {
									int mes=mesExport.getSelectedIndex();
									Object añoSelec=añoExport.getSelectedItem();
									int año=(Integer.parseInt(añoSelec.toString()));
									File prueba=new File(System.getProperty( "user.home")+"\\Mis documentos\\Planillas de horarios\\Monitoreo - Planilla horarios mes "+CalendarioGrafic.nombreMes(mes)+" "+año+".pdf");
									if (prueba.exists()){
										//FileOutputStream archivoSalida=new FileOutputStream(prueba);
										Runtime.getRuntime().exec( "rundll32 url.dll, FileProtocolHandler " + prueba );
									}else{
										ExportacionPdf.exportarPdf(mes, año);
									}
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
				    	});
					}
					{
						añoExport = new JComboBox();
						añoExport.setPreferredSize(new java.awt.Dimension(65, 23));
						panExpMon.add(añoExport, new AnchorConstraint(210, 782, 410, 617, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						for (int i = 2000; i < 2050; i++) {
							añoExport.addItem(i);
						}
						añoExport.setSelectedItem(select.get(Calendar.YEAR));
					}
					{
						labelAñoExp = new JLabel();
						panExpMon.add(labelAñoExp, new AnchorConstraint(245, 570, 355, 500, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						labelAñoExp.setText("Año:");
						labelAñoExp.setPreferredSize(new java.awt.Dimension(29, 23));
					}

					JPanel panExp08=new JPanel();
					panExp08.setLayout(new AnchorLayout());
					panExp08.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createTitledBorder("0800:")));
					panelExportMes.add(panExp08, new AnchorConstraint(525, 970, 970, 40, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					
					
					{
						labelMesEx = new JLabel();
						panExp08.add(labelMesEx, new AnchorConstraint(245, 174, 355, 100, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				    	labelMesEx.setText("Mes:");
				    	labelMesEx.setPreferredSize(new java.awt.Dimension(28, 25));
					}
					{
						mesExport=new JComboBox();
						mesExport.setPreferredSize(new java.awt.Dimension(100, 22));
						panExp08.add(mesExport, new AnchorConstraint(210, 454, 410, 199, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				    	for (int i = 0; i < 12; i++) {
							mesExport.addItem(CalendarioGrafic.nombreMesM(i));
						}
						
						mesExport.setSelectedItem(CalendarioGrafic.nombreMesM(select.get(Calendar.MONTH)));
					}
					{
						exportMes=new JButton("Exportar");
						panExp08.add(exportMes, new AnchorConstraint(500, 770, 745, 500, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						exportMes.setPreferredSize(new java.awt.Dimension(96, 20));
				    	exportMes.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								try {
									int mes=mesExport.getSelectedIndex();
									Object añoSelec=añoExport.getSelectedItem();
									int año=(Integer.parseInt(añoSelec.toString()));
									File prueba=new File(System.getProperty( "user.home")+"\\Mis documentos\\Planillas de horarios\\0800 - Planilla horarios mes "+CalendarioGrafic.nombreMes(mes)+" "+año+".pdf");
									if (prueba.exists()){
										//FileOutputStream archivoSalida=new FileOutputStream(prueba);
										Runtime.getRuntime().exec( "rundll32 url.dll, FileProtocolHandler " + prueba );
									}else{
										ExportacionPdf08.exportarPdf(mes, año);
									}
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
				    	});
					}
					{
						añoExport = new JComboBox();
						añoExport.setPreferredSize(new java.awt.Dimension(65, 23));
						panExp08.add(añoExport, new AnchorConstraint(210, 782, 410, 617, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						for (int i = 2000; i < 2050; i++) {
							añoExport.addItem(i);
						}
						añoExport.setSelectedItem(select.get(Calendar.YEAR));
					}
					{
						labelAñoExp = new JLabel();
						panExp08.add(labelAñoExp, new AnchorConstraint(245, 570, 355, 500, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						labelAñoExp.setText("Año:");
						labelAñoExp.setPreferredSize(new java.awt.Dimension(29, 23));
					}

					
				}
						
				
				panleConsultaH = new JPanel();
				//panleConsultaH.setPreferredSize(new java.awt.Dimension(803, 209));
				//panleConsultaH.add(calendario.calendario(select.get(Calendar.MONTH),select.get(Calendar.YEAR)), new AnchorConstraint(0,700, 785, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				calendario.calendario(/*select.get(Calendar.MONTH),select.get(Calendar.YEAR),*/panleConsultaH);
				
				panelH.add(panleConsultaH, new AnchorConstraint(0, 989, 992, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		    	

				//panelH.setPreferredSize(new java.awt.Dimension(414, 384));
				pestaniaHorarios.add( panelH, BorderLayout.SOUTH);
			}
			
			//pestaña Agentes
			{	
				pestaniaAgentes =new JTabbedPane();
				pestaniaAgentes.setLayout(new BorderLayout());
				pestaniaAgentes.setPreferredSize(new Dimension(589, 379));
				
				final JPanel panelA=new JPanel();
				AnchorLayout grupoLayout=new AnchorLayout();
				panelA.setLayout(grupoLayout);
								
				textBuscarAg= new JTextField();
				
				botonBuscarAg=new JButton("Buscar");
				panelA.add(agente.fichaVacia(panelFicha), new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				
								
				tablaAgentes=new JTable(12,7);
				tablaAgentes.setAutoscrolls(true);
				tablaAgentes.setModel(getModelAgentes());
				tablaAgentes.setAutoCreateRowSorter(true);
				tablaAgentes.setAutoCreateColumnsFromModel(true);
				tablaAgentes.setBorder(new LineBorder(new Color(0,0,0), 1, true));
				tablaAgentes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				//tablaAgentes.setCellSelectionEnabled(false);
				tablaAgentes.addMouseListener(new MouseAdapter() 
				   {
				      public void mouseClicked(MouseEvent e) 
				      {
				    	  if(e.getClickCount() == 2) {
				    		  AgenteGsi agentesillo=new AgenteGsi();
								System.out.println((String)tablaAgentes.getValueAt(tablaAgentes.getSelectedRow(), 0));
								agentesillo.leerAgente((String)tablaAgentes.getValueAt(tablaAgentes.getSelectedRow(), 0));
								System.out.println(agentesillo.getLegajo());
								System.out.println(agentesillo.getDireccion());
								System.out.println(agentesillo.getTelefono());
								 panelA.add(agentesillo.ficha(panelFicha,pestaniaAgentes),new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL, 
										 AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
										 AnchorConstraint.ANCHOR_REL)); //panelA.repaint(); } } });
				    	  }
				      }
				   });
				//tablaAgentes.isEditing();
				TableColumn columnaNom=tablaAgentes.getColumn("Nombre y Apellido");
				columnaNom.setPreferredWidth(130);
				TableColumn columnDirec=tablaAgentes.getColumn("Dirección");
				columnDirec.setPreferredWidth(90);
				TableColumn columnaTelefono=tablaAgentes.getColumn("Teléfono");
				columnaTelefono.setPreferredWidth(100);
				TableColumn columnaEmail=tablaAgentes.getColumn("E-mail");
				columnaEmail.setPreferredWidth(130);
				TableColumn columGrupo=tablaAgentes.getColumn("Grupo");
				columGrupo.setPreferredWidth(20);
				
				AgenteGsi agenteD=new AgenteGsi();
				rs=agenteD.getDatosAgentes();
				cargaFilasDeDatos(rs, modeloTablaAgente);
				tablaAgentes.setModel(modeloTablaAgente);
				
				scrollTabla= new JScrollPane();
				scrollTabla.setViewportView(tablaAgentes);
				
				panelA.add(scrollTabla, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				
				
				agregarAg=new JButton("Agregar");
				agregarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						agente.agregarDialog();				
					}
				});
				
				panelA.add(agregarAg, new AnchorConstraint(905, 490, 965, 398, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								
				botonModificarAg=new JButton("Modificar");
				botonModificarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						agente.dialogoModificar(tablaAgentes,scrollTabla);
						actualizarTabla(rs, modeloTablaAgente, tablaAgentes,scrollTabla,agente);
						//scrollTabla.setViewportView(tablaAgentes);
						panelA.add(scrollTabla, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						
					}
				});
				
				panelA.add(botonModificarAg, new AnchorConstraint(905, 586, 965, 492, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				
				botonBorrarAg=new JButton("Borrar");
				botonBorrarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println("nombre: "+(String)tablaAgentes.getValueAt(tablaAgentes.getSelectedRow(), 0));
						agente.borrarAgente((String)tablaAgentes.getValueAt(tablaAgentes.getSelectedRow(), 0));
						actualizarTabla(rs, modeloTablaAgente, tablaAgentes,scrollTabla,agente);
					}
				});
				panelA.add(botonBorrarAg, new AnchorConstraint(905, 680, 965, 588, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				
				textBuscarAg= new JTextField();
				
				botonBuscarAg=new JButton("Buscar");
				
				panelA.add(botonBuscarAg, new AnchorConstraint(16, 788, 57, 704, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				botonBuscarAg.setPreferredSize(new java.awt.Dimension(94, 24));
				botonBuscarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						
						//TablaAgM.cargaFilasBusquedaAgentes(modeloTablaAgente, rs);
						
						actualizarTabla(rs, modeloTablaAgente, tablaAgentes,scrollTabla, AgenteGsi.buscarAgente(textBuscarAg.getText()));
						scrollTabla= new JScrollPane();
						scrollTabla.setViewportView(tablaAgentes);
						
						panelA.add(scrollTabla, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						
						
						/*
						 * switch (sala.getSelectedIndex()) { case 0:
						 * 
						 * tabAg.cargaFilasBusquedaAgentes(modelo,
						 * agentes);actualizarTabla(modeloTablaAgente, tablaAgentes,
						 * scrollTabla,AgenteGsi.buscarAgente(textBuscarAg.getText())); break; case 1:
						 * 
						 * actualizarTablaAg08(modeloTablaAg08, tablaAg08,
						 * scrollTabla,AgenteCerocho.buscarAgente(textBuscarAg.getText()));
						 * 
						 * break; case 2: actualizarTablaCt(modeloTablaCt, tablaCt, scrollCt,
						 * Contacto.buscarCt(textBuscarCt.getText())); break;
						 * 
						 * default: break; }
						 */
												
					}
					
				});
				
				panelA.add(textBuscarAg, new AnchorConstraint(16, 701, 57, 452, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				textBuscarAg.setPreferredSize(new java.awt.Dimension(243, 21));
				textBuscarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
											
						
						actualizarTabla(rs, modeloTablaAgente, tablaAgentes,scrollTabla, AgenteGsi.buscarAgente(textBuscarAg.getText()));
						
						/*
						 * switch (sala.getSelectedIndex()) { case 0:
						 * 
						 * actualizarTabla(modeloTablaAgente, tablaAgentes,
						 * scrollTabla,AgenteGsi.buscarAgente(textBuscarAg.getText())); break; case 1:
						 * 
						 * actualizarTablaAg08(modeloTablaAg08, tablaAg08,
						 * scrollTabla,AgenteCerocho.buscarAgente(textBuscarAg.getText()));
						 * 
						 * break; case 2: actualizarTablaCt(modeloTablaCt, tablaCt, scrollCt,
						 * Contacto.buscarCt(textBuscarCt.getText())); break;
						 * 
						 * default: break; }
						 */						
					}
				});
				

								
		        panelA.setLayout(grupoLayout);
		        
		    
				/*
				 * grupoLayout.setHorizontalGroup(
				 * grupoLayout.createParallelGroup(GroupLayout.Alignment.LEADING )
				 * .addGroup(grupoLayout.createSequentialGroup()
				 * .addGroup(grupoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				 * .addGroup(grupoLayout.createSequentialGroup() .addGap(111, 111, 111)
				 * .addComponent(textBuscarAg)
				 * .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				 * .addComponent(botonBuscarAg)) .addGroup(grupoLayout.createSequentialGroup()
				 * .addContainerGap() .addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE,
				 * 375, GroupLayout.PREFERRED_SIZE))
				 * .addGroup(grupoLayout.createSequentialGroup() .addGap(0, 111, 111)
				 * .addComponent(agregarAg)
				 * .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				 * .addComponent(botonModificarAg)
				 * .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				 * .addComponent(botonTrabajaAg)
				 * .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				 * .addComponent(botonVer)
				 * .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				 * .addComponent(botonBorrarAg))) .addContainerGap(15, Short.MAX_VALUE)) );
				 * grupoLayout.setVerticalGroup(
				 * grupoLayout.createParallelGroup(GroupLayout.Alignment.LEADING )
				 * .addGroup(grupoLayout.createSequentialGroup() .addContainerGap()
				 * .addGroup(grupoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				 * .addComponent(botonBuscarAg) .addComponent(textBuscarAg))
				 * .addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE, 250,
				 * GroupLayout.PREFERRED_SIZE) .addGap(10, 18, 18)
				 * .addGroup(grupoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				 * .addComponent(agregarAg) .addComponent(botonVer)
				 * .addComponent(botonModificarAg) .addComponent(botonTrabajaAg)
				 * .addComponent(botonBorrarAg)) .addContainerGap(30, Short.MAX_VALUE)) );
				 */
		       
			
				pestaniaAgentes.add(panelA,BorderLayout.CENTER);
			}
			contenedorPestanias.addTab("Agentes", null, pestaniaAgentes,null);
			
			//pestañia contactos
			{
			pestaniaContactos=new JTabbedPane();
			pestaniaContactos.setPreferredSize(new java.awt.Dimension(387, 194));
			pestaniaContactos.setLayout(new BorderLayout());
			
			JPanel panelC=new JPanel();
			
			GroupLayout grupoCont= new GroupLayout(panelC);
			
			botonBuscarCt=new JButton("Buscar");
			textBuscarCt=new JTextField();
			agregarCt=new JButton("Agregar");
			agregarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					contacto.agregarDialog(tablaCt, modeloTablaCt, scrollCt);				
				}
			});
			botonModificarCt=new JButton("Modificar");
			botonBorrarCt=new JButton("Borrar");
			
			tablaCt=new JTable(12,3);
			tablaCt.setAutoscrolls(true);
			tablaCt.setModel(getModelContactos());
			tablaCt.setAutoCreateRowSorter(true);
			tablaCt.setAutoCreateColumnsFromModel(true);
			tablaCt.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, true));
			tablaCt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tablaCt.setCellSelectionEnabled(false);
			scrollCt=new JScrollPane();
			scrollCt.setViewportView(tablaCt);
			
			panelC.setLayout(grupoCont);
			grupoCont.setHorizontalGroup(
					grupoCont.createParallelGroup(GroupLayout.Alignment.LEADING )
		            .addGroup(grupoCont.createSequentialGroup()
			                .addGroup(grupoCont.createParallelGroup(GroupLayout.Alignment.LEADING)
			                	.addGroup(grupoCont.createSequentialGroup()
			                			.addGap(111, 111, 111)
			                			.addComponent(textBuscarCt)
					                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					                    .addComponent(botonBuscarCt))
			                	.addGroup(grupoCont.createSequentialGroup()
			                        .addContainerGap()
			                        .addComponent(scrollCt, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
			                    .addGroup(grupoCont.createSequentialGroup()
			                        .addGap(70, 111, 111)
			                        .addComponent(agregarCt)
			                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			                        .addComponent(botonModificarCt)
			                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			                        .addComponent(botonBorrarCt)))
			                .addContainerGap(15, Short.MAX_VALUE)));
			grupoCont.setVerticalGroup(
		        		grupoCont.createParallelGroup(GroupLayout.Alignment.LEADING )
		            .addGroup(grupoCont.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(grupoCont.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                		.addComponent(botonBuscarCt)
		                		.addComponent(textBuscarCt))
		                .addComponent(scrollCt, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
		                .addGap(18, 18, 18)
		                .addGroup(grupoCont.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(agregarCt)
		                    .addComponent(botonModificarCt)
		                    .addComponent(botonBorrarCt))
		                .addContainerGap(30, Short.MAX_VALUE))
		        );
			pestaniaContactos.add(panelC,BorderLayout.CENTER);
			}
			
			contenedorPestanias.addTab("Contactos", null, pestaniaContactos, null);
			
			panel.add(contenedorPestanias,new GridLayout());
			
			setJMenuBar(menuBarra);
			menuArchivo=new JMenu();
			menuArchivo.setText("Archivo");
			menuBarra.add(menuArchivo);
			nuevo=new JMenuItem();
			nuevo.setText("Nuevo");
			menuArchivo.add(nuevo);
			menuArchivo.addSeparator();
			cerrar=new JMenuItem("Cerrar");
			cerrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
			});
			
			menuArchivo.add(cerrar);
			
			menuVer=new JMenu("Ver");			
			menuBarra.add(menuVer);
			itemAgente=new JMenuItem("Agente");
			menuVer.add(itemAgente);
			itemContactos=new JMenuItem("Contactos");
			menuVer.add(itemContactos);
			itemHorario=new JMenuItem("Horarios");
			menuVer.add(itemHorario);
			
			menuHerramientas=new JMenu("Herramientas");
			menuBarra.add(menuHerramientas);
			configurar=new JMenuItem("Configuración");
			menuHerramientas.add(configurar);
			
			menuAyuda=new JMenu();
			menuAyuda.setText("Ayuda");
			menuBarra.add(menuAyuda);
			itemAcerca=new JMenuItem();
			itemAcerca.setText("Acerca de...");
			menuAyuda.add(itemAcerca);
			
			
			this.setSize(425, 415);
			pack();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * Añade al DefaultTableModel las filas correspondientes al ResultSet.
     * @param rs El resultado de la consulta a base de datos
     * @param modelo El DefaultTableModel que queremos rellenar.
     */
	private static void cargaFilasDeDatos(final ResultSet rs, final DefaultTableModel modelo)
	{
		
		try
		{/*
			if (SwingUtilities.isEventDispatchThread()){
				System.out.println("if carga");
				SwingUtilities.invokeLater(new Runnable()
				{
					
					public void run()
					{
						System.out.println("run");
						try
						{
							//Thread.sleep(500);
							int numeroFila = 0;
							System.out.println("try");
							// Para cada registro de resultado en la consulta 
							while (rs.next())
							{
								System.out.println("while");
								// Se crea y rellena la fila para el modelo de la tabla.
								ResultSetMetaData metaDatos = rs.getMetaData();

								// Se obtiene el numero de columnas.
								int numeroColumnas = metaDatos.getColumnCount();
								Object[] datosFila = new Object[10];


								for (int i = 0; i < numeroColumnas; i++){
									datosFila[i] = rs.getObject(i + 1);

								}

								Object[] datosUtiles={datosFila[1],datosFila[2],datosFila[6],datosFila[7]};
								modelo.addRow(datosUtiles);
								numeroFila++;
							}
							System.out.println("salio while");
							//rs.close();
							
						} catch (Exception e)
						{
							e.printStackTrace();
						}

						}
					})
					;
				System.out.println("sale if");
			}
			else{
				System.out.println("entro else cargadatos");*/
			SwingUtilities.invokeLater(new Runnable()
			{

				public void run()
				{
					try
					{
			System.out.println("cargara datos");
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
							modelo.addRow(datosUtiles);
							numeroFila++;
						}
						//rs.close();
					} catch (Exception e)
					{
						e.printStackTrace();
					}

					}

				})/**/
		;//}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
    }
	
	/**
     * Borra todas las filas del modelo.
     * @param modelo El modelo para la tabla.
     */
    private static void vaciaFilasModelo(final DefaultTableModel modelo)
    {
    	try
        {
    		System.out.println(modelo.getRowCount());
    		if (SwingUtilities.isEventDispatchThread()){
    			System.out.println("entra if a vaciar");
    			SwingUtilities.invokeLater(new Runnable()
                {

                    public void run()
                    {
                        while (modelo.getRowCount() > 0)
                            modelo.removeRow(0);
                        System.out.println("termino el while if");
                        System.out.println(modelo.getRowCount());
                    }

                });
    		}
    	else{
    		System.out.println("entra else a vaciar");
            SwingUtilities.invokeAndWait(new Runnable()
            {

                public void run()
                {
                    while (modelo.getRowCount() > 0)
                        modelo.removeRow(0);
                    System.out.println("termino while else");
                }

            });
    	}
    		System.out.println("salio run vaciar filas");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void tomaDatos(JTable tabla,DefaultTableModel modelo)
    {
    	System.out.println("tomadatos");
        if (tabla==null){
        	System.out.println("entro if tomadatos");
            tabla = new JTable();
            tabla.setModel(modelo);
            
        }
    }
    
    public void actualizarTabla(ResultSet rs,DefaultTableModel modelo,JTable tabla){
    		//tomaDatos(tabla,modelo);
    		vaciaFilasModelo(modelo);
    		AgenteGsi agenteD=new AgenteGsi();
			rs=agenteD.getDatosAgentes();
			cargaFilasDeDatos(rs, modelo);
			System.out.println(modelo.getRowCount());
			tomaDatos(tabla,modelo);	
			
    }
    
    public static void actualizarTabla(final ResultSet rs,final DefaultTableModel modelo, final JTable tabla,final JScrollPane scroll, final Object clase){
    	
    	final AgenteGsi uno=new AgenteGsi();
    	final AgenteCerocho dos= new AgenteCerocho();
    	final Contacto tres= new Contacto();
    	
    	try		
    	{
    		
    		SwingUtilities.invokeLater(new Runnable()
    		{

    			public void run()
    			{
    				int tipo=0;
    				if (clase.getClass()==uno.getClass()) {
    					System.out.println("if case 1");
    					tipo=1;
    				} else {
    					if (clase.getClass()==dos.getClass()) {
    						tipo=2;
    					} else {
    						if (clase.getClass()==tres.getClass()) {
        						tipo=3;
        					}
    					} 
    				}
    				System.out.println("tipo: "+tipo);
    				try
    				{

    					vaciaFilasModelo(modelo);
    					switch (tipo) {
    					case 1:
    						System.out.println("case 1");
    						TablaAgM.cargaFilasDeAgentes(modelo,rs, (AgenteGsi) clase);
    						//tabla = new JTable();
    						tabla.setModel(modelo);
    						scroll.setViewportView(tabla);
    						break; 
    					case 2:
    						TablaAg08.cargaFilasDeAgentes08(modelo,rs);
    						break;
    					case 3:
    						TablaCt.cargaFilasDeContactos(modelo, rs);
    						break;
    					default:
    						System.out.println("case default");
    						TablaAgM.cargaFilasBusquedaAgentes(modelo, (ArrayList<AgenteGsi>) clase);
    						tabla.setModel(modelo);
    						scroll.setViewportView(tabla);
    						break;
    					}


    					/*
    					 * tabla.setModel(modelo); 
    					 */
    				} catch (Exception ex)
    				{
    					ex.printStackTrace();
    				}

    			}
    		});
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}


    	
    }

    private class ModeloNoEditable extends DefaultTableModel{

    	
    	private static final long serialVersionUID = 1L;

    	public boolean isCellEditable(int row, int col) {
    		return false;    	
    	}

    }
}
