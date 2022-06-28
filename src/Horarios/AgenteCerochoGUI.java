package Horarios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;


/**
* Contiene todo lo relacionado con la parte GUI de los agentes
*/
public class AgenteCerochoGUI {
	

	private DefaultTableModel modelo=new DefaultTableModel();
	private JTable tabla=new JTable();
	private JScrollPane scrollP;
	
	private JLabel labelNroLeg;
	private JTextField textNroLeg; 
	private JLabel labelNomYap;
	private JRadioButton labelNomYapTrab;
	private JLabel labelFecNac;
	private JComboBox comboDiaNac;
	private JComboBox comboMesNac;
	private JComboBox comboAñoNac;
	private JTextField textNomYap;
	private JComboBox jComboNomYap;
	private JLabel labelTipoD;
	private JComboBox comboTipoDni;
	private JLabel labelNroD;
	private JTextField textNroD;
	private JLabel labelDir;
	private JTextField textDir;
	private JLabel labelTel;
	private JTextField textTel;
	private JLabel labelEmail;
	private JTextField textEmail;
	private JLabel labelGrupo;
	private JRadioButton labelGrupoTrab;
	private JLabel labelFechaConsulta;
	private JComboBox jComboBoxGrupo;
	private JComboBox jComboBoxGrupoTrab;
	private JButton botonAgregar;
	private JButton botonConsultar;
	private JButton botonCancelar;
	private JDateChooser calendarDesp;
	private DateFormat df;
	@SuppressWarnings("unused")
	private AgenteCerochoDAO agenteD=new AgenteCerochoDAO("java");
	private ResultSet rs;

	private DiaLaboral diaL;
	private Date diaTrabajoQuest;
	private GregorianCalendar gregCalTrabQ;
	private GregorianCalendar select=(GregorianCalendar) Calendar.getInstance();
	private Object[] datosFila;
	private ArrayList<Object> datosFilaNomG=new ArrayList<Object>();
	private JFormattedTextField textFecha;
	private GregorianCalendar calendar;
	private int aux;
	
	
	
	/**
	 * Dialogo para agregar agentes
	 * @param tabla 
	 * @param modelo 
	 * @param grupoLayout 
	 * @param panelA 
	 * @param scrollP 
	 * @wbp.parser.entryPoint
	 * 
	 */
	public void agregarDialog(JTable tabla, DefaultTableModel modelo, JScrollPane scrollP){
		this.tabla=tabla;
		this.modelo=modelo;
		this.scrollP=scrollP;
		
		try{
			final JDialog dialogoAgreg=new JDialog();
			dialogoAgreg.setTitle("Carga de nuevos agentes");
			dialogoAgreg.setVisible(true);
			dialogoAgreg.setLocation(500, 250);
			dialogoAgreg.setPreferredSize(new java.awt.Dimension(330, 298));

			{
				JPanel panelDiag=new JPanel(new GridBagLayout());
				panelDiag.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createTitledBorder("Datos")));
				AnchorLayout panelLayout = new AnchorLayout();
				panelDiag.setLayout(panelLayout);
				panelDiag.setPreferredSize(new java.awt.Dimension(318, 264));
				dialogoAgreg.getContentPane().add(panelDiag, BorderLayout.NORTH);
				{
					labelNroLeg=new JLabel("Nro de Legajo:");
					panelDiag.add(labelNroLeg, new AnchorConstraint(82, 386, 160, 155, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout nombreLabelLayout = new BoxLayout(labelNroLeg, javax.swing.BoxLayout.X_AXIS);
					labelNroLeg.setLayout(nombreLabelLayout);
					labelNroLeg.setPreferredSize(new java.awt.Dimension(78, 23));
				}
				{
					textNroLeg=new JTextField(6);
					textNroLeg.setText("0");
					panelDiag.add(textNroLeg, new AnchorConstraint(90, 587, 160, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textNroLeg.setPreferredSize(new java.awt.Dimension(51, 19));
				}
				{
					labelNomYap=new JLabel("Nombre y Apellido:");
					panelDiag.add(labelNomYap, new AnchorConstraint(168, 386, 230, 100, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout labelNomLayout=new BoxLayout(labelNomYap, BoxLayout.X_AXIS);
					labelNomYap.setLayout(labelNomLayout);
					labelNomYap.setPreferredSize(new java.awt.Dimension(105, 23));
				}
				{
					textNomYap=new JTextField(20);
					panelDiag.add(textNomYap, new AnchorConstraint(170, 932, 240, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textNomYap.setPreferredSize(new java.awt.Dimension(141, 19));
				}
				{
					labelFecNac=new JLabel("Fecha de nacimiento:");
					panelDiag.add(labelFecNac, new AnchorConstraint(235, 386, 335, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout labelNomLayout=new BoxLayout(labelFecNac, BoxLayout.X_AXIS);
					labelFecNac.setLayout(labelNomLayout);
					labelFecNac.setPreferredSize(new java.awt.Dimension(105, 23));
				}

				comboDiaNac= new JComboBox();
				panelDiag.add(comboDiaNac, new AnchorConstraint(250, 545, 330, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				int nroDias=CalendarioGrafic.daysMonth[select.get(Calendar.MONTH)];
				for (int i = 0; i < nroDias; i++) {
					comboDiaNac.addItem(i+1);
				}
				comboDiaNac.setSelectedItem(select.get(Calendar.DAY_OF_MONTH));
				comboDiaNac.setPreferredSize(new java.awt.Dimension(46, 17));
				aux=comboDiaNac.getSelectedIndex();
				comboDiaNac.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						aux=comboDiaNac.getSelectedIndex();
					}
				});
				comboMesNac= new JComboBox();
				panelDiag.add(comboMesNac, new AnchorConstraint(250, 800, 330, 555, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				for (int i = 0; i < 12; i++) {
					comboMesNac.addItem(CalendarioGrafic.nombreMesM(i));
				}
				comboMesNac.setSelectedItem(CalendarioGrafic.nombreMesM(select.get(Calendar.MONTH)));
				comboMesNac.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						// TODO Auto-generated method stub
						int nroDias=CalendarioGrafic.daysMonth[comboMesNac.getSelectedIndex()];
						calendar=new GregorianCalendar(comboAñoNac.getSelectedIndex()+1950,comboMesNac.getSelectedIndex(),1);
						if (comboMesNac.getSelectedIndex() == Calendar.FEBRUARY && calendar.isLeapYear(comboAñoNac.getSelectedIndex()+1950)) {
							nroDias++;
						}

						int aux2=aux;
						comboDiaNac.removeAllItems();
						for (int i = 0; i < nroDias; i++) {
							comboDiaNac.addItem(i+1);
						}
						comboDiaNac.setSelectedItem(aux2+1);
					}
				});
				comboMesNac.setPreferredSize(new java.awt.Dimension(106, 17));


				{
					comboAñoNac= new JComboBox();
					panelDiag.add(comboAñoNac, new AnchorConstraint(250, 969, 330, 810, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					for (int i = 1950; i < 2013; i++) {
						comboAñoNac.addItem(i);
					}
					comboAñoNac.setSelectedItem(select.get(Calendar.YEAR));
					comboAñoNac.setPreferredSize(new java.awt.Dimension(46, 17));
					comboAñoNac.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							int nroDias=CalendarioGrafic.daysMonth[comboMesNac.getSelectedIndex()];
							calendar=new GregorianCalendar(comboAñoNac.getSelectedIndex()+1950,comboMesNac.getSelectedIndex(),1);
							if (comboMesNac.getSelectedIndex() == Calendar.FEBRUARY && calendar.isLeapYear(comboAñoNac.getSelectedIndex()+1950)) {
								nroDias++;
								int aux2=aux;
								comboDiaNac.removeAllItems();
								for (int i = 0; i < nroDias; i++) {
									comboDiaNac.addItem(i+1);
								}
								comboDiaNac.setSelectedItem(aux2+1);
							}

						}
					});

				}

				{
					labelTipoD=new JLabel("Tipo de doc:");
					panelDiag.add(labelTipoD, new AnchorConstraint(355, 386, 390, 192, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout labelTipoDLay =new BoxLayout(this.labelTipoD, BoxLayout.X_AXIS);
					labelTipoD.setPreferredSize(new java.awt.Dimension(68, 23));
					labelTipoD.setLayout(labelTipoDLay);
				}
				{
					comboTipoDni = new JComboBox();
					panelDiag.add(comboTipoDni, new AnchorConstraint(342, 571, 417, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					comboTipoDni.addItem("DNI");
					comboTipoDni.addItem("L.C.");
					comboTipoDni.addItem("L.E.");
					comboTipoDni.setPreferredSize(new java.awt.Dimension(46, 17));
				}
				{
					labelNroD=new JLabel("Nro de documento:");
					panelDiag.add(labelNroD, new AnchorConstraint(355, 664, 390, 587, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelNroD.setPreferredSize(new java.awt.Dimension(24, 20));
					labelNroD.setText("Nro:");
				}
				{
					textNroD=new JTextField(15);
					panelDiag.add(textNroD, new AnchorConstraint(342, 934, 417, 679, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textNroD.setPreferredSize(new java.awt.Dimension(80, 18));
				}
				{
					labelDir=new JLabel("Dirección:");
					panelDiag.add(labelDir, new AnchorConstraint(425, 383, 470, 230, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelDir.setPreferredSize(new java.awt.Dimension(55, 27));
				}
				{
					textDir=new JTextField(15);
					panelDiag.add(textDir, new AnchorConstraint(425, 932, 495, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textDir.setPreferredSize(new java.awt.Dimension(162, 20));
				}
				{
					labelTel=new JLabel("Teléfonos:");
					panelDiag.add(labelTel, new AnchorConstraint(510, 385, 560, 220, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelTel.setPreferredSize(new java.awt.Dimension(55, 27));
				}
				{
					textTel=new JTextField(15);
					panelDiag.add(textTel, new AnchorConstraint(505, 932, 575, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textTel.setPreferredSize(new java.awt.Dimension(102, 20));
				}
				{
					labelEmail=new JLabel("E-mail:");
					panelDiag.add(labelEmail, new AnchorConstraint(588, 385,660, 280, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelEmail.setPreferredSize(new java.awt.Dimension(43, 20));
				}
				{
					textEmail=new JTextField(15);
					textEmail.setText("");
					panelDiag.add(textEmail, new AnchorConstraint(590, 932, 660, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textEmail.setPreferredSize(new java.awt.Dimension(162, 20));
				}
				{
					labelGrupo=new JLabel("Grupo:");
					panelDiag.add(labelGrupo, new AnchorConstraint(677, 385, 719, 273, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelGrupo.setPreferredSize(new java.awt.Dimension(30, 5));
				}
				{
					jComboBoxGrupo = new JComboBox();
					panelDiag.add(jComboBoxGrupo, new AnchorConstraint(670, 533, 661, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					jComboBoxGrupo.addItem("A");
					jComboBoxGrupo.addItem("B");
					jComboBoxGrupo.addItem("C");

					jComboBoxGrupo.setPreferredSize(new java.awt.Dimension(35, 18));

					jComboBoxGrupo.addKeyListener(new KeyListener() {

						@Override public void keyTyped(KeyEvent arg0) {
							// TODO Auto-generated method					  stub

						}

						@Override public void keyReleased(KeyEvent arg0) { 
							// TODO Auto-generated method stub

						}

						@Override public void keyPressed(KeyEvent arg0) { 
							// TODO Auto-generated method stub 
							if(arg0.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){

								agregarAg(dialogoAgreg); }

						} });

				}
				{
					botonAgregar=new JButton("Agregar");
					panelDiag.add(botonAgregar, new AnchorConstraint(790, 630, 884, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonAgregar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							//try{
						
							agregarAg(dialogoAgreg);
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
								agregarAg(dialogoAgreg);
							}

						}
					});
					botonAgregar.setPreferredSize(new java.awt.Dimension(86, 26));
				}
				{
					botonCancelar=new JButton("Cancelar");
					panelDiag.add(botonCancelar, new AnchorConstraint(790, 935, 884, 665, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonCancelar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dialogoAgreg.dispose();
						}
					});
					botonCancelar.setPreferredSize(new java.awt.Dimension(86, 26));
				}

			}

			dialogoAgreg.pack();
			dialogoAgreg.setSize(330, 298);
			dialogoAgreg.setResizable(false);
			dialogoAgreg.isModal();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void dialogoModificar(JTable tabla,DefaultTableModel modelo, JScrollPane scrollP){
		final JDialog dialogoModif=new JDialog();
		final AgenteCerocho agentesillo=new AgenteCerocho();
		this.tabla=tabla;
		this.modelo=modelo;
		this.scrollP=scrollP;
		agentesillo.leerAgente((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
		
		try{
			dialogoModif.setTitle("Modificación de agentes");
			dialogoModif.setVisible(true);
			dialogoModif.setLocation(500, 250);
			dialogoModif.setPreferredSize(new java.awt.Dimension(330, 280));
			{
				JPanel panelDiag=new JPanel(new GridBagLayout());
				AnchorLayout panelLayout = new AnchorLayout();
				panelDiag.setBorder(BorderFactory.createTitledBorder(
	                    BorderFactory.createTitledBorder("Datos")));
				panelDiag.setLayout(panelLayout);
				panelDiag.setPreferredSize(new Dimension(318, 260));
				dialogoModif.getContentPane().add(panelDiag, BorderLayout.NORTH);
				{
					labelNroLeg=new JLabel("Nro de Legajo:");
					panelDiag.add(labelNroLeg, new AnchorConstraint(82, 386, 160, 155, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout nombreLabelLayout = new BoxLayout(labelNroLeg, javax.swing.BoxLayout.X_AXIS);
					labelNroLeg.setLayout(nombreLabelLayout);
					labelNroLeg.setPreferredSize(new java.awt.Dimension(78, 23));
				}
				{
					textNroLeg=new JTextField(6);
					textNroLeg.setText(String.valueOf(agentesillo.getLegajo()));
					panelDiag.add(textNroLeg, new AnchorConstraint(90, 587, 160, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textNroLeg.setPreferredSize(new java.awt.Dimension(51, 21));
				}
				{
					labelNomYap=new JLabel("Nombre y Apellido:");
					panelDiag.add(labelNomYap, new AnchorConstraint(168, 386, 230, 100, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout labelNomLayout=new BoxLayout(labelNomYap, BoxLayout.X_AXIS);
					labelNomYap.setLayout(labelNomLayout);
					labelNomYap.setPreferredSize(new java.awt.Dimension(106, 22));
				}
				{
					textNomYap=new JTextField(20);
					textNomYap.setText(agentesillo.getNombreyApellido());
					panelDiag.add(textNomYap, new AnchorConstraint(170, 932, 240, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textNomYap.setPreferredSize(new java.awt.Dimension(142, 24));
				}
				{
					labelFecNac=new JLabel("Fecha de nacimiento:");
					panelDiag.add(labelFecNac, new AnchorConstraint(235, 386, 335, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout labelNomLayout=new BoxLayout(labelFecNac, BoxLayout.X_AXIS);
					labelFecNac.setLayout(labelNomLayout);
					labelFecNac.setPreferredSize(new java.awt.Dimension(105, 23));
				}
				{
					comboDiaNac= new JComboBox();
					panelDiag.add(comboDiaNac, new AnchorConstraint(250, 545, 330, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					int nroDias=CalendarioGrafic.daysMonth[select.get(Calendar.MONTH)];
					for (int i = 0; i < nroDias; i++) {
						comboDiaNac.addItem(i+1);
					}
					comboDiaNac.setSelectedItem(agentesillo.getDiaNac());
					comboDiaNac.setPreferredSize(new java.awt.Dimension(46, 17));
					
				}
				{
					comboMesNac= new JComboBox();
					for (int i = 0; i < 12; i++) {
						comboMesNac.addItem(CalendarioGrafic.nombreMesM(i));
					}
					comboMesNac.setSelectedItem(CalendarioGrafic.nombreMesM(agentesillo.getMesNac()-1));
					
					comboMesNac.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							comboAñoNac=new JComboBox();
							for (int i = 1950; i < 2013; i++) {
								comboAñoNac.addItem(i);
							}
							comboAñoNac.setSelectedItem(agentesillo.getAñoNac());
							int nroDias=CalendarioGrafic.daysMonth[comboMesNac.getSelectedIndex()];
							calendar=new GregorianCalendar(comboAñoNac.getSelectedIndex()+1950,comboMesNac.getSelectedIndex(),1);
							if (comboMesNac.getSelectedIndex() == Calendar.FEBRUARY && calendar.isLeapYear(comboAñoNac.getSelectedIndex()+1950)) {
					            nroDias++;
					        }
							
							aux=comboDiaNac.getSelectedIndex();
							int aux2=aux;
							comboDiaNac.removeAllItems();
							for (int i = 0; i < nroDias; i++) {
								comboDiaNac.addItem(i+1);
							}

							comboDiaNac.setSelectedItem(aux2+1);
						}
						
					});
					comboMesNac.setPreferredSize(new java.awt.Dimension(106, 17));
					panelDiag.add(comboMesNac, new AnchorConstraint(250, 800, 330, 555, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					
					
				}
				{
					comboAñoNac= new JComboBox();
					panelDiag.add(comboAñoNac, new AnchorConstraint(250, 969, 330, 810, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					for (int i = 1950; i < 2013; i++) {
						comboAñoNac.addItem(i);
					}
					comboAñoNac.setSelectedItem(agentesillo.getAñoNac());
					comboAñoNac.setPreferredSize(new java.awt.Dimension(46, 17));
					comboAñoNac.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							int nroDias=CalendarioGrafic.daysMonth[comboMesNac.getSelectedIndex()];
							calendar=new GregorianCalendar(comboAñoNac.getSelectedIndex()+1950,comboMesNac.getSelectedIndex(),1);
							if (comboMesNac.getSelectedIndex() == Calendar.FEBRUARY && calendar.isLeapYear(comboAñoNac.getSelectedIndex()+1950)) {
								nroDias++;
								aux=comboDiaNac.getSelectedIndex();
								int aux2=aux;
								
								comboDiaNac.removeAllItems();
								for (int i = 0; i < nroDias; i++) {
									comboDiaNac.addItem(i+1);
								}
								comboDiaNac.setSelectedItem(aux2+1);
								
							}

						}
					});
					
				}
				
				{
					labelTipoD=new JLabel("Tipo de doc:");
					panelDiag.add(labelTipoD, new AnchorConstraint(355, 386, 390, 192, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BoxLayout labelTipoDLay =new BoxLayout(this.labelTipoD, BoxLayout.X_AXIS);
					labelTipoD.setPreferredSize(new java.awt.Dimension(69, 25));
					labelTipoD.setLayout(labelTipoDLay);
				}
				{
					comboTipoDni = new JComboBox();
					panelDiag.add(comboTipoDni, new AnchorConstraint(342, 571, 417, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					comboTipoDni.addItem("DNI");
					comboTipoDni.addItem("L.C.");
					comboTipoDni.addItem("L.E.");
					comboTipoDni.setSelectedItem(agentesillo.getTipoDni());
					comboTipoDni.setPreferredSize(new java.awt.Dimension(46, 20));
				}
				{
					labelNroD=new JLabel("Nro:");
					panelDiag.add(labelNroD, new AnchorConstraint(355, 664, 390, 587, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelNroD.setPreferredSize(new java.awt.Dimension(31, 23));
				}
				{
					textNroD=new JTextField(15);
					textNroD.setText(String.valueOf(agentesillo.getDni()));
					panelDiag.add(textNroD, new AnchorConstraint(342, 934, 417, 679, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textNroD.setPreferredSize(new java.awt.Dimension(91, 21));
				}
				{
					labelDir=new JLabel("Dirección:");
					panelDiag.add(labelDir, new AnchorConstraint(425,385, 495, 222, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelDir.setPreferredSize(new java.awt.Dimension(55, 28));
				}
				{
					textDir=new JTextField(15);
					textDir.setText(agentesillo.getDireccion());
					panelDiag.add(textDir, new AnchorConstraint(425, 932, 495, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textDir.setPreferredSize(new java.awt.Dimension(167, 20));
				}
				{
					labelTel=new JLabel("Telefonos:");
					panelDiag.add(labelTel, new AnchorConstraint(510, 385, 560, 220, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelTel.setPreferredSize(new java.awt.Dimension(55, 21));
				}
				{
					textTel=new JTextField(15);
					textTel.setText(agentesillo.getTelefono());
					panelDiag.add(textTel, new AnchorConstraint(505, 932, 575, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textTel.setPreferredSize(new java.awt.Dimension(167, 22));
				}
				{
					labelEmail=new JLabel("E-mail:");
					panelDiag.add(labelEmail, new AnchorConstraint(588, 385,660, 280, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelEmail.setPreferredSize(new java.awt.Dimension(43, 21));
				}
				{
					textEmail=new JTextField(15);
					textEmail.setText(agentesillo.getEmail());
					panelDiag.add(textEmail, new AnchorConstraint(590, 932, 660, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					textEmail.setPreferredSize(new java.awt.Dimension(166, 20));
				}
				{
					labelGrupo=new JLabel("Grupo:");
					panelDiag.add(labelGrupo, new AnchorConstraint(677, 385, 719, 273, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelGrupo.setPreferredSize(new java.awt.Dimension(43, 15));
				}
				{
					jComboBoxGrupo = new JComboBox();
					panelDiag.add(jComboBoxGrupo, new AnchorConstraint(670, 533, 661, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					jComboBoxGrupo.addItem("A");
					jComboBoxGrupo.addItem("B");
					jComboBoxGrupo.addItem("C");
					
					jComboBoxGrupo.setSelectedItem(agentesillo.getNombreGrupo().toUpperCase());
					jComboBoxGrupo.setPreferredSize(new java.awt.Dimension(35, 18));
					jComboBoxGrupo.addKeyListener(new KeyListener() {
						
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

								modifAg(agentesillo,dialogoModif);
							}
							
						}
					});
					
				}
				{
					botonAgregar=new JButton("Modificar");
					panelDiag.add(botonAgregar, new AnchorConstraint(790, 630, 884, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonAgregar.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
								
								modifAg(agentesillo,dialogoModif);
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

								modifAg(agentesillo,dialogoModif);
							}
							
						}
					});
					botonAgregar.setPreferredSize(new Dimension(85, 23));
				}
				{
					botonCancelar=new JButton("Cancelar");
					panelDiag.add(botonCancelar, new AnchorConstraint(790, 935, 884, 665, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonCancelar.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dialogoModif.dispose();
						}
					});
					botonCancelar.setPreferredSize(new Dimension(86, 23));
				}
				
			}
			
			dialogoModif.pack();
			dialogoModif.setSize(330, 316);
			dialogoModif.setResizable(false);
			dialogoModif.isModal();
		} catch(IndexOutOfBoundsException e) {
			
			e.printStackTrace();
		} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public JPanel ficha(AgenteCerocho agente,JPanel panelFicha,JTabbedPane pestaña){

		panelFicha=new JPanel(new GridBagLayout());
		AnchorLayout panelLayout = new AnchorLayout();
		panelFicha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Agente 0800")));
		panelFicha.setLayout(panelLayout);
		//panelFicha.setPreferredSize(new java.awt.Dimension(50, 199));

		JLabel labelNroLeg=new JLabel("<html><b>Legajo:</b></html>");
		panelFicha.add(labelNroLeg, new AnchorConstraint(91, 327, 194, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNroLeg.setPreferredSize(new java.awt.Dimension(52,15));

		JLabel nroLeg=new JLabel(Long.toString(agente.getLegajo()));
		panelFicha.add(nroLeg, new AnchorConstraint(91, 595, 194, 395, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		nroLeg.setPreferredSize(new java.awt.Dimension(77, 15));

		JLabel labelNombre=new JLabel("<html><b>Nombre:</b></html>");
		panelFicha.add(labelNombre, new AnchorConstraint(216, 327, 283, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNombre.setPreferredSize(new java.awt.Dimension(52, 15));

		JLabel nombre=new JLabel(agente.getNombreyApellido());
		panelFicha.add(nombre, new AnchorConstraint(216, 864, 283, 395, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		nombre.setPreferredSize(new java.awt.Dimension(133, 15));

		JLabel labelFacNac=new JLabel("<html><b>Fecha de nac:</b></html>");
		panelFicha.add(labelFacNac, new AnchorConstraint(305, 385, 381, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelFacNac.setPreferredSize(new java.awt.Dimension(76, 14));
		
		JLabel fechaNac=new JLabel(agente.getFechaNacFormat());
		panelFicha.add(fechaNac, new AnchorConstraint(305, 914, 381, 395, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		fechaNac.setPreferredSize(new java.awt.Dimension(133, 15));
		
		JLabel labelTipo=new JLabel("<html><b>Documento:</b></html>");
		panelFicha.add(labelTipo, new AnchorConstraint(403, 347, 479, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTipo.setPreferredSize(new java.awt.Dimension(76, 14));

		JLabel tipoD=new JLabel(agente.getTipoDni());
		panelFicha.add(tipoD, new AnchorConstraint(403, 480,479, 395, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		tipoD.setPreferredSize(new java.awt.Dimension(29, 15));

		JLabel nroD=new JLabel(Long.toString(agente.getDni()));
		panelFicha.add(nroD, new AnchorConstraint(403, 790, 479, 490, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		nroD.setPreferredSize(new java.awt.Dimension(90, 15));

		JLabel labelDir=new JLabel("<html><b>Dirección:</b></html>");
		panelFicha.add(labelDir, new AnchorConstraint(501, 347, 577, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelDir.setPreferredSize(new java.awt.Dimension(82, 13));

		JLabel dir=new JLabel(agente.getDireccion());
		panelFicha.add(dir, new AnchorConstraint(501, 863, 577, 395, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		dir.setPreferredSize(new java.awt.Dimension(161, 13));

		JLabel labelTel=new JLabel("<html><b>Teléfonos:</b></html>");
		panelFicha.add(labelTel, new AnchorConstraint(599, 327, 675, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTel.setPreferredSize(new java.awt.Dimension(82, 13));

		JLabel tel=new JLabel(agente.getTelefono());
		panelFicha.add(tel, new AnchorConstraint(599, 850, 675, 395, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		tel.setPreferredSize(new java.awt.Dimension(171, 16));
		{
			JLabel labelEmail=new JLabel("<html><b>E-mail:</b></html>");
			panelFicha.add(labelEmail, new AnchorConstraint(697, 327, 773, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelEmail.setPreferredSize(new java.awt.Dimension(81, 14));
		}
		{
			JLabel email=new JLabel(agente.getEmail());
			panelFicha.add(email, new AnchorConstraint(697, 908, 773, 395, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			email.setPreferredSize(new java.awt.Dimension(176, 16));
		}
		{
			JLabel labelGrupo=new JLabel("<html><b>Grupo:</b></html>");
			panelFicha.add(labelGrupo, new AnchorConstraint(795, 327, 871, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelGrupo.setPreferredSize(new java.awt.Dimension(81, 14));
		}
		{
			JLabel grupo=new JLabel(agente.getNombreGrupo().toUpperCase());
			panelFicha.add(grupo, new AnchorConstraint(795, 456, 871, 395, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			grupo.setPreferredSize(new java.awt.Dimension(25, 14));
		}
		
		pestaña.repaint();
		
		return panelFicha;


	}
	
	public JPanel fichaVacia(JPanel panelFicha){
		
		panelFicha=new JPanel();
		AnchorLayout panelLayout = new AnchorLayout();
		panelFicha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Agente 0800")));
		panelFicha.setLayout(panelLayout);
		//panelFicha.setPreferredSize(new java.awt.Dimension(50, 199));

		JLabel labelNroLeg=new JLabel("<html><b>Legajo:</b></html>");
		panelFicha.add(labelNroLeg, new AnchorConstraint(91, 327, 194, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNroLeg.setPreferredSize(new java.awt.Dimension(52,15));

		JLabel labelNombre=new JLabel("<html><b>Nombre:</b></html>");
		panelFicha.add(labelNombre, new AnchorConstraint(216, 327, 283, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNombre.setPreferredSize(new java.awt.Dimension(52, 15));

		JLabel labelFacNac=new JLabel("<html><b>Fecha de nac:</b></html>");
		panelFicha.add(labelFacNac, new AnchorConstraint(305, 385, 381, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelFacNac.setPreferredSize(new java.awt.Dimension(76, 14));
				
		JLabel labelTipo=new JLabel("<html><b>Documento:</b></html>");
		panelFicha.add(labelTipo, new AnchorConstraint(403, 347, 479, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTipo.setPreferredSize(new java.awt.Dimension(76, 14));

		JLabel labelDir=new JLabel("<html><b>Dirección:</b></html>");
		panelFicha.add(labelDir, new AnchorConstraint(501, 327, 577, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelDir.setPreferredSize(new java.awt.Dimension(82, 13));

		JLabel labelTel=new JLabel("<html><b>Teléfonos:</b></html>");
		panelFicha.add(labelTel, new AnchorConstraint(599, 327, 675, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTel.setPreferredSize(new java.awt.Dimension(82, 13));

		{
			JLabel labelEmail=new JLabel("<html><b>E-mail:</b></html>");
			panelFicha.add(labelEmail, new AnchorConstraint(697, 327, 773, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelEmail.setPreferredSize(new java.awt.Dimension(81, 14));
		}
		{
			JLabel labelGrupo=new JLabel("<html><b>Grupo:</b></html>");
			panelFicha.add(labelGrupo, new AnchorConstraint(795, 327, 871, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelGrupo.setPreferredSize(new java.awt.Dimension(81, 14));
		}
		
		
		return panelFicha;
	}
	
	public void modifAg(AgenteCerocho agentesillo,JDialog dialogoModif){
		agentesillo.modificarAgente("nro_legajo", Long.parseLong(textNroLeg.getText()));
		agentesillo.modificarAgente("nombre",textNomYap.getText());
		agentesillo.modificarAgente("fecha_nac",comboDiaNac.getSelectedIndex()+1,comboMesNac.getSelectedIndex(),comboAñoNac.getSelectedIndex()+1950);
		agentesillo.modificarAgente("tipo_dni",(String) comboTipoDni.getSelectedItem());
		agentesillo.modificarAgente("dni",Long.parseLong(textNroD.getText()));
		agentesillo.modificarAgente("direccion",textDir.getText());
		agentesillo.modificarAgente("telefonos",textTel.getText());
		agentesillo.modificarAgente("email",textEmail.getText());
		String grupo=((String) jComboBoxGrupo.getSelectedItem()).toLowerCase();
		
				
		/*
		 * if (!grupo.equals(agentesillo.getMyGrupo().getNombre())) {
		 * 
		 * agentesillo.modificarAgente("subgrupo",
		 * AgenteCerochoDAO.asignSubGrupo(((String)
		 * jComboBoxGrupo.getSelectedItem()).toLowerCase()));
		 * //agentesillo.setSubGrupo(AgenteCerochoDAO.asignSubGrupo(((String)
		 * jComboBoxGrupo.getSelectedItem()).toLowerCase())); }
		 */
		
		agentesillo.modificarAgente("grupo",((String) jComboBoxGrupo.getSelectedItem()).toLowerCase());
						
		Maquetilla maq=new Maquetilla();
		maq.actualizarTabla(rs, modelo, tabla, scrollP, 1);
						
		dialogoModif.dispose();
		maq.repaint();
	}
	
	public void agregarAg(JDialog dialogoAgreg){

		String nombreGrupo=((String) jComboBoxGrupo.getSelectedItem()).toLowerCase();
		
		int sg = 0;
		
		//sg=AgenteCerochoDAO.asignSubGrupo(nombreGrupo);
		sg=1;
		System.out.println("subgrupo: "+sg);
		
		AgenteCerocho agente=new AgenteCerocho(textNomYap.getText(),comboDiaNac.getSelectedIndex()+1,comboMesNac.getSelectedIndex(),
				comboAñoNac.getSelectedIndex()+1950,textDir.getText(),textTel.getText(),textEmail.getText(),nombreGrupo, sg);
		agente.setLegajo(Long.parseLong(textNroLeg.getText()));
		agente.setTipoDni((String) comboTipoDni.getSelectedItem());
		System.out.println(textNroD.getText());
		if (textNroD.getText()==null){
			
			agente.setDni(Long.parseLong(textNroD.getText()));
		}
		
		agente.setSubGrupo(sg);
		System.out.println("Subgrupo Agente: "+agente.getSubGrupo());
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
		
		rs=agente.getDatosAgentes();
		
		AgenteCerochoDAO.cargarAgente(agente);
		Maquetilla maq=new Maquetilla();
		maq.actualizarTabla(rs, modelo, tabla, scrollP, 1);
						
		dialogoAgreg.dispose();
		maq.repaint();
		
		
		textNroLeg.setText("");
		textNomYap.setText("");
		comboDiaNac.setSelectedItem(select.get(Calendar.DAY_OF_MONTH));
		comboMesNac.setSelectedItem(select.get(Calendar.MONTH));
		comboAñoNac.setSelectedItem(select.get(Calendar.YEAR));
		textNroD.setText("");
		textDir.setText("");
		textTel.setText("");
		textEmail.setText("");
	}

	public void setGregCalTrabQ(GregorianCalendar gregCalTrabQ) {
		this.gregCalTrabQ = gregCalTrabQ;
	}

	public GregorianCalendar getGregCalTrabQ() {
		return gregCalTrabQ;
	}

	public void setDf(DateFormat df) {
		this.df = df;
	}

	public DateFormat getDf() {
		return df;
	}
	
	/**
	 * Panel con la información del dia seleccionado
	 * @param agente
	 * @param panelDia
	 * @param tabla
	 * @param pestania
	 * @return
	 */
	public JPanel diaSelect(AgenteCerocho agente,JPanel panelDia,JTable tabla) {
		// TODO Auto-generated method stub
		
		Calendar calendar = new GregorianCalendar(CalendarioGrafic.getAño(),CalendarioGrafic.getMes(),tabla.getSelectedColumn()+1);
		long date= calendar.getTimeInMillis();
		java.util.Date diaAc= new java.util.Date(date);
		
		panelDia=new JPanel(new GridBagLayout());
		AnchorLayout panelLayout = new AnchorLayout();
		panelDia.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Info de turno")));
		panelDia.setLayout(panelLayout);
		panelDia.setPreferredSize(new java.awt.Dimension(260, 195));
		
		JLabel labelDia=new JLabel("<html><b>Día:</b></html>");
		panelDia.add(labelDia, new AnchorConstraint(91, 327, 194, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelDia.setPreferredSize(new java.awt.Dimension(52,15));
		
		JLabel dia=new JLabel((tabla.getSelectedColumn()+1)+" de "+CalendarioGrafic.nombreMesM(CalendarioGrafic.getMes())+" de "+CalendarioGrafic.getAño());
		panelDia.add(dia, new AnchorConstraint(91, 924, 194, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		dia.setPreferredSize(new java.awt.Dimension(159, 20));

		JLabel labelNombre=new JLabel("<html><b>Grupo:</b></html>");
		panelDia.add(labelNombre, new AnchorConstraint(216, 327, 283, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNombre.setPreferredSize(new java.awt.Dimension(52, 15));

		JLabel groupo=new JLabel();
		DiaLab08 diaL0= new DiaLab08();
		groupo.setText(diaL0.calcularGrupo(diaAc));
		panelDia.add(groupo, new AnchorConstraint(216, 464, 283, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		groupo.setPreferredSize(new java.awt.Dimension(133, 15));

		JLabel labelTipo=new JLabel("<html><b>Agente:</b></html>");
		JLabel labelTipo1=new JLabel("<html><b>Agentes:</b></html>");
		

		JTextArea integrantes=new JTextArea(5,1);
		
		if (tabla.getSelectedRow()==0) {
			panelDia.add(labelTipo1, new AnchorConstraint(314, 327, 384, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelTipo.setPreferredSize(new java.awt.Dimension(76, 14));
			
			integrantes.setText(agente.soloNombres(diaAc));
			integrantes.setOpaque(false);
			integrantes.setEditable(false);
			integrantes.setLineWrap(true);
			integrantes.setWrapStyleWord(true);
			integrantes.setFont(new Font("TAHOMA", Font.PLAIN, 11));
			JScrollPane scroll=new JScrollPane();
			panelDia.add(integrantes, new AnchorConstraint(314, 780, 894, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			integrantes.setPreferredSize(new java.awt.Dimension(165, 93));
			scroll.setPreferredSize(new java.awt.Dimension(80, 66));
			
		} else {
			panelDia.add(labelTipo, new AnchorConstraint(314, 327, 384, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			labelTipo.setPreferredSize(new java.awt.Dimension(76, 14));
			
			integrantes.setText(agente.soloNombre(diaAc, tabla.getSelectedRow()));
			integrantes.setOpaque(false);
			integrantes.setEditable(false);
			integrantes.setLineWrap(true);
			integrantes.setWrapStyleWord(true);
			integrantes.setFont(new Font("TAHOMA", Font.PLAIN, 11));
			JScrollPane scroll=new JScrollPane();
			panelDia.add(integrantes, new AnchorConstraint(314, 780, 894, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			integrantes.setPreferredSize(new java.awt.Dimension(165, 93));
			scroll.setPreferredSize(new java.awt.Dimension(80, 66));

		}
				
				
		return panelDia;
	}
	
	/**
	 * Panel solo con rótulos, donde luego se cargará la información del dia que se seleccione
	 * @param agente
	 * @param panelDia
	 * @param tabla
	 * @return
	 */
	public JPanel diaSelectVacio(AgenteCerocho agente,JPanel panelDia,JTable tabla) {
		// TODO Auto-generated method stub
		panelDia=new JPanel();
		AnchorLayout panelLayout = new AnchorLayout();
		panelDia.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Info de turno")));
		panelDia.setLayout(panelLayout);
		panelDia.setPreferredSize(new java.awt.Dimension(260, 195));
		
		JLabel labelDia=new JLabel("<html><b>Día:</b></html>");
		panelDia.add(labelDia, new AnchorConstraint(91, 327, 194, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelDia.setPreferredSize(new java.awt.Dimension(52,15));
		
		JLabel labelNombre=new JLabel("<html><b>Grupo:</b></html>");
		panelDia.add(labelNombre, new AnchorConstraint(216, 327, 283, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNombre.setPreferredSize(new java.awt.Dimension(52, 15));

		JLabel labelTipo=new JLabel("<html><b>Agente:</b></html>");
		panelDia.add(labelTipo, new AnchorConstraint(314, 327, 384, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTipo.setPreferredSize(new java.awt.Dimension(76, 14));
		
		return panelDia;
	}

}
