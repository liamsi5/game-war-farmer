package jeu.plateau;

import jeu.personnage.Personnage;
import jeu.personnage.Ressources;

public abstract class Tuile {
	//Personnage qui est sur la Tuile
	protected Personnage personnage;
	
	//Coordonnée en abscisse de la tuile sur le plateau
	protected int x;
	
	//Coordonnée en ordonnée de la tuile sur le plateau
	protected int y;	
	//capacité max d'unités sur la tuile
	protected int capaciteMax;

	//Paye que doit recevoir l'ouvrier sur la tuile
	protected int paye;
	
	//Coefficient qui nous permet de savoir combien de nourritures doit recevoir une armée (ex: 2 fois plus sur une tuile Desert)
	protected int coefNourriture;
	
	
	public Tuile() {
	}
	
	public Tuile(int x, int y) {
	    this.x= x;
		this.y=y;
	}
	
	public int avoirx() {
		return this.x;
	}
	
	public int avoiry() {
		return this.y;
	}
	
	public Tuile(Personnage perso) throws Exception {
		this.ajouterPersonnage(perso);
	}
	
	/**
	 * Renvoie le personnage qui est sur la tuile s'il y en a un
	*@return personnage sur la tuile (ou null s'il n'y a personne)
	 */
	
	public Personnage avoirPersonnage() {
		return this.personnage;
	}
	
	
	
	
	public int avoirTaillePersonnage() throws Exception{
		//S'il y a quelqu'un sur la tuile, on renvoie directement sa taille
		if(this.estOccupe()) {
			return this.avoirPersonnage().tailleUnitee();
		}
		//S'il n'y a personne, on renvoie une exception
		else {
			throw new IllegalArgumentException();
		}
	}
	
	
	
	
	/**
	 * renvoie true si la case est occupée 
	 * @return true si la case est occupée, false sinon
	 */
	public boolean estOccupe() {
		return this.personnage!=null;
	}
	
	/**
	 * Renvoie le montant que doit recevoir un ouvrier sur cette Tuile
	 * @return le montant que doit recevoir un ouvrier sur cette Tuile
	 */
	public int avoirPaye() {
		return this.paye;
	}
	
	/**
	 * Renvoie combien de fois une armée doit être nourrie en plus sur la tuile
	 * @return combien de fois une armée doit être nourrie en plus sur la tuile
	 */
	public int avoirCoefNourriture() {
		return this.coefNourriture;
	}
		
	
	
	/**
	 * renvoie le nombre max d'unités sur la tuile
	 * @return le nombre max d'unités sur la tuile (ex: une armée de taille 3 sur une tuile montagne) 
	 */
	public int avoirUniteetMax() {
		return this.capaciteMax;
	}
	
	
	
	
	public String toString() {
		return "";
	}
	
	
	
	
	
	public void ajouterPersonnage(Personnage perso) throws Exception{
		//On vérifie la condition la plus importante: est-ce que le personnage a la bonne taille
		//On vérifie aussi que notre personnage n'est pas déjà placé autre part
		if(perso.tailleUnitee()>this.capaciteMax || (perso.estPlacee()&&perso.estEnVie() || this.isOcean())) {
			throw new IllegalArgumentException();
		}
		else {
		//On vérifie si la tuile est occupée, car si elle ne l'est pas, on peut ajouter le personnage sur la tuile
			if(!this.estOccupe()) {
				this.personnage=perso;
				perso.ajoutTuile(this);
				}
			//on vérifie si le personnage qui est sur la tuile est en vie ou non, car si il ne l'est pas, alors on peut le remplacer
			else if(!this.personnage.estEnVie()) {
				this.personnage=perso;
				perso.ajoutTuile(this);
			}
			
			else {
				throw new IllegalArgumentException();
			}
	}
	}
	
	/**
	 * Renvoie le type de ressources que l'on peut retrouver sur la tuile
	 * @return le type de ressources que l'on peut retrouver sur la tuile
	 */
	public abstract Ressources avoirTypeDeRessources();
	
	public abstract boolean isOcean();
	
	/**
	 * fonction qui permet de savoir combien de points sont associés à cet emplacement
	 * @return points de l'emplacement
	 */
	public abstract int pointEmplacement();
	
	
	
}
