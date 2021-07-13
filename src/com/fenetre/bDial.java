package com.fenetre;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class bDial extends JDialog implements ActionListener{
	
	private JButton	butOk;
	private JLabel		lNom,lSymb,lRegl;
	private JTextField	fNom;
	private JComboBox	bSymb;
	private Partie		_part;
	
	// Constructeur de bDial
	// Argument 1 : La fenetre à laquelle la bDial appartient
	// Argument 2 : La partie en cours
	
	public bDial(JFrame prop,Partie part)
	{	
		// Appel du constructeur de la classe de base (JDialog)
		super (prop, "Informations Joueur", true);

		_part = part;
		butOk = new JButton("Ok");
		lNom = new JLabel("  Votre nom :");
		fNom = new JTextField(10);
		fNom.setText("Moi");
		lSymb = new JLabel("  Symbole :");
		lRegl = new JLabel("      Le rond commence la partie !");
		String tSymb[] = { "Croix", "Rond" };
		bSymb = new JComboBox(tSymb);
		bSymb.setSelectedIndex(1);
		
		JPanel haut = new JPanel();
		JPanel bas = new JPanel();
		JPanel mil = new JPanel();
		Container  c = getContentPane();
		c.add(haut,"North");
		c.add(mil);
		c.add(bas,"South");
		
		haut.setLayout(new GridLayout(2,2,5,5));
		haut.add(lNom);
		haut.add(fNom);
		haut.add(lSymb);
		haut.add(bSymb);
		
		mil.add(lRegl);
		
		bas.add(butOk);
		butOk.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Création du joueur dans la partie
		_part.createJoueurs((int)bSymb.getSelectedIndex(), fNom.getText());
		
		setVisible(false);
		
		// Destruction de l'objet bDial
		dispose();
	}
}
