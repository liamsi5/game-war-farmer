package jeu;

import jeu.personnage.util.Armee;
import jeu.plateau.Plateau;
import jeu.util.JeuAgricole;
import jeu.util.JeuGuerre;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JeuMain{	
	  public static void main(String[] args){
		
		int i = args.length;		
		//Liste qui va contenir les joueurs
		List<Joueur> listeJoueur= new ArrayList<>();
		
		//On va ajouter dans notre liste les joueurs dont le nom a été donné en argument
		for (int a = 0; a<i;a++) {
			listeJoueur.add(new Joueur(args[a]));
		}
		
	    Scanner choix  = new Scanner(System.in);


	    System.out.println("À quel jeu jouez-vous? Agricole / Guerre");
	    String jeu = choix.nextLine();
	    
        Scanner lar = new Scanner(System.in);
        Scanner lon = new Scanner(System.in);

	    System.out.println("Entrez la largeur pour le plateau : ");
	    int largeur = lar.nextInt();
	    System.out.println("Entrez la longueur pour le plateau : ");
	    int longueur = lon.nextInt();

	    if(jeu.equals("Agricole")){
	      int t = 0;
	      JeuAgricole agr = new JeuAgricole(listeJoueur, largeur, longueur);
	      boolean fin = agr.avoirPlateau().plusDeTerritoire();
	      while(t<6 && !fin){
	        agr.jouerUnTour();
	        t+=1;
	      }
	      String vainqueur;
	      try{
	        vainqueur = agr.declarerVainqueur();
	      }
	      catch(Exception e){
	        System.out.println("Match nul!");
	      }
	    }
	    if(jeu.equals("Guerre")){
	      int t = 0;
	      JeuGuerre guerre = new JeuGuerre(listeJoueur, largeur, longueur);
	      boolean fin = guerre.avoirPlateau().plusDeTerritoire();
	      while(t<10 && !fin){
	        guerre.jouerUnTour();
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
	}