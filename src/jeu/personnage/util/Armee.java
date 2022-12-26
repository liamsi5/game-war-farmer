package jeu.personnage.util;

import java.util.HashMap;

import jeu.Joueur;
import jeu.personnage.Personnage;
import jeu.personnage.Ressources;

public class Armee extends Personnage {


	// nombre de soldats dans l'armée
	private int tailleArmee;

	// quantité du stock de nourriture
	private int nourritureStock;

	
	/**
	* Construit des instances d'Armee
	* @param unites le nombre d'unités dans l'armée (forcément positif)
    * @param joueur qui possède l'armée
    * @throws IllegalArgumentException quand on met trop d'unité
	*/
	public Armee(int unites, Joueur joueur) throws IllegalArgumentException {
		super(joueur);
		//je vérifie si le paramètre est bien positif
		if(unites<0){
			throw  new IllegalArgumentException("Il faut un nombre d'unités positif") ;
		}
		//si tout se passe bien
		else{
			this.tailleArmee = unites;
		}

		//On initialise le stock de nourriture à 0
		this.nourritureStock = 0;

		//On initialise le stock d'or à 0
		this.or = 0;
		
		//On initialise le stock de ressources 
		this.stockRessource = new HashMap<>();
	}


	/**
	 * renvoie le nombre d'unités de nourriture en stock
	 * @return le nombre d'unités de nourriture en stock
	 */
	public int avoirNourritureStock(){
		return this.nourritureStock;
	}


	/**
	* Renvoie la taille d'une armée en nombre d'unités
	* @return le nombre d'unités d'une armée
	*/
	public int tailleUnitee()
	{
		return this.tailleArmee;
	}



	/**
	* Vérifie si l'ajout d'une unité à n membres est réussi. Si on en ajoute trop, seul le nombre requis pour remplir l'armée sera pris en compte
	* @param n le nombre de soldats de l'unité
	* @return le nombre de soldats qui n'ont pas pu être ajoutés
	*/
	public int ajoutUnit(int n)
	{
		if(this.position!=null) {
		//On vérifie si on a entré un nombre de soldats qui ne va pas nous faire avoir une armée trop grande
		boolean ajoute = (this.tailleArmee +n <= this.position.avoirUniteetMax());
		int res = 0;
		if (ajoute)
		{
			this.tailleArmee+=n;
			res=n;
		}

		else{
			this.tailleArmee = this.tailleArmee + (this.position.avoirUniteetMax() - this.tailleArmee);
			res = n - (this.position.avoirUniteetMax() - this.tailleArmee);
		}
		
		return res;
		}
		
		else {
			this.tailleArmee+=n;
			return n;
		}
	}


	/**
	 * renvoie le stock de nourriture de l'armee 
	 * @return le stock de nourriture de l'armee
	 */
	public int prendreNourriture() {
		return this.nourritureStock;
	}
	
	
	

	/**
	 * Permet de nourrir l'armée en fonction du stock disponible.
	 * S'il y a assez de stocks de nourriture, tout le est nourri, sinon cela convertit le sotck de ressources en nourriture pour nourrir l'armée
	 * Si après conversion il n'y a pas assez de nourriture, les unités non nourries vont mourir 
	 *@return entier qui correspond au nombre d'unité nourrit
	 */
	public int nourrireUnite(){

		int res = 0;
		int coef =1;
		if(this.avoirPosition()!=null) {
			coef = this.avoirPosition().avoirCoefNourriture();
		}
		//On vérifie si on n'a pas assez de nourriture pour toute l'armée
		if(this.nourritureStock<this.tailleArmee*coef){

			//On vérifie si on a pu convertir des ressources en nourriture
			if(this.convertirNourriture()){				
				this.convertirNourriture();
				this.nourrireUnite();
				
			}
			//Si on a pas pu, on détruit les soldats qui n'ont pas été nourris
			else{
				int nourritureNecessaire = this.nourritureStock*coef;

				this.detruitUnite((tailleArmee-this.nourritureStock/coef));
				int utilise = this.tailleArmee * coef;
				res=this.nourritureStock*coef;
				//On remet le stock à 0 comme on a utilisé toute la nourriture
				if(this.nourritureStock-utilise<0) {
					this.nourritureStock=0;
				}
				else {
					this.nourritureStock -= utilise;
				}
				
			}
		}
		//Si on a assez de nourriture pour nourrir tout le monde, pas de problème
		else{
			this.nourritureStock -= this.tailleArmee*coef;
			res=this.tailleArmee;
		}
		return res;
	}



	/**
	 * Permet de savoir si l'armée est en vie ou non
	 * @return true si l'armée est en vie et false sinon 
	 */
	public boolean estEnVie(){
		return this.tailleArmee!=0;
	}


	/**
	 * détruit n unités dans l'armée
	 * @param n nombre d'unités à détruire
	 */
	public void detruitUnite(int n){
		
		if(n>0 && this.tailleArmee-n>0){
			this.tailleArmee -=n;
	}
		else if(n>0 && (this.tailleArmee-n)==0){
			this.tailleArmee=0;
			this.or+=1;

		}
		else if(n>0 && tailleArmee-n<0) {
			this.tailleArmee=0;
			this.or+=1;
		}
}


	/**
	* Renvoie une chaine de caractéres décrivant une armée
	* @return une chaine de caractéres décrivant une armée
	*/
	@Override
	public String toString(){
		return "cette armée est de taille: " + this.tailleArmee + " et possède un stock de nourriture de: " + this.nourritureStock + " et " + super.toString();
	}


	/**
	 * Convertit le stock de ressources en nourriture
	 * @return true s'il y a des ressources à convertir en nourriture, et false sinon
	 */
	public boolean convertirNourriture(){
		
		boolean res = false;
		//on vérifie qu'on a déjà recolté au moins une fois 
		if( this.stockRessource.keySet().size() != 0){
		
		Ressources r = (Ressources) this.stockRessource.keySet().toArray()[0];
		int valeur = this.stockRessource.get(r);
		//On vérifie qu'on a bien au moins une ressource à convertir
		if(valeur>0){
			System.out.println("on va convertir "+valeur+" ressources en nourriture");
			this.nourritureStock += r.avoirValeurNourriture()*valeur;
			this.stockRessource.put(r,0);
			res = true;
		}

		}
		return res;
	}

	
	
	
	
	/**
	* Vérifie si une armée est pleine
	* @return true si 5 unités ou 35 soldats ont déjà été deployés, si aucun des deux false
	 * @throws Exception 
	*/
	public boolean armeePleine() throws Exception{	
		if(this.estPlacee()) {
			return this.position.avoirUniteetMax()==this.tailleArmee;
	}
	else {
		return false;
	}
		
	}




	/**
	* Permet à 2 armées de se rencontrer
	* @param autre armée a rencontrer
	* @return True en cas de victoire contre l'autre armée, et false sinon
	* @throws Exception
	 */
	public boolean rencontrerArmee(Armee autre) throws Exception{		
		//On regarde si l'armée autre est une armée alliée
		if(this.avoirJoueur().equals(autre.avoirJoueur())) {
			rencontreArmeeAmie(autre);
			return true;
		}

		//Armée ennemie 
		
		else if(this.avoirPosition().avoirTaillePersonnage()<=autre.avoirPosition().avoirTaillePersonnage()) {
			System.out.println("On envoie des éclaireurs sur le camp ennemi au loin ... ");
			System.out.println("Ils reviennent avec une mauvaise nouvelle.... Ils sont plus nombreux que nous...(envirion "+autre.tailleUnitee()+")...");
			System.out.println("Restons cachés alors...");
			return false;
		}
		
		//On regarde si l'armée autre est une armée ennemie
		else if(!this.avoirJoueur().equals(autre.avoirJoueur())) {
			return combattreArmeeEnnemie(autre);
		}	
		return true;
	}


	private void rencontreArmeeAmie(Armee autre) throws Exception {
		System.out.println(this.joueur.avoirNom()+" possède "+this.tailleArmee+" guerriers et a rencontré une armée alliée de "+autre.tailleArmee+" guerriers et va récupérer 1 unité si possible");
		if(!autre.armeePleine()) {
			autre.ajoutUnit(1);
			System.out.println("notre alliée a maintenant "+autre.tailleArmee+" guerriers dans l'armée ");
		}
		else {
			System.out.println("On est complet, aucun recrutement possible");
		}
	}


	private boolean combattreArmeeEnnemie(Armee autre) throws Exception {
		System.out.println("ON TOMBE SUR UN ENNEMI !!! L'armée de "+autre.avoirJoueur().avoirNom()+" qui a "+autre.tailleUnitee()+" guerriers");
		
		if(autre.avoirPosition().avoirTaillePersonnage()<this.avoirPosition().avoirTaillePersonnage()) {
			System.out.println("On a plus de guerriers que lui !!! à l'attaqueeeeeee");
			
			if(autre.avoirPosition().avoirTaillePersonnage()==1) {
				System.out.println("Il n'avait qu'un guerrier !! On récupère son armée et 2 or au passage !!");
				autre.setJoueur(this.joueur);
				this.donnerOr(2);
			}
			
			else {
			autre.detruitUnite(autre.tailleUnitee()/2);
			System.out.println("Après un gros combat, l'ennemi a perdu la moitié de ses guerriers et passe à "+autre.tailleUnitee());
			}
			return true;
		}
		
		else {
			return false;
		}
		
	}


	/**
	 * permet de mettre à i le stock de ressource de l'armée
	 * @param i nouveau stock de nourriture 
	 */
	public void modifierNourritureStock(int i) {
		this.nourritureStock = i;
		
	}
	
    /**
     *Permet de donner i nourriture à l'armée
     *@param i nourriture à donner
     */	
	public void donNourriture(int i) {
		this.nourritureStock += i;
	}
	
	
	
}
