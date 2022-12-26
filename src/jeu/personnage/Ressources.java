package jeu.personnage;

public enum Ressources {
	// type de ressource
	ROCHE(8,0), SABLE(5,0), BOIS(2,1), BLE(2,5);

	// valeur de conversion en or
	private int gld;

 	// valeur de conversion en nourriture
	 private int valeurNourriture;

	/**
	* Construit des instances de Ressources
	* @param val la valeur en or associée à la ressource
	*/
	private Ressources(int val, int n)
	{
		this.gld = val;
		this.valeurNourriture = n;
	}

	/**
	* Renvoie la valeur associée à la ressource
	* @return la valeur en or pour la conversion de la ressource
	*/
	public int getValeurMarchande()
	{
		return this.gld;
	}

	/**
	 * renvoie la valeur en nourriture associée à la ressource
	 * @return la valeur en nourriture associée à la ressource
	 */
	public int avoirValeurNourriture(){
		return this.valeurNourriture;
	}
}

