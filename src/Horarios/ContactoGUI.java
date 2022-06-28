package Horarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
* 
*/
public class ContactoGUI {


	private JLabel labelNomYap;
	private JTextField textNomYap;
	private JLabel labelTel;
	private JTextField textTel;
	private JButton botonAgregar;
	private JButton botonCancelar;
	private JLabel labelDir;
	private JTextField textDir;
	private JTextArea textAreaComentario;
	private JLabel labelComentario;
	private JTable tabla=new JTable();
	private JScrollPane scrollP;
	private JPanel panelA;
	private DefaultTableModel modelo=new DefaultTableModel();
	//private JButton botonAceptar;
	private ResultSet rs;
	private JLabel labelEmail;
	private JTextField textEmail;
	private JScrollPane scrollArea;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void agregarDialog(JTable tabla, JScrollPane scrollP, DefaultTableModel modelo){
		this.tabla=tabla;
		this.modelo=modelo;
		this.scrollP=scrollP;
		try{
			final JDialog dialogoAgreg=new JDialog();
			dialogoAgreg.setTitle("Carga de nuevos contactos");
			dialogoAgreg.setVisible(true);
			dialogoAgreg.setLocation(500, 250);
			dialogoAgreg.setPreferredSize(new java.awt.Dimension(324, 313));

			{
				JPanel panelDiag=new JPanel(new GridBagLayout());
				panelDiag.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createTitledBorder("Datos")));
				AnchorLayout panelLayout = new AnchorLayout();
				panelDiag.setLayout(panelLayout);
				panelDiag.setPreferredSize(new java.awt.Dimension(318, 277));
				dialogoAgreg.getContentPane().add(panelDiag, BorderLayout.NORTH);
				{
					textAreaComentario = new JTextArea();
					textAreaComentario.setBorder(BorderFactory.createLineBorder(Color.gray));
					textAreaComentario.setLineWrap(true);
					textAreaComentario.setWrapStyleWord(true);
					textAreaComentario.setFont(new Font("TAHOMA",Font.PLAIN,12));
					scrollArea=new JScrollPane();
					scrollArea.setViewportView(textAreaComentario);
					panelDiag.add(scrollArea, new AnchorConstraint(611, 936, 800, 421, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					textAreaComentario.setPreferredSize(new java.awt.Dimension(130, 47));
					scrollArea.setPreferredSize(new java.awt.Dimension(167, 47));
					//panelDiag.add(textAreaComentario, new AnchorConstraint(615, 916, 788, 426, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					//textAreaComentario.setPreferredSize(new java.awt.Dimension(167, 48));
				}
				{
					labelComentario = new JLabel();
					panelDiag.add(labelComentario, new AnchorConstraint(611, 388, 676, 174, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelComentario.setText("Comentario:");
					labelComentario.setPreferredSize(new java.awt.Dimension(68, 18));
				}
				{
					labelNomYap=new JLabel("Nombre:");
					panelDiag.add(labelNomYap, new AnchorConstraint(77, 390, 178, 210, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout labelNomLayout=new BoxLayout(labelNomYap, BoxLayout.X_AXIS);
					labelNomYap.setLayout(labelNomLayout);
					labelNomYap.setPreferredSize(new java.awt.Dimension(52, 28));
				}
				{
					textNomYap=new JTextField(20);
					panelDiag.add(textNomYap, new AnchorConstraint(89, 918, 168, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textNomYap.setPreferredSize(new java.awt.Dimension(167, 20));
				}
				{
					labelDir=new JLabel("Dirección:");
					panelDiag.add(labelDir, new AnchorConstraint(360, 386, 460, 211, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelDir.setPreferredSize(new java.awt.Dimension(55, 24));
				}
				{
					textDir=new JTextField(15);
					panelDiag.add(textDir, new AnchorConstraint(377, 916, 460, 426, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textDir.setPreferredSize(new java.awt.Dimension(156, 23));
				}
				{
					labelTel=new JLabel("Telefonos:");
					panelDiag.add(labelTel, new AnchorConstraint(216, 367, 310, 192, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelTel.setPreferredSize(new java.awt.Dimension(55, 23));
				}
				{
					textTel=new JTextField(15);
					panelDiag.add(textTel, new AnchorConstraint(225, 916, 308, 426, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textTel.setPreferredSize(new java.awt.Dimension(156, 23));
				}
				{
					labelEmail=new JLabel("E-mail:");
					panelDiag.add(labelEmail, new AnchorConstraint(496, 385, 568, 250, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelEmail.setPreferredSize(new java.awt.Dimension(43, 20));
				}
				{
					textEmail=new JTextField(15);
					panelDiag.add(textEmail, new AnchorConstraint(507, 916, 579, 422, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textEmail.setPreferredSize(new java.awt.Dimension(157, 20));
				}
				{
					botonAgregar=new JButton("Agregar");
					panelDiag.add(botonAgregar, new AnchorConstraint(838, 628, 934, 358, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonAgregar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							agregarCt(dialogoAgreg);
						}

					});
					botonAgregar.addKeyListener(new KeyListener() {

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
							agregarCt(dialogoAgreg);

						}
					});
					botonAgregar.setPreferredSize(new java.awt.Dimension(85, 23));
				}
				{
					botonCancelar=new JButton("Cancelar");
					panelDiag.add(botonCancelar, new AnchorConstraint(838, 937, 934, 664, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonCancelar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dialogoAgreg.dispose();
						}
					});
					botonCancelar.setPreferredSize(new java.awt.Dimension(86, 23));
				}

			}


			dialogoAgreg.pack();
			dialogoAgreg.setSize(324, 313);
			dialogoAgreg.setResizable(false);
			dialogoAgreg.isModal();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public JScrollPane getScrollP() {
		return scrollP;
	}

	public void setScrollP(JScrollPane scrollP) {
		this.scrollP = scrollP;
	}

	public JPanel getPanelA() {
		return panelA;
	}

	public void setPanelA(JPanel panelA) {
		this.panelA = panelA;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

	public JPanel ficha(Contacto contacto,JPanel panelFicha,JTabbedPane pestaña) {
		// TODO Auto-generated method stub

		panelFicha=new JPanel(new GridBagLayout());
		AnchorLayout panelLayout = new AnchorLayout();
		panelFicha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Contacto")));
		panelFicha.setLayout(panelLayout);
		panelFicha.setPreferredSize(new java.awt.Dimension(304, 199));

		JLabel labelNombre=new JLabel("<html><b>Nombre:</b></html>");
		panelFicha.add(labelNombre, new AnchorConstraint(91, 327, 194, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNombre.setPreferredSize(new java.awt.Dimension(52, 15));

		JLabel nombre=new JLabel(contacto.getNombre());
		panelFicha.add(nombre, new AnchorConstraint(92, 972, 193, 333, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		nombre.setPreferredSize(new java.awt.Dimension(194, 20));

		JLabel labelDir=new JLabel("<html><b>Dirección:</b></html>");
		panelFicha.add(labelDir, new AnchorConstraint(216, 327, 283, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelDir.setPreferredSize(new java.awt.Dimension(82, 13));

		JLabel dir=new JLabel(contacto.getDireccion());
		panelFicha.add(dir, new AnchorConstraint(213, 972, 283, 333, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		dir.setPreferredSize(new java.awt.Dimension(194, 14));

		JLabel labelTel=new JLabel("<html><b>Teléfonos:</b></html>");
		panelFicha.add(labelTel, new AnchorConstraint(314, 327, 384, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTel.setPreferredSize(new java.awt.Dimension(63, 15));

		JLabel tel=new JLabel(contacto.getTelefonos());
		panelFicha.add(tel, new AnchorConstraint(314, 972, 384, 333, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		tel.setPreferredSize(new java.awt.Dimension(194, 14));
		{
			JLabel labelEmail=new JLabel("<html><b>E-mail:</b></html>");
			panelFicha.add(labelEmail, new AnchorConstraint(409, 327, 474, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelEmail.setPreferredSize(new java.awt.Dimension(47, 15));
		}
		{
			JLabel email=new JLabel(contacto.getEmail());
			panelFicha.add(email, new AnchorConstraint(409, 972, 474, 333, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			email.setPreferredSize(new java.awt.Dimension(194, 13));
		}
		{
			JLabel labelGrupo=new JLabel("<html><b>Comentario:</b></html>");
			panelFicha.add(labelGrupo, new AnchorConstraint(500, 345, 565, 57, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelGrupo.setPreferredSize(new java.awt.Dimension(69, 16));
		}
		{
			
			JTextArea grupo=new JTextArea(5,1);
			grupo.removeAll();

			grupo.setText(contacto.getComentario());
			grupo.setOpaque(true);
			grupo.setEditable(false);
			grupo.setLineWrap(true);
			grupo.setWrapStyleWord(true);
			grupo.setFont(new Font("TAHOMA", Font.PLAIN, 11));
			JScrollPane scroll=new JScrollPane();
			scroll.setBorder(null);
			//scroll.removeAll();
			scroll.add(grupo);
			panelFicha.add(grupo, new AnchorConstraint(497, 979, 977, 349, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			
			scroll.setPreferredSize(new java.awt.Dimension(80, 66));
		}

		pestaña.repaint();

		return panelFicha;
	}

	public void dialogoModificar(TablaCt tabla) {
		// TODO Auto-generated method stub
		final JDialog dialogoModif=new JDialog();
		final Contacto cont=new Contacto();


		//System.out.println((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
		cont.leerCt((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
		
		try{
			dialogoModif.setTitle("Modificación de contactos");
			dialogoModif.setVisible(true);
			dialogoModif.setLocation(500, 250);
			dialogoModif.setPreferredSize(new java.awt.Dimension(332, 311));
			{
				JPanel panelDiag=new JPanel(new GridBagLayout());
				AnchorLayout panelLayout = new AnchorLayout();
				panelDiag.setBorder(BorderFactory.createTitledBorder(
	                    BorderFactory.createTitledBorder("Datos")));
				panelDiag.setLayout(panelLayout);
				panelDiag.setPreferredSize(new java.awt.Dimension(324, 274));
				dialogoModif.getContentPane().add(panelDiag, BorderLayout.NORTH);
				
				{
					labelNomYap=new JLabel("Nombre:");
					panelDiag.add(labelNomYap, new AnchorConstraint(89, 384, 169, 211, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout labelNomLayout=new BoxLayout(labelNomYap, BoxLayout.X_AXIS);
					labelNomYap.setLayout(labelNomLayout);
					labelNomYap.setPreferredSize(new java.awt.Dimension(103, 22));
				}
				{
					textNomYap=new JTextField(20);
					textNomYap.setText(cont.getNombre());
					panelDiag.add(textNomYap, new AnchorConstraint(93, 934, 169, 421, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textNomYap.setPreferredSize(new java.awt.Dimension(167, 21));
				}
				
				{
					labelDir=new JLabel("Dirección:");
					panelDiag.add(labelDir, new AnchorConstraint(430, 384, 530, 211, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelDir.setPreferredSize(new java.awt.Dimension(56, 28));
				}
				{
					textDir=new JTextField(15);
					textDir.setText(cont.getDireccion());
					panelDiag.add(textDir, new AnchorConstraint(444, 936, 516, 421, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textDir.setPreferredSize(new java.awt.Dimension(167, 20));
				}
				{
					labelTel=new JLabel("Telefonos:");
					panelDiag.add(labelTel, new AnchorConstraint(212, 384, 287, 211, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelTel.setPreferredSize(new java.awt.Dimension(56, 21));
				}
				{
					textTel=new JTextField(15);
					textTel.setText(cont.getTelefonos());
					panelDiag.add(textTel, new AnchorConstraint(208, 936, 287, 421, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textTel.setPreferredSize(new java.awt.Dimension(167, 20));
				}
				{
					labelEmail=new JLabel("E-mail:");
					panelDiag.add(labelEmail, new AnchorConstraint(326, 388, 399, 253, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelEmail.setPreferredSize(new java.awt.Dimension(44, 20));
				}
				{
					textEmail=new JTextField(15);
					textEmail.setText(cont.getEmail());
					panelDiag.add(textEmail, new AnchorConstraint(330, 936, 401, 424, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textEmail.setPreferredSize(new java.awt.Dimension(167, 20));
				}
				{
					labelComentario=new JLabel("Comentario:");
					panelDiag.add(labelComentario, new AnchorConstraint(527, 384, 593, 170, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelComentario.setPreferredSize(new java.awt.Dimension(70, 18));
				}
				{
					textAreaComentario = new JTextArea(5,1);
					textAreaComentario.setText(cont.getComentario());
					textAreaComentario.setBorder(BorderFactory.createLineBorder(Color.gray));
					textAreaComentario.setLineWrap(true);
					textAreaComentario.setWrapStyleWord(true);
					textAreaComentario.setFont(new Font("TAHOMA",Font.PLAIN,12));
					scrollArea=new JScrollPane();
					scrollArea.setViewportView(textAreaComentario);
					panelDiag.add(scrollArea, new AnchorConstraint(541, 936, 661, 421, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					textAreaComentario.setPreferredSize(new java.awt.Dimension(130, 47));
					scrollArea.setPreferredSize(new java.awt.Dimension(167, 47));
				}
				{
					botonAgregar=new JButton("Modificar");
					panelDiag.add(botonAgregar, new AnchorConstraint(812, 628, 905, 356, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonAgregar.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
								
								modifCt(cont, dialogoModif);
						}
							
					});
					botonAgregar.addKeyListener(new KeyListener() {
						
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

								modifCt(cont, dialogoModif);
							}
							
						}
					});
					botonAgregar.setPreferredSize(new java.awt.Dimension(88, 26));
				}
				{
					botonCancelar=new JButton("Cancelar");
					panelDiag.add(botonCancelar, new AnchorConstraint(812, 933, 905, 662, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonCancelar.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dialogoModif.dispose();
						}
					});
					botonCancelar.setPreferredSize(new java.awt.Dimension(88, 26));
				}
				
			}
			
			dialogoModif.pack();
			dialogoModif.setSize(332, 311);
			dialogoModif.setResizable(false);
			dialogoModif.isModal();
		} catch(IndexOutOfBoundsException e) {
			
			e.printStackTrace();
		} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public void agregarCt(JDialog dialogoAgreg){

		Contacto cont=new Contacto();
		cont.setNombre(textNomYap.getText());
		cont.setDireccion(textDir.getText());
		cont.setTelefonos(textTel.getText());
		cont.setEmail(textEmail.getText());
		cont.setComentario(textAreaComentario.getText());

		rs=cont.getDatosCt();

		ContactoDAO.cargarContacto(cont);
		Maquetilla maq=new Maquetilla();
		//maq.actualizarTabla(rs, modelo, tabla,scrollP, cont);
						
		dialogoAgreg.dispose();
		maq.repaint();


		textNomYap.setText("");
		textDir.setText("");
		textTel.setText("");
		textEmail.setText("");
		textAreaComentario.setText("");
	}
	
	public void modifCt(Contacto cont,JDialog dialogoModif){
		cont.modificarCt("nombre",textNomYap.getText());
		cont.modificarCt("direccion",textDir.getText());
		cont.modificarCt("telefonos",textTel.getText());
		cont.modificarCt("email",textEmail.getText());
		cont.modificarCt("comentario",textAreaComentario.getText());
		
		Maquetilla maq=new Maquetilla();
		//maq.actualizarTabla(rs, modelo, tabla,scrollP, cont);
						
		dialogoModif.dispose();
		maq.repaint();
	}

	public JPanel fichaVacia(Contacto contacto,JPanel panelFicha) {
		// TODO Auto-generated method stub
		panelFicha=new JPanel(new GridBagLayout());
		AnchorLayout panelLayout = new AnchorLayout();
		panelFicha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Contacto")));
		panelFicha.setLayout(panelLayout);
		panelFicha.setPreferredSize(new java.awt.Dimension(304, 199));

		JLabel labelNombre=new JLabel("<html><b>Nombre:</b></html>");
		panelFicha.add(labelNombre, new AnchorConstraint(91, 327, 194, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNombre.setPreferredSize(new java.awt.Dimension(52, 15));

		JLabel labelDir=new JLabel("<html><b>Dirección:</b></html>");
		panelFicha.add(labelDir, new AnchorConstraint(216, 327, 283, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelDir.setPreferredSize(new java.awt.Dimension(82, 13));

		JLabel labelTel=new JLabel("<html><b>Teléfonos:</b></html>");
		panelFicha.add(labelTel, new AnchorConstraint(314, 327, 384, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTel.setPreferredSize(new java.awt.Dimension(63, 15));

		{
			JLabel labelEmail=new JLabel("<html><b>E-mail:</b></html>");
			panelFicha.add(labelEmail, new AnchorConstraint(409, 327, 474, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelEmail.setPreferredSize(new java.awt.Dimension(47, 15));
		}
		{
			JLabel labelGrupo=new JLabel("<html><b>Comentario:</b></html>");
			panelFicha.add(labelGrupo, new AnchorConstraint(500, 345, 565,57, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelGrupo.setPreferredSize(new java.awt.Dimension(69, 16));
		}


		return panelFicha;
	}

}
