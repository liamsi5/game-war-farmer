package jeu.plateau;
import jeu.plateau.emplacement.*;
import java.util.*;
public class Plateau{

	//le plateau 
	protected Tuile[][] plateau;
	
	//la largeur
	protected int largeur;

	//la longueur
	protected int longueur;

	//nombre de tuile (sans compter les océans  )
	protected int nbNormalTuiles;

	//nombre de tuiles type océans
	protected int  nbOceans;

	//taille du plateau
	protected int  nbTuiles;

	private static final Random RANDOM = new Random();
	private static final String[] allTuiles =  {"Foret","Desert","Montagne","Plaine"};



	public Plateau(int largeur, int longueur){
		this.largeur = largeur;
		this.longueur = longueur;
		this.nbTuiles = (this.longueur)*this.largeur;
		this.plateau = new Tuile[this.largeur][this.longueur];
		int minOceans= nbTuiles * 2/3;
		this.nbOceans = RANDOM.nextInt((nbTuiles - minOceans) + 1) + minOceans;
		this.nbNormalTuiles = (largeur*longueur)-this.nbOceans;
		initplateau();

	}


	/**
	* Renvoie la largeur  du plateau 
	* @return la largeur  du plateau 
	*/
	public int avoirLargeur(){
		return this.largeur;
	}


	/**
	* Renvoie la longueur   du plateau 
	* @return la longueur   du plateau 
	*/
	public int avoirLongueur(){
		return this.longueur;
	}


	/**

	* Renvoie le tuile de position  x,y
	* @param x  position niveau  largeur 
	* @param y  position niveau  longueur
	* @return la longueur   du plateau 
	*/
	public Tuile avoirTuile(int x, int y){
		return this.plateau[x][y];
	}
	
	/** 
	 * Crée une liste des tuiles en vue d'une itération.
	 * @return la liste des tuiles du plateau.
	 */
	public List<Tuile> avoirListeTuiles(){
		List<Tuile> tuiles = new ArrayList<>();
		
		for(int x=0; x<this.largeur; x++){
			for(int y=0; y<this.longueur; y++){
				tuiles.add(this.plateau[x][y]);
			}
		}
		return tuiles;
	}

	/**
	* permet de modifier  un type de tuile précis    
	* @param x  position niveau  largeur 
	* @param y  position niveau  longueur
	* @param tuile  le nouveau  type de la tuile  
	*/
	public void modifierTuile(int x, int y,Tuile tuile) {
		this.plateau[x][y] = tuile;
	}
	
	



	/**
	*    méthode qui permet d'initialiser le plateau 
	*/
	protected void initplateau(){
        int random = 0;                     //initialisation variable a valeur 0
        boolean doitEtreTerre= false;       //initialisation variable a valeur false
        for (int x = 0; x < this.largeur; x++) {    //parcour largeur
            for (int y = 0; y < this.longueur ; y++) {      //parcour longueur
                if (doitEtreTerre){         //Si on trouve que ‘laterredoitêtreterre’ == true 
                    if(this.nbNormalTuiles<=0){     
                        nbNormalTuiles+=1;      //ajout 1 a nbNormalTuiles
                    }
                    this.plateau[x][y] = randomTuile(0, x, y); //appelle a la méthode randomTuile(0, x, y) avec un paramètre 0 
                    doitEtreTerre= false;  //doitEtreTerre prend une valeur false
                }else{


                    random = RANDOM.nextInt(5); // on prend un entier aleatoir < 5
                    Tuile randomTuile = randomTuile(random,x, y); //= "Foret","Desert","Montagne","Plaine ou mm un ocean si random == 0

                    if (!(randomTuile.isOcean())){ // si c'est pas un ocean
                        doitEtreTerre = !aAdjacent(x, y); //on verifie la condition d’adjacent 

                    }
                    else{

                        doitEtreTerre= false; // sinon doitEtreTerre prend la valeur 
                    }
                    this.plateau[x][y] = randomTuile; // la position x , y  prend randomTuile(random,x, y)

                }
            }
        }
        rectifierAdjacent();   //reparcourir  notre plateau pour rectifier les problèmes  d eu au bord  
    } 

	/**
	*rectifie les tuiles des bords qui n'ont pas de tuiles adjacentes 
	*/
    protected void rectifierAdjacent(){  
        for (int i = 0; i < largeur; i++) {
            if ( !(this.plateau[i][0].isOcean()) && !aAdjacent(i,0)  ){
                this.nbOceans+=1;
                this.plateau[i][0]= avoirNouveauOcean(i, 0);
            }
            if ( !(this.plateau[i][longueur-1].isOcean()) && !aAdjacent(i,longueur-1)){
                this.nbOceans+=1;
                this.plateau[i][longueur-1]= avoirNouveauOcean(i,longueur-1);
            }  
    }

}




	/**
	*étudie les alentours  de la tuile localisée  par x et y et renvoie true or false selon qu'il y ait un adjacent terre ou pas
	*@param x  position niveau  largeur 
	*@param y  position niveau  longueur
	*@return booléen 
	*/
	protected boolean aAdjacent(int x, int y){
        if(y+1 < this.longueur && this.plateau[x][y+1]!= null && !(this.plateau[x][y+1].isOcean())){
            return true;
        }
        else if(y-1 >= 0 && this.plateau[x][y-1] != null && !(this.plateau[x][y-1].isOcean())){
            return true;
        }
        else if(x+1 < this.largeur && this.plateau[x+1][y] != null && !(this.plateau[x+1][y].isOcean())){
            return true;
        }
        else if ( x-1 >= 0  && this.plateau[x-1][y]!= null && !(this.plateau[x-1][y].isOcean() )){
            return true;
        }
        return false;
    }



	/**
	*renvoie les Cellules  adjacentes  de la tuile  localisée par x et y 
	*@param x  position niveau  largeur 
	*@param y  position niveau  longueur
	*@return Tuile 
	*/
	public List<Tuile> avoirCellulesAdjacentes(int x, int y){
        List<Tuile> tuiles = new ArrayList<Tuile>();
        if(y+1 < this.longueur && this.plateau[x][y+1]!= null && !(this.plateau[x][y+1].isOcean() )){
            tuiles.add(this.plateau[x][y+1]);
        } 	 	   
        if(y-1 >= 0 && this.plateau[x][y-1] != null && !(this.plateau[x][y-1].isOcean() )){
            tuiles.add(this.plateau[x][y-1]); 
        }
        if ( x+1 < this.largeur  && this.plateau[x+1][y]!= null && !(this.plateau[x+1][y].isOcean())){
            tuiles.add(this.plateau[x+1][y]);
        }
        if (x-1 >= 0 && this.plateau[x-1][y] != null && !(this.plateau[x-1][y].isOcean() )){
            tuiles.add(this.plateau[x-1][y]);
        }
        return tuiles;
    }


	/**
	*si le nombre d’océans est inférieur à 0, renvoie une tuile de type Normal   ("Foret" ou "Desert" ou "Montagne"ou "Plaine") sinon on diminue  le nombre 			d'oceans qu'il nous reste et crée un océan  à la position xPosition et yPosition
	*@param xPosition  position niveau  largeur 
	*@param yPosition  position niveau  longueur
	*@return Tuile   (oceon si nbOceans est positif  sinon NormalTuile )
	*/
	protected Tuile avoirNouveauOcean(int xPosition,int yPosition){
	    if (this.nbOceans<1){
	            return randomNormalTuile(xPosition, yPosition);
	    }
	    this.nbOceans-=1;
	    return new Ocean(xPosition, yPosition);
	}




    /**
     *permet d'attribuer à la tuile de position xPosition et yPosition selon la valeur de n: soit un océan via la méthode "getNouveauOcean(xPosition, yPosition)"soit une tuile normale de type("Foret","Desert","Montagne","Plaine") à l'aide de la fonction "randomNormalTuile(xPosition, yPosition)".
     *@param n  entier 
     *@param xPosition  position niveau  largeur 
     *@param yPosition  position niveau  longueur
     *@return 
     */
    protected Tuile randomTuile(int n,int xPosition, int yPosition) {
        if (n==0){
            return randomNormalTuile(xPosition, yPosition);
        }
        else{
            return avoirNouveauOcean(xPosition, yPosition);
        }
    }



    /**
     *attribue à la tuile de position xPosition et yPosition un type de tuile aléatoire ("Foret" ou "Desert" ou "Montagne"ou "Plaine") et si le nombre de tuiles normales est inférieur à 0 elle lui attribue un océan  
     *@param xPosition  position niveau  largeur 
     *@param yPosition  position niveau  longueur
     *@return  Tuile (soit nouvel océan nbNormalTuiles est négatif sinon ("Foret" ou "Desert" ou "Montagne"ou "Plaine"))
     */
    protected Tuile randomNormalTuile(int xPosition, int yPosition){
        if (this.nbNormalTuiles<1){
            return avoirNouveauOcean(xPosition, yPosition);
        }
        String tile = allTuiles[RANDOM.nextInt(allTuiles.length)];
        this.nbNormalTuiles-=1;
        if (tile.equals("Foret")){
            return new Foret(xPosition,yPosition);
        }
        else if(tile.equals("Desert")){
            return new Desert(xPosition,yPosition);
        }
        else if(tile.equals("Montagne")){
            return new Montagne(xPosition,yPosition);
        }
        else{
            return new Plaine(xPosition,yPosition);
        }
    }



    /**
     * Détermine si l'objet spécifié est égal à l'objet actuel et renvoie true si l’objet spécifié est égal à l’objet actuel ; sinon, false     
     * @param o Objet à comparer à l'objet actuel  
     * @return  booléen (true si l’objet spécifié est égal à l’objet actuel ; sinon, false)
     */ 
    public boolean equals(Object o){
        if (o instanceof Plateau){
            Plateau autre = (Plateau) o;
            if (this.largeur == autre.largeur && this.longueur == autre.longueur){
                for(int i=0; i< largeur;i++){
                    for (int j =0; j< longueur;j++){
                        if (!(this.plateau[i][j].equals(autre.plateau[i][j]))){
                            return false;
                        }
                    }
                }
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    } 

	
	/**
	 * Fonction qui permet de savoir si toutes les tuiles du plateau sont occupées ou non
	 * @return true si toutes les tuiles sont occupées, false sinon
	 */
	public boolean plusDeTerritoire(){
        for(Tuile tuile : this.avoirListeTuiles()){
            if(!tuile.isOcean() && (!tuile.estOccupe() || (tuile.estOccupe() && !tuile.avoirPersonnage().estEnVie()))){
                return false;
            }
        }
        return true;
    }
	
	
}
