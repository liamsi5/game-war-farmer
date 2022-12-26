package jeu.personnage.util;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import jeu.Joueur;
import jeu.personnage.Ressources;
import jeu.plateau.Tuile;
import jeu.plateau.emplacement.*;

public class OuvrierTest {

	  private Joueur j;
	  private Tuile t;
	  private Ouvrier ouvrier;

	  @Before
	  public void before() throws Exception{
	    this.j = new Joueur("pedro");
	    this.t = new Desert();
	    this.ouvrier = new Ouvrier(this.j);
	    this.t.ajouterPersonnage(ouvrier);

	  }
	  
	  @Test(expected=IllegalArgumentException.class)
	  public void testOuvrierSurOcean() throws Exception {
		  Ouvrier trav ;
		  trav = new Ouvrier(this.j);
		  Tuile ocean = new Ocean();
		  ocean.ajouterPersonnage(trav);
	  }

	  @Test
	  public void donneesCorrectementcreees(){
	    HashMap<Ressources, Integer> res = new HashMap<>();
	    assertSame(this.ouvrier.avoirPosition(), this.t);
	    assertSame(this.ouvrier.avoirJoueur(), this.j);
	    assertEquals(this.ouvrier.avoirOr(), 0);
	    assertEquals(this.ouvrier.avoirRessource(), res);
	  }

	  @Test
	  public void orCorrectementConvertiSable(){
	    assertEquals(this.ouvrier.avoirOr(), 0);
	    Ressources res = Ressources.SABLE;
	    this.ouvrier.collectRessources(res, 10);
	    this.ouvrier.convertirOr(Ressources.SABLE, 10);
	    assertTrue(this.ouvrier.avoirOr()== 50);
	  }

	  @Test
	  public void orCorrectementConvertiBle(){
	    assertEquals(this.ouvrier.avoirOr(), 0);
	    Ressources res = Ressources.BLE;
	    this.ouvrier.collectRessources(res, 10);
	    this.ouvrier.convertirOr(Ressources.BLE, 4);
	    assertTrue(this.ouvrier.avoirOr()== 8);
	  }
	  @Test
	  public void orCorrectementConvertiBois(){
	    assertEquals(this.ouvrier.avoirOr(), 0);
	    Ressources res = Ressources.BOIS;
	    this.ouvrier.collectRessources(res, 10);
	    this.ouvrier.convertirOr(Ressources.BOIS, 6);
	    assertTrue(this.ouvrier.avoirOr()== 12);
	  }
	  @Test
	  public void orCorrectementConvertiRoche(){
	    assertEquals(this.ouvrier.avoirOr(), 0);
	    Ressources res = Ressources.ROCHE;
	    this.ouvrier.collectRessources(res, 6);
	    this.ouvrier.convertirOr(Ressources.ROCHE, 3);
	    assertTrue(this.ouvrier.avoirOr()== 24);
	  }
  
	  
	  
	  
	  @Test(expected = IllegalArgumentException.class)
	  public void exceptionCorrectementDeclenchee() throws IllegalArgumentException{
	    assertEquals(this.ouvrier.avoirOr(), 0);
	    Ressources res1 = Ressources.SABLE;
	    Ressources res2 = Ressources.ROCHE;
	    this.ouvrier.collectRessources(res1, 10);
	    this.ouvrier.collectRessources(res2, 6);
	    try{
	      this.ouvrier.convertirOr(Ressources.SABLE, 5);
	    }
	    catch(IllegalArgumentException e){
	      fail();
	    }
	    this.ouvrier.convertirOr(Ressources.BLE, 8);
	  }
	  
	  @Test
	  public void testgiveOr() {
		assertEquals(this.ouvrier.avoirOr(), 0);
		this.ouvrier.donnerOr(43);
		assertEquals(this.ouvrier.avoirOr(), 43);
		  		  
	  }
	  
	  @Test
	  public void testgetPosition() {
		  assertTrue(this.ouvrier.avoirPosition() instanceof Desert);
	  }
	  
	  @Test
	  public void testgetJoueur() {
		  Joueur clone = new Joueur("pedro");
		  assertTrue(this.ouvrier.avoirJoueur().equals(clone));
	  }
	  
	  @Test
	  public void testPasPayerUnOuvrier() {
		  assertTrue(this.ouvrier.avoirOr()==0);
		  this.ouvrier.donnerOr(12);
		  assertTrue(this.ouvrier.avoirOr()==12);
		  assertTrue(this.ouvrier.estEnVie());
		  this.ouvrier.payer(3);
		  assertTrue(this.ouvrier.estEnVie());
		  this.ouvrier.payer(2);
		  assertFalse(this.ouvrier.estEnVie());
	  }
	  
	  @Test
	  public void testRemplacerUnOuvrierPasPaye() throws Exception {
		  this.ouvrier.payer(2);
		  Ouvrier nv = new Ouvrier(new Joueur("nouveau travailleur"));
		  this.t.ajouterPersonnage(nv);
		  assertSame(this.t.avoirPersonnage(),nv);
	  }
	  
	  
	  
	  @Test
	  public void testRecolterDesRessourcesAPartirDeSaTuile() {
		  assertTrue(this.ouvrier.avoirRessource().isEmpty());
		  
		  this.ouvrier.collectRessources(this.ouvrier.avoirPosition().avoirTypeDeRessources(), 2);
		  assertTrue(this.ouvrier.avoirRessource().get(Ressources.SABLE)==2);

		  assertTrue(this.ouvrier.avoirOr()==0);
		  this.ouvrier.convertirToutOr();
		  assertTrue(this.ouvrier.avoirOr()==10);
		  assertTrue(this.ouvrier.avoirRessource().get(Ressources.SABLE)==0);
	  }
	  
	  @Test
	  public void testDePrendreToutLOr() {
		  assertTrue(this.ouvrier.avoirOr()==0);
		  this.ouvrier.donnerOr(12);
		  assertTrue(this.ouvrier.avoirOr()==12);
		  int orPrit = this.ouvrier.prendreLOr();
		  assertTrue(orPrit==12);
		  assertTrue(this.ouvrier.avoirOr()==0);
	  }

 public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            jeu.personnage.util.OuvrierTest.class
        );
    }
}
