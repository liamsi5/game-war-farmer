package jeu.plateau.emplacement;

import jeu.personnage.Personnage;
import jeu.personnage.Ressources;
import jeu.plateau.Tuile;

public class Foret extends Tuile{
	public Foret() {
		super();
		this.capaciteMax = 5;
		this.coefNourriture=1;
		this.paye=1;
	}
	
	public Foret(int x, int y){
		super(x,y);
		this.capaciteMax = 5;
		this.coefNourriture=1;
		this.paye=1;
	}
	
	public Foret(Personnage perso) throws Exception {
		super(perso);
		this.capaciteMax = 5;
		this.coefNourriture=1;
		this.paye=1;

	}
	public String toString() {
		return "Foret";
	}

	@Override
	public Ressources avoirTypeDeRessources() {
		return Ressources.BOIS;
	}
	
	public boolean isOcean() {
		return false;
	}
	
	public int pointEmplacement() {
		return 2;
	}
}
