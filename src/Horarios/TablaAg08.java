package Horarios;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;


public class TablaAg08 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaAgentes;
	private JScrollPane scrollTabla;
	private ModeloNoEditable modeloTablaAg08 = new ModeloNoEditable();
	private ResultSet rs;

	public DefaultTableModel getModelAg08(){

		modeloTablaAg08.addColumn("Nombre y Apellido");
		modeloTablaAg08.addColumn("Dirección");
		modeloTablaAg08.addColumn("Teléfono");
		modeloTablaAg08.addColumn("E-mail");
		modeloTablaAg08.addColumn("Grupo");

		return modeloTablaAg08;
	}

	public JPanel tabla(final JPanel panelGral, final JPanel panelFicha, final JTabbedPane pestania){

		AnchorLayout panelLayout = new AnchorLayout();
		panelGral.setLayout(panelLayout);

		tablaAgentes=new JTable(12,7);
		tablaAgentes.setAutoscrolls(true);
		tablaAgentes.setModel(getModelAg08());
		tablaAgentes.setAutoCreateRowSorter(true);
		tablaAgentes.setAutoCreateColumnsFromModel(true);
		tablaAgentes.setBorder(new LineBorder(new Color(0,0,0), 1, true));
		tablaAgentes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaAgentes.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				if(e.getClickCount() == 1) {
					AgenteGsi agentesillo=new AgenteGsi();
					//System.out.println((String)tablaAgentes.getValueAt(tablaAgentes.getSelectedRow(), 0));
					agentesillo.leerAgente((String)tablaAgentes.getValueAt(tablaAgentes.getSelectedRow(), 0));
					panelGral.add(agentesillo.ficha(panelFicha,pestania), new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					//panelA.repaint();
				}
			}
		});

		scrollTabla= new JScrollPane();
		scrollTabla.setViewportView(tablaAgentes);
		panelGral.add(scrollTabla, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		scrollTabla.setPreferredSize(new java.awt.Dimension(697, 421));


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
		//cargaFilasDeAgentes(modeloTablaAg08,rs);
		tablaAgentes.setModel(modeloTablaAg08);




		return panelGral;
	}

	/**
	 * Añade al DefaultTableModel las filas correspondientes al ResultSet.
	 * @param rs El resultado de la consulta a base de datos
	 * @param modelo El DefaultTableModel que queremos rellenar.
	 */
	static void cargaFilasDeAgentes(DefaultTableModel modelo, ResultSet rs)
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

	/** Añade al DefaultTableModel las filas correspondientes al arreglo.
	 * @param agentes los que se cargaran en la tabla
	 * @param modelo El DefaultTableModel que queremos rellenar.
	 */
	public static void cargaFilasBusquedaAgentes08(final DefaultTableModel modelo, ArrayList<AgenteCerocho> agentes)
	{

		try{
			int numeroFila = 0;
			// Para cada agente en el array
			for (int j=0; j<agentes.size();j++)
			{
				Object[] datosUtiles={agentes.get(j).getNombreyApellido(),agentes.get(j).getDireccion(),agentes.get(j).getTelefono(),
						agentes.get(j).getEmail(),agentes.get(j).getNombreGrupo(), agentes.get(j).getSubGrupo()};
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void actualizarTablaAg08(ModeloNoEditable modelo, JTable tabla,JScrollPane scroll,ArrayList<AgenteCerocho> ag08) {
		// TODO Auto-generated method stub
		try		
		{

			vaciaFilasModelo(modelo);
			cargaFilasBusquedaAgentes08(modelo, ag08);

			tabla.setModel(modelo);		
			scroll.setViewportView(tabla);}
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
