package com.fenetre;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.sun.media.sound.Toolkit;

public class MorpionFrame extends JFrame implements ActionListener {
	
	private Paneau pan0,pan1,pan2,pan3,pan4,pan5,pan6,pan7,pan8;
	private Paneau pans[] = { pan0,pan1,pan2,pan3,pan4,pan5,pan6,pan7,pan8 };
	private Partie part;
	private JPanel haut, bas;
	private JButton but;
	
	private GridLayout grid;
	
	// Constructeur de la classe Morpion
	public MorpionFrame()
	{
		setTitle("Morpion");
				
		Container cont = getContentPane();
		
		// Le tableau des Paneau est transmis au constructeur de Partie
		part = new Partie(pans);
		
		// Création de 2 JPanel pour la mise en forme de la fenetre
		haut = new JPanel();
		cont.add(haut);
		bas = new JPanel();
		cont.add(bas,"South");

		// Le gestionnaire de mise en forme du Panel haut est redéfini
		// en grille de 3 lignes, 3 colonnes avec 2 pixels entre chaques
		haut.setLayout(new GridLayout(3,3,2,2));

		// Création des 9 Paneau et ajout au Panel haut
		for (int  i=0 ; i<9 ; i++)
		{
			pans[i] = new  Paneau(part,i);
			haut.add(pans[i]);
		}
				
		// Définition du bouton, ajout au Panel bas
		but = new JButton("Commencer");
		bas.add(but);

		// Ajout d'un ActionListener (écouteur d'action) sur le bouton
		// Cela va permettre d'intercepter le click par le biais de la 
		// méthode actionPerformed (nom de méthode déja défini et à respecter)

		but.addActionListener(this);
					
	}

	// Methode qui intercepte toutes les actions se déroulant dans la classe
	// Reçoit l'évenement déclencheur en argument
	
	public void actionPerformed(ActionEvent e)
	{
		// Test sur la source de l'évènement
		// Ici le test aurait pu etre supprimé etant donné qu'il n'y à
		// q'un seul bouton dans ma classe
		
		if (e.getSource() == but)
		{
			// Création d'une boite de dialogue de type bDial dérivée de JDial
			// Le constructeur attend comme argument une fenetre et une partie
			bDial dial = new bDial(this,part);
			dial.setSize(200,150);
			
			// Recupere la taille de l'écran et positionne lla bDial au milieu
//			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//			dial.setLocation((screenSize.width-dial.getWidth())/2,(screenSize.height-dial.getHeight())/2);
			
			dial.setLocationRelativeTo(null);
			
			dial.setVisible(true);
			
			// Lance la partie
			part.commencer();
		}
	}
}
