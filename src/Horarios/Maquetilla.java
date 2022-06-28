package Horarios;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Maquetilla  extends JFrame{
	
	private static final long serialVersionUID = 1L;
	static final boolean shouldFill = false;
	static final boolean shouldWeightX = true;
	static final boolean RIGHT_TO_LEFT = false;
	private DiaLaboral diaL = new DiaLaboral();
	private JPanel panel;
	private JMenuBar menuBarra = new JMenuBar();
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
	private JTable tablaAgentes08;
	private ModeloNoEditable modeloTablaAgente = new ModeloNoEditable();
	private ModeloNoEditable modeloTablaAg08 = new ModeloNoEditable();
	private JScrollPane scrollTabla;
	private JTextField textBuscarAg;
	private JButton botonBuscarAg;
	private JButton agregarAg;
	private JButton botonModificarAg;
	private JButton botonBorrarAg;
	private JPanel panelFicha;
	private JTabbedPane pestaniaContactos;
	private JButton botonBuscarCt;
	private JTextField textBuscarCt;
	private JButton agregarCt;
	private JButton botonModificarCt;
	private JButton botonBorrarCt;
	private JTable tablaCt;
	private ModeloNoEditable modeloTablaCt = new ModeloNoEditable();
	private JScrollPane scrollCt;
	private JPanel panelFichaCt;
	private CalendarioGrafic calendario;
	GregorianCalendar select = (GregorianCalendar)Calendar.getInstance();
	private ResultSet rs;
	AgenteGsi agente = new AgenteGsi();
	AgenteCerocho agente08=new AgenteCerocho();
	Contacto contacto = new Contacto();
	private JTabbedPane pestania08;
	private JTextField textBuscar08;
	private JButton botonBuscar08;
	private JScrollPane scrollTabla08;

	public Maquetilla()
	{
		super("Monitoring System - Beta 0.95");
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		correGUI();
	}

	public DefaultTableModel getModelAgentes()
	{
		this.modeloTablaAgente.addColumn("Nombre y Apellido");
		this.modeloTablaAgente.addColumn("Dirección");
		this.modeloTablaAgente.addColumn("Teléfono");
		this.modeloTablaAgente.addColumn("E-mail");
		this.modeloTablaAgente.addColumn("Grupo");

		return this.modeloTablaAgente;
	}

	public DefaultTableModel getModelAg08(){

		modeloTablaAg08.addColumn("Nombre y Apellido");
		modeloTablaAg08.addColumn("Dirección");
		modeloTablaAg08.addColumn("Teléfono");
		modeloTablaAg08.addColumn("E-mail");
		modeloTablaAg08.addColumn("Grupo");

		return modeloTablaAg08;
	}
	
	public DefaultTableModel getModelContactos()
	{
		this.modeloTablaCt.addColumn("Nombre");
		this.modeloTablaCt.addColumn("Dirección");
		this.modeloTablaCt.addColumn("Teléfono");
		this.modeloTablaCt.addColumn("E-mail");
		this.modeloTablaCt.addColumn("Comentario");

		return this.modeloTablaCt;
	}

	public void correGUI()
	{
		try
		{
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

			this.pestaniaAgentes = new JTabbedPane();
			this.pestaniaAgentes.setLayout(new BorderLayout());
			this.pestaniaAgentes.setPreferredSize(new Dimension(589, 379));

			final JPanel panelA = new JPanel();
			AnchorLayout grupoLayout = new AnchorLayout();
			panelA.setLayout(grupoLayout);

			this.textBuscarAg = new JTextField();

			this.botonBuscarAg = new JButton("Buscar");

			panelA.add(agente.fichaVacia(panelFicha), new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			

			this.tablaAgentes = new JTable(12, 7);
			this.tablaAgentes.setAutoscrolls(true);
			this.tablaAgentes.setModel(getModelAgentes());
			this.tablaAgentes.setAutoCreateRowSorter(true);
			this.tablaAgentes.setAutoCreateColumnsFromModel(true);
			this.tablaAgentes.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			this.tablaAgentes.setAutoResizeMode(4);
			this.tablaAgentes.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					if (e.getClickCount() == 1)
					{
						AgenteGsi agentesillo = new AgenteGsi();

						agentesillo.leerAgente((String)Maquetilla.this.tablaAgentes.getValueAt(Maquetilla.this.tablaAgentes.getSelectedRow(), 0));
						panelA.add(agentesillo.ficha(panelFicha,pestaniaAgentes),new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						panelA.repaint();
					}
				}
			});
			TableColumn columnaNom = this.tablaAgentes.getColumn("Nombre y Apellido");
			columnaNom.setPreferredWidth(100);
			TableColumn columnDirec = this.tablaAgentes.getColumn("Dirección");
			columnDirec.setPreferredWidth(80);
			TableColumn columnaTelefono = this.tablaAgentes.getColumn("Teléfono");
			columnaTelefono.setPreferredWidth(90);
			TableColumn columnaEmail = this.tablaAgentes.getColumn("E-mail");
			columnaEmail.setPreferredWidth(130);
			TableColumn columGrupo = this.tablaAgentes.getColumn("Grupo");
			columGrupo.setPreferredWidth(2);

			AgenteGsi agenteD = new AgenteGsi();
			this.rs = agenteD.getDatosAgentes();
			cargaFilasDeAgentes(this.modeloTablaAgente, this.rs);
			this.tablaAgentes.setModel(this.modeloTablaAgente);

			this.scrollTabla = new JScrollPane();
			this.scrollTabla.setViewportView(this.tablaAgentes);
			panelA.add(this.scrollTabla, new AnchorConstraint(80, 700, 895, 10, 1, 1, 1, 1));
			this.scrollTabla.setPreferredSize(new Dimension(697, 421));

			this.agregarAg = new JButton("Agregar");
			this.agregarAg.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					agente.agregarDialog(tablaAgentes, modeloTablaAgente, scrollTabla);
				}
			});
			panelA.add(agregarAg, new AnchorConstraint(905, 490, 965, 398, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			this.agregarAg.setPreferredSize(new Dimension(94, 24));

			this.botonModificarAg = new JButton("Modificar");
			this.botonModificarAg.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					agente.dialogoModificar(tablaAgentes, modeloTablaAgente, scrollTabla);
				}
			});
			panelA.add(botonModificarAg, new AnchorConstraint(905, 586, 965, 492, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			this.botonModificarAg.setPreferredSize(new Dimension(96, 24));

			this.botonBorrarAg = new JButton("Borrar");

			this.botonBorrarAg.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					Maquetilla.this.agente.borrarAgente((String)Maquetilla.this.tablaAgentes.getValueAt(Maquetilla.this.tablaAgentes.getSelectedRow(), 0));
					Maquetilla.this.actualizarTabla(Maquetilla.this.rs, Maquetilla.this.modeloTablaAgente, Maquetilla.this.tablaAgentes, Maquetilla.this.scrollTabla, 0);
				}
			});
			panelA.add(botonBorrarAg, new AnchorConstraint(905, 680, 965, 588, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			this.botonBorrarAg.setPreferredSize(new Dimension(94, 24));

			panelA.add(this.botonBuscarAg, new AnchorConstraint(16, 788, 57, 704, 1, 1, 1, 1));
			this.botonBuscarAg.setPreferredSize(new Dimension(94, 24));
			this.botonBuscarAg.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					Maquetilla.this.actualizarTabla(Maquetilla.this.modeloTablaAgente, Maquetilla.this.tablaAgentes, Maquetilla.this.scrollTabla, AgenteGsi.buscarAgente(Maquetilla.this.textBuscarAg.getText()));
				}
			});
			panelA.add(this.textBuscarAg, new AnchorConstraint(16, 701, 57, 452, 1, 1, 1, 1));
			this.textBuscarAg.setPreferredSize(new Dimension(243, 21));
			this.textBuscarAg.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					Maquetilla.this.actualizarTabla(Maquetilla.this.modeloTablaAgente, Maquetilla.this.tablaAgentes, Maquetilla.this.scrollTabla, AgenteGsi.buscarAgente(Maquetilla.this.textBuscarAg.getText()));
				}
			});
			this.pestaniaAgentes.add(panelA, "Center");
			panelA.setPreferredSize(new Dimension(414, 384));

			this.contenedorPestanias.addTab("Agentes", null, this.pestaniaAgentes, null);
			
			//pestania 0800
			
			{
				pestania08 = new JTabbedPane(); pestania08.setLayout(new BorderLayout());
				pestania08.setPreferredSize(new Dimension(589, 379));

				final JPanel panel08 = new JPanel();

				panel08.setLayout(grupoLayout);

				textBuscar08 = new JTextField();

				botonBuscar08 = new JButton("Buscar");

				panel08.add(agente08.fichaVacia(panelFicha), new AnchorConstraint(80, 990,
						560, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));


				this.tablaAgentes08 = new JTable(12, 7);
				this.tablaAgentes08.setAutoscrolls(true);
				this.tablaAgentes08.setModel(getModelAg08());
				this.tablaAgentes08.setAutoCreateRowSorter(true);
				this.tablaAgentes08.setAutoCreateColumnsFromModel(true);
				this.tablaAgentes08.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				this.tablaAgentes08.setAutoResizeMode(4);
				this.tablaAgentes08.addMouseListener(new MouseAdapter() 
				{ 
					public void
					mouseClicked(MouseEvent e) { if (e.getClickCount() == 1) { 
						AgenteCerocho agentesillo = new AgenteCerocho();

					agentesillo.leerAgente((String)tablaAgentes08.getValueAt(
							Maquetilla.this.tablaAgentes08.getSelectedRow(),0)); 
					panel08.add(agentesillo.ficha(panelFicha,pestania08),new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL,
													AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
													AnchorConstraint.ANCHOR_REL));
					panel08.repaint(); 
						}
					}
				}); 
				TableColumn columnNom = this.tablaAgentes08.getColumn("Nombre y Apellido");
				columnNom.setPreferredWidth(100); 
				TableColumn colmnDirec =this.tablaAgentes08.getColumn("Dirección"); 
				colmnDirec.setPreferredWidth(80);
				TableColumn columnTelefono = this.tablaAgentes08.getColumn("Teléfono");
				columnTelefono.setPreferredWidth(90); 
				TableColumn columnEmail =this.tablaAgentes08.getColumn("E-mail");
				columnEmail.setPreferredWidth(130);
				TableColumn colmGrupo = this.tablaAgentes08.getColumn("Grupo");
				colmGrupo.setPreferredWidth(2);

				final AgenteCerocho agente08 = new AgenteCerocho(); 
				this.rs =agente08.getDatosAgentes(); 
				cargaFilasDeAgentes08(this.modeloTablaAg08,this.rs); 
				this.tablaAgentes08.setModel(this.modeloTablaAg08);
				
				this.scrollTabla08 = new JScrollPane();
				this.scrollTabla08.setViewportView(this.tablaAgentes08);
				
				panel08.add(this.scrollTabla08, new AnchorConstraint(80, 700, 895, 10, 1, 1, 1,1));
				this.scrollTabla08.setPreferredSize(new Dimension(697, 421));
				this.agregarAg = new JButton("Agregar"); 
				this.agregarAg.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) {
						agente08.agregarDialog(tablaAgentes08, modeloTablaAg08, scrollTabla08); 
						} 
					});
				panel08.add(agregarAg, new AnchorConstraint(905, 490, 965, 398,AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				this.agregarAg.setPreferredSize(new Dimension(94, 24));

				this.botonModificarAg = new JButton("Modificar");
				this.botonModificarAg.addActionListener(new ActionListener() { 
					public void actionPerformed(ActionEvent arg0) {
						agente08.dialogoModificar(tablaAgentes08,modeloTablaAg08, scrollTabla08); 
					} 
				}); 
				panel08.add(botonModificarAg, new AnchorConstraint(905, 586, 965, 492, AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL)); this.botonModificarAg.setPreferredSize(new Dimension(96, 24));

				this.botonBorrarAg = new JButton("Borrar");

				botonBorrarAg.addActionListener(new ActionListener() { 
							public void	actionPerformed(ActionEvent arg0) {
								Maquetilla.this.agente08.borrarAgente((String) Maquetilla.this.tablaAgentes08.getValueAt(Maquetilla.this.tablaAgentes08.getSelectedRow(), 0));
								Maquetilla.this.actualizarTabla(Maquetilla.this.rs,	Maquetilla.this.modeloTablaAg08, Maquetilla.this.tablaAgentes08,
										Maquetilla.this.scrollTabla08, 1);
								} 
							}); 
				panel08.add(botonBorrarAg, new AnchorConstraint(905, 680, 965, 588, AnchorConstraint.ANCHOR_REL,
														AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
														AnchorConstraint.ANCHOR_REL)); 
				botonBorrarAg.setPreferredSize(new	Dimension(94, 24));

						panel08.add(this.botonBuscar08, new AnchorConstraint(16, 788, 57, 704, 1, 1,	1, 1));
						this.botonBuscar08.setPreferredSize(new Dimension(94, 24));
						this.botonBuscar08.addActionListener(
								new ActionListener() { 
									public void actionPerformed(ActionEvent arg0) 
									{
										actualizarTabla08(modeloTablaAg08,tablaAgentes08,scrollTabla08, AgenteCerocho.buscarAgente(Maquetilla.this.textBuscar08.getText()));
									} 
								}); 
						panel08.add(this.textBuscar08, new AnchorConstraint(16, 701, 57, 452, 1,1, 1, 1)); 
						this.textBuscar08.setPreferredSize(new Dimension(243, 21));

						this.textBuscar08.addActionListener(
								new ActionListener() 
								{ 
									public void actionPerformed(ActionEvent arg0) {
										actualizarTabla08(modeloTablaAg08, tablaAgentes08,scrollTabla08, AgenteCerocho.buscarAgente(Maquetilla.this.textBuscar08.getText()));														}
								}); 
						this.pestania08.add(panel08, "Center"); 
						panel08.setPreferredSize(new Dimension(414, 384));
						contenedorPestanias.addTab("0800", null, this.pestania08, null);
			}



			//pestania contactos

			this.pestaniaContactos = new JTabbedPane();
			this.pestaniaContactos.setPreferredSize(new Dimension(589, 379));
			this.pestaniaContactos.setLayout(new BorderLayout());

			final JPanel panelC = new JPanel();
			//AnchorLayout grupoLayout = new AnchorLayout();
			panelC.setLayout(grupoLayout);

			this.botonBuscarCt = new JButton("Buscar");
			this.textBuscarCt = new JTextField();

			this.tablaCt = new JTable(12, 7);
			this.tablaCt.setAutoscrolls(true);
			this.tablaCt.setModel(getModelContactos());
			this.tablaCt.setAutoCreateRowSorter(true);
			this.tablaCt.setAutoCreateColumnsFromModel(true);
			this.tablaCt.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			this.tablaCt.setAutoResizeMode(4);
			this.tablaCt.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					Contacto cont = new Contacto();
					cont.leerCt((String)Maquetilla.this.tablaCt.getValueAt(Maquetilla.this.tablaCt.getSelectedRow(), 0));
					panelC.add(cont.ficha(Maquetilla.this.panelFicha, Maquetilla.this.pestaniaContactos), new AnchorConstraint(80, 990, 560, 710, 1, 1, 1, 1));
					panelC.repaint();
				}
			});
			this.scrollCt = new JScrollPane();
			this.scrollCt.setViewportView(this.tablaCt);

			TableColumn colunNom = this.tablaCt.getColumn("Nombre");
			colunNom.setPreferredWidth(100);
			TableColumn columnDirc = this.tablaCt.getColumn("Dirección");
			columnDirc.setPreferredWidth(80);
			TableColumn columnaTelfono = this.tablaCt.getColumn("Teléfono");
			columnaTelfono.setPreferredWidth(90);
			TableColumn columEmail = this.tablaCt.getColumn("E-mail");
			columEmail.setPreferredWidth(100);
			TableColumn columGrpo = this.tablaCt.getColumn("Comentario");
			columGrpo.setPreferredWidth(100);

			panelC.add(contacto.fichaVacia(panelFichaCt), new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
					  AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));

			this.rs = this.contacto.getDatosCt();
			cargaFilasDeContactos(this.modeloTablaCt, this.rs);
			this.tablaCt.setModel(this.modeloTablaCt);

			this.scrollCt = new JScrollPane();
			this.scrollCt.setViewportView(this.tablaCt);

			panelC.add(this.scrollCt, new AnchorConstraint(80, 700, 895, 10, 1, 1, 1, 1));
			this.scrollCt.setPreferredSize(new Dimension(697, 421));

			this.agregarCt = new JButton("Agregar");
			panelC.add(agregarCt, new AnchorConstraint(905, 490, 965, 398, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			this.agregarCt.setPreferredSize(new Dimension(94, 24));

			this.agregarCt.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					//Maquetilla.this.contacto.agregarDialog(Maquetilla.this.tablaCt, Maquetilla.this.modeloTablaCt, panelC, Maquetilla.this.scrollCt);
				}
			});
			this.botonModificarCt = new JButton("Modificar");
			this.botonModificarCt.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					//Maquetilla.this.contacto.dialogoModificar(Maquetilla.this.tablaCt, Maquetilla.this.modeloTablaCt, Maquetilla.this.scrollCt, panelC);
				}
			});
			panelC.add(botonModificarCt, new AnchorConstraint(905, 586, 965, 492, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			this.botonModificarCt.setPreferredSize(new Dimension(96, 24));

			this.botonBorrarCt = new JButton("Borrar");
			this.botonBorrarCt.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					Maquetilla.this.contacto.borrarContacto((String)Maquetilla.this.tablaCt.getValueAt(Maquetilla.this.tablaCt.getSelectedRow(), 0));
					Maquetilla.this.actualizarTabla(Maquetilla.this.rs, Maquetilla.this.modeloTablaCt, Maquetilla.this.tablaCt, Maquetilla.this.scrollCt, 2);
				}
			});
			panelC.add(botonBorrarCt, new AnchorConstraint(905, 680, 965, 588, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			this.botonBorrarCt.setPreferredSize(new Dimension(94, 24));

			panelC.add(this.botonBuscarCt, new AnchorConstraint(16, 788, 57, 704, 1, 1, 1, 1));
			this.botonBuscarCt.setPreferredSize(new Dimension(94, 24));
			this.botonBuscarCt.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Maquetilla.this.actualizarTablaCt(Maquetilla.this.modeloTablaCt, Maquetilla.this.tablaCt, Maquetilla.this.scrollCt, Contacto.buscarCt(Maquetilla.this.textBuscarCt.getText()));
				}
			});
			panelC.add(this.textBuscarCt, new AnchorConstraint(16, 701, 57, 452, 1, 1, 1, 1));
			this.textBuscarCt.setPreferredSize(new Dimension(243, 21));
			this.textBuscarCt.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Maquetilla.this.actualizarTablaCt(Maquetilla.this.modeloTablaCt, Maquetilla.this.tablaCt, Maquetilla.this.scrollCt, Contacto.buscarCt(Maquetilla.this.textBuscarCt.getText()));
				}
			});
			this.pestaniaContactos.add(panelC, "Center");

			this.contenedorPestanias.addTab("Contactos", null, this.pestaniaContactos, null);

			this.panel.add(this.contenedorPestanias, new GridLayout());

			setJMenuBar(this.menuBarra);
			this.menuArchivo = new JMenu();
			this.menuArchivo.setText("Archivo");
			this.menuBarra.add(this.menuArchivo);
			this.nuevo = new JMenuItem();
			this.nuevo.setText("Nuevo");
			this.menuArchivo.add(this.nuevo);
			this.menuArchivo.addSeparator();
			this.cerrar = new JMenuItem("Cerrar");
			this.cerrar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					System.exit(0);
				}
			});
			this.menuArchivo.add(this.cerrar);

			this.menuVer = new JMenu("Ver");
			this.menuBarra.add(this.menuVer);
			this.itemAgente = new JMenuItem("Agente");
			this.menuVer.add(this.itemAgente);
			this.itemContactos = new JMenuItem("Contactos");
			this.menuVer.add(this.itemContactos);
			this.itemHorario = new JMenuItem("Horarios");
			this.menuVer.add(this.itemHorario);

			this.menuHerramientas = new JMenu("Herramientas");
			this.menuBarra.add(this.menuHerramientas);
			this.configurar = new JMenuItem("Configuración");
			this.configurar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					Preferences pref = new Preferences();
				}
			});
			this.menuHerramientas.add(this.configurar);

			this.menuAyuda = new JMenu();
			this.menuAyuda.setText("Ayuda");
			this.menuBarra.add(this.menuAyuda);
			this.itemAcerca = new JMenuItem();
			this.itemAcerca.setText("Acerca de...");
			this.menuAyuda.add(this.itemAcerca);

			setSize(1035, 737);
			pack();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void actualizarTabla(ModeloNoEditable modelo, JTable tabla, JScrollPane scroll, ArrayList<AgenteGsi> agentes)
	{
		try
		{
			vaciaFilasModelo(modelo);
			cargaFilasBusquedaAgentes(modelo, agentes);

			tabla.setModel(modelo);
			scroll.setViewportView(tabla);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void actualizarTabla08(ModeloNoEditable modelo, JTable tabla, JScrollPane scroll, ArrayList<AgenteCerocho> agentes)
	{
		try
		{
			vaciaFilasModelo(modelo);
			cargaFilasBusquedaAgentes08(modelo, agentes);

			tabla.setModel(modelo);
			scroll.setViewportView(tabla);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void actualizarTablaCt(ModeloNoEditable modelo, JTable tabla, JScrollPane scroll, ArrayList<Contacto> contactos)
	{
		try
		{
			vaciaFilasModelo(modelo);
			cargaFilasBusquedaCt(modelo, contactos);

			tabla.setModel(modelo);
			scroll.setViewportView(tabla);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void cargaFilasDeAgentes(DefaultTableModel modelo, ResultSet rs)
	{
		try
		{
			AgenteGsi agente = new AgenteGsi();
			rs = agente.getDatosAgentes();
			int numeroFila = 0;
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();

				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[10];
				for (int i = 0; i < numeroColumnas; i++) {
					datosFila[i] = rs.getObject(i + 1);
				}
				Object[] datosUtiles = { datosFila[1], datosFila[3], datosFila[7], datosFila[8], datosFila[9] };
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static void cargaFilasDeAgentes08(DefaultTableModel modelo, ResultSet rs)
	{
		try
		{
			AgenteCerocho agente = new AgenteCerocho();
			rs = agente.getDatosAgentes();
			int numeroFila = 0;
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();

				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[11];
				for (int i = 0; i < numeroColumnas; i++) {
					datosFila[i] = rs.getObject(i + 1);
				}
				Object[] datosUtiles = { datosFila[1], datosFila[3], datosFila[7], datosFila[8], datosFila[9] };
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
		

	private static void cargaFilasBusquedaAgentes(DefaultTableModel modelo, ArrayList<AgenteGsi> agentes)
	{
		try
		{
			int numeroFila = 0;
			for (int j = 0; j < agentes.size(); j++)
			{
				Object[] datosUtiles = { ((AgenteGsi)agentes.get(j)).getNombreyApellido(), ((AgenteGsi)agentes.get(j)).getDireccion(), ((AgenteGsi)agentes.get(j)).getTelefono(), ((AgenteGsi)agentes.get(j)).getEmail(), ((AgenteGsi)agentes.get(j)).getNombreGrupo() };
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void cargaFilasBusquedaAgentes08(DefaultTableModel modelo, ArrayList<AgenteCerocho> agentes)
	{
		try
		{
			int numeroFila = 0;
			for (int j = 0; j < agentes.size(); j++)
			{
				Object[] datosUtiles = { ((AgenteCerocho)agentes.get(j)).getNombreyApellido(), ((AgenteCerocho)agentes.get(j)).getDireccion(), 
						((AgenteCerocho)agentes.get(j)).getTelefono(), ((AgenteCerocho)agentes.get(j)).getEmail(), ((AgenteCerocho)agentes.get(j)).getNombreGrupo() };
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static void cargaFilasBusquedaCt(DefaultTableModel modelo, ArrayList<Contacto> contactos)
	{
		try
		{
			int numeroFila = 0;
			for (int j = 0; j < contactos.size(); j++)
			{
				Object[] datosUtiles = { ((Contacto)contactos.get(j)).getNombre(), ((Contacto)contactos.get(j)).getDireccion(), ((Contacto)contactos.get(j)).getTelefonos(), ((Contacto)contactos.get(j)).getEmail(), ((Contacto)contactos.get(j)).getComentario() };
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void cargaFilasDeContactos(DefaultTableModel modelo, ResultSet rs)
	{
		Contacto cont = new Contacto();
		rs = cont.getDatosCt();
		try
		{
			int numeroFila = 0;
			while (rs.next())
			{
				ResultSetMetaData metaDatos = rs.getMetaData();

				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[10];
				for (int i = 0; i < numeroColumnas; i++) {
					datosFila[i] = rs.getObject(i + 1);
				}
				Object[] datosUtiles = { datosFila[1], datosFila[2], datosFila[3], datosFila[4], datosFila[5] };
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void actualizarTabla(ResultSet rs, DefaultTableModel modelo, JTable tabla, JScrollPane scroll, int tipo)
	{
		try
		{
			vaciaFilasModelo(modelo);
			
			switch (tipo) { 
			case 0: 
				TablaAgM.cargaFilasDeAgentes(modelo, rs, agente);
				break; 
			case 1: 
				TablaAg08.cargaFilasDeAgentes(modelo,rs);
			break; 
			case 2: 
				TablaCt.cargaFilasDeContactos(modelo, rs); 
				break; 
			default: 
				break; 
			}

/*

			if (tipo==0) {
				//cargaFilasDeAgentes(modelo, rs);
			  TablaAgM.cargaFilasDeAgentes(modelo, rs, agente); } 
			  else {
			  cargaFilasDeContactos(modelo, rs); }
			 */
			tabla.setModel(modelo);
			scroll.setViewportView(tabla);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setDiaL(DiaLaboral diaL)
	{
		this.diaL = diaL;
	}

	public DiaLaboral getDiaL()
	{
		return this.diaL;
	}

	private class ModeloNoEditable
	extends DefaultTableModel
	{
		private static final long serialVersionUID = 1L;

		private ModeloNoEditable() {}

		public boolean isCellEditable(int row, int col)
		{
			return true;
		}
	}
}