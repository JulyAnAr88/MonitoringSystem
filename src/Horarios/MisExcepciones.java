package Horarios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
* 
*/
public class MisExcepciones extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton boton=new JButton("pulsame");
	
	
	public MisExcepciones(){
		super("prueba");
		correGUI();
	}
	public void correGUI(){
		try{
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().add(boton, BorderLayout.NORTH);
			boton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null,"Revise la fecha, respete el formato","Consulta de turno",JOptionPane.CLOSED_OPTION);
				}
			});
			
			setVisible(true);
			pack();

		}catch(Exception e){
			
		}
	
	
	}
	public static void main (String args[]){
		
		//MisExcepciones mise=new MisExcepciones();
		
		
		
	}
	
	

}
