package Horarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
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
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.TabableView;

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
	
	private DiaLaboral diaL=new DiaLaboral();;
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
	
	private JTabbedPane pestaniaCerocho;
	
	private JTabbedPane pestaniaHorarios;
	private JLabel labelAñoExp;
	private JLabel labelMesEx;
	private JPanel panleConsultaH;
	private JComboBox mesExport;
	private JComboBox añoExport;
	private JButton exportMes;
	
	private JTabbedPane pestaniaAgentes;
	
	private JComboBox sala;
	
	private JTextField textBuscarCt;

	private JTextField textBuscarAg;
	private JButton botonBuscarAg;
	private JButton agregarAg;
	private JButton botonModificarAg;
	private JButton botonBorrarAg;
	private JPanel panelFicha;
	
	private JPanel panelFicha08;
			
	private ModeloNoEditable modeloTablaCt = new ModeloNoEditable();
	
	private JPanel panelFichaCt;
	
	private CalendarioGrafic calendario;
	
	GregorianCalendar select=(GregorianCalendar) Calendar.getInstance();
	
	private ResultSet rs;
	private ResultSet rs1;
	private ResultSet rs2;

	private TablaAgM tabAg=new TablaAgM();
	private TablaAg08 tab08=new TablaAg08();
	private TablaCt tabCt=new TablaCt();
	
	AgenteGsi agente=new AgenteGsi();
	Contacto contacto=new Contacto();
	AgenteCerocho ag08=new AgenteCerocho();
	protected JPanel panelTabla;
	protected JPanel panelT;
	private JPanel panelA;
	private JScrollPane scrollTabla= new JScrollPane();
	private JTabbedPane pestaniaContactos;
	private JButton botonBuscarCt;
	private JTable tablaCt;
	private JScrollPane scrollCt;
	private JButton agregarCt;
	private JButton botonModificarCt;
	private JButton botonBorrarCt;
	private JTable tablaAgentes;
	private ModeloNoEditable modeloTablaAgente = new ModeloNoEditable();
	
	private JTable tablaAg08;

	//Set Look & Feel
	{
		
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Maquetilla(){
		super("Monitoring System - Beta 0.95");
		correGUI();
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
				pestaniaAgentes.setPreferredSize(new java.awt.Dimension(589, 379));
								
				
				panelA=new JPanel();
				final AnchorLayout grupoLayout = new AnchorLayout();
				panelA.setLayout(grupoLayout);
								
				
								
				//tabAg.tabla(scrollTabla);
				
				sala=new JComboBox();
				sala.addItem("Monitoreo");
				sala.addItem("0800");
				sala.addItem("Contactos");
				
				//sala.setSelectedIndex(0);
				
				textBuscarAg= new JTextField();
				
				botonBuscarAg=new JButton("Buscar");
				
				/*
				 * switch (sala.getSelectedIndex()) { case 0: tabAg.tabla(panelA,
				 * pestaniaAgentes);
				 * 
				 * break; case 1:
				 * 
				 * tab08.tabla(panelA, panelFicha08, pestaniaAgentes);
				 * 
				 * break; case 2: tabCt.tabla(panelA, panelFicha, pestaniaAgentes);
				 * 
				 * default: break;
				 * 
				 * }
				 */
				//panelA.add(sala, new AnchorConstraint(10, 160, 57, 15, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				panelA.add(agente.fichaVacia(panelFicha), new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				
				
				
				sala.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						System.out.println(sala.getSelectedIndex());
						switch (sala.getSelectedIndex()) {
						case 0:
							
							//tabAg.tabla(scrollTabla);
							panelA.add(scrollTabla, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
							panelA.add(agente.fichaVacia(panelFicha), new AnchorConstraint(80, 1020, 540, 700, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
							/*
							pestaniaAgentes.repaint();*/
							break;
						case 1:		
							
							tab08.tabla(panelA, panelFicha08, pestaniaAgentes);
							panelA.add(ag08.fichaVacia(panelFicha08), new AnchorConstraint(80, 1020, 540,
									 700, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
									 AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL)); 
							panelA.repaint();
							pestaniaAgentes.repaint();

							/*
							 * panelA.add(scrollTabla08, new AnchorConstraint(80, 700, 895, 10,
							 * AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
							 * AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
							 * 
							 * 
							 */
						case 2:
							panelA.add(contacto.fichaVacia(panelFichaCt), new AnchorConstraint(80, 1020,
									 540, 700, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
									  AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL)); 
							panelA.repaint();

							/*
							 * panelA.add(scrollCt, new AnchorConstraint(80, 700, 895, 10,
							 * AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
							 * AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
							 * 
							 * pestaniaAgentes.repaint(); break;
							 */

						default:
							break;
						}
					}
				});
				
				//JPanel panelchu= new JPanel();
				//tabAg.crearTabla(panelchu);
				
				
		    	//panelGral.setLayout(panelLayout);

				//scrollTabla= new JScrollPane();
				
		    	tablaAgentes=new JTable(12,7);
				tablaAgentes.setAutoscrolls(true);
				tablaAgentes.setModel(getModelAgentes());
				tablaAgentes.setAutoCreateRowSorter(true);
				tablaAgentes.setAutoCreateColumnsFromModel(true);
				tablaAgentes.setBorder(new LineBorder(new Color(0,0,0), 1, true));
				tablaAgentes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				/*
				 * tablaAgentes.addMouseListener(new MouseAdapter() { public void
				 * mouseClicked(MouseEvent e) { if(e.getClickCount() == 1) { AgenteGsi
				 * agentesillo=new AgenteGsi();
				 * System.out.println((String)tablaAgentes.getValueAt(tablaAgentes.
				 * getSelectedRow(), 0));
				 * agentesillo.leerAgente((String)tablaAgentes.getValueAt(tablaAgentes.
				 * getSelectedRow(), 0)); panelGral.add(agentesillo.ficha(panelFicha,pestania),
				 * new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL,
				 * AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
				 * AnchorConstraint.ANCHOR_REL)); //panelA.repaint(); } } });
				 */		

				
				
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
				TablaAgM.cargaFilasDeAgentes(modeloTablaAgente,rs);
				

				
				
				
				
				scrollTabla.setViewportView(tabAg.tabla(scrollTabla, panelA, panelFicha, pestaniaAgentes));
				
				panelA.add(scrollTabla, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				
				agregarAg=new JButton("Agregar");
				agregarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						switch (sala.getSelectedIndex()) {
						case 0:
							agente.agregarDialog();	
							
							break;
						case 1:
							//ag08.agregarDialog(tablaAg08, modeloTablaAg08, scrollTabla08);
							break;
						case 2:
							//contacto.agregarDialog(tablaCt,modeloTablaCt,scrollCt);
							break;

						default:
							break;
						}
												
					}
				});
				
				panelA.add(agregarAg, new AnchorConstraint(905, 490, 965, 398, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				//agregarAg.setPreferredSize(new java.awt.Dimension(94, 24));

				botonModificarAg=new JButton("Modificar");
				botonModificarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						switch (sala.getSelectedIndex()) {
						case 0:
							
							agente.dialogoModificar(tabAg, scrollTabla);	
							//tabAg.tabla(scrollTabla);
							break;
						case 1:
							//ag08.dialogoModificar(tablaAg08,modeloTablaAg08,scrollTabla08);
							break;
						case 2: 
							//contacto.dialogoModificar(tablaCt,modeloTablaCt,scrollCt);
							break;

						default:
							break;
						}
						
						
					}
				});
				
				panelA.add(botonModificarAg, new AnchorConstraint(905, 586, 965, 492, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				//botonModificarAg.setPreferredSize(new java.awt.Dimension(96, 24));

				botonBorrarAg=new JButton("Borrar");
				
				botonBorrarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println("borrar: "+sala.getSelectedIndex());
						switch (sala.getSelectedIndex()) {
					case 0:
						agente.borrarAgente((String)tablaAgentes.getValueAt(tablaAgentes.getSelectedRow(), 0));
						actualizarTabla(rs, modeloTablaAgente, tablaAgentes, scrollTabla,0);
	
						break;
					case 1:
						
							/*
							 * ag08.borrarAgente((String)tablaAg08.getValueAt(tablaAgentes.getSelectedRow(),
							 * 0)); actualizarTabla(rs1, modeloTablaAg08, tablaAg08, scrollTabla08,1);
							 */

						break;
					case 2:
							/*
							 * contacto.borrarContacto((String)tablaCt.getValueAt(tablaCt.getSelectedRow(),
							 * 0)); actualizarTabla(rs, modeloTablaCt, tablaCt, scrollCt,2);
							 */
						break;

					default:
						break;
						}
												
					}
				});
				
				
				
				panelA.add(botonBorrarAg, new AnchorConstraint(905, 680, 965, 588, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				//botonBorrarAg.setPreferredSize(new java.awt.Dimension(94, 24));
				
				panelA.add(botonBuscarAg, new AnchorConstraint(16, 788, 57, 704, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				botonBuscarAg.setPreferredSize(new java.awt.Dimension(94, 24));
				botonBuscarAg.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
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
				
				
				pestaniaAgentes.add(panelA,BorderLayout.CENTER);
				
				
				
				//panelA.setPreferredSize(new java.awt.Dimension(414, 384));
			}
			contenedorPestanias.addTab("Agentes", null, pestaniaAgentes,null);
			
			//pestañia contactos
			{
			pestaniaContactos=new JTabbedPane();
			pestaniaContactos.setPreferredSize(new java.awt.Dimension(589, 379));
			pestaniaContactos.setLayout(new BorderLayout());
			
			final JPanel panelC=new JPanel();
			final AnchorLayout grupoLayout = new AnchorLayout();
			panelC.setLayout(grupoLayout);
			
			botonBuscarCt=new JButton("Buscar");
					
			
			scrollCt= new JScrollPane();
			
			tabCt= new TablaCt();
			tabCt.tabla(scrollCt);
			
			panelC.add(scrollCt, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			scrollCt.setPreferredSize(new java.awt.Dimension(697, 421));

			agregarCt=new JButton("Agregar");
			panelC.add(agregarCt, new AnchorConstraint(905, 490, 965, 398, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			agregarCt.setPreferredSize(new java.awt.Dimension(94, 24));

			agregarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					contacto.agregarDialog(tablaCt,modeloTablaCt,scrollCt);				
				}
			});
			botonModificarCt=new JButton("Modificar");
			botonModificarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					contacto.dialogoModificar(tabCt);				
				}
			});
			panelC.add(botonModificarCt, new AnchorConstraint(905, 586, 965, 492, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			botonModificarCt.setPreferredSize(new java.awt.Dimension(96, 24));

			botonBorrarCt=new JButton("Borrar");
			botonBorrarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					contacto.borrarContacto((String)tablaCt.getValueAt(tablaCt.getSelectedRow(), 0));
					actualizarTabla(rs, modeloTablaCt, tablaCt, scrollCt,2);
									
				}
			});
			
			
			panelC.add(botonBorrarCt, new AnchorConstraint(905, 680, 965, 588, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			botonBorrarCt.setPreferredSize(new java.awt.Dimension(94, 24));
			
			panelC.add(botonBuscarCt, new AnchorConstraint(16, 788, 57, 704, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			botonBuscarCt.setPreferredSize(new java.awt.Dimension(94, 24));
			botonBuscarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//TablaCt.actualizarTablaCt(tablaCt, scrollCt, Contacto.buscarCt(textBuscarCt.getText()));
				}
			});
			
			textBuscarCt=new JTextField();
			panelC.add(textBuscarCt, new AnchorConstraint(16, 701, 57, 452, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			textBuscarCt.setPreferredSize(new java.awt.Dimension(243, 21));
			textBuscarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//actualizarTablaCt(modeloTablaCt, tablaCt, scrollCt, Contacto.buscarCt(textBuscarCt.getText()));
				}
			});
			
			pestaniaContactos.add(panelC,BorderLayout.CENTER);
			}
			
			contenedorPestanias.addTab("Contactos", null, pestaniaContactos, null);
			
			
			//pestañia cerocho
			{
			pestaniaCerocho=new JTabbedPane();
			pestaniaCerocho.setPreferredSize(new java.awt.Dimension(589, 379));
			pestaniaCerocho.setLayout(new BorderLayout());
			
			final JPanel panel0=new JPanel();
			final AnchorLayout grupoLayout = new AnchorLayout();
			panel0.setLayout(grupoLayout);
			
			botonBuscarCt=new JButton("Buscar");
					
			tablaAg08=new JTable(12,7);
			tablaAg08.setAutoscrolls(true);
			tablaAg08.setModel(getModelAgentes());
			tablaAg08.setAutoCreateRowSorter(true);
			tablaAg08.setAutoCreateColumnsFromModel(true);
			tablaAg08.setBorder(new LineBorder(new Color(0,0,0), 1, true));
			tablaAg08.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tablaAg08.addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent e) 
				{
					if(e.getClickCount() == 1) {
						AgenteCerocho agentesillo=new AgenteCerocho();
						//System.out.println((String)tablaAgentes.getValueAt(tablaAgentes.getSelectedRow(), 0));
						agentesillo.leerAgente((String)tablaAg08.getValueAt(tablaAg08.getSelectedRow(), 0));
						panel0.add(agentesillo.ficha(panelFicha,pestaniaCerocho), new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						//panelA.repaint();
					}
				}
			});

			scrollTabla= new JScrollPane();
			
			

			TableColumn columnaNom=tablaAg08.getColumn("Nombre y Apellido");
			columnaNom.setPreferredWidth(130);
			TableColumn columnDirec=tablaAg08.getColumn("Dirección");
			columnDirec.setPreferredWidth(90);
			TableColumn columnaTelefono=tablaAg08.getColumn("Teléfono");
			columnaTelefono.setPreferredWidth(100);
			TableColumn columnaEmail=tablaAg08.getColumn("E-mail");
			columnaEmail.setPreferredWidth(130);
			TableColumn columGrupo=tablaAg08.getColumn("Grupo");
			columGrupo.setPreferredWidth(20);

			final AgenteCerocho agenteD=new AgenteCerocho();
			rs=agenteD.getDatosAgentes();
			
			
			/*TablaAg08.cargaFilasDeAgentes08(modeloTablaAgente,rs);
			tablaAgentes.setModel(modeloTablaAgente);*/
			
			scrollTabla.setViewportView(tablaAg08);
			
			panel0.add(scrollTabla, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			scrollTabla.setPreferredSize(new java.awt.Dimension(697, 421));

			
			agregarCt=new JButton("Agregar");
			panel0.add(agregarCt, new AnchorConstraint(905, 490, 965, 398, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			agregarCt.setPreferredSize(new java.awt.Dimension(94, 24));

			agregarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					agenteD.agregarDialog(tablaAg08,modeloTablaAgente,scrollCt);				
				}
			});
			botonModificarCt=new JButton("Modificar");
			botonModificarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//ag08.dialogoModificar(tabCt);				
				}
			});
			panel0.add(botonModificarCt, new AnchorConstraint(905, 586, 965, 492, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			botonModificarCt.setPreferredSize(new java.awt.Dimension(96, 24));

			botonBorrarCt=new JButton("Borrar");
			botonBorrarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//agenteD.borrarContacto((String)tablaAg08.getValueAt(tablaAg08.getSelectedRow(), 0));
					actualizarTabla(rs, modeloTablaAgente, tablaAg08, scrollCt,1);
									
				}
			});
			
			
			panel0.add(botonBorrarCt, new AnchorConstraint(905, 680, 965, 588, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			botonBorrarCt.setPreferredSize(new java.awt.Dimension(94, 24));
			
			panel0.add(botonBuscarCt, new AnchorConstraint(16, 788, 57, 704, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			botonBuscarCt.setPreferredSize(new java.awt.Dimension(94, 24));
			botonBuscarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//TablaCt.actualizarTablaCt(tablaCt, scrollCt, Contacto.buscarCt(textBuscarCt.getText()));
				}
			});
			
			textBuscarCt=new JTextField();
			panel0.add(textBuscarCt, new AnchorConstraint(16, 701, 57, 452, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			textBuscarCt.setPreferredSize(new java.awt.Dimension(243, 21));
			textBuscarCt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//actualizarTablaCt(modeloTablaCt, tablaCt, scrollCt, Contacto.buscarCt(textBuscarCt.getText()));
				}
			});
			
			pestaniaCerocho.add(panel0,BorderLayout.CENTER);
			}
			
			contenedorPestanias.addTab("0800", null, pestaniaCerocho, null);
			
			
			
			
			/**/
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
			configurar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					@SuppressWarnings("unused")
					Preferences pref=new Preferences();
				}
			});
			menuHerramientas.add(configurar);
			
			menuAyuda=new JMenu();
			menuAyuda.setText("Ayuda");
			menuBarra.add(menuAyuda);
			itemAcerca=new JMenuItem();
			itemAcerca.setText("Acerca de...");
			menuAyuda.add(itemAcerca);
			
			
			this.setSize(1035, 737);
			pack();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
    
    private DefaultTableModel getModelAgentes() {
		// TODO Auto-generated method stub
    	modeloTablaAgente.addColumn("Nombre y Apellido");
        modeloTablaAgente.addColumn("Dirección");
        modeloTablaAgente.addColumn("Teléfono");
        modeloTablaAgente.addColumn("E-mail");
        modeloTablaAgente.addColumn("Grupo");
        
        return modeloTablaAgente;
	}



	/**
     *    Tipo: 
     *    0- Agente Monitoreo
     *    1- Agente 0800
     *    2- Contactos
     *    
     *    
     * @param rs
     * @param modelo
     * @param tabla
     * @param scroll
     * @param tipo
     */
    public void actualizarTabla(ResultSet rs,DefaultTableModel modelo,JTable tabla,JScrollPane scroll,int tipo){
    	try		
    	{
    	
    	vaciaFilasModelo(modelo);
    	switch (tipo) {
		case 0:
			TablaAgM.cargaFilasDeAgentes(modelo,rs);
			break; 
		case 1:
			TablaAg08.cargaFilasDeAgentes08(modelo,rs);
			break;
		case 2:
			TablaCt.cargaFilasDeContactos(modelo, rs);
			break;
		default:
			break;
		}
    	
    	    
			/*
			 * tabla.setModel(modelo); scroll.setViewportView(tabla);
			 */
		}
    	catch(Exception e){
    		e.printStackTrace();
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
			if (SwingUtilities.isEventDispatchThread()){

				{
					while (modelo.getRowCount() > 0)
						modelo.removeRow(0);
				}
			}
			else{
				SwingUtilities.invokeAndWait(new Runnable()
				{

					public void run()
					{
						while (modelo.getRowCount() > 0)
							modelo.removeRow(0);
					}

				});
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Añade al DefaultTableModel las filas correspondientes al ResultSet.
	 * @param rs El resultado de la consulta a base de datos
	 * @param modelo El DefaultTableModel que queremos rellenar.
	 */
	public static void cargaFilasDeAgentes(final DefaultTableModel modelo, ResultSet rs)
	{

		try{
			AgenteCerocho agente=new AgenteCerocho();
			rs=agente.getDatosAgentes();
			int numeroFila = 0;
			// Para cada registro de resultado en la consulta 
			while (rs.next())
			{
				// Se crea y rellena la fila para el modelo de la tabla.
				ResultSetMetaData metaDatos = rs.getMetaData();

				// Se obtiene el numero de columnas.
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[11];


				for (int i = 0; i < numeroColumnas; i++){
					datosFila[i] = rs.getObject(i + 1);

				}

				Object[] datosUtiles={datosFila[1],datosFila[3],datosFila[7],datosFila[8],datosFila[9]};
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	

    public void setDiaL(DiaLaboral diaL) {
		this.diaL = diaL;
	}

	public DiaLaboral getDiaL() {
		return diaL;
	}

	private class ModeloNoEditable extends DefaultTableModel{

    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int col) {
            return true;    	
    	}
		
    }
	
	
}
