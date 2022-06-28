package Horarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Install {

	
	public void copiarFile(String origen, String destino) throws IOException{
		File sourceFile = new File(origen);  
	    File destinyFile = new File(destino);  
	    InputStream in = new FileInputStream(sourceFile);  
	      
	    OutputStream out = new FileOutputStream(destinyFile);  
	      
	    byte[] buf = new byte[2048];  
	    int len;  
	    while ((len = in.read(buf)) > 0) {  
	        out.write(buf, 0, len);  
	    }  
	    in.close();  
	    out.close();  
		
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
