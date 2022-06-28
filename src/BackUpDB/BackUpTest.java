package BackUpDB;

import javax.swing.JOptionPane;

public class BackUpTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Set Look & Feel
		{
			
			try {
				javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		Object seleccion = JOptionPane.showInputDialog(
				   null,
				   "Elija modelo",
				   "Back Up",
				   JOptionPane.QUESTION_MESSAGE,
				   null,  // null para icono defecto
				   new Object[] { "Modelo 1", "Modelo 2" }, 
				   "opcion 1");
		//int i=0;
		if(seleccion.equals("Modelo 1")){
			BackUpOldV backO=new BackUpOldV();
		}else{
			BackUpGUI back=new BackUpGUI();
		}

	}

}
