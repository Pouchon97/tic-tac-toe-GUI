package com.fenetre;

import java.util.Random;

import javax.swing.JOptionPane;

public class Partie {
	static public final int CROIX	= 0;
	static public final int ROND  	= 1;
	static public final int RIEN  	= 2;
	static public final int NON		= 10;
		
	private boolean 	isRunning = false;
	private int	 	tab[] = { RIEN,RIEN,RIEN,RIEN,RIEN,RIEN,RIEN,RIEN,RIEN };
	private int 		sol[][] = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
	private int 		strat = NON;
	private Joueur  	j1,j2;
	private Paneau		pans[];
	
	// Constructeur de Partie
	// Re�oit la r�f�rence au tableau de Paneau et l'affecte
	public Partie(Paneau t[])
	{
		pans = t;
		
	}
	
	// Cr�ation des Joueurs
	// Re�oit le nom et le symbole choisi par l'utilisateur dans la bDial
	public void createJoueurs(int symb, String nom)
	{
		j1 = new Joueur(symb,nom);
		if (symb == CROIX)
		{
			j2 = new Joueur(ROND,"L'Ordinateur");
			jeuOrdi();
		}
		else
			j2 = new Joueur(CROIX,"L'Ordinateur");
	}
	
	// M�thode qui renvoit le contenu d'une case donn�e de tab
	public int getCase(int _case)
	{
		return tab[_case];	
	}

	// M�thode qui affecte le contenu d'une case donn�e de tab	
	public void setCase(int _case, int symb)
	{
		tab[_case] = symb;
	}
	
	// M�thode qui initialise la partie
	public void commencer()
	{
		isRunning = true;
	}
	
	// M�thode qui fait jouer l'ordinateur
	// Teste si l'ordinateur peut gagner sur ce coup
	// Si oui joue ou il faut grace a ouJouer()
	// Si non, teste si l'ordinateur peut perdre au prochain coup
	// Si oui joue ou il faut pour l'emp�cher grace � ouJouer()
	// Si non teste si il peut essayer de mettre en place une combinaison gagnante
	// Si oui il le fait grace a isStrat()
	// Si non joue al�atoirement
	
	public void jeuOrdi()
	{
		int jouEn;
		
		jouEn = ouJouer(j2.getSymb());
		if (jouEn == NON)
		{
			jouEn = ouJouer(j1.getSymb());
			if (jouEn == NON)
			{
				isStrat();
				if (strat == NON)
				{
					
					Random alea = new Random();
					// G�n�re un nombre entre 0 et 8
					jouEn = alea.nextInt(9);
					
					// Recommence si la case est prise
					while ( tab[jouEn] != RIEN )
						jouEn = alea.nextInt(9);
				}
				else
					jouEn = strat;
			}
		}
		
		// Met tab �  jour		
		tab[jouEn] = j2.getSymb();
		
		// Dessine sur le Paneau correspondant
		pans[jouEn].setSymbo(j2.getSymb());
		pans[jouEn].repaint();
		
		// Appel de la m�thode qui interroge les r�gles
		// pour voir si la partie est finie
		// Lui transmet le joueur
		intRegl(j2);
		
	}
	
	
	// M�thode qui interroge les r�gles en transmettant 
	// le dernier symbole jou�
	// Re�oit un joueur en argument
	// Si un joueur � gagn� ou qu'il y a match nul : propose de rejouer
	public void intRegl(Joueur j)
	{
		int result = regles(j.getSymb());			
		
		if (result == 1)
		{
			JOptionPane.showMessageDialog(null,j.getNom() + " a Gagn� !");

			int rep = JOptionPane.showConfirmDialog(null,"Voulez vous rejouer ?","Partie finie",JOptionPane.YES_NO_OPTION)	;
			propRej(rep);
		}
		else if ( ( j.getNom() != "L'Ordinateur" ) && (result != RIEN) )
					jeuOrdi();
		
		if (result == RIEN)
		{
			int rep = JOptionPane.showConfirmDialog(null,"Voulez vous rejouer ?","Partie finie",JOptionPane.YES_NO_OPTION)	;
			propRej(rep);
		}
	}
	
	// M�thode qui g�re la r�ponse � la proposition de rejouer
	// Attend la r�ponse comme argument
	// Si oui -> remet tout � z�ro
	// Si non -> quitte l'application
	public void propRej(int rep)
	{
		
		if (rep == 0)
		{
			for (int i = 0 ; i < 9 ; i++)
			{
				pans[i].setSymbo(RIEN);
				pans[i].setDejDess(false);
				pans[i].repaint();
				tab[i] = RIEN;
			}
			if (j2.getSymb() == ROND)
				jeuOrdi();
		}
		else
			System.exit(0); 
			
	}
	
	// M�thode qui est appel�e quand l'utilisateur clique sur un Paneau
	// Re�oit une r�f�rence au Paneau en argument
	public void jeu(Paneau p)
	{
		// Teste si le Paneau est d�ja dessin� ou non
		if (p.getDejDess() == false)
		{
			p.setDejDess(true);
			
			// Dessine sur le Paneau et met tab � jour
			p.setSymbo(j1.getSymb());
			tab[p.getIndice()] = j1.getSymb();
			p.repaint();
			
			// Appelle l'interrogation des r�gles pour l'utilisateur
			intRegl(j1);
		}
	}

	// M�thode qui renvoit l'�tat de la partie
	public boolean getIsRunning()
	{
		return isRunning;	
	}
	
	// M�thode qui d�termine si l'ordinateur peut perdre ou gagner au prochain coup
	// Parcours le tableau des combinaisons gagnantes et regarde si
	// le symbole  re�u peut en faire une ou  au prochain coup
	// si oui -> affecte le num�ro de la case d'ou vient le danger/la solution � jouer
	public int ouJouer(int symb)
	{
		int cases = 0,c1,c2,c3;
		int jouer = NON;
		
		while ( (jouer == NON) && (cases < 8) )
		{
			for (cases = 0 ; cases < 8  ; cases++)
			{
				c1 = tab[sol[cases][0]];
				c2 = tab[sol[cases][1]];
				c3 = tab[sol[cases][2]];
				
				if ( (c1 == symb) && ( (c2 == c1) && (c3 == RIEN) ) )
					jouer = sol[cases][2];
				if ( (c1 == symb) && ( (c3 == c1) && (c2 == RIEN) ) )
					jouer = sol[cases][1];
				if ( (c2 == symb) && ( (c2 == c3) && (c1 == RIEN) ) )
					jouer = sol[cases][0];
					
			}
		}
		return jouer;	
	}
	
	
	// M�thode qui d�termine si l'ordinateur peut gagner dans 2 coups
	// Parcours le tableau des combinaisons gagnantes et regarde si
	// il peut en faire une dans 2 coups
	// si oui -> affecte le num�ro d'une des cases gagnantes � strat
	public void isStrat()
	{
		int cases = 0,c1,c2,c3;
		strat = NON;
		
		while ( (strat == NON) && (cases < 8) )
		{
			for (cases = 0 ; cases < 8  ; cases++)
			{
				c1 = tab[sol[cases][0]];
				c2 = tab[sol[cases][1]];
				c3 = tab[sol[cases][2]];
				
				if ( (c1 == j2.getSymb()) && ( (c2 == c3) && (c3 == RIEN) ) )
					strat = sol[cases][2];
				if ( (c1 == j2.getSymb()) && ( (c3 == c2) && (c2 == RIEN) ) )
					strat = sol[cases][1];
				if ( (c2 == j2.getSymb()) && ( (c1 == c3) && (c1 == RIEN) ) )
					strat = sol[cases][0];
			}
		}	
	}
	
	// M�thode qui d�termine si un symbole � gagn� ou si match nul
	// Parcours tab pour voir si il reste des cases vides
	// Puis parcours le tableau des combinaisons gagnantes et regarde si
	// le symbole re�u en argument en a fait une.
	// si oui -> renvoit 1
	// si non -> renvoit 0 si il reste des cases vides ou 2 si match nul
	
	//  Re�oit le symbole du joueur qui vient juste de jouer
	public int regles(int symbCur)
	{
		int cases = 0,c1,c2,c3,parc;
		parc = tab[cases++];
		while ( (parc != RIEN) && (cases < 9) )
			parc = tab[cases++];
		
		for (cases = 0 ; cases < 8  ; cases++)
		{
			c1 = tab[sol[cases][0]];
			c2 = tab[sol[cases][1]];
			c3 = tab[sol[cases][2]];
			
			if ( (c1 == symbCur) && ( (c2 == c1) && (c3 == c1) ) )
				return 1;
		}
		
		if (parc == RIEN)
			return 0;
		else
			return 2;
	}
}
