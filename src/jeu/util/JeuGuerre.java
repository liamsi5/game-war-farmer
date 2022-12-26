package jeu.util;

import jeu.*;
import jeu.personnage.util.Armee;
import jeu.plateau.Tuile;
import java.util.*;

public class JeuGuerre extends Jeu{

  /** Constructeur de la classe JeuGuerre.
   * @param joueurs liste des joueurs
   * @param largeur largeur du plateau sur lequel se déroulera la partie.
   * @param longueur longueur du plateau sur lequel se déroulera la partie.
   */
  public JeuGuerre(List<Joueur> joueurs, int largeur, int longueur){
    super(joueurs, largeur, longueur);
  }

  /**
   * Effectue un tour de la partie en cours.
   */
  public void jouerUnTour(){
	  System.out.println("######################################### Nouveau tours de jeu  ########################################################");
    for(Joueur joueur : this.joueurs){
      Scanner sc = new Scanner(System.in);
      System.out.println("-----------------------------------------------------C'est à " + joueur.avoirNom() + " de jouer!--------------------------------------------");
      System.out.println("Déployer une armée? Oui/Non");
      String action = sc.nextLine();
      if(action.equals("Oui")){
        System.out.println("Tuiles disponibles :");
        for(Tuile tuile : this.plateau.avoirListeTuiles()){
          if(!(tuile.isOcean() || tuile.estOccupe()) || (tuile.estOccupe() && !tuile.avoirPersonnage().estEnVie()) ){
            System.out.println("Tuile de coordonnées (" + tuile.avoirx() + "," + tuile.avoiry() + ") qui est de type "+tuile.toString());
          }
        }
        System.out.println("Choisissez une tuile parmi celles proposées ainsi que le nombre d'unités à déployer.");
        int x = sc.nextInt();
        int y = sc.nextInt();
        int u = sc.nextInt();
        try{
          joueur.deployerArmee(x, y, u);
          System.out.println(joueur.avoirNom() + " a déployé " + u + " unités de son armée sur la tuile de coordonnées (" + this.plateau.avoirTuile(x,y).avoirx() + "," + this.plateau.avoirTuile(x,y).avoiry() + ") qui est de type "+this.plateau.avoirTuile(x,y).toString());

        }
        catch(Exception e){
          System.out.println("Coordonnées ou nombre d'unités invalides.");
        }
        List<Tuile> adjacentes = this.plateau.avoirCellulesAdjacentes(x, y);
        for(Tuile tuile : adjacentes){
          Armee armee1 = (Armee) this.plateau.avoirTuile(x,y).avoirPersonnage();
          Armee armee2 = (Armee) tuile.avoirPersonnage();
          if(tuile.estOccupe()){
            System.out.println("Armée détectée dans les environs.");
            boolean reaction;
            try{
              reaction = armee1.rencontrerArmee(armee2);
            }
            catch(Exception e){
              System.out.println("Exception levée!");
            }
          }
        }
      }
      System.out.println("Choisissez la quantité de ressources que vous récolterez.");
        int q = sc.nextInt();
        //Nos armées qui sont en vies vont récotler q ressources
        joueur.recolteressourceArmee(q);
        
        //Elles vont convertir les ressources
        joueur.convertitRessources();
        
        joueur.recupereNourriture();
        
        //On va utiliser la nourriture en stock afin de nourrire nos armées
        joueur.nourritSesArmees();
        
        System.out.println("#############Récapitulatif du joueur "+joueur.avoirNom()+"######################################");
        System.out.println("#"+joueur.avoirNom()+" n'a plus que "+joueur.avoirNombreGuerrierStock()+" guerriers prêt à la bataille et un stock de "+joueur.avoirNourritureStock()+" nourritures en stock #");
        System.out.println("#Listes des tuiles possédées par le joueur:#");
        for(Armee armee : joueur.avoirListeArmee()) {
        	System.out.println("#-)On a une armée de taille "+armee.tailleUnitee()+" sur la tuile ("+armee.avoirPosition().avoirx()+","+armee.avoirPosition().avoiry()+")#");
        }
        System.out.println("#############################################################################");

    }
  }

  /**
   * Effectue un tour de la partie en cours avec des choix aléatoire.
   */
  public void jouerUnTourHasard(){
	  System.out.println("######################################### Nouveau tours de jeu  ########################################################");
    for(Joueur joueur : this.joueurs){
      List<Tuile>  tuiles = new ArrayList<Tuile>();
      int x;
      int y;
      int u;
      System.out.println("-----------------------------------------------------C'est à " + joueur.avoirNom() + " de jouer!--------------------------------------------");
      System.out.println("Déployer une armée? Oui/Non");
      String action = "Oui";
      if(action.equals("Oui")){
        System.out.println("Tuiles disponibles :");
        for(Tuile tuile : this.plateau.avoirListeTuiles()){
          if(!(tuile.isOcean() || tuile.estOccupe()) || (tuile.estOccupe() && !tuile.avoirPersonnage().estEnVie()) ){
        	  tuiles.add(tuile);
        	  System.out.println("Tuile de coordonnées (" + tuile.avoirx() + "," + tuile.avoiry() + ") qui est de type "+tuile.toString());
          }
        }
        System.out.println("Choisissez une tuile parmi celles proposées ainsi que le nombre d'unités à déployer.");
        x=0;
        y=0;
        u=0;
        if(tuiles.size() > 0) {
        	x= tuiles.get(0).avoirx();
        	y= tuiles.get(0).avoiry();
        	if(joueur.avoirNombreGuerrierStock()>tuiles.get(0).avoirUniteetMax()) {
        		u = tuiles.get(0).avoirUniteetMax();
        	}
        	else {
        		u = joueur.avoirNombreGuerrierStock();
        	}
        }
        
        try{
          joueur.deployerArmee(x, y, u);
          System.out.println(joueur.avoirNom() + " a déployé " + u + " unités de son armée sur la tuile de coordonnïées (" + this.plateau.avoirTuile(x,y).avoirx() + "," + this.plateau.avoirTuile(x,y).avoiry() + ") qui est de type "+this.plateau.avoirTuile(x,y).toString());

        }
        catch(Exception e){
          System.out.println("Coordonnées ou nombre d'unités invalides.");
        }
        List<Tuile> adjacentes = this.plateau.avoirCellulesAdjacentes(x, y);
        for(Tuile tuile : adjacentes){
          Armee armee1 = (Armee) this.plateau.avoirTuile(x,y).avoirPersonnage();
          Armee armee2 = (Armee) tuile.avoirPersonnage();
          if(tuile.estOccupe()){
            System.out.println("Armée détectée dans les environs.");
            boolean reaction;
            try{
              reaction = armee1.rencontrerArmee(armee2);
            }
            catch(Exception e){
              System.out.println("Exception levée!");
            }
          }
        }
      }
      System.out.println("Vous allez récolter 10 ressource pour chaque armée .");
        int q = 10;
        //Nos armées qui sont en vies vont écotler q ressources
        joueur.recolteressourceArmee(q);
        
        //Elles vont convertir les ressources
        joueur.convertitRessources();
        
        joueur.recupereNourriture();
        
        //On va utiliser la nourriture en stock afin de nourrire nos armï¿½es
        joueur.nourritSesArmees();
        
        System.out.println("#############Récapitulatif du joueur "+joueur.avoirNom()+"######################################");
        System.out.println("#"+joueur.avoirNom()+" n'a plus que "+joueur.avoirNombreGuerrierStock()+" guerriers prêt à la bataille et un stock de "+joueur.avoirNourritureStock()+" nourritures en stock #");
        System.out.println("#Listes des tuiles possédées par le joueur:#");
        for(Armee armee : joueur.avoirListeArmee()) {
        	if(armee.tailleUnitee()>0) {
        	System.out.println("#-)On a une armée de taille "+armee.tailleUnitee()+" sur la tuile ("+armee.avoirPosition().avoirx()+","+armee.avoirPosition().avoiry()+")#");
        }
        	}
        System.out.println("#############################################################################");

    }
  }
  

  /** Déclare le vainqueur de la partie ou déclenche une exception en
   * cas d'égalité.
   * @return le joueur victorieux de la partie.
   * @exception DrawException lancée en cas d'égalité.
   */
  public String declarerVainqueur() throws DrawException{
	  Joueur gagnant = null;
	  boolean egalite = false;
	  for(Joueur joueur: this.joueurs) {
		  if(gagnant==null) {
			  gagnant=joueur;
		  }
		  else if(joueur.avoirPointsArmee()>=gagnant.avoirPointsArmee()) {
			  if(joueur.avoirPointsArmee()==gagnant.avoirPointsArmee()) {
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


