package jeu.personnage.util;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import jeu.Joueur;
import jeu.personnage.Ressources;
import jeu.plateau.Plateau;
import jeu.plateau.Tuile;
import jeu.plateau.emplacement.*;

public class ArmeeTest {

	  private Joueur j1;
	  private Joueur j2;
	  private Tuile t1;
	  private Tuile t2;
	  private Tuile t3;
	  private Armee armee1;
	  private Armee armee2;
	  private Armee armee3;
	  private Armee armee4;
	  private Armee armee5;


	  @Before
	  public void before() throws Exception{
	    this.j1 = new Joueur("joueur1");
	    this.t1 = new Plaine();
	    this.j2 = new Joueur("joueur2");
	    this.t2 = new Montagne();
	    this.t3 =new Foret();
	    
	    this.armee1 = new Armee(3, this.j1);
	    this.armee2 = new Armee(3, this.j2);
	    this.armee3 = new Armee(1,this.j2);
	    this.armee4 = new Armee(5,this.j1);
	    this.armee5 = new Armee(1,this.j2);
	    
	    this.t1.ajouterPersonnage(armee1);
	    this.t2.ajouterPersonnage(armee2);
	    this.t3.ajouterPersonnage(armee3);
	  }

	  @Test
	  public void donneesCorrectementcreees() throws Exception{
		  
	    HashMap<Ressources, Integer> res = new HashMap<>();
	    
	    assertSame(this.t1.avoirPersonnage(), this.armee1);
	    
	    assertSame(this.armee1.avoirJoueur(), this.j1);
	    assertEquals(this.armee1.tailleUnitee(), 3);
	    
	    assertEquals(this.armee2.tailleUnitee(), 3);
	    assertEquals(this.t2.avoirTaillePersonnage(),5);
	    
	    assertEquals(this.armee1.avoirNourritureStock(), 0);
	    assertEquals(this.armee1.avoirOr(), 0);
	    assertEquals(this.armee1.avoirRessource(), res);
	  }

	  @Test(expected = IllegalArgumentException.class)
	  public void exceptionCorrectementDeclenchee() throws Exception{
	    Joueur j = new Joueur("joueur");
	    Tuile t = new Desert();
      Armee armee = new Armee(5, j);
      t.ajouterPersonnage(armee);

	  }

	  @Test
	  public void unitesCorrectementAjoutees() throws Exception{
	    assertEquals(this.armee1.tailleUnitee(), 3);
	    
	    assertEquals(this.armee2.tailleUnitee(), 3);
	    assertEquals(this.t2.avoirTaillePersonnage(), 5);
	    
	    int ajt1 = this.armee1.ajoutUnit(1);
	    int ajt2 = this.armee2.ajoutUnit(3);
	    
	    assertEquals(this.armee1.tailleUnitee(), 4);
	    assertEquals(this.t1.avoirTaillePersonnage(),4);
	    
	    assertEquals(this.armee2.tailleUnitee(), 3);
	    assertEquals(this.t2.avoirTaillePersonnage(),5);
	    
	    assertTrue(ajt1==1);
	    assertFalse(ajt2==0);
	  }

	  
	  
	  
	  @Test
	  public void uniteCorrectementNourrie(){
		 //Mon armee a 10 blés
	    this.armee1.collectRessources(Ressources.BLE, 2);
	    //j'ai assez de nourriture pour nourire tout le monde (je le vrifi)
	    assertTrue(this.armee1.avoirNourritureStock() < this.armee1.tailleUnitee());
	    
	    this.armee1.nourrireUnite();
	    
	    assertEquals(this.armee1.tailleUnitee(), 3);
	    
	    assertEquals(this.armee1.avoirNourritureStock(), 7);
	    assertFalse(this.armee1.avoirNourritureStock() < this.armee1.tailleUnitee());
	    this.armee1.nourrireUnite();
	    assertEquals(this.armee1.avoirNourritureStock(), 4);
	    assertFalse(this.armee1.avoirNourritureStock() < this.armee1.tailleUnitee());
	    this.armee1.nourrireUnite();
	    assertEquals(this.armee1.avoirNourritureStock(), 1);
	    assertTrue(this.armee1.avoirNourritureStock() < this.armee1.tailleUnitee());
	    this.armee1.nourrireUnite();
	    assertEquals(this.armee1.tailleUnitee(), 1);
	    assertEquals(this.armee1.avoirNourritureStock(), 0);
	  }

	  @Test
	  public void uniteCorrectementDetruite() throws Exception{
	    assertEquals(this.armee1.tailleUnitee(), 3);
	    assertEquals(this.t2.avoirTaillePersonnage(), 5);
	    this.armee1.detruitUnite(2);
	    this.armee2.detruitUnite(-3);
	    assertEquals(this.armee1.tailleUnitee(), 1);
	    assertEquals(this.t2.avoirTaillePersonnage(), 5);
	  }

	  @Test(expected = IllegalArgumentException.class)
	  public void testAvoirLaTailleDuPersonnageDeLaTuileVide() throws Exception{
		  Tuile t4 = new Plaine();
		  t4.avoirTaillePersonnage();
	  }
	  
	  @Test
	  public void nourritureCorrectementConvertie(){
	    this.armee1.collectRessources(Ressources.BLE, 2);
	    Ressources r = (Ressources) this.armee1.avoirRessource().keySet().toArray()[0];
	    assertEquals(this.armee1.avoirNourritureStock(), 0);
	    assertEquals(this.armee2.avoirNourritureStock(), 0);
	    assertTrue(this.armee1.avoirRessource().keySet().size() != 0);
	    assertTrue(this.armee2.avoirRessource().keySet().size() == 0);
	    Boolean res1 = this.armee1.convertirNourriture();
	    Boolean res2 = this.armee2.convertirNourriture();
	    assertTrue(this.armee1.avoirRessource().get(r)== 0);
	    assertEquals(this.armee1.avoirNourritureStock(), 10);
	    assertEquals(this.armee2.avoirNourritureStock(), 0);
	    assertTrue(this.armee1.avoirRessource().get(r) == 0);
	    assertTrue(res1);
	    assertFalse(res2);
	  }

	  @Test
	  public void armeeEffectivementPleine() throws Exception{
		assertTrue(this.armee1.tailleUnitee()==3);
		assertTrue(this.t1.avoirTaillePersonnage()==3);
		assertTrue(this.t1.avoirUniteetMax()==5); 
		
		assertTrue(this.armee1.estPlacee());
		
	    assertFalse(this.armee1.armeePleine());
	    
	    assertTrue(this.armee2.armeePleine());
	  }

	  @Test
	  public void testCombatEngage() throws Exception{
		//Ici on verifie bien que l'arme 2 qui est sur une montagne donne bien l'impression d'être 5 au lieu de 3
		assertTrue(this.t2.avoirTaillePersonnage()==5);
	    assertFalse(this.armee1.rencontrerArmee(this.armee2));

	    //Ici on verifie que si l'autre arme est ennemie, et qu'en plus elle n'a qu'un soldat, alors le joueur change pour cette autre arme
	    assertTrue(this.armee1.rencontrerArmee(this.armee3));
	    assertTrue(this.armee1.avoirJoueur().equals(this.armee3.avoirJoueur()));
	    
	    //Ici on verifie que si on a deux armes du mme joueur qui s'affronte, celle qui a le moins d'unit gagne une unit
	    assertTrue(this.armee1.rencontrerArmee(this.armee3));
	    assertTrue(this.armee3.tailleUnitee()==2);
	
	    
	    //Ici je vrifie bien qu'une arme de moins d'une unite qu'une autre ne remporte pas la victoire
	    Plateau p = new Plateau(2,2);
	    p.modifierTuile(0, 0, new Plaine(0,0));
	    
	    p.modifierTuile(1, 1, new Plaine(1,1));

	    Joueur inter = new Joueur("intermediaire");
	    Joueur inter2 = new Joueur("intermediaire2");

	    
	    inter.ajoutPlateau(p);
	    inter2.ajoutPlateau(p);

	    inter.deployerArmee(0, 0, 1);
	    inter2.deployerArmee(1, 1, 5);

	    Plateau i = inter.avoirPlateau();
	    Armee ar = (Armee) i.avoirTuile(0, 0).avoirPersonnage();
	    Armee ar4 = (Armee) i.avoirTuile(1, 1).avoirPersonnage();

	    System.out.println("####################################################### ");
	    
	    
	    assertFalse(ar.rencontrerArmee(this.armee1));
	    
	    //Ici je vrifie qu'en cas de changement de joueur, l'arme qui a gagnée gagne bien 2or
	    assertTrue(ar4.avoirOr()==0);
	    System.out.println("--------------------------------------------------------------------");
	    System.out.println(ar.tailleUnitee()+" taille de A5");
	    assertTrue(ar4.rencontrerArmee(ar));
	    assertTrue(ar4.avoirOr()==2);
	  }
	  
	  @Test
	  public void testDesCoeffDeNourriture() throws Exception {
		  Tuile Desert = new Desert();
		  Desert.ajouterPersonnage(new Armee(2,this.j1));
		  
		  ((Armee) Desert.avoirPersonnage()).nourrireUnite();
		  assertTrue(Desert.avoirPersonnage().tailleUnitee()==0);
		  assertFalse(Desert.avoirPersonnage().estEnVie());
		  
		  Desert.ajouterPersonnage(new Armee(3,this.j1));
		  assertTrue(((Armee) Desert.avoirPersonnage()).avoirNourritureStock()==0);
		  Desert.avoirPersonnage().collectRessources(Ressources.BOIS, 2);
		  //Comme un de bois vaut 1 de nourriture et qu'on a trois personnes a nourrir, alors 2 vont mourir 
		  ((Armee) Desert.avoirPersonnage()).nourrireUnite();
		  
		  assertTrue(Desert.avoirPersonnage().tailleUnitee()==1);
		  			  		
	  }
	  
	  
	  @Test
	  public void testRecolterDesRessourcesAPartirDeSaTuile() {
		  assertTrue(this.armee1.avoirRessource().isEmpty());
		  
		  this.armee1.collectRessources(this.armee1.avoirPosition().avoirTypeDeRessources(), 2);
		  
		  assertFalse(this.armee1.avoirRessource().isEmpty());
		  this.armee1.convertirNourriture();
		  assertTrue(this.armee1.avoirNourritureStock()==10);
	  }
	  
	  @Test
	  public void testDeNourrireUneArmeeSurLeDesert() {
		  Armee a = new Armee(3,this.j1);
		  Desert desert = new Desert();
		  a.ajoutTuile(desert);
		  assertTrue(a.avoirNourritureStock()==0);
		  a.collectRessources(Ressources.BLE, 3);
		  assertTrue(a.tailleUnitee()==3);
		  
		  a.nourrireUnite();
		  
		  assertTrue(a.avoirNourritureStock()==9);
		  assertTrue(a.tailleUnitee()==3);
		  
		  a.nourrireUnite();
		  
		  assertTrue(a.avoirNourritureStock()==3);
		  a.nourrireUnite();
		  assertTrue(a.avoirNourritureStock()==1);
		  assertTrue(a.tailleUnitee()==1);

	  }
	  
 public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            jeu.personnage.util.ArmeeTest.class
        );
    }
	  

}
