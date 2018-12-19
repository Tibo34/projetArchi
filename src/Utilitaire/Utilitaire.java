package Utilitaire;

import java.text.DecimalFormat;

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

}
