package jeu;

import java.util.*;

import jeu.personnage.util.Armee;
import jeu.personnage.util.Ouvrier;
import jeu.plateau.Plateau;

public class Joueur {
	
	//Liste des différentes Armées déployées par le joueur
	private List<Armee> listeArmee;
	
	//Liste des différents Ouvriers déployés par le joueur
	private List<Ouvrier> listeOuvrier;
	
	//nombre de guerriers du joueur
	private int nbGuerrier;
	
	//Or total du joueur pour le jeu Armee;
	private int orArmee;
	
	//Or total du joueur pour le jeu Agricole
	private int orOuvrier;
	
	//Nourriture du joueur
	private int nourritureStock;

	
	//Nom du joueur
    private String nom;
    
    //Plateau sur le quel le joueur va jouer
    private Plateau plateau;

    public Joueur(String nom){
        this.nom = nom;
        this.listeArmee = new ArrayList<>();
        this.listeOuvrier = new ArrayList<>();
        this.nbGuerrier = 35;
        this.orArmee = 0;
        this.orOuvrier = 15;
        this.nourritureStock= 10;
    }
    
    /**
     * permet d'avoir le plateau sur lequel le joueur va jouer
     * @return le plateau sur lequel le joueur joue
     */
    public Plateau avoirPlateau() {
    	return this.plateau;
    }
    
    /**
     * permet d'ajouter un plateau sur lequel le joueur va jouer
     * @param plateau sur lequel le joueur va jouer
     */
    public void ajoutPlateau(Plateau plateau) {
    	this.plateau = plateau;
    }
    
    //////////////////////////////////////////////////Méthodes associées aux jeux ouvriers
    
    /**
     * Permet d'ajouter un ouvrier sur le plateau
     * @param x abscisse de notre coordonée
     * @param y ordonnée de notre coordonné
     * @throws Exception si la case n'existe pas
     */
    public void deployerOuvrier(int x,int y) throws Exception {
    	
    	///////////////////// PRINT
    	
    	if(x>this.plateau.avoirLargeur() && y>this.plateau.avoirLongueur()) {
    		
    		throw new IllegalArgumentException();
    	}
    	else {
    		Ouvrier o = new Ouvrier(this);
    		this.listeOuvrier.add(o);
    		this.plateau.avoirTuile(x, y).ajouterPersonnage(o);
    	}
    }
    
    
    /**
     * Permet de recolter l'or de tous les ouvriers/armées du joueur
     */
    public void recolteOr() {
    	
    	///
    	
    	
    	for(Ouvrier ouvrier : this.listeOuvrier) {
    		this.orOuvrier = this.orOuvrier + ouvrier.prendreLOr();
    	}
    	
    	for(Armee armee: this.listeArmee) {
    		this.orArmee = this.orArmee + armee.prendreLOr();
    	}
    }
    
    
    /**
     * Permet de faire recolter les ressources de tous les ouvriers du joueur
     * @param i quantité de ressources qu'on veut récolter
     */
    public void recolteressourceOuvrier(int i) {
    	///
    	
    	for(Ouvrier ouvrier:this.listeOuvrier) {
    		if(ouvrier.estEnVie()) {
    			System.out.println("Notre ouvrier sur la tuile ("+ouvrier.avoirPosition().avoirx()+","+ouvrier.avoirPosition().avoiry()+") va récolter "+i+" ressources");
        		ouvrier.collectRessources(ouvrier.avoirPosition().avoirTypeDeRessources(), i);
    		}
    	}
    }
    
    /**
     * Méthode qui fait convertir toutes les ressources de tous les ouvriers en Or
     */
    public void convertirLOrTousOuvriers() {
    	for(Ouvrier ouvrier : this.listeOuvrier) {
    		if(ouvrier.estEnVie()) {
    		ouvrier.convertirToutOr();
    	}}
    }

    
    public void payerOuvrier() {
    	//Je parcours tous les ouvriers du joueur
    	for(Ouvrier ouvrier : this.listeOuvrier) {
    		//si l'ouvrier est en vie
    		if(ouvrier.estEnVie()) {
    			//Je paye cet ouvrier avec l'or que j'ai en stock
    			ouvrier.payer(this.orOuvrier);
    			//Si l'ouvrier est encore en vie après lui avoir donné l'argent qu'on a en stock tout va bien et on enlève du stock l'argent qu'on a donné à l'ouvrier
    			//S'il est pas "vivant" après avoir reçu l'argent en stock, alors on ne peut juste pas le payer et il sera "mort"
    			if(ouvrier.estEnVie()) {
    				//On en enlève l'argent 
    				this.orOuvrier = this.orOuvrier - ouvrier.avoirPosition().avoirPaye();
    			}		
    		}
    	}
    	
    }
    
    
    
    
    /**
     * fonction qui renvoie le nombre de points du joueur s'il joue au jeu des ouvriers
     * @return le nombre de points du joueur s'il joue au jeu des ouvriers
     */
    public int avoirPointsOuvrier() {
    	this.recolteOr();
    	return this.orOuvrier;
    }
    
    
    /**
     * méthode qui renvoie l'or en stock 
     * @return renvoie l'or en stock
     */
    public int avoirOrOuvrier() {
    	return this.orOuvrier;
    }
    
    public List<Ouvrier> avoirListeOuvrier(){
    	return this.listeOuvrier;
    }
////////////////////////////////////////////////////////Méthodes associées aux jeux d'armées 
    
    /**
     * Renvoie le nombre de guerriers disponibles pour être déployés
     * @return le nombre de guerriers disponibles pour être déployés
     */
    public int avoirNombreGuerrierStock() {
    	return this.nbGuerrier;
    }
    
    public List<Armee> avoirListeArmee(){
    	return this.listeArmee;
    }
    
    /**
     * méthode qui renvoie l'or en stock 
     * @return l'or disponible en stock
     */
    public int avoirOrArmee() {
    	return this.orArmee;
    }
    
    
    /**
     * fonction qui renvoie le nombre de points du joueur s'il joue au jeu des armées
     * @return le nombre de points pour le jeu des armées 
     */
    public int avoirPointsArmee() {
    	this.recolteOr();
    	int cpt = 0;
    	int pts = 0;
    	for(Armee armee : this.listeArmee) {
    		if(armee.estEnVie()) {
    		pts = pts+armee.avoirPosition().pointEmplacement();
    		cpt+=1;}
    	}
    	if(cpt>=10) {
    		pts+=5;
    	}
    	
    	return this.orArmee + pts;
    }
    
    
    /**
     * Permet d'ajouter une armée sur le plateau
     * @param x abscisse 
     * @param y ordonnée
     * @param unite nombre de guerrier à déployer
     * @throws Exception si la case n'existe pas
     */
    public void deployerArmee(int x,int y, int unite) throws Exception {
    	if(x<=this.plateau.avoirLargeur() && y<=this.plateau.avoirLongueur() && unite>0) {
    		if((this.nbGuerrier - unite) >= 0) {
    			this.nbGuerrier = this.nbGuerrier - unite ;
    			Armee armee = new Armee(unite,this);
	    		this.plateau.avoirTuile(x, y).ajouterPersonnage(armee);
	    		this.listeArmee.add(armee);
    		}
    		
    		else {
    			throw new IllegalArgumentException();
    		}
    	}  
    }
    
    /**
     * fonction qui nourrit toutes les armées du joueur
     */
    public void nourritSesArmees() {
    	for(Armee armee : this.listeArmee) {
    		if(armee.estEnVie()) {
        		System.out.println("armée est de taille "+armee.tailleUnitee());

    			//Ici on va donner à l'armée nos stocks de nourriture pour qu'elle puisse nourrir ses armées
    			armee.donNourriture(this.nourritureStock);
    			
    			//On dit à l'armée de se nourrir
        		armee.nourrireUnite();
        		
        		//Puis on récupère le reste de nourriture pour nourrir les autres
        		this.nourritureStock = armee.avoirNourritureStock();
        		armee.modifierNourritureStock(0);
        		
        		System.out.println("armée est de taille "+armee.tailleUnitee());
    		}
    	}
    }
    
    /**
     * permet de recolter toutes les ressources des armées sur la tuile
     * @param i nombre de ressource à recolter
     */
    public void recolteressourceArmee(int i) {
    	for(Armee armee : this.listeArmee) {
    		if(armee.estEnVie()) {
    			System.out.println("Le joueur "+this.avoirNom()+" va récolter "+i+" ressources de type "+armee.avoirPosition().toString());
        		armee.collectRessources(armee.avoirPosition().avoirTypeDeRessources(), i);
    		}
    	}    
    }
    
    /**
     * Fonction qui convertit toutes les ressources des armées en nourriture
     */
    public void convertitRessources() {
    	for(Armee armee : this.listeArmee) {
    		if(armee.estEnVie()) {
    		armee.convertirNourriture();
    	}
    	}
    }
    
    /**
     * fonction qui récupère la nourriture de toutes les armées et la donne au joueur
     */
    public void recupereNourriture() {
    	for(Armee a : this.listeArmee) {
    		int avant = this.nourritureStock;
    		this.nourritureStock += a.avoirNourritureStock();
    		System.out.println("On a récuperé "+(this.nourritureStock-avant)+" unité de nourriture");
    		a.modifierNourritureStock(0);
    	}
		System.out.println("Ce qui nous fait un stock de "+this.nourritureStock+" nourritures");

    }
    
    /**
     * fonction qui renvoie le nombre de nourriture en stock
     * @return le nombre de nourriture en stock
     */
    public int avoirNourritureStock() {
    	return this.nourritureStock;
    }
    
    
    
    
    
    /**
     * méthode qui renvoie le nom du joueur 
     * @return le nom du joueur 
     */
    public String avoirNom(){
        return this.nom;
    }

    /** deux joueurs sont considérés égaux s'ils ont le même nom
     * @param o objet
     * @return true si o est un Joueur de même nom, et false sinon
     */
    public boolean equals(Object o) {
    	if (o instanceof Joueur) {
    		Joueur other = (Joueur) o;
    		return this.nom.equals(other.nom);
    	}
    	return false;
    }
}
