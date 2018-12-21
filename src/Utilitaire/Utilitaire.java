package Utilitaire;

import java.text.DecimalFormat;

import fenetre.ConvertDoubleExcpetion;
import fenetre.ConvertIntegerException;
import fenetre.StringException;

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

}
