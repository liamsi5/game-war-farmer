package jeu.personnage;

import java.util.HashMap;

import jeu.Joueur;
import jeu.plateau.Tuile;

public abstract class Personnage {
	// association clé-valeur du type de ressources et sa quantité
	protected HashMap<Ressources, Integer> stockRessource;

	// quantité d'or
	protected int or;

	// type de ressource
	protected Ressources ressource;

	//Propriétaire du personnage
	protected Joueur joueur;

	//Position du joueur 
	protected Tuile position;

	//boolean qui nous sert à savoir si le personnage est placé sur une tuile ou non
	private boolean estPlace;


	protected Personnage(Joueur joueur){
		this.stockRessource = new HashMap<>();
		this.joueur = joueur;
		this.estPlace = false;
	}





	/**
	* Collecte les ressources d'une tuile et l'ajoute dans stockRessource si c'est la première fois, ou ajoute la valeur seulement sinon
	* @param res ressource le type de ressource
	* @param qtt quantité de ressources
	*/
	public void collectRessources(Ressources res, int qtt)
	{	
		Ressources cle = res;
		if(this.stockRessource.containsKey(cle)){
			int valeur = this.stockRessource.get(cle);
			this.stockRessource.put(cle, valeur +qtt);
		}
		else{
			this.stockRessource.put(cle, qtt);
		}
	
	}

	/**
	 * Renvoie le stock de ressources
	 * @return les ressources en stock
	 */
	public HashMap<Ressources,Integer> avoirRessource(){
		return this.stockRessource;
	} 

	
	/**
	 * permet de recupérer tout l'or du personnage (et donc ne lui laisse rien en poche)
	 * @return l'or du personnage 
	 */
	public int prendreLOr() {
		int enPoche = this.or;
		this.or= 0;
		return enPoche;
	}
	
	/**
	* Renvoie la quantité d'or d'un personnage
	* @return la quantité d'or
	*/
	public int avoirOr()
	{
		return this.or;
	}


	/**
	* Paie les personnages avec un montant d'or spécifique
	* @param n quantité d'or à ajouter au personnage
	*/
	public void donnerOr(int n)
	{
		this.or += n;
	}
 
	/**
	* Renvoie une chaine de caractères décrivant un personnage
	"possède " + this.or + " d'or et " + this.stockRessource.get(this.ressource) + " de " + this.ressource
	* @return une chaine de caractères décrivant un personnage
	*/
	public String toString()
	{
		return " ";
	}

	/**
	 * Renvoie la tuile sur laquelle le joueur est
	 * @return la tuile sur laquelle le joueur est
	 */
	public Tuile avoirPosition(){
		return this.position;
	}

	/**
	 * Renvoie le joueur qui possède le personnage 
	 * @return le joueur qui possède le personnage
	 */
	public Joueur avoirJoueur(){
		return this.joueur;
	}
	
	/**
	 * Permet de changer de joueur
	 * @param nouveauJoueur à déployer
	 */
	public void setJoueur(Joueur nouveauJoueur) {
		this.joueur=nouveauJoueur;
	}

    /**
     *Fonction qui permet de savoir si le personnage est en vie ou non
     *@return True si le personnage est en vie
     */
	public abstract boolean estEnVie();

    /**
     *Fonction qui permet de connaitre la taille du personnage (ex: 1,2,...,5 pour une armée)
     *@return int taille du personnage
     */
	public abstract int tailleUnitee();

    /**
     *Fonction qui permet d'ajouter le personnage sur une tuile
     *@param t tuile sur la quelle on veut déployé l'armée
     */
	public void ajoutTuile(Tuile t) {
		this.position=t;
		this.estPlace = true;
	}
	
    /**
     *Fonction qui permet de savoir si le personnage est déployé ou non
     *@return True si elle est déployée
     */
	public boolean estPlacee() {
		return this.estPlace;
	}





	

}
