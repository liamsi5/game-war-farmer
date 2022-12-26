package jeu.plateau.emplacement;

import jeu.personnage.Personnage;
import jeu.personnage.Ressources;
import jeu.plateau.Tuile;

public class Montagne extends Tuile{
	public Montagne() {
		super();
		this.capaciteMax = 3;
		this.coefNourriture=1;
		this.paye=5;
	}
	
	public Montagne(int x, int y){
		super(x,y);
		this.capaciteMax = 3;
		this.coefNourriture=1;
		this.paye=5;
	}
	
	public Montagne(Personnage perso) throws Exception {
		super(perso);
		this.capaciteMax = 3;
		this.coefNourriture=1;
		this.paye=5;
	}
	
	public int avoirTaillePersonnage() throws Exception{
		int taille = super.avoirTaillePersonnage();
		if(taille==0) {
			return 0;
		}
		else {
			return super.avoirTaillePersonnage()+2;
		}
	}
	public String toString() {
		return "Montagne";
	}

	@Override
	public Ressources avoirTypeDeRessources() {
		return Ressources.ROCHE;
	}
	public boolean isOcean() {
		return false;
	}
	
	public int pointEmplacement() {
		return 4;
	}

}
