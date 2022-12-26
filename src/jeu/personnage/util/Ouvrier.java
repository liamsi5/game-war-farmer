package jeu.personnage.util;

import java.util.HashMap;

import jeu.Joueur;
import jeu.personnage.Personnage;
import jeu.personnage.Ressources;

public class Ouvrier extends Personnage{
	//Permet de savoir si l'ouvrier a été payé ou non
	private boolean paye;
	
	/**
	 * Construit des instances d'Ouvrier
	 * @param joueur qui crée l'unité
	 * @throws Exception
	 */
	public Ouvrier(Joueur joueur) throws Exception{
		super(joueur);
		this.or = 0;
		this.stockRessource = new HashMap<>();
		this.paye = true;
	}




	/**
	* Convertit une quantité d'une certaine ressource en or
	* @param res une ressource
	* @param qtt une quantité
	* @exception IllegalArgumentException si la quantité entrée en paramètre est supérieure à la quantité disponible 
										  ou si l'ouvrier n'est pas en possession de la ressource entrée en paramètre
	*/
	public void convertirOr(Ressources res, int qtt) throws IllegalArgumentException{
		if (this.stockRessource.containsKey(res) && this.stockRessource.get(res) >= qtt)
			this.or += res.getValeurMarchande() * qtt;
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Méthode qui permet de convertir toutes les ressources en stock en or
	 * @return True si on avait des ressources à convertir
	 */
	public boolean convertirToutOr() {
		boolean res = false;
		//on vérifie qu'on a déjà recolté au moins une fois 
		if( this.stockRessource.keySet().size() != 0){
		
		Ressources r = (Ressources) this.stockRessource.keySet().toArray()[0];
		int valeur = this.stockRessource.get(r);
		//On vérifie qu'on a bien au moins une ressource à convertir
		if(valeur>0){
			this.or += r.getValeurMarchande()*valeur;
			this.stockRessource.put(r,0);
			res = true;
		}

		}
		return res;
		}


	/**
	* Renvoie une chaîne de caractères décrivant un ouvrier
	* @return une chaîne de caractères décrivant un ouvrier
	*/
	public String toString(){
		return "L'ouvrier  " + super.toString();
	}
	

	/**
	 * un ouvrier ne peut pas mourir, donc renvoie toujours vrai
	 */
	public boolean estEnVie() {
		return this.paye;
	}

	/**
    *renvoi 1 car on ne va déployer qu'un ouvrier à chaque fois
    *@return 1
    */
	public int tailleUnitee() {
		return 1;
	}
	
	/**
	 * Méthode qui permet de gérer la paye des ouvriers. Si l'ouvrier n'a pas pu être payé, alors il arrête de travailler
	 * @param n somme payée à l'ouvrier 
	 */
	public void payer(int n) {
		if(n<this.avoirPosition().avoirPaye()) {
			this.paye=false;
		}
		
		
	}
	
}
