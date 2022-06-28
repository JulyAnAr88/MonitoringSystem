package Horarios;

import java.io.IOException;

import javax.swing.SwingUtilities;



public class HorarioTest {
	

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Maquetilla inst = new Maquetilla();
				inst.setLocationRelativeTo(null);
				inst.getContentPane().setLayout(null);
				inst.setVisible(true);
			}/**/
		});/*
		Conectarse conect=new Conectarse("jdbc:mysql://localhost:3306/", "root", "java");
		
		Turno turno=new Turno();*/
		//turno.copiarFile("C:\\Documents and Settings\\Administrador\\Mis documentos\\Documentos del Lic. Aragón\\Java\\para llevar a la sala\\juli.msi", "c:\\mysql-essential-5.1.49-win32.msi");
		

	}

}
