package jeu.plateau.emplacement;

import jeu.personnage.Personnage;
import jeu.personnage.Ressources;
import jeu.plateau.Tuile;

public class Desert extends Tuile{
	public Desert() {
		super();
		this.capaciteMax = 3;
		this.coefNourriture=2;
		this.paye=3;
	}
	
	public Desert(int x, int y){
		super(x,y);
		this.capaciteMax = 3;
		this.coefNourriture=2;
		this.paye=3;
	}
	
	public Desert(Personnage perso) throws Exception {
		super(perso);
		this.capaciteMax = 3;
		this.coefNourriture=2;
		this.paye=3;

	}
	
	public String toString() {
		return "Desert";
	}

	@Override
	public Ressources avoirTypeDeRessources() {
		return Ressources.SABLE;
	}
	
	public boolean isOcean() {
		return false;
	}
	
	public int pointEmplacement() {
		return 4;
	}
	
}
