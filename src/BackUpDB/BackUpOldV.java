package BackUpDB;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import Horarios.AgenteGsi;
import Horarios.Conectarse;
import Horarios.DiaLaboral;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

public class BackUpOldV extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user;
	private String pass;
	private String url;
	
	
	private JPanel panel2;
	private ImageIcon banner;
	private JLabel jLabel_IL;
	private JPanel panel;
	private JTextField textFieldPuerto;
	private JLabel labelPort;
	private JLabel labelServer;
	private JTextField textUsuario;
	private JPasswordField jPasswordField1;
	private JLabel nombreUsuario;
	private JLabel contraseña;
	private JTextField jTextField1;
	private JButton buttonConectar;
	private JButton buttonCancelar;
	
	//Set Look & Feel
	{
		
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public BackUpOldV(){
		super("BackUp");
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
		banner=new ImageIcon(getClass().getResource("/Imagenes/banner backup.jpg"));

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
								Object[] options ={"Ok","Cancelar"};
								n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexión exitosa!</i><br>Se respaldarán los datos de su base </br></center></html>","Back Up",JOptionPane.YES_NO_OPTION,
									    JOptionPane.QUESTION_MESSAGE,
									    null,     //do not use a custom Icon
									    options,  //the titles of buttons
									    options[0]);
								
							}
							if (n==0){
								setVisible(false);
								new DiaLaboral();
								new AgenteGsi();
								leerAgentes();
								/*for (int i = 0; i < agentes.size(); i++) {
									AgenteGsiDAO.cargarAgente(agentes.get(i));
									
								}
								for (int i = 0; i < contactos.size(); i++) {
									ContactoDAO.cargarContacto(contactos.get(i));
								}*/
								JOptionPane.showMessageDialog(null,"<html><center>La operación se produjo con éxito!!!</center></html>","Back Up",JOptionPane.INFORMATION_MESSAGE);
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
							
							Object[] options ={"Ok","Cancelar"};
							n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexión exitosa!</i><br>Se respaldarán los datos de su base </br></center></html>","Back Up",JOptionPane.YES_NO_OPTION,
								    JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    options,  //the titles of buttons
								    options[0]);
							
						}
						if (n==0){
							setVisible(false);
							new DiaLaboral();
							new AgenteGsi();
							leerAgentes();
							/*for (int i = 0; i < agentes.size(); i++) {
								AgenteGsiDAO.cargarAgente(agentes.get(i));
								
							}
							for (int i = 0; i < contactos.size(); i++) {
								ContactoDAO.cargarContacto(contactos.get(i));
							}*/
							JOptionPane.showMessageDialog(null,"<html><center>La operación se produjo con éxito!!!</center></html>","Back Up",JOptionPane.INFORMATION_MESSAGE);
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
						Object[] options ={"Ok","Cancelar"};
						n=JOptionPane.showOptionDialog(null,"<html><center><i>Conexión exitosa!</i><br>Se respaldarán los datos de su base </br></center></html>","Back Up",JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
							    null,     //do not use a custom Icon
							    options,  //the titles of buttons
							    options[0]);
						
					}
					if (n==0){
						setVisible(false);
						new DiaLaboral();
						new AgenteGsi();
						leerAgentes();
						/*for (int i = 0; i < agentes.size(); i++) {
							AgenteGsiDAO.cargarAgente(agentes.get(i));
							
						}
						for (int i = 0; i < contactos.size(); i++) {
							ContactoDAO.cargarContacto(contactos.get(i));
						}*/
						JOptionPane.showMessageDialog(null,"<html><center>La operación se produjo con éxito!!!</center></html>","Back Up",JOptionPane.INFORMATION_MESSAGE);
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
	

	private void leerAgentes() {
		// TODO Auto-generated method stub
		File ruta;
		AgenteGsi agente=new AgenteGsi();
		String ag;
		String accion="select * from agentes;";
		Conectarse con=new Conectarse("monitoreo");
		ResultSet rs;
		try{
			//ruta = new File(getClass().getResource("/cfg").toURI());
			ruta = new File(System.getProperty("user.dir")+"/backup");
			ruta.mkdirs();

			File archivo=new File(ruta, "backAg.csv");


			FileWriter out = new FileWriter(archivo);
			Statement st=con.getCon().createStatement();
			rs = st.executeQuery("use monitoreo;");
			rs=st.executeQuery(accion);
			while (rs.next()){

				agente.setNombreYapellido(rs.getString(2));
				agente.setDireccion(rs.getString(3));
				agente.setLegajo(rs.getLong(4));
				agente.setTipoDni(rs.getString(5));
				agente.setDni(rs.getLong(6));
				agente.setTelefonos(rs.getString(7));
				String nomG=rs.getString(8);
				agente.setNombreGrupo(nomG);
				ag=agente.getLegajo()+"; "+agente.getNombreyApellido()+"; "+agente.getTipoDni()+"; "+agente.getDni()+"; "+
				agente.getDireccion()+"; "+agente.getTelefono()+";  ; "+agente.getNombreGrupo();
				
				out.write(ag+System.getProperty("line.separator"));
				
			}
			
			out.close();
						
			con.cerrar();
		}catch(SQLException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

	}


}
