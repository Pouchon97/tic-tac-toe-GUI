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
	// Re�oit une r�f�rence � la partie et son indice
	public Paneau(Partie part, int indice)
	{
		_indice = indice;	
		_part = part;
		setSize(60,60);
		setBackground(Color.lightGray);
		
		// Ajoute un MouseListener (�couteur de souris) qui va
		// intercepter les clicks sur le Paneau
		addMouseListener(new MouseAdapter()
			{
				// D�finition de la m�thode mouseClicked qui sera 
				// automatiquement appel�e quand il y aura un click
				//c'est la commande new MouseAdapter qui permet 
				//de la d�finir de suite
				public void mouseClicked(MouseEvent e)
				{
					if (_part.getIsRunning())
						_part.jeu((Paneau)e.getSource());
				}
			});
	}
	
	// M�thode qui renvoit le symbole du Paneau
	public int getSymbo()
	{
		return symbo;
	}

	// M�thode qui affecte le symbole du Paneau	
	public void setSymbo(int symb)
	{
		symbo = symb;
	}

	// M�thode qui renvoit l'�tat du Paneau (d�ssin� oui / non )
	public boolean getDejDess()
	{
		return dejDess;
	}
	
	// M�thode qui affecte l'�tat du Paneau
	public void setDejDess(boolean etat)
	{
		dejDess = etat;
	}	
	
	// M�thode qui renvoit l'indice du Paneau
	public int getIndice()
	{
		return _indice;	
	}
	
	// M�thode qui est appel� � la cr�ation d'un Paneau et
	// d�s que l'on fait un Paneau.repaint()
	
	public void paintComponent(Graphics g)
	{
		// Appel de la m�thode paintComponent de la classe parente
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
