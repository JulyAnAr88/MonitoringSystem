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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;


/**
* Contiene todo lo relacionado con la parte GUI de los agentes
*/
public class AgenteGsiGUI {
	

	private DefaultTableModel modelo=new DefaultTableModel();
	private TablaAgM tablaA=new TablaAgM();
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
	private AgenteGsiDAO agenteD=new AgenteGsiDAO("java");
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
	
	//Maquetilla maq=new Maquetilla();
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel trabajoQuest(final JPanel resultado, final JTabbedPane pestania, final JTable tabla, final JComboBox mes, final JComboBox año/**/){
		
		final JPanel panelDiagTrab=new JPanel(new GridBagLayout());
		resultado.setLayout(new GridBagLayout());
		
		try{
		{
			
			panelDiagTrab.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createTitledBorder("Consulta si debes trabajar:")));
			resultado.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createTitledBorder("Resultado")));
			AnchorLayout panelLayout = new AnchorLayout();
			panelDiagTrab.setLayout(panelLayout);
			resultado.setLayout(panelLayout);
			panelDiagTrab.setPreferredSize(new java.awt.Dimension(326, 159));
			resultado.setPreferredSize(new java.awt.Dimension(226, 159));
			{
				calendarDesp=new JDateChooser();
				try{
				    calendarDesp.setDateFormatString("dd/MM/yyyy");
				    calendarDesp.setPreferredSize(new java.awt.Dimension(20, 20));
				    /*calendarDesp.setBounds(419, 89, 175, 20);
				    frame.getContentPane().add(calendarDesp);*/

				    final String start = (((JTextField)calendarDesp.getDateEditor().getUiComponent()).getText());

				    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				    LocalDateTime startDate = LocalDateTime.parse(start ,format);
				    LocalDateTime endDate = LocalDateTime.now();
				    System.out.println("startdate: "+ startDate+ ", enddate: "+ endDate);
				}
				catch(Exception e){
				    e.printStackTrace();
				}
				calendarDesp.getDateEditor().addPropertyChangeListener(
					    new PropertyChangeListener() {
					        @Override
					        public void propertyChange(PropertyChangeEvent e) {
					            if ("date".equals(e.getPropertyName())) {
					                System.out.println(e.getPropertyName()
					                    + ": " + (java.util.Date) e.getNewValue());
					            }
					        }
					    });
				
				
				setDf(new SimpleDateFormat("dd/MM/yyyy"));
				String caldesp=calendarDesp.getDateFormatString();
				System.out.println(caldesp);
				textFecha = new JFormattedTextField(/*df.format(*/calendarDesp.getDateFormatString());
				textFecha.setPreferredSize(new java.awt.Dimension(115, 20));
				panelDiagTrab.add(textFecha, new AnchorConstraint(513, 857, 625, 504, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				panelDiagTrab.add(calendarDesp, new AnchorConstraint(513, 937, 625, 875, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				//textFechaConsulta.setPreferredSize(new java.awt.Dimension(97, 22));

			}
			calendarDesp.addPropertyChangeListener(new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					// TODO Auto-generated method stub
					textFecha.setText(calendarDesp.getDateFormatString());
				}
			});
			{
				labelNomYapTrab=new JRadioButton("Nombre y Apellido:",true);
				panelDiagTrab.add(labelNomYapTrab, new AnchorConstraint(162, 475, 234, 66, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				BoxLayout labelNomLayout=new BoxLayout(labelNomYapTrab, BoxLayout.X_AXIS);
				labelNomYapTrab.setLayout(labelNomLayout);
				labelNomYapTrab.setPreferredSize(new java.awt.Dimension(140, 14));
				labelNomYapTrab.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub

						jComboNomYap.setEnabled(true);
						jComboBoxGrupoTrab.setEnabled(false);

					}
				});
			}
			{
				jComboBoxGrupoTrab = new JComboBox();
				jComboBoxGrupoTrab.setEnabled(false);

				panelDiagTrab.add(jComboBoxGrupoTrab, new AnchorConstraint(336, 650, 441, 511, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jComboBoxGrupoTrab.addItem(null);
				jComboBoxGrupoTrab.addItem("A");
				jComboBoxGrupoTrab.addItem("B");
				jComboBoxGrupoTrab.addItem("C");
				jComboBoxGrupoTrab.addItem("D");
				jComboBoxGrupoTrab.addItem("E");
				jComboBoxGrupoTrab.addItem("F");
				jComboBoxGrupoTrab.setPreferredSize(new java.awt.Dimension(43, 19));



			}
			{
				jComboNomYap=new JComboBox();
				panelDiagTrab.add(jComboNomYap, new AnchorConstraint(144, 912, 258, 510, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				rs=AgenteGsiDAO.datosAgentes();
				jComboNomYap.setPreferredSize(new java.awt.Dimension(131, 17));
				int numeroFila = 0;
				jComboNomYap.addItem("");
				try
				{
					while (rs.next())
					{
						ResultSetMetaData metaDatos = rs.getMetaData();

						int numeroColumnas = metaDatos.getColumnCount();
						datosFila = new Object[20];			                
						for (int i = 0; i < numeroColumnas; i++){
							datosFila[i] = rs.getObject(i + 1);			                    
						}

						Object datosUtiles=datosFila[1];
						datosFilaNomG.add(datosFila[9]);	                
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
					labelGrupoTrab=new JRadioButton("Grupo:");
					panelDiagTrab.add(labelGrupoTrab, new AnchorConstraint(342, 452, 435, 66, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					labelGrupoTrab.setPreferredSize(new java.awt.Dimension(119, 18));
				}
				ButtonGroup groupBotones = new ButtonGroup();
				groupBotones.add(labelNomYapTrab);
				groupBotones.add(labelGrupoTrab);
				labelGrupoTrab.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
									jComboNomYap.setEnabled(false);
									jComboBoxGrupoTrab.setEnabled(true);
								}
							});
						
				
				{
					labelFechaConsulta=new JLabel("Fecha a consultar: ");/*<html><center><br>(formato dd/mm/aaaa)</CENTER></html>*/
					panelDiagTrab.add(labelFechaConsulta, new AnchorConstraint(76, 189, 57, 26, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS));
					labelFechaConsulta.setPreferredSize(new java.awt.Dimension(111, 16));
				}
				{
					
					botonConsultar=new JButton("Consultar");
					panelDiagTrab.add(botonConsultar, new AnchorConstraint(745, 802, 877, 504, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					botonConsultar.setPreferredSize(new java.awt.Dimension(97, 21));
					botonConsultar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							//JFrame frame=new JFrame();
							try{
								String[] fec=textFecha.getText().split("/");
								/*System.out.println("fec: "+fec[0]+" "+fec[1]+" "+fec[2]);
								System.out.println("texfecha: "+textFecha.getText());
								*/
								GregorianCalendar aux=new GregorianCalendar(Integer.parseInt(fec[2]), Integer.parseInt(fec[1])-1, Integer.parseInt(fec[0]));
								diaTrabajoQuest= aux.getTime();
								//System.out.println("diatrab: "+diaTrabajoQuest);
								while(diaTrabajoQuest==null){
									System.out.println("entra while");
									diaL=new DiaLaboral();
								}
								}catch(ArrayIndexOutOfBoundsException e){
									e.printStackTrace();
									JLabel indexOut=new JLabel("<html><i>Revise la fecha, respete el formato</i></html>");
									resultado.removeAll();
									resultado.add(indexOut, new AnchorConstraint(110, 900, 250, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
									resultado.repaint();
									/*frame.getContentPane().add(resultado, BorderLayout.CENTER);
							    	frame.setSize(912, 619);
							    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							    	frame.setVisible(true);
							    	frame.pack();*/
									pestania.repaint();
								}catch (NumberFormatException e){
									resultado.removeAll();
									JLabel number=new JLabel("Solo números por favor");
									resultado.add(number, new AnchorConstraint(110, 900, 150, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
									pestania.repaint();
								}catch(NullPointerException e){
									e.printStackTrace();								
								}catch(Exception e){
									e.printStackTrace();
								}
						
							
							try{
								//System.out.println("segundo try");
								
								if(jComboNomYap.isEnabled()&& jComboNomYap.getSelectedItem()!=""){
									diaL=new DiaLaboral();
									String[] fec=textFecha.getText().split("/");
									GregorianCalendar aux=new GregorianCalendar(Integer.parseInt(fec[2]), Integer.parseInt(fec[1])-1, Integer.parseInt(fec[0]));
									System.out.println("algun time: "+aux.getTime());
									diaTrabajoQuest= aux.getTime();
									resultado.removeAll();
									AgenteGsi agenteConsultado=new AgenteGsi();
									agenteConsultado.setNombreYapellido((String) jComboNomYap.getSelectedItem());
									agenteConsultado.setNombreGrupo((String) datosFilaNomG.get(jComboNomYap.getSelectedIndex()-1));
									/*System.out.println("datosFilasG: "+(String) datosFilaNomG.get(jComboNomYap.getSelectedIndex()-1));
									System.out.println("diatrab: "+diaTrabajoQuest);*/
									Turno turno = new Turno(1);
									System.out.println(turno);
									resultado.add(diaL.calcularDia(diaTrabajoQuest, agenteConsultado), new AnchorConstraint(110, 900, 500, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
									turno=diaL.calcularTurno(diaTrabajoQuest, agenteConsultado);
									tabla.setCellSelectionEnabled(true);
									////mes=Integer.parseInt(diaSeparado[1]);
									if (turno!=null){
										System.out.println("año: "+(Integer.parseInt(fec[2])));
										año.setSelectedItem(Integer.parseInt(fec[2]));
										System.out.println("mes: "+CalendarioGrafic.nombreMesM(Integer.parseInt(fec[1])-1));
										mes.setSelectedIndex(Integer.parseInt(fec[1])-1);
										/**/
										mes.repaint();
										año.repaint();
										tabla.changeSelection(turno.getTurno()-1, Integer.parseInt(fec[0])-1, false, false);
										System.out.println(turno.getTurno()-1+" "+ (Integer.parseInt(fec[0])-1));
									}
									resultado.repaint();
									pestania.repaint();
								}else{
									diaL=new DiaLaboral();
									resultado.removeAll();
									String nombreG=(String)jComboBoxGrupoTrab.getSelectedItem();
									Grupo grupo =new Grupo(nombreG.toLowerCase());
									Turno turno= new Turno(1);
									System.out.println(turno);
									resultado.add(diaL.calcularDia(diaTrabajoQuest, grupo), new AnchorConstraint(110, 900, 500, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
									turno=diaL.calcularTurno(diaTrabajoQuest, grupo);
									System.out.println(turno.getTurno());
									pestania.repaint();
									
								}
								diaTrabajoQuest=null;

						}catch(NullPointerException e){
							e.printStackTrace();
							JLabel nullpoint=new JLabel("<html><i>Debe elegir un agente o grupo</i></html>");
							resultado.removeAll();
							resultado.add(nullpoint, new AnchorConstraint(110, 900, 250, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
							
							/*frame.getContentPane().add(resultado, BorderLayout.CENTER);
					    	frame.setSize(912, 619);
					    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					    	frame.setVisible(true);
					    	frame.pack();*/
							pestania.repaint();
						}
						}
					});
				}
				
			
		}
			} catch(Exception e) {
				e.printStackTrace();
			}
		return panelDiagTrab;
		
		
	}
	
	/**
	 * Dialogo para agregar agentes
	 * @param tabla 
	 * @param modelo 
	 * @param grupoLayout 
	 * @param panelA 
	 * @param scrollP 
	 * 
	 */
	public void agregarDialog( JTable tabla, DefaultTableModel modelo, JScrollPane scrollP ){
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
				labelNroLeg=new JLabel("Nro de Legajo: ");
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
				labelNomYap=new JLabel("Nombre y Apellido: ");
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
				labelFecNac=new JLabel("Fecha de nacimiento: ");
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
				labelTipoD=new JLabel("Tipo de doc: ");
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
				labelNroD=new JLabel("Nro de documento: ");
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
				labelDir=new JLabel("Dirección: ");
				panelDiag.add(labelDir, new AnchorConstraint(425, 383, 470, 230, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				labelDir.setPreferredSize(new java.awt.Dimension(55, 27));
			}
			{
				textDir=new JTextField(15);
				panelDiag.add(textDir, new AnchorConstraint(425, 932, 495, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				textDir.setPreferredSize(new java.awt.Dimension(162, 20));
			}
			{
				labelTel=new JLabel("Telefonos: ");
				panelDiag.add(labelTel, new AnchorConstraint(510, 385, 560, 220, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				labelTel.setPreferredSize(new java.awt.Dimension(55, 27));
			}
			{
				textTel=new JTextField(15);
				panelDiag.add(textTel, new AnchorConstraint(505, 932, 575, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				textTel.setPreferredSize(new java.awt.Dimension(102, 20));
			}
			{
				labelEmail=new JLabel("E-mail: ");
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
				labelGrupo=new JLabel("Grupo: ");
				panelDiag.add(labelGrupo, new AnchorConstraint(677, 385, 719, 273, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				labelGrupo.setPreferredSize(new java.awt.Dimension(30, 5));
			}
			{
				jComboBoxGrupo = new JComboBox();
				panelDiag.add(jComboBoxGrupo, new AnchorConstraint(670, 533, 661, 425, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
				jComboBoxGrupo.addItem("A");
				jComboBoxGrupo.addItem("B");
				jComboBoxGrupo.addItem("C");
				jComboBoxGrupo.addItem("D");
				jComboBoxGrupo.addItem("E");
				jComboBoxGrupo.addItem("F");
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

							agregarAg(dialogoAgreg);
						}
						
					}
				});
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
	
	public void dialogoModificar( JTable tabla, DefaultTableModel modelo, JScrollPane scrollP){
		final JDialog dialogoModif = new JDialog();
	    final AgenteGsi agentesillo = new AgenteGsi();
	    this.tabla = tabla;
	    this.modelo = modelo;
	    this.scrollP = scrollP;
		
		
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
					labelTel=new JLabel("Teléfonos:");
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
					jComboBoxGrupo.addItem("D");
					jComboBoxGrupo.addItem("E");
					jComboBoxGrupo.addItem("F");
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

								SwingUtilities.invokeLater(new Runnable()
								{

									public void run()
									{

										modifAg(agentesillo,dialogoModif);
									}
								});
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

							SwingUtilities.invokeLater(new Runnable()
							{

								public void run()
								{

									DefaultTableModel tab=new DefaultTableModel();
									modifAg(agentesillo,dialogoModif);
								}
							});
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


								SwingUtilities.invokeLater(new Runnable()
								{

									public void run()
									{
										DefaultTableModel tab=new DefaultTableModel();

										modifAg(agentesillo,dialogoModif);
									}
								});

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
	
	public JPanel ficha(AgenteGsi agente,JPanel panelFicha,JTabbedPane pestaña){

		panelFicha=new JPanel(new GridBagLayout());
		AnchorLayout panelLayout = new AnchorLayout();
		panelFicha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Agente Monitoreo")));
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
		panelFicha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Agente Monitoreo")));
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
	
	public void modifAg(AgenteGsi agentesillo,JDialog dialogoModif){
		agentesillo.modificarAgente("nro_legajo", Long.parseLong(textNroLeg.getText()));
		agentesillo.modificarAgente("nombre",textNomYap.getText());
		agentesillo.modificarAgente("fecha_nac",comboDiaNac.getSelectedIndex()+1,comboMesNac.getSelectedIndex(),comboAñoNac.getSelectedIndex()+1950);
		agentesillo.modificarAgente("tipo_dni",(String) comboTipoDni.getSelectedItem());
		agentesillo.modificarAgente("dni",Long.parseLong(textNroD.getText()));
		agentesillo.modificarAgente("direccion",textDir.getText());
		agentesillo.modificarAgente("telefonos",textTel.getText());
		agentesillo.modificarAgente("email",textEmail.getText());
		agentesillo.modificarAgente("grupo",((String) jComboBoxGrupo.getSelectedItem()).toLowerCase());
		
		
	    Maquetilla maq = new Maquetilla();
	    maq.actualizarTabla(rs,modelo, tabla, scrollP, 0);
	    
	    dialogoModif.dispose();
	    maq.repaint();
	}
	
	public void agregarAg(JDialog dialogoAgreg){

		String nombreGrupo=((String) jComboBoxGrupo.getSelectedItem()).toLowerCase();
		AgenteGsi agente=new AgenteGsi(textNomYap.getText(),comboDiaNac.getSelectedIndex()+1,comboMesNac.getSelectedIndex(),comboAñoNac.getSelectedIndex()+1950,textDir.getText(),textTel.getText(),textEmail.getText(),nombreGrupo);
		agente.setLegajo(Long.parseLong(textNroLeg.getText()));
		agente.setTipoDni((String) comboTipoDni.getSelectedItem());
		
		if (textNroD.getText()==null){
			agente.setDni(Long.parseLong(textNroD.getText()));
		}
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
		
	    rs = agente.getDatosAgentes();
	    
	    AgenteGsiDAO.cargarAgente(agente);
	    Maquetilla maq = new Maquetilla();
	    maq.actualizarTabla(rs, this.modelo, this.tabla, this.scrollP, 0);
	    
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
	public JPanel diaSelect(AgenteGsi agente,JPanel panelDia,JTable tabla) {
		// TODO Auto-generated method stub
		panelDia=new JPanel(new GridBagLayout());
		AnchorLayout panelLayout = new AnchorLayout();
		panelDia.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Info de turno")));
		panelDia.setLayout(panelLayout);
		panelDia.setPreferredSize(new java.awt.Dimension(260, 195));
		
		JLabel labelDia=new JLabel("<html><b>Dia:</b></html>");
		panelDia.add(labelDia, new AnchorConstraint(91, 327, 194, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelDia.setPreferredSize(new java.awt.Dimension(52,15));
		
		JLabel dia=new JLabel((tabla.getSelectedColumn()+1)+" de "+CalendarioGrafic.nombreMesM(CalendarioGrafic.getMes())+" de "+CalendarioGrafic.getAño());
		panelDia.add(dia, new AnchorConstraint(91, 924, 194, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		dia.setPreferredSize(new java.awt.Dimension(159, 20));

		JLabel labelNombre=new JLabel("<html><b>Grupo:</b></html>");
		panelDia.add(labelNombre, new AnchorConstraint(216, 327, 283, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNombre.setPreferredSize(new java.awt.Dimension(52, 15));

		JLabel groupo=new JLabel();
		groupo.setText((String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()));
		panelDia.add(groupo, new AnchorConstraint(216, 464, 283, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		groupo.setPreferredSize(new java.awt.Dimension(133, 15));

		JLabel labelTipo=new JLabel("<html><b>Integrantes:</b></html>");
		panelDia.add(labelTipo, new AnchorConstraint(314, 327, 384, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTipo.setPreferredSize(new java.awt.Dimension(76, 14));

		JTextArea integrantes=new JTextArea(5,1);
		integrantes.setText(agente.soloNombres((String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn())));
		integrantes.setOpaque(false);
		integrantes.setEditable(false);
		integrantes.setLineWrap(true);
		integrantes.setWrapStyleWord(true);
		integrantes.setFont(new Font("TAHOMA", Font.PLAIN, 11));
		JScrollPane scroll=new JScrollPane();
		panelDia.add(integrantes, new AnchorConstraint(314, 780, 894, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		integrantes.setPreferredSize(new java.awt.Dimension(165, 93));
		scroll.setPreferredSize(new java.awt.Dimension(80, 66));
				
		return panelDia;
	}
	
	/**
	 * Panel solo con rótulos, donde luego se cargará la información del dia que se seleccione
	 * @param agente
	 * @param panelDia
	 * @param tabla
	 * @return
	 */
	public JPanel diaSelectVacio(AgenteGsi agente,JPanel panelDia,JTable tabla) {
		// TODO Auto-generated method stub
		panelDia=new JPanel();
		AnchorLayout panelLayout = new AnchorLayout();
		panelDia.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Info de turno")));
		panelDia.setLayout(panelLayout);
		panelDia.setPreferredSize(new java.awt.Dimension(260, 195));
		
		JLabel labelDia=new JLabel("<html><b>Dia:</b></html>");
		panelDia.add(labelDia, new AnchorConstraint(91, 327, 194, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelDia.setPreferredSize(new java.awt.Dimension(52,15));
		
		JLabel labelNombre=new JLabel("<html><b>Grupo:</b></html>");
		panelDia.add(labelNombre, new AnchorConstraint(216, 327, 283, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelNombre.setPreferredSize(new java.awt.Dimension(52, 15));

		JLabel labelTipo=new JLabel("<html><b>Integrantes:</b></html>");
		panelDia.add(labelTipo, new AnchorConstraint(314, 327, 384, 60, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		labelTipo.setPreferredSize(new java.awt.Dimension(76, 14));
		
		return panelDia;
	}

}
