package com.fenetre;

import java.awt.Dimension;

//import com.sun.media.sound.Toolkit;

public class Jouer {
	public static void main(String[] args) 
	{
		// Cr�ation d'une fenetre MorpionFrame
		MorpionFrame morp = new MorpionFrame();

		morp.setSize(190,250);
		
		// R�cup�re la taille de l'�cran pour centrer la fenetre
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		morp.setLocation((screenSize.width-morp.getWidth())/2,(screenSize.height-morp.getHeight())/2);
		
		morp.setLocationRelativeTo(null);
		
		morp.setVisible(true);
	}
}
