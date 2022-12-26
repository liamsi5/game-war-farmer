package jeu;

import jeu.personnage.util.Armee;
import jeu.plateau.Plateau;
import jeu.util.JeuAgricole;
import jeu.util.JeuGuerre;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Main pour lancer une partie du jeu Agricole seulement les demandes du sujet
 *
 */
public class AgricoleMain{	
	  public static void main(String[] args){
		
		int i = args.length;		
		//Liste qui va contenir les joueurs
		List<Joueur> listeJoueur= new ArrayList<>();
		
		//On va ajouter dans notre liste les joueurs dont le nom a t donn en argument
		for (int a = 0; a<i;a++) {
			listeJoueur.add(new Joueur(args[a]));
		}
		

	    int largeur = 10;
	    int longueur = 10;

      int t = 0;
      JeuAgricole agr = new JeuAgricole(listeJoueur, largeur, longueur);
      boolean fin = agr.avoirPlateau().plusDeTerritoire();
      while(t<6 && !fin){
        agr.jouerUnTour();
        t+=1;
        fin = agr.avoirPlateau().plusDeTerritoire();

      }
      String vainqueur;
      try{
        vainqueur = agr.declarerVainqueur();
        System.out.println("LE VAINQUEUR EST...............");
        System.out.println(vainqueur);
      }
      catch(Exception e){
        System.out.println("Match nul!");
      }
    

	  }
	}
