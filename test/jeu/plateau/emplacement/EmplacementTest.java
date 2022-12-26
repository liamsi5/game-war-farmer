package jeu.plateau.emplacement;

import org.junit.Before;
import org.junit.Test;

import jeu.Joueur;
import jeu.personnage.Personnage;
import jeu.personnage.util.Armee;
import jeu.personnage.util.Ouvrier;
import jeu.plateau.Tuile;
import static org.junit.Assert.*;

public class EmplacementTest {
	Tuile montagne;
	Tuile foret;
	Tuile desert;
	Tuile plaine;
	Tuile ocean;
	Joueur j;
	Personnage perso1;
	Personnage perso2;
	
	
	@Before
	public void before() throws Exception {
		this.montagne=new Montagne();
		this.foret=new Foret();
		this.desert=new Desert();
		this.plaine=new Plaine();
		this.ocean=new Ocean();
		this.j = new Joueur("Tuco");
		this.perso1 = new Armee(2,this.j);
		this.perso2 = new Ouvrier(this.j);
		this.montagne.ajouterPersonnage(perso1);
		this.foret.ajouterPersonnage(perso2);
	}
	
	@Test
	public void testgetPersonnage() {
		assertSame(this.montagne.avoirPersonnage(),this.perso1);
		assertSame(this.foret.avoirPersonnage(),this.perso2);
		assertTrue(this.desert.avoirPersonnage()==null);
		assertTrue(this.plaine.avoirPersonnage()==null);
		assertTrue(this.ocean.avoirPersonnage()==null);

	}
	
	@Test
	public void testestOccupe() {
		assertTrue(this.montagne.estOccupe());
		assertTrue(this.foret.estOccupe());

		assertFalse(this.desert.estOccupe());
		assertFalse(this.plaine.estOccupe());
		assertFalse(this.ocean.estOccupe());
	}
	
	@Test
	public void testgetSoldatMax() {
		assertTrue(this.montagne.avoirUniteetMax()==3);
		assertTrue(this.foret.avoirUniteetMax()==5);
		assertTrue(this.desert.avoirUniteetMax()==3);
		assertTrue(this.plaine.avoirUniteetMax()==5);
		assertTrue(this.ocean.avoirUniteetMax()==0);
	}
	
	
	  @Test(expected = IllegalArgumentException.class)
	  public void testAjouterDeuxArmeeSurUneTuile() throws Exception{
		  this.montagne.ajouterPersonnage(new Armee(1,new Joueur("Pink")));
	  }
	
	
	@Test
	public void testDeChangerUnMort() throws Exception {
		assertSame(this.montagne.avoirPersonnage(),this.perso1);
		//On détruit les unitées de notre armée afin d'avoir une armée morte
		((Armee) this.montagne.avoirPersonnage()).detruitUnite(2);
		assertTrue(this.montagne.avoirTaillePersonnage()==0);
		//Maintenant que l'ancienne armee sur la tuile montagne est morte, on la remplace
		Armee remplacant = new Armee(3,new Joueur("joueur"));
		this.montagne.ajouterPersonnage(remplacant);
		assertSame(this.montagne.avoirPersonnage(),remplacant);	
	}
	
	@Test
	public void testIsOcean() {
		assertTrue((new Ocean()).isOcean());
		assertFalse((new Foret()).isOcean());
		assertFalse((new Montagne()).isOcean());
		assertFalse((new Desert()).isOcean());
		assertFalse((new Plaine()).isOcean());

		
	}
	
	
	@Test
	public void testPointsEmplacement() {
		assertTrue((new Desert()).pointEmplacement()==4);
		assertTrue((new Plaine()).pointEmplacement()==1);
		assertTrue((new Foret()).pointEmplacement()==2);
		assertTrue((new Montagne()).pointEmplacement()==4);

	}

 public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            jeu.plateau.emplacement.EmplacementTest.class
        );
    }
	
}
