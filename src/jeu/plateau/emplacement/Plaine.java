package jeu.plateau.emplacement;

import jeu.personnage.Personnage;
import jeu.personnage.Ressources;
import jeu.plateau.Tuile;

public class Plaine extends Tuile{
	public Plaine() {
		super();
		this.capaciteMax = 5;
		this.coefNourriture=1;
		this.paye=1;
	}
	
	
	public Plaine(int x, int y){
		super(x,y);
		this.capaciteMax = 5;
		this.coefNourriture=1;
		this.paye=1;
	}
	
	public Plaine(Personnage perso) throws Exception {
		super(perso);
		this.capaciteMax = 5;
		this.coefNourriture=1;
		this.paye=1;

	}
	public String toString() {
		return "Plaine";
	}


	@Override
	public Ressources avoirTypeDeRessources() {
		return Ressources.BLE;
	}
	public boolean isOcean() {
		return false;
	}

	public int pointEmplacement() {
		return 1;
	}
}
