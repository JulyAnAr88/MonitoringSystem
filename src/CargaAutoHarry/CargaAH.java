package CargaAutoHarry;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Horarios.AgenteCerocho;
import Horarios.AgenteCerochoDAO;
import Horarios.AgenteGsi;
import Horarios.AgenteGsiDAO;
import Horarios.Conectarse;
import Horarios.Contacto;
import Horarios.ContactoDAO;
import Horarios.DiaLaboral;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

/**
 * Carga autom�tica de agentes presentes al 05/2011
 * @author Juli�n Arag�n
 *
 */
public class CargaAH extends JFrame{

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private static final long serialVersionUID = 1L;
	private String user;
	private String pass;
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
	private JLabel contrasenia;
	private JLabel nombreUsuario;
	private JPasswordField jPasswordField1;
	private ArrayList<AgenteGsi> agentes=new ArrayList<AgenteGsi>(11);
	private ArrayList<Contacto> contactos=new ArrayList<Contacto>(4);
	private ArrayList<AgenteCerocho> agCero=new ArrayList<AgenteCerocho>(4);
	
	public CargaAH() {
		// TODO Auto-generated constructor stub
		super("Carga Autom�tica de Harries");
		iniciar();
	}
	
	public void iniciar(){

		panel2=new JPanel();
		setLocation(450, 200);
		panel2.setPreferredSize(new java.awt.Dimension(312, 239));
		AnchorLayout panelLayout = new AnchorLayout();
		panel2.setLayout(panelLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().add(panel2, BorderLayout.SOUTH);
		banner=new ImageIcon(getClass().getResource("/Imagenes/bannerchico.jpg"));

		{
			jLabel_IL = new JLabel(banner);
			getContentPane().add(jLabel_IL);
			jLabel_IL.setPreferredSize(new java.awt.Dimension(108, 22));
		}
		setVisible(true);
		
		
		{
			panel= new JPanel();
			panel2.add(panel, new AnchorConstraint(19, 969, 776, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			panel.setPreferredSize(new java.awt.Dimension(292, 187));
			panel.setLayout(panelLayout);
			panel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createTitledBorder("Conexi�n a servidor MySQL")));

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
							setUrl(url1+jTextField1.getText()+":"+textFieldPuerto.getText());
							setUser(textUsuario.getText());
							char[] passc=jPasswordField1.getPassword();
							pass=new String();
							for (int i = 0; i < passc.length; i++) {
								pass+=passc[i];
							}
							Conectarse conect=new Conectarse();
							conect.guardarDatos(url, user, pass);
							if (conect.exito()){
								Object[] options ={"Cargar","Cancelar"};
								n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexi�n exitosa!</i><br>Se cargaran a su base de datos <br>un grupo de agentes autom�ticamente</center></html>","Carga de agentes",JOptionPane.YES_NO_OPTION,
									    JOptionPane.QUESTION_MESSAGE,
									    null,     //do not use a custom Icon
									    options,  //the titles of buttons
									    options[0]);
								
							}
							if (n==0){
								setVisible(false);
								new DiaLaboral();
								new AgenteGsi();new Contacto(); new AgenteCerocho();
								agentes();
								cerocho();
								contactos();
								for (int i = 0; i < agentes.size(); i++) {
									AgenteGsiDAO.cargarAgente(agentes.get(i));	
								}
								for (int i = 0; i < agCero.size(); i++) {
									AgenteCerochoDAO.cargarAgente(agCero.get(i));	
								}
								for (int i = 0; i < contactos.size(); i++) {
									ContactoDAO.cargarContacto(contactos.get(i));
								}
								JOptionPane.showMessageDialog(null,"<html><center>La carga se produjo con �xito!!!</center></html>","Carga Autom�tica",JOptionPane.INFORMATION_MESSAGE);
								System.exit(0);
							}
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
				contrasenia = new JLabel();
				panel.add(contrasenia, new AnchorConstraint(748, 251, 809, 42, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				contrasenia.setText("Contrase�a:");
				contrasenia.setPreferredSize(new java.awt.Dimension(61, 11));
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
						setUrl(url1+jTextField1.getText()+":"+textFieldPuerto.getText());
						setUser(textUsuario.getText());
						char[] passc=jPasswordField1.getPassword();
						pass=new String();
						for (int i = 0; i < passc.length; i++) {
							pass+=passc[i];
						}
						Conectarse conect=new Conectarse();
						conect.guardarDatos(url, user, pass);
						if (conect.exito()){
							
							Object[] options ={"Cargar","Cancelar"};
							n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexi�n exitosa!</i><br>Se cargaran a su base de datos <br>agentes y contactos b�sicos autom�ticamente</center></html>","Carga de datos",JOptionPane.YES_NO_OPTION,
								    JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    options,  //the titles of buttons
								    options[0]);
							
						}
						if (n==0){
							setVisible(false);
							new DiaLaboral();
							new AgenteGsi();
							new AgenteCerocho();
							new Contacto();
							
							agentes();
							contactos();
							cerocho();
							for (int i = 0; i < agentes.size(); i++) {
								AgenteGsiDAO.cargarAgente(agentes.get(i));								
							}
							for (int i = 0; i < agCero.size(); i++) {
								AgenteCerochoDAO.cargarAgente(agCero.get(i));	
							}
							for (int i = 0; i < contactos.size(); i++) {
								ContactoDAO.cargarContacto(contactos.get(i));
							}
							JOptionPane.showMessageDialog(null,"<html><center>La carga se produjo con �xito!!!</center></html>","Carga Autom�tica",JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}
					
				}
			});
			buttonConectar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int n=0;
					String url1="jdbc:mysql://";
					setUrl(url1+jTextField1.getText()+":"+textFieldPuerto.getText());
					setUser(textUsuario.getText());
					char[] passc=jPasswordField1.getPassword();
					pass=new String();
					for (int i = 0; i < passc.length; i++) {
						pass+=passc[i];
					}
					Conectarse conect=new Conectarse();
					conect.guardarDatos(url, user, pass);
					if (conect.exito()){
						Object[] options ={"Cargar","Cancelar"};
						n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexi�n exitosa!</i><br>Se cargaran a su base de datos <br>agentes y contactos b�sicos autom�ticamente</center></html>","Carga de datos",JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
							    null,     //do not use a custom Icon
							    options,  //the titles of buttons
							    options[0]);
						
					}
					if (n==0){
						setVisible(false);
						new DiaLaboral();
						new AgenteGsi();
						new AgenteCerocho();
						new Contacto();
						agentes();
						cerocho();
						//contactos();
						for (int i = 0; i < agentes.size(); i++) {
							AgenteGsiDAO.cargarAgente(agentes.get(i));
						}
						for (int i = 0; i < agCero.size(); i++) {
							AgenteCerochoDAO.cargarAgente(agCero.get(i));	
						}
						for (int i = 0; i < contactos.size(); i++) {
							ContactoDAO.cargarContacto(contactos.get(i));
						}
						JOptionPane.showMessageDialog(null,"<html><center>La carga se produjo con �xito!!!</center></html>","Carga Autom�tica",JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
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
					System.exit(0);
				}
			});
			
		}
		pack();
		this.setSize(411, 400);

	}
	
	public void agentes(){
		AgenteGsi a1=new AgenteGsi("Sosa, Ju�n Pablo",0,0,0,"","","","f");
		AgenteGsi a2=new AgenteGsi("Burgos, Leandro",0,0,0,"","","","d");
		AgenteGsi a13=new AgenteGsi("Ferrer, Claudio",0,0,0,"","","","f");
		AgenteGsi a3=new AgenteGsi("Arag�n, Juli�n",8,9,1988,"","","","a");
		AgenteGsi a4=new AgenteGsi("Ferr�n, Cesar",0,0,0,"","","","e");
		AgenteGsi a14=new AgenteGsi("Kurganoff, Ariel",0,0,0,"","","","e");
		AgenteGsi a5=new AgenteGsi("Martinet, Mariano",0,0,0,"","","","b");
		AgenteGsi a6=new AgenteGsi("Petit�n, Nicolas",0,0,0,"","","","b");
		AgenteGsi a15=new AgenteGsi("Fernandez, Facundo",0,0,0,"","","","c");
		AgenteGsi a7=new AgenteGsi("Cantero, Leonel",0,0,0,"","","","f");
		AgenteGsi a8=new AgenteGsi("Casco, �lvaro",0,0,0,"","","","b");
		AgenteGsi a16=new AgenteGsi("Coria, Matias",0,0,0,"","","","c");
		AgenteGsi a9=new AgenteGsi("Demarchi, Pablo",0,0,0,"","","","d");
		AgenteGsi a10=new AgenteGsi("Belucci, Ariel",0,0,0,"","","","a");
		AgenteGsi a17=new AgenteGsi("Barrera, Bel�n",0,0,0,"","","","e");
		AgenteGsi a11=new AgenteGsi("Stegman, Mauricio",0,0,0,"","","","a");
		AgenteGsi a12=new AgenteGsi("Lagrost, Sergio",0,0,0,"","","","d");
		AgenteGsi a18=new AgenteGsi("Torres, Leonardo",0,0,0,"","","","c");
		
		agentes.add(a1);
		agentes.add(a2);
		agentes.add(a3);
		agentes.add(a4);
		agentes.add(a5);
		agentes.add(a6);
		agentes.add(a7);
		agentes.add(a8);
		agentes.add(a9);
		agentes.add(a10);
		agentes.add(a11);
		agentes.add(a12);
		agentes.add(a13);
		agentes.add(a14);
		agentes.add(a15);
		agentes.add(a16);
		agentes.add(a17);
		agentes.add(a18);
		
		
	
	}
	
	public void contactos(){
		Contacto c1=new Contacto("Sosa, Fernando","","3425121491/3426122023","","");
		Contacto c2=new Contacto("Lorenzatto, Alfredo","","3426316030","","");
		Contacto c3=new Contacto("Principato, Luciano","","3425023398","","");
		Contacto c4=new Contacto("Inalb�n, Nelson","","342","","");
		Contacto c5=new Contacto("Velzi, Diego","","3426122027","","");
		
		contactos.add(c1);
		contactos.add(c2);
		contactos.add(c3);
		contactos.add(c4);
		contactos.add(c5);
		
	}
	
	public void cerocho(){
		AgenteCerocho ac1=new AgenteCerocho("�lvarez, �ngeles",0,0,0,"","","","a",2);
		AgenteCerocho ac2=new AgenteCerocho("Aguiar, Ileana",0,0,0,"","","","a", 1);
		AgenteCerocho ac3=new AgenteCerocho("De Feo, Betina",0,0,0,"","","","b",1);
		AgenteCerocho ac4=new AgenteCerocho("Paredes, Eugenia",0,0,0,"","","","b",2);
		
		agCero.add(ac1);
		agCero.add(ac2);
		agCero.add(ac3);
		agCero.add(ac4);
		
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

}
