package com.fenetre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Paneau extends JPanel {
	
	private boolean dejDess = false;
	private Partie _part;
	private int _indice;
	private int symbo = 2;
	
	// Constructeur de Paneau
	// Reçoit une référence à la partie et son indice
	public Paneau(Partie part, int indice)
	{
		_indice = indice;	
		_part = part;
		setSize(60,60);
		setBackground(Color.lightGray);
		
		// Ajoute un MouseListener (écouteur de souris) qui va
		// intercepter les clicks sur le Paneau
		addMouseListener(new MouseAdapter()
			{
				// Définition de la méthode mouseClicked qui sera 
				// automatiquement appelée quand il y aura un click
				//c'est la commande new MouseAdapter qui permet 
				//de la définir de suite
				public void mouseClicked(MouseEvent e)
				{
					if (_part.getIsRunning())
						_part.jeu((Paneau)e.getSource());
				}
			});
	}
	
	// Méthode qui renvoit le symbole du Paneau
	public int getSymbo()
	{
		return symbo;
	}

	// Méthode qui affecte le symbole du Paneau	
	public void setSymbo(int symb)
	{
		symbo = symb;
	}

	// Méthode qui renvoit l'état du Paneau (déssiné oui / non )
	public boolean getDejDess()
	{
		return dejDess;
	}
	
	// Méthode qui affecte l'état du Paneau
	public void setDejDess(boolean etat)
	{
		dejDess = etat;
	}	
	
	// Méthode qui renvoit l'indice du Paneau
	public int getIndice()
	{
		return _indice;	
	}
	
	// Méthode qui est appelé à la création d'un Paneau et
	// dès que l'on fait un Paneau.repaint()
	
	public void paintComponent(Graphics g)
	{
		// Appel de la méthode paintComponent de la classe parente
		super.paintComponent(g);
		
		if (symbo == _part.CROIX)
		{
			// Dessine une croix
			g.setColor(Color.blue);
			
			g.drawLine(5,5,50,50);
			g.drawLine(50,5,5,50);
		}
		if (symbo == _part.ROND)
		{
			// Dessine un rond
			g.setColor(Color.yellow);
			g.drawOval(5,5,this.getWidth()-5,this.getHeight()-5);
		}
	}
}
