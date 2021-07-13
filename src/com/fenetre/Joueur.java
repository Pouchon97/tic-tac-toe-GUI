package com.fenetre;

public class Joueur {
	private int 		_symb;
	private String		_nom;
	
	// Constructeur de Joueur
	// Reçoit le nom et le symbole et les affectent
	public Joueur(int symb, String nom)
	{
		_symb = symb;
		_nom = nom;		
	}
	
	// Méthode qui renvoit le symbole
	public int getSymb()
	{
		return _symb;
	}
	
	// Méthode qui renvoit le nom
	public String getNom()
	{
		return _nom;
	}
}
