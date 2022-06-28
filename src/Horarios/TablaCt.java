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



public class TablaCt extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaAgentes;
	private JScrollPane scrollTabla;
	private ModeloNoEditable modeloTablaCt = new ModeloNoEditable();
	private ResultSet rs;
	
	public DefaultTableModel getModelContactos(){
		 
        modeloTablaCt.addColumn("Nombre");
        modeloTablaCt.addColumn("Dirección");
        modeloTablaCt.addColumn("Teléfono");
        modeloTablaCt.addColumn("E-mail");
        modeloTablaCt.addColumn("Comentario");

        return modeloTablaCt;
    }

	public void tabla(JScrollPane scrollTabla/*final JPanel panelGral, final JPanel panelFicha, final JTabbedPane pestania*/){
		
		AnchorLayout panelLayout = new AnchorLayout();
    	//panelGral.setLayout(panelLayout);
		
    	tablaAgentes=new JTable(12,7);
		tablaAgentes.setAutoscrolls(true);
		tablaAgentes.setModel(getModelContactos());
		tablaAgentes.setAutoCreateRowSorter(true);
		tablaAgentes.setAutoCreateColumnsFromModel(true);
		tablaAgentes.setBorder(new LineBorder(new Color(0,0,0), 1, true));
		tablaAgentes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		/*
		 * tablaAgentes.addMouseListener(new MouseAdapter() { public void
		 * mouseClicked(MouseEvent e) { if(e.getClickCount() == 1) { AgenteGsi
		 * agentesillo=new AgenteGsi();
		 * //System.out.println((String)tablaAgentes.getValueAt(tablaAgentes.
		 * getSelectedRow(), 0));
		 * agentesillo.leerAgente((String)tablaAgentes.getValueAt(tablaAgentes.
		 * getSelectedRow(), 0)); panelGral.add(agentesillo.ficha(panelFicha,pestania),
		 * new AnchorConstraint(80, 990, 560, 710, AnchorConstraint.ANCHOR_REL,
		 * AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL,
		 * AnchorConstraint.ANCHOR_REL)); //panelA.repaint(); } } });
		 */
		
		scrollTabla.setViewportView(tablaAgentes);
		//panelGral.add(scrollTabla, new AnchorConstraint(80, 700, 895, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		scrollTabla.setPreferredSize(new java.awt.Dimension(697, 421));
		
		
		TableColumn columnaNom=tablaAgentes.getColumn("Nombre");
		columnaNom.setPreferredWidth(110);
		TableColumn columnDirec=tablaAgentes.getColumn("Dirección");
		columnDirec.setPreferredWidth(100);
		TableColumn columnaTelefono=tablaAgentes.getColumn("Teléfono");
		columnaTelefono.setPreferredWidth(100);
		TableColumn columnaEmail=tablaAgentes.getColumn("E-mail");
		columnaEmail.setPreferredWidth(110);
		TableColumn columGrupo=tablaAgentes.getColumn("Comentario");
		columGrupo.setPreferredWidth(100);
		
		AgenteGsi agenteD=new AgenteGsi();
		rs=agenteD.getDatosAgentes();
		cargaFilasDeContactos(modeloTablaCt,rs);
		tablaAgentes.setModel(modeloTablaCt);
		
		

	}

	/** Añade al DefaultTableModel las filas correspondientes al ResultSet.
	 * @param rs El resultado de la consulta a base de datos
	 * @param modelo El DefaultTableModel que queremos rellenar.
	 */
	public void actualizarTablaCt(ModeloNoEditable modelo, JTable tabla,JScrollPane scroll,ArrayList<Contacto> contactos) {
		// TODO Auto-generated method stub
		try		
    	{
    	
    	vaciaFilasModelo(modelo);
    		cargaFilasBusquedaCt(modelo, contactos);
    	
		tabla.setModel(modelo);		
		scroll.setViewportView(tabla);}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
		
	/**
	 * Añade al DefaultTableModel las filas correspondientes al arreglo.
	 * @param modelo
	 * @param contactos
	 */
	private static void cargaFilasBusquedaCt(final DefaultTableModel modelo, ArrayList<Contacto> contactos)
	{
		
		try{
			int numeroFila = 0;
			// Para cada agente en el array
			for (int j=0; j<contactos.size();j++)
			{
				Object[] datosUtiles={contactos.get(j).getNombre(),contactos.get(j).getDireccion(),contactos.get(j).getTelefonos(),
						contactos.get(j).getEmail(),contactos.get(j).getComentario()};
				modelo.addRow(datosUtiles);
				numeroFila++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
   }
	
	
	public static void cargaFilasDeContactos(final DefaultTableModel modelo, ResultSet rs)
	{
		Contacto cont=new Contacto();
		rs=cont.getDatosCt();
		try{
			int numeroFila = 0;
			// Para cada registro de resultado en la consulta 
			while (rs.next())
			{
				// Se crea y rellena la fila para el modelo de la tabla.
				ResultSetMetaData metaDatos = rs.getMetaData();

				// Se obtiene el numero de columnas.
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] datosFila = new Object[10];


							for (int i = 0; i < numeroColumnas; i++){
								datosFila[i] = rs.getObject(i + 1);

							}

							//for (int k=0,utiles.s);
							Object[] datosUtiles={datosFila[1],datosFila[2],datosFila[3],datosFila[4],datosFila[5]};
							modelo.addRow(datosUtiles);
							numeroFila++;
						}
					} catch (Exception e)
					{
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
	    
	    public int getSelectedRow() {
	    	// TODO Auto-generated method stub

	    	return tablaAgentes.getSelectedRow();
	    }

		public String getValueAt(int selectedRow, int i) {
			// TODO Auto-generated method stub
			String value=new String();
			value=(String) tablaAgentes.getValueAt(selectedRow, i);
			return value;
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
