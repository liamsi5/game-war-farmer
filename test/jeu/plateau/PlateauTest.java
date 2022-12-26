package jeu.plateau;
import jeu.plateau.emplacement.*;
import jeu.personnage.util.*;
import jeu.*;
import java.util.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * class de test qui permet de tester le bon fonctionnement d'un plateau 
 *
 */
public class PlateauTest{
	private Plateau p0;
	private Plateau p1;
	private Plateau p2;
	private Plateau p3;
	private Plateau p4;
	private Plateau p5;
	private Plateau p6;
	private Plateau p7;
	private Plateau p8;
	private Plateau p9;
	private Plateau p10;
	private Plateau p11;
	private ArrayList<Plateau> plateaux;

	@Before
	public void before() {
		this.p0 = new Plateau(1, 1);
		this.p1 = new Plateau(1, 10);
		this.p2 = new Plateau(12, 1);
		this.p3 = new Plateau(15, 15);
		this.p4 = new Plateau(18, 10);
		this.p5 = new Plateau(20, 27);
		this.p6 = new Plateau(30, 15);
		this.p7 = new Plateau(15, 13);
		this.p8 = new Plateau(20, 21);
		this.p9 = new Plateau(150, 150);
		this.p10 = new Plateau(50, 40);
		this.p11 = new Plateau(2, 23);
		
		this.plateaux = new ArrayList<>();
		Collections.addAll(plateaux, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);
	}

	@Test
	public void testAvoirLargeur() {

		assertTrue(this.p0.avoirLargeur() == 1);
		assertTrue(this.p1.avoirLargeur() == 1);
		assertTrue(this.p2.avoirLargeur() == 12);
		assertTrue(this.p3.avoirLargeur() == 15);
		assertTrue(this.p4.avoirLargeur() == 18);
		assertTrue(this.p5.avoirLargeur() == 20);
	}
	
	@Test
	public void testAvoirLongueur() {

		assertTrue(this.p0.avoirLongueur() == 1);
		assertTrue(this.p1.avoirLongueur() == 10);
		assertTrue(this.p2.avoirLongueur() == 1);
		assertTrue(this.p3.avoirLongueur() == 15);
		assertTrue(this.p4.avoirLongueur() == 10);
		assertTrue(this.p5.avoirLongueur() == 27);
	}
	
	@Test
	public void testInitPlateau() {
		int ocean = 0, other = 0;
		for (int i = 0; i < 12; i++){
			for (int j = 0; j < this.plateaux.get(i).avoirLargeur() * this.plateaux.get(i).avoirLongueur(); j++){
				if (this.plateaux.get(i).avoirListeTuiles().get(j).isOcean())
					ocean++;
				else
					other++;
			}
			assertTrue(ocean >= (other+ocean)*2/3);
		}
	}	

	@Test
	public void testAvoirTuileAdjacentes() {
		//On va creer un plateau avec 3 Tuiles Plaines et verifier que avoirCellulesAdjacentes renvoie les bonnes tuiles
		Plateau plateau = new Plateau(2,2);
		Plaine p00 = new Plaine(0,0);
		Plaine p01 = new Plaine(0,1);
		Plaine p10 = new Plaine(1,0);
		Ocean o11 = new Ocean(1,1);
		plateau.modifierTuile(0, 0,p00);
		plateau.modifierTuile(0, 1, p01);
		plateau.modifierTuile(1, 0, p10);
		plateau.modifierTuile(1, 1, o11);
		
        List<Tuile> tuiles = new ArrayList<Tuile>();
        tuiles = plateau.avoirCellulesAdjacentes(0, 0);
        //Comme on va d abord regarder la tuiles au dessus puis en dessous puis a gauche puis a droite, on connait l'ordre de nos tuiles
        assertSame(tuiles.get(0),p01);
        assertSame(tuiles.get(1),p10);
	
	}
	
	@Test

	public void testMethodeaAdjacent() {
		//On va creer un plateau et y mettre manuellement 2 tuiles adjacentes afin de verifier que aAdjacent fonctionne
		Plateau plateau1 = new Plateau(2,2);
		plateau1.modifierTuile(0, 0, new Plaine(0,0));
		plateau1.modifierTuile(0, 1, new Plaine(0,1));		
		assertTrue(plateau1.aAdjacent(0, 0));
		
		//On va verifier que aAdjacent renvoie Fasle si une tuile de type non Ocean n a que des tuiles Ocean autour
		Plateau plateau2 = new Plateau(2,2);
		plateau2.modifierTuile(0, 0, new Plaine(0,0));
		plateau2.modifierTuile(1, 0, new Ocean(1,0));
		plateau2.modifierTuile(0, 1, new Ocean(0,1));	
		assertFalse(plateau2.aAdjacent(0, 0));		
	}
	
	@Test
	public void testplusDeTerritoire() throws Exception {
		this.p3.modifierTuile(0, 0, new Plaine(0,0));
		
		assertFalse(this.p3.plusDeTerritoire());

        List<Tuile> tuiles = new ArrayList<Tuile>();

		tuiles = this.p3.avoirListeTuiles();
		Joueur j = new Joueur("Lounes");
		Ouvrier o = new Ouvrier(j);
		//On va mettre des ouvriers sur toutes les tuiles disponibles	
		for(Tuile t : tuiles) {
			if(!t.isOcean() & !t.estOccupe()) {
				t.ajouterPersonnage(new Ouvrier(j));
			}
		}
		//On verifie donc que la methode plusDeTerritoire renvoie True (car toutes les tuiles sont occupees)
		assertTrue(this.p3.plusDeTerritoire());
	}

 public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
          
                jeu.plateau.PlateauTest.class
        );
    }

}
