package jeu.plateau.emplacement;

import jeu.personnage.Ressources;
import jeu.plateau.Tuile;

public class Ocean extends Tuile{
	public Ocean() {
		super();
		this.capaciteMax = 0;

	}
	
	public Ocean(int x, int y){
		super(x,y);
		this.capaciteMax = 0;
	}
	public String toString() {
		return "Ocean";
	}

	@Override
	public Ressources avoirTypeDeRessources() {
		return null;
	}
	public boolean isOcean() {
		return true;
	}
	
	public int pointEmplacement() {
		return 0;
	}
	

}
