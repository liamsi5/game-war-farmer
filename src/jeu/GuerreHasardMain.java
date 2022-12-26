package jeu;

import java.util.ArrayList;
import java.util.List;

import jeu.util.JeuGuerre;
/**
 * Main pour lancer une partie du jeu Guerre seulement les demandes du sujet
 *
 */
public class GuerreHasardMain{	
	  public static void main(String[] args){
		
		int i = args.length;		
		//Liste qui va contenir les joueurs
		List<Joueur> listeJoueur= new ArrayList<>();
		
		//On va ajouter dans notre liste les joueurs dont le nom a été donné en argument
		for (int a = 0; a<i;a++) {
			listeJoueur.add(new Joueur(args[a]));
		}
	    
	    int largeur = 10;
	    int longueur = 10;

      int t = 0;
      JeuGuerre guerre = new JeuGuerre(listeJoueur, largeur, longueur);
      boolean fin = guerre.avoirPlateau().plusDeTerritoire();
      while(t<10 && !fin){
        guerre.jouerUnTourHasard();
        t+=1;
        fin = guerre.avoirPlateau().plusDeTerritoire();
      }
      String vainqueur;
      try{
        vainqueur = guerre.declarerVainqueur();
        System.out.println("LE VAINQUEUR EST...............");
        System.out.println(vainqueur);

      }
      catch(Exception e){
        System.out.println("Match nul!");
      }
	    
	  }
	}