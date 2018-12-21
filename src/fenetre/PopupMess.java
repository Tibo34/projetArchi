package fenetre;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopupMess extends JOptionPane {
	
	public static void display(JFrame f,String str) {		
		showMessageDialog(f,str,"Selection",JOptionPane.DEFAULT_OPTION);		
	}
}
