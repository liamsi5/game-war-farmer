package jeu;

import jeu.*;
import jeu.plateau.*;
import java.util.*;

public abstract class Jeu{

  // liste des joueurs à itérer
  protected List<Joueur> joueurs;


  // plateau sur lequel se déroule la partie
  protected Plateau plateau;

  /** Constructeur de la classe abstraite Jeu.
   *@param joueurs qui vont pouvoir jouer au jeu
   * @param largeur largeur du plateau sur lequel se déroulera la partie.
   * @param longueur longueur du plateau sur lequel se déroulera la partie.
   */
  public Jeu(List<Joueur> joueurs, int largeur, int longueur){

    this.joueurs = new ArrayList<>();
    
    this.plateau = new Plateau(largeur, longueur);
    
    this.joueurs = joueurs;
    for(Joueur joueur : this.joueurs) {
    	joueur.ajoutPlateau(plateau);    	
    }
  }

  // Méthode abstraite modélisant une manche selon le jeu qui la définit.
  public abstract void jouerUnTour();

  // Méthode abstraite décidant du vainqueur d'une partie selon le jeu qui la définit.
  public abstract String declarerVainqueur() throws DrawException;
  
  /**
   * Renvoie le plateau sur lequel se joue la partie.
   * @return le plateau du jeu.
   */
  public Plateau avoirPlateau(){
	    return this.plateau;
	  }
   
}
