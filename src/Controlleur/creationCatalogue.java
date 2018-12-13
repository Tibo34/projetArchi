package Controlleur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class creationCatalogue {
	
	public static void main(String[] args) {
		String file="properties.propertie";
		Properties p=new Properties();
		
		 p.setProperty("Mars","qte:5 prix:1.1");
		 p.setProperty("Raider","qte:5 prix:1.0");
		 p.setProperty("Twix","qte:5 prix:1.2");
		 p.setProperty("Treets","qte:5 prix:1.3");
		 p.setProperty("M&M's","qte:5 prix:2.5");
		 p.setProperty("Smarties","qte:5 prix:1.5");
		 try(OutputStream out=new FileOutputStream(file)){
			 p.store(out,"Fichier catalogue");
			 
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 
	}
	
	

}
