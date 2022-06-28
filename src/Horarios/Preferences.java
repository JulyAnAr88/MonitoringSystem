package Horarios;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;


/**
* Permite configuar algunos aspectos
*/
public class Preferences extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String user;
	private String pass;
	private String url;
	//private JPanel panel2;
	private ImageIcon banner;
	private JLabel jLabelBanner;
	//private JPanel panel;
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
	private JSplitPane panelSplit=null;
	private static JTree arbol;

	static int i=0;
	DefaultMutableTreeNode raiz,raiz2,rama,seleccion;
	DefaultTreeModel modelo,modelo2;

	private JButton botonAceptar;
	
	//Set Look & Feel
	{
		
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Preferences(){
		//super("Configuración");
		lanzarFrame();
	}
	
	
	private void lanzarFrame() {
		try {
			{				
				this.setSize(484, 300);
				setLocation(400, 100);
			//	setResizable(false);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		 final JPanel panelIzq= new JPanel();
		panelIzq.setPreferredSize(new java.awt.Dimension(322, 300));
		{
			banner=new ImageIcon(getClass().getResource("/Imagenes/bannerchico.jpg"));
			jLabelBanner = new JLabel(banner);
			jLabelBanner.setPreferredSize(new java.awt.Dimension(322, 120));
		}
		AnchorLayout panelLayout = new AnchorLayout();
		panelIzq.setLayout(panelLayout);
		panelIzq.add(jLabelBanner, new AnchorConstraint(-8, 1001, 391, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		panelIzq.add(configBaseD(panelIzq), new AnchorConstraint(380, 1001,1000, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		panelIzq.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createTitledBorder("")));

		panelSplit=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		panelSplit.setPreferredSize(new java.awt.Dimension(580, 380));
		panelSplit.setContinuousLayout(true);
		panelSplit.setOneTouchExpandable(false);
		
		

		raiz = new DefaultMutableTreeNode( "Mis Cosas" );
		crearNodos(raiz);
		arbol = new JTree(raiz);
		arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		final JScrollPane treeView = new JScrollPane(arbol);
		treeView.setPreferredSize(new Dimension(150, 300));
		final JScrollPane panelI=new JScrollPane(panelIzq);
		panelI.setPreferredSize(new java.awt.Dimension(447, 300));
		
		arbol.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				// TODO Auto-generated method stub
				raiz = 
	                (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
				System.out.println(raiz);//
				if (raiz == null) return;
				if (raiz.equals("Base de Datos")){
					panelIzq.add(configBaseD(panelIzq), new AnchorConstraint(380, 1001,1000, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					panelIzq.add(jLabelBanner, new AnchorConstraint(-8, 1001, 391, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					panelIzq.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createTitledBorder("")));	
					panelI.add(panelIzq);

					panelSplit.setRightComponent(panelI);

					panelSplit.setLeftComponent(treeView);

					panelSplit.setDividerLocation(150);

					getContentPane().add(panelSplit);}
				/*//panelIzq.repaint();

				treeView.repaint();

				repaint();*/
			}
		});
		
		/*arbol.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				panelIzq.add(configBaseD(panelIzq), new AnchorConstraint(380, 1001,1000, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				panelIzq.repaint();
				treeView.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				panelIzq.add(configBaseD(panelIzq), new AnchorConstraint(380, 1001,1000, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				panelIzq.repaint();
				treeView.repaint();
			}
		})
		;*/
		
		
		
		//treeView.setMinimumSize(new Dimension(100,50));
		//panelI.setMinimumSize(new Dimension(100,150));
		
		
		
		modelo =(DefaultTreeModel)arbol.getModel();
		
		panelSplit.setLeftComponent(treeView);
		
		panelSplit.setRightComponent(panelI);
		panelSplit.setDividerLocation(150);
		//arbol = new JTree(raiz2);
		
		getContentPane().add(panelSplit);
		
        setVisible(true);
        pack();


	}

	/**
	 * Crea las categorias del arbol de preferencias
	 * 
	 * @param top
	 */
	private void crearNodos(DefaultMutableTreeNode top) {
		
		DefaultMutableTreeNode category = null;
        //DefaultMutableTreeNode book = null;

        category = new DefaultMutableTreeNode("Base de Datos");
        top.add(category);
        
        category = new DefaultMutableTreeNode("Respaldo");
        top.add(category);
        
        
        
        /*
          Cambiamos los iconos
        DefaultTreeCellRenderer render= (DefaultTreeCellRenderer)tree.getCellRenderer();
        render.setLeafIcon(new ImageIcon("d:/futbol.gif"));
        render.setOpenIcon(new ImageIcon("d:/hombre.gif"));
        render.setClosedIcon(new ImageIcon("d:/viejo.gif"));
         
          
          rama de la configuracion de la base de datos
        book = new DefaultMutableTreeNode(new BookInfo
                ("titulo rama",
                supongo que accion));
            category.add(book);*/
        
	}
	

	/**
	 * Configurar acceso a la db
	 * 
	 */
	public JPanel configBaseD(JPanel panel){

		/*
		dialogoConfigBD.setLocation(500, 300);
		dialogoConfigBD.setPreferredSize(new java.awt.Dimension(314, 222));*/
		JPanel panel2;

		panel2=new JPanel();
		panel2.setPreferredSize(new java.awt.Dimension(330, 300));
		AnchorLayout panelLayout = new AnchorLayout();
		panel2.setLayout(panelLayout);

		{
			panel = new JPanel();
			panel2.add(panel, new AnchorConstraint(19, 969, 776, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			panel.setPreferredSize(new java.awt.Dimension(292, 187));
			panel.setLayout(panelLayout);
			panel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createTitledBorder("Conexión a servidor")));

			{
				textFieldPuerto = new JTextField();
				panel.add(textFieldPuerto, new AnchorConstraint(306, 960, 395, 806, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				textFieldPuerto.setText("3306");
				textFieldPuerto.setPreferredSize(new java.awt.Dimension(45, 20));
			}
			{
				labelPort = new JLabel();
				panel.add(labelPort, new AnchorConstraint(323, 792, 378, 666, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				labelPort.setText("Puerto:");
				labelPort.setPreferredSize(new java.awt.Dimension(37, 10));
			}
			{
				labelServer = new JLabel();
				panel.add(labelServer, new AnchorConstraint(317, 251, 383, 62, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
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
						// TODO Auto-generated method stub  int n=0;
						if(arg0.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
							
							String url1="jdbc:mysql://";
							url=url1+jTextField1.getText()+":"+textFieldPuerto.getText();
							user=textUsuario.getText();
							char[] passc=jPasswordField1.getPassword();
							pass=new String();
							for (int i = 0; i < passc.length; i++) {
								pass+=passc[i];
							}
							guardarDatos(url, user, pass);
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
				panel.add(contraseña, new AnchorConstraint(748, 251, 809, 62, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
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
			botonAceptar=new JButton();
			panel2.add(botonAceptar, new AnchorConstraint(821, 489, 918, 292, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			botonAceptar.setText("Aceptar");
			botonAceptar.setPreferredSize(new java.awt.Dimension(65, 29));
			botonAceptar.addKeyListener(new KeyListener() {
				
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
						
						String url1="jdbc:mysql://";
						url=url1+jTextField1.getText()+":"+textFieldPuerto.getText();
						user=textUsuario.getText();
						char[] passc=jPasswordField1.getPassword();
						pass=new String();
						for (int i = 0; i < passc.length; i++) {
							pass+=passc[i];
						}
						guardarDatos(url, user, pass);
						
					}
					dispose();
				}
			});
			
			botonAceptar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub

						String url1="jdbc:mysql://";
						url=url1+jTextField1.getText()+":"+textFieldPuerto.getText();
						user=textUsuario.getText();
						char[] passc=jPasswordField1.getPassword();
						pass=new String();
						for (int i = 0; i < passc.length; i++) {
							pass+=passc[i];
						guardarDatos(url, user, pass);

					}
					dispose();
				}
			});
		}
		{
			buttonConectar = new JButton();
			panel2.add(buttonConectar, new AnchorConstraint(821, 686, 918, 507, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			buttonConectar.setText("Aplicar");
			buttonConectar.setPreferredSize(new java.awt.Dimension(59, 29));
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
					// TODO Auto-generated method stub  int n=0;
					
					if(arg0.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
						
						String url1="jdbc:mysql://";
						url=url1+jTextField1.getText()+":"+textFieldPuerto.getText();
						user=textUsuario.getText();
						char[] passc=jPasswordField1.getPassword();
						pass=new String();
						for (int i = 0; i < passc.length; i++) {
							pass+=passc[i];
						}
						guardarDatos(url, user, pass);
						
					}

				}
			});
			buttonConectar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub  int n=0;
					
					String url1="jdbc:mysql://";
					url=url1+jTextField1.getText()+":"+textFieldPuerto.getText();
					user=textUsuario.getText();
					char[] passc=jPasswordField1.getPassword();
					pass=new String();
					for (int i = 0; i < passc.length; i++) {
						pass+=passc[i];
					}
					guardarDatos(url, user, pass);
				}
			});
			
			buttonCancelar= new JButton();
			panel2.add(buttonCancelar, new AnchorConstraint(821, 895, 918, 716, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			buttonCancelar.setText("Cerrar");
			buttonCancelar.setPreferredSize(new java.awt.Dimension(59, 29));
			buttonCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					dispose();
				}
			});

		}
		panel.repaint();
		return panel2;
		

	}
	
	/**
	 * Guardará en un archivo los datos ingresados por parámetro
	 * 
	 * @param host
	 * @param user
	 * @param pass
	 */

	public void guardarDatos(String host, String user, String pass){

		File ruta;

		try {
			//ruta = new File(getClass().getResource("/cfg").toURI());
			ruta = new File(System.getProperty("user.dir")+"/cfg");
			ruta.mkdirs();

			File archivo=new File(ruta, "cfg.jcdb");


			FileWriter out = new FileWriter(archivo);

			out.write(host+System.getProperty("line.separator"));
			out.write(user+System.getProperty("line.separator"));
			out.write(pass+System.getProperty("line.separator"));
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
        JDialog frame = new Preferences();
 
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);} 
        });  
 
        frame.pack();
        frame.setVisible(true);
    }/**/
	
		

}
