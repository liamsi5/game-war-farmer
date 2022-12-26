package jeu.util;

import jeu.*;
import jeu.personnage.util.Armee;
import jeu.personnage.util.Ouvrier;
import jeu.plateau.Tuile;
import java.util.*;

public class JeuAgricole extends Jeu{

	  /** Constructeur de la classe JeuGuerre.
	   * @param joueurs liste des joueurs 
	   * @param largeur largeur du plateau sur lequel se droulera la partie.
	   * @param longueur longueur du plateau sur lequel se droulera la partie.
	   */
	  public JeuAgricole(List<Joueur> joueurs, int largeur, int longueur){
	    super(joueurs, largeur, longueur);
	  }

	  /**
	   * Effectue un tour de la partie en cours.
	   */
	  public void jouerUnTour(){
	    for(Joueur joueur : this.joueurs){
	    	
	      Scanner sc = new Scanner(System.in);
	      System.out.println("C'est  " + joueur.avoirNom() + " de jouer!");
	      System.out.println("Dployer un ouvrier, changer des ressources ou attendre? Deployer/Echanger/Attendre");
	      
	      String action = sc.nextLine();
	      if(action.equals("Deployer")){
	        System.out.println("Tuiles disponibles :");
	        for(Tuile tuile : this.plateau.avoirListeTuiles()){
	          if(!(tuile.isOcean() || tuile.estOccupe()) || (tuile.estOccupe() && !tuile.avoirPersonnage().estEnVie()) ){
	            System.out.println("Tuile de coordonnes (" + tuile.avoirx() + "," + tuile.avoiry() + ") qui est de type "+tuile.toString());
	          }
	        }
	        System.out.println("Choisissez une tuile parmi celles proposes.");
	        int x = sc.nextInt();
	        int y = sc.nextInt();
	        try{
	          joueur.deployerOuvrier(x,y);
	        }
	        catch(Exception e){
	          System.out.println("Coordonnes ou ressources indisponibles.");
	        }
	        System.out.println(joueur.avoirNom() + " a dploy un ouvrier sur la tuile de coordonnes (" + this.plateau.avoirTuile(x,y).avoirx() + "," + this.plateau.avoirTuile(x,y).avoiry() + ") qui est de type "+this.plateau.avoirTuile(x,y).toString());
	      }
	      if(action.equals("Echanger")){
	        joueur.convertirLOrTousOuvriers();
	        System.out.println(joueur.avoirNom() + " a chang ses ressources contre de l'or.");
	      }
	      System.out.println("Choisissez la quantit de ressources que vous rcolterez.");
	      int q = sc.nextInt();
	      joueur.recolteressourceOuvrier(q);
	      System.out.println(joueur.avoirNom() + " a rcolt " + q + " units de ressources chez ses ouvriers.");
	      joueur.recolteOr();
	      joueur.payerOuvrier();
	      
	      System.out.println("#############Rcapitulatif du joueur "+joueur.avoirNom()+"######################################");
	      System.out.println("#"+joueur.avoirNom()+" a "+joueur.avoirOrOuvrier() +" Or en stock. #");
	      System.out.println("#Listes des tuiles possdes par le joueur:#");
	      for(Ouvrier ouvrier : joueur.avoirListeOuvrier()) {
	      	System.out.println("#-)un ouvrier  sur la tuile ("+ouvrier.avoirPosition().avoirx()+","+ouvrier.avoirPosition().avoiry()+") qui est de type"+ouvrier.avoirPosition().toString()+"#");
	      }
	      System.out.println("#############################################################################");
	      
	      
	    }
	  }

	  /**
	   * Effectue un tour de la partie en cours avec des choix aléatoires.
	   */
	  public void jouerUnTourHasard(){
	    for(Joueur joueur : this.joueurs){
	      int x;
	      int y;	      
	      List<Tuile>  tuiles = new ArrayList<Tuile>();
	      Scanner sc = new Scanner(System.in);
	      System.out.println("C'est à " + joueur.avoirNom() + " de jouer!");
	      System.out.println("Déployer un ouvrier, échanger des ressources ou attendre? Deployer/Echanger/Attendre");
	      


	      List<Integer> list = new ArrayList<Integer>();
	      list.add(0);
	      list.add(1);
	      list.add(2);
	      Random rand = new Random();
	      int v = list.get(rand.nextInt(list.size()));
	      
	      List<String> actions = new ArrayList<String>();
	      actions.add("Deployer");
	      actions.add("Echanger");
	      actions.add("Attendre");
	      String action = actions.get(v);




	      if(action.equals("Deployer")){
	        System.out.println("Tuiles disponibles :");
	        for(Tuile tuile : this.plateau.avoirListeTuiles()){
	          if(!(tuile.isOcean() || tuile.estOccupe()) || (tuile.estOccupe() && !tuile.avoirPersonnage().estEnVie()) ){
	            System.out.println("Tuile de coordonnées (" + tuile.avoirx() + "," + tuile.avoiry() + ") qui est de type "+tuile.toString());
	            tuiles.add(tuile);
	          }
	        }
	        System.out.println("Choisissez une tuile parmi celles proposées.");
	        
	        x=0;
	        y=0;
	        if(tuiles.size() > 0) {
	        	x = tuiles.get(0).avoirx();
	        	y = tuiles.get(0).avoiry();
	        }
	        
	        try{
	          joueur.deployerOuvrier(x,y);
	        }
	        catch(Exception e){
	          System.out.println("Coordonnées ou ressources indisponibles.");
	        }
	        System.out.println(joueur.avoirNom() + " a déployé un ouvrier sur la tuile de coordonnées (" + this.plateau.avoirTuile(x,y).avoirx() + "," + this.plateau.avoirTuile(x,y).avoiry() + ") qui est de type "+this.plateau.avoirTuile(x,y).toString());
	      }
	      if(action.equals("Echanger")){
	        joueur.convertirLOrTousOuvriers();
	        System.out.println(joueur.avoirNom() + " a échangé ses ressources contre de l'or.");
	      }
	      
	      System.out.println("On va récolter 10 ressources par ouvrier.");
	      int q = 10;
	      joueur.recolteressourceOuvrier(q);
	      System.out.println(joueur.avoirNom() + " a récolté " + q + " unités de ressources chez ses ouvriers.");
	      joueur.recolteOr();
	      joueur.payerOuvrier();
	      
	      System.out.println("#############Récapitulatif du joueur "+joueur.avoirNom()+"######################################");
	      System.out.println("#"+joueur.avoirNom()+" a "+joueur.avoirOrOuvrier() +" Or en stock. #");
	      System.out.println("#Listes des tuiles possédées par le joueur:#");
	      for(Ouvrier ouvrier : joueur.avoirListeOuvrier()) {
	    	  if(ouvrier.estEnVie()) {
	      	System.out.println("#-)un ouvrier  sur la tuile ("+ouvrier.avoirPosition().avoirx()+","+ouvrier.avoirPosition().avoiry()+") qui est de type"+ouvrier.avoirPosition().toString()+"#");
	      }}
	      System.out.println("#############################################################################");
	      
	      
	    }
	  }

	  
	  /** Dclare le vainqueur de la partie ou dclenche une exception en
	   * cas d'galit.
	   * @return le joueur victorieux de la partie.
	   * @exception DrawException lance en cas d'galit.
	   */
	  public String declarerVainqueur() throws DrawException{
		  Joueur gagnant = null;
		  boolean egalite = false;
		  for(Joueur joueur: this.joueurs) {
			  if(gagnant==null) {
				  gagnant=joueur;
			  }
			  else if(joueur.avoirPointsOuvrier()>=gagnant.avoirPointsOuvrier()) {
				  if(joueur.avoirPointsOuvrier()==gagnant.avoirPointsOuvrier()) {
					  egalite = true;
				  }
				  else {
					  gagnant = joueur;
				  }
			  }
		  }
		  
	  if(egalite){
	    throw new DrawException("match nul!");
	  }
	  return gagnant.avoirNom() + " remporte la partie.";
	}
}

