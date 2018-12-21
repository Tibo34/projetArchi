package Utilitaire;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Exception.ConvertDoubleExcpetion;
import Exception.ConvertIntegerException;
import Exception.StringException;

public class Utilitaire {
	
	 public static String formatDouble(double a) {
	    	DecimalFormat format=new DecimalFormat("#0.##");    	
	    	String s=format.format(a);
	    	String[]split;
	    	split=s.split(",");	    	
	    	if(split.length>1) {
	    		if(split[1].length()<2) {
	    			s=split[0]+","+split[1]+"0";
	    		}
	    	}
	    	else {
	    		s+=",00";
	    	}
	    	return s;
	 }
	 
	 public static double roundDouble(double a) {
	    	a=Math.round(a*100);
	    	a/=100;
	    	return a; 
	    }
	 
	 public static String extractString(String str) throws StringException {
			if(str.isEmpty()) {
				throw new StringException();
			}
			return str;
		}

	 public static int convertInteger(String str) throws ConvertIntegerException {
			int num=0;
			try {
				num=Integer.parseInt(str);
			}
			catch(NumberFormatException e) {
				throw new ConvertIntegerException();
			}
			return num;
		}

	 public static double convertDouble(String str) throws ConvertDoubleExcpetion {
			double num=0;
			try {
				num=Double.parseDouble(str);
			}
			catch(NumberFormatException e) {
				throw new ConvertDoubleExcpetion();
			}
			return num;
		}
	 
	 public static boolean checkQte(JFrame parentComponent, String text) {
			int qte;
			try {
				qte = Integer.parseInt(text);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(parentComponent, "La quantité entrée n'est pas un nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
				return true;

			}

			if (qte <= 0) {
				JOptionPane.showMessageDialog(parentComponent, "La quantité entrée ne peut être négative ou nulle", "Erreur", JOptionPane.ERROR_MESSAGE);
				return true;
			}
			return false;
		}

	public static Properties LoadFileProperties() {
		String file="properties.propertie";
		Properties p=new Properties();
		 try(InputStream in=new FileInputStream(file)){
			 p.load(in);
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 return p;
	}
}
