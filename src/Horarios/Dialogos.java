package Horarios;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;



/**
* 
*/
public class Dialogos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton botonConsultar;
	//private DiaLaboral diaL;
	private JLabel labelFechaConsulta;
	private JComboBox jComboBoxGrupoTrab;
	private JLabel labelNomYapTrab;
	private JComboBox jComboNomYap;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private AgenteGsiDAO agenteD=new AgenteGsiDAO("java");
	private JTextField textDia;
	private JLabel labelGrupoTrab;
	
	private String user;
	private String pass="java";
	private String url;
	private JPanel panel;
	private JLabel jLabel_IL;
	private JPanel panel2;
	private ImageIcon banner;
	private JLabel labelPort;
	private JButton buttonCancelar;
	private JButton buttonConectar;
	private JTextField textFieldPuerto;
	private JLabel labelServer;
	private JTextField jTextField1;
	private JTextField textUsuario;
	private JLabel contraseña;
	private JLabel nombreUsuario;
	//private AgenteGsi agenteGsiD;
	//private DiaLaboral diaLab;
	private JPasswordField jPasswordField1;
		
	
	public Dialogos(){
		//launchDialog();
		configBaseD();
	}
	
	public void launchDialog(){
	try{
		setTitle("Consulta de turno");
		setVisible(true);
		setLocation(500, 300);
		this.setPreferredSize(new java.awt.Dimension(314, 222));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	{
		JPanel panelDiagTrab=new JPanel(new GridBagLayout());
		AnchorLayout panelLayout = new AnchorLayout();
		panelDiagTrab.setLayout(panelLayout);
		panelDiagTrab.setPreferredSize(new java.awt.Dimension(305, 194));
		getContentPane().add(panelDiagTrab, BorderLayout.NORTH);
		{
			textDia = new JTextField();
			panelDiagTrab.add(textDia, new AnchorConstraint(507, 832, 621, 514, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			textDia.setPreferredSize(new java.awt.Dimension(98, 22));
		}

			{
				labelNomYapTrab=new JLabel("Nombre y Apellido:");
				panelDiagTrab.add(labelNomYapTrab, new AnchorConstraint(162, 475, 234, 121, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				BoxLayout labelNomLayout=new BoxLayout(labelNomYapTrab, BoxLayout.X_AXIS);
				labelNomYapTrab.setLayout(labelNomLayout);
				labelNomYapTrab.setPreferredSize(new java.awt.Dimension(109, 14));
			}
			{
				jComboNomYap=new JComboBox();
				panelDiagTrab.add(jComboNomYap, new AnchorConstraint(141, 832, 260, 514, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				rs=AgenteGsiDAO.datosAgentes();
				jComboNomYap.setPreferredSize(new java.awt.Dimension(98, 23));
				int numeroFila = 0;
		        try
		        {
		            while (rs.next())
		            {
		            	ResultSetMetaData metaDatos = rs.getMetaData();
		                
		                int numeroColumnas = metaDatos.getColumnCount();
		                Object[] datosFila = new Object[10];
		                
		                for (int i = 0; i < numeroColumnas; i++){
		                    datosFila[i] = rs.getObject(i + 1);
		                }
		                
		                Object datosUtiles=datosFila[1];
		                jComboNomYap.addItem(datosUtiles);
		                numeroFila++;
		            }
		            rs.close();
		        } catch (Exception e)
		        {
		            e.printStackTrace();
		        }
			}				
			{
				labelGrupoTrab=new JLabel("Grupo: ");
				panelDiagTrab.add(labelGrupoTrab, new AnchorConstraint(342, 475, 435, 329, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				labelGrupoTrab.setPreferredSize(new java.awt.Dimension(45, 18));
			}
			{
				jComboBoxGrupoTrab = new JComboBox();
				panelDiagTrab.add(jComboBoxGrupoTrab, new AnchorConstraint(342, 654, 445, 514, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jComboBoxGrupoTrab.addItem("A");
				jComboBoxGrupoTrab.addItem("B");
				jComboBoxGrupoTrab.addItem("C");
				jComboBoxGrupoTrab.addItem("D");
				jComboBoxGrupoTrab.addItem("E");
				jComboBoxGrupoTrab.addItem("F");
				jComboBoxGrupoTrab.setPreferredSize(new java.awt.Dimension(43, 20));
			}
			{
				labelFechaConsulta=new JLabel("<html><center>Fecha a consultar: <br>(formato dd/mm/aaaa)</CENTER></html>");
				panelDiagTrab.add(labelFechaConsulta, new AnchorConstraint(93, 162, 66, 20, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS));
				labelFechaConsulta.setPreferredSize(new java.awt.Dimension(126, 35));
			}
			{
				
				botonConsultar=new JButton("Consultar");
				panelDiagTrab.add(botonConsultar, new AnchorConstraint(724, 852, 873, 514, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				botonConsultar.setPreferredSize(new java.awt.Dimension(104, 29));
				botonConsultar.addActionListener(new ActionListener() {
					
					
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println(textDia.getText());
						System.out.println(Encrip.encriptar(textDia.getText()));
						System.out.println(Encrip.encriptar(textDia.getText()).equals(Encrip.encriptar(pass)));
							
						//configBaseD();
						
					}
				});
			}
			
		
	}
	pack();
	this.setSize(314, 222);
	setResizable(false);
	isModal();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	
	public void configBaseD(){
		
		final JDialog dialogoConfigBD=new JDialog();
		dialogoConfigBD.setVisible(true);
		dialogoConfigBD.setLocation(500, 300);
		dialogoConfigBD.setPreferredSize(new java.awt.Dimension(314, 222));
		
		panel2=new JPanel();
		panel2.setPreferredSize(new java.awt.Dimension(312, 239));
		AnchorLayout panelLayout = new AnchorLayout();
		panel2.setLayout(panelLayout);
		dialogoConfigBD.setResizable(false);
		dialogoConfigBD.getContentPane().add(panel2, BorderLayout.SOUTH);
		banner=new ImageIcon(getClass().getResource("/Imagenes/bannerchico.jpg"));

		{
			jLabel_IL = new JLabel(banner);
			dialogoConfigBD.getContentPane().add(jLabel_IL);
			jLabel_IL.setPreferredSize(new java.awt.Dimension(108, 22));
		}
		
		{
			panel = new JPanel();
			panel2.add(panel, new AnchorConstraint(19, 969, 776, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			panel.setPreferredSize(new java.awt.Dimension(292, 187));
			panel.setLayout(panelLayout);
			panel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createTitledBorder("Conexión a servidor MySQL")));

			{
				textFieldPuerto = new JTextField();
				panel.add(textFieldPuerto, new AnchorConstraint(306, 960, 395, 806, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				textFieldPuerto.setText("3306");
				textFieldPuerto.setPreferredSize(new java.awt.Dimension(45, 16));
			}
			{
				labelPort = new JLabel();
				panel.add(labelPort, new AnchorConstraint(323, 792, 378, 666, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				labelPort.setText("Puerto:");
				labelPort.setPreferredSize(new java.awt.Dimension(37, 10));
			}
			{
				labelServer = new JLabel();
				panel.add(labelServer, new AnchorConstraint(317, 251, 383, 42, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				labelServer.setText("Server Host:");
				labelServer.setPreferredSize(new java.awt.Dimension(61, 12));
			}
			{
				textUsuario = new JTextField("root");
				panel.add(textUsuario, new AnchorConstraint(511, 631, 610, 316, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				textUsuario.setPreferredSize(new java.awt.Dimension(92, 18));
			}
			{
				jPasswordField1 = new JPasswordField();
				panel.add(jPasswordField1, new AnchorConstraint(748, 631, 842, 316, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPasswordField1.setPreferredSize(new java.awt.Dimension(92, 17));
				jPasswordField1.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(KeyEvent arg0) {
						// TODO Auto-generated method stub
						if(arg0.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
							int n=0;
							String url1="jdbc:mysql://";
							url=url1+jTextField1.getText()+":"+textFieldPuerto.getText();
							user=textUsuario.getText();
							char[] passc=jPasswordField1.getPassword();
							pass=new String();
							for (int i = 0; i < passc.length; i++) {
								pass+=passc[i];
							}
							Conectarse conect=new Conectarse();
							if (conect.exito()){
								Object[] options ={"Cargar","Cancelar"};
								n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexión exitosa!</i><br>Se cargaran a su base de datos <br>un grupo de agentes automáticamente</center></html>","Carga de agentes",JOptionPane.YES_NO_OPTION,
									    JOptionPane.QUESTION_MESSAGE,
									    null,     //do not use a custom Icon
									    options,  //the titles of buttons
									    options[0]);
								
							}/*
							if (n==0){
								dialogoConfigBD.dispose();
								diaLab=new DiaLaboral();
								agenteGsiD = new AgenteGsi();
								agentes();
								for (int i = 0; i < agentes.size(); i++) {
									AgenteGsiDAO.cargarAgente(agentes.get(i));
								}
								JOptionPane.showMessageDialog(null,"<html><center>La carga se produjo con éxito!!!</center></html>","Carga Automática",JOptionPane.INFORMATION_MESSAGE);
								System.exit(0);
							}*/
						}
						
					}
				});
			}
			{
				nombreUsuario = new JLabel();
				panel.add(nombreUsuario, new AnchorConstraint(544, 268, 610, 118, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				nombreUsuario.setText("Usuario:");
				nombreUsuario.setPreferredSize(new java.awt.Dimension(44, 12));
			}
			{
				contraseña = new JLabel();
				panel.add(contraseña, new AnchorConstraint(748, 251, 809, 42, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				contraseña.setText("Contraseña:");
				contraseña.setPreferredSize(new java.awt.Dimension(61, 11));
			}
			{
				jTextField1 = new JTextField();
				panel.add(jTextField1, new AnchorConstraint(301, 631, 400, 316, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField1.setText("localhost");
				jTextField1.setPreferredSize(new java.awt.Dimension(92, 18));
			}
			
		}
		{
			buttonConectar = new JButton();
			panel2.add(buttonConectar, new AnchorConstraint(823, 607, 917, 328, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			buttonConectar.setText("Ok");
			buttonConectar.setPreferredSize(new java.awt.Dimension(87, 22));
			buttonConectar.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					if(arg0.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
						int n=0;
						String url1="jdbc:mysql://";
						url=url1+jTextField1.getText()+":"+textFieldPuerto.getText();
						user=textUsuario.getText();
						char[] passc=jPasswordField1.getPassword();
						pass=new String();
						for (int i = 0; i < passc.length; i++) {
							pass+=passc[i];
						}
						Conectarse conect=new Conectarse();
						if (conect.exito()){
							Object[] options ={"Cargar","Cancelar"};
							n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexión exitosa!</i><br>Se cargaran a su base de datos <br>un grupo de agentes automáticamente</center></html>","Carga de agentes",JOptionPane.YES_NO_OPTION,
								    JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    options,  //the titles of buttons
								    options[0]);
							
						}
						/*if (n==0){
							setVisible(false);
							diaLab=new DiaLaboral();
							agenteGsiD = new AgenteGsi();
							agentes();
							for (int i = 0; i < agentes.size(); i++) {
								AgenteGsiDAO.cargarAgente(agentes.get(i));
							}
							JOptionPane.showMessageDialog(null,"<html><center>La carga se produjo con éxito!!!</center></html>","Carga Automática",JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}*/
					}
					
				}
			});
			buttonConectar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int n=0;
					String url1="jdbc:mysql://";
					url=url1+jTextField1.getText()+":"+textFieldPuerto.getText();
					user=textUsuario.getText();
					char[] passc=jPasswordField1.getPassword();
					pass=new String();
					for (int i = 0; i < passc.length; i++) {
						pass+=passc[i];
					}
					Conectarse conect=new Conectarse();
					if (conect.exito()){
						guardarDatos(url, user, pass);
						Object[] options ={"Cargar","Cancelar"};
						n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexión exitosa!</i><br>Se cargaran a su base de datos <br>un grupo de agentes automáticamente</center></html>","Carga de agentes",JOptionPane.YES_NO_OPTION,
							    JOptionPane.QUESTION_MESSAGE,
							    null,     //do not use a custom Icon
							    options,  //the titles of buttons
							    options[0]);
						
					}
				/*	if (n==0){
						setVisible(false);
						diaLab=new DiaLaboral();
						agenteGsiD = new AgenteGsi();
						agentes();
						for (int i = 0; i < agentes.size(); i++) {
							AgenteGsiDAO.cargarAgente(agentes.get(i));
						}
						JOptionPane.showMessageDialog(null,"<html><center>La carga se produjo con éxito!!!</center></html>","Carga Automática",JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}*/
				}
			});
			
		}
		{
			buttonCancelar = new JButton();
			panel2.add(buttonCancelar, new AnchorConstraint(822, 953, 918, 639, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			buttonCancelar.setText("Cancelar");
			buttonCancelar.setPreferredSize(new java.awt.Dimension(98, 23));
			buttonCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dialogoConfigBD.dispose();
				}
			});
			
		}
		dialogoConfigBD.pack();
		dialogoConfigBD.setSize(411, 400);

		
	}
	
	public void guardarDatos(String host, String user, String pass){
		
		File ruta;
				
		try {
			ruta = new File(getClass().getResource("/cfg").toURI());
			
			File archivo=new File(ruta, "cfg.jcdb");
			
			
			FileWriter out = new FileWriter(archivo);
			
				out.write(host+System.getProperty("line.separator"));
				out.write(user+System.getProperty("line.separator"));
				out.write(Encrip.encriptar(pass)+System.getProperty("line.separator"));
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
	
	public static void main(String args[]){
		/*Dialogos diag=new Dialogos();
		diag.guardarDatos("jdbc:mysql://localhost:3306/", "root", "java");*/
		String rutaCopia="\\lib\\ext";
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("java.home")+rutaCopia);
		System.out.println(System.getProperty("%systemroot%"));
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		
		String variablesDelSistema = "" +
		   "Archivo del programa: " +System.getProperty("user.dir") + "\n" +
		   "CPU: " +System.getProperty("sun.cpu.isalist") + "\n" +
		   "Usuario: " +System.getProperty("user.name") + "\n" +
		   "Idioma: " +System.getProperty("user.language") + "\n" +
		   "Encoding: " +System.getProperty("sun.jnu.encoding") + "\n" +
		   "Tempdir: " +System.getProperty("java.io.tmpdir") + "\n"+
		   "Java CLASS path: " +System.getProperty("java.class.path") + "\n"+
		   "Java path: " +System.getProperty("sun.boot.class.path") + "\n"+
		   "Java LIB path: " +System.getProperty("java.ext.dirs") + "\n"+
	           "Windows path: " + System.getProperty("java.library.path") + "\n" +

		   "Version de Java: " +System.getProperty("java.vm.version") + "\n"+
		   "Vendor: " + System.getProperty("java.vm.vendor") + " - " +
		   "Web:" + System.getProperty("java.vendor.url") + "\n"+
		   "Version de Windows: " + System.getProperty("os.name") + " " +
			 System.getProperty("os.version") + " " +
		   System.getProperty("sun.os.patch.level") + "\n"+
		   "OS architectura: " +System.getProperty("os.arch") + "\n";

		   System.out.println(variablesDelSistema);
	}

}
