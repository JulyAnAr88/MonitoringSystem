package Horarios;
import java.awt.Component;

import com.cloudgarden.layout.AnchorConstraint;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
//import javax.swing.JTable;

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
public class DialogoExportacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton botonJCalendar;


	public DialogoExportacion() {
		// TODO Auto-generated constructor stub
	}

	public void launchDialog(){
		this.setVisible(true);
		AnchorLayout panelLayout = new AnchorLayout();
		this.setLayout(panelLayout);
		this.setSize(740, 463);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel panelMes=new JPanel();
		panelMes.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder("Mes")));

		getContentPane().add(panelMes);
		Component panelAgente = new JPanel();
		getContentPane().add(panelAgente);
		
		//JTable tablaMes=new JTable();
		
		
		
		
		getContentPane().add(panelMes, new AnchorConstraint(124, 943, 924, 36, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		panelMes.setPreferredSize(new java.awt.Dimension(664, 344));
		{
			JPanel panelAgente1=new JPanel();
			panelMes.add(panelAgente1);
			panelAgente1.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createTitledBorder("Marcar")));
			panelAgente1.setPreferredSize(new java.awt.Dimension(89, 54));
		}
		{
			botonJCalendar = new JButton("calendario");
			panelMes.add(botonJCalendar);
			//botonJCalendar.setText("calendario");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DialogoExportacion edialos=new DialogoExportacion();
		edialos.launchDialog();

	}

}
