package jeu.plateau;

import jeu.*;
import jeu.personnage.util.*;
import jeu.personnage.*;
import jeu.plateau.*;
import jeu.plateau.emplacement.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class JoueurTest {
	
	private Joueur joueur;
	
	private Plateau plateau;
	
	private Ouvrier ouvrier1;


	@Before
	public void Before() throws Exception {
		this.joueur = new Joueur("Prenom");
		this.plateau = new Plateau(4,5);
		this.plateau.modifierTuile(2, 2, new Plaine(2,2));
		this.plateau.modifierTuile(1, 1, new Plaine(1,1));
		this.plateau.modifierTuile(3, 3, new Plaine(3,3));
		this.ouvrier1 = new Ouvrier(this.joueur);

		this.joueur.ajoutPlateau(this.plateau);
		
		
	}
	
	@Test
	public void testAvoirPlateau() {
		assertSame(this.joueur.avoirPlateau(),plateau);
	}

	/////////////////////////////////////Test des m�thodes li�es au jeu des ouvriers
	
	//On va tester qu'une erreur est bien renvoy�e lorsqu'on essai de d�ployer un ouvrier sur un plateau trop petit
	@Test(expected = Exception.class)
	public void testExcpeptionQuandOnDeploieUnOuvrierSurUneTuileInexistante() throws Exception{
		
		this.joueur.deployerOuvrier(5, 5);
	
	}
	
	
	@Test
	public void testDeployerUnOuvrier() throws Exception {
		assertFalse(this.joueur.avoirPlateau().avoirTuile(2,2).isOcean());
		assertSame(this.joueur.avoirPlateau().avoirTuile(2, 2).avoirPersonnage(),null);
		
		this.joueur.avoirPlateau().avoirTuile(2, 2).ajouterPersonnage(this.ouvrier1);
		
		assertSame(this.joueur.avoirPlateau().avoirTuile(2, 2).avoirPersonnage(),this.ouvrier1); 
	}
	
	@Test
	public void TestRecolterToutOrOuvrier() throws Exception {
		//On v�rfie qu'on commencebien avec 15or en stock
		assertTrue(this.joueur.avoirOrOuvrier()==15);
		this.joueur.deployerOuvrier(1, 1);
		this.joueur.deployerOuvrier(2, 2);
		this.joueur.deployerOuvrier(3, 3);
		
		//On verifie que tous les ouvrier n'ont pas d'or en poche avant la r�colte
		for(Ouvrier o : this.joueur.avoirListeOuvrier()) {
			assertTrue(o.avoirOr()==0);
		}
		
		//Ici tous nos ouvriers vont recolter 1 de cl�
		this.joueur.recolteressourceOuvrier(1);
		
		//On va convertir tout ce bl� en OR
		this.joueur.convertirLOrTousOuvriers();
		
		//On verifie que tous les ouvrier ont 2 d'or en poche apr�s la r�colte
		for(Ouvrier o : this.joueur.avoirListeOuvrier()) {
			assertTrue(o.avoirOr()==2);
		}
		
		//On va r�cuper l'or des ouvrier
		this.joueur.recolteOr();
		
		assertTrue(this.joueur.avoirOrOuvrier()==21);
		
		
		//On verifie que tous les ouvrier n'ont plus d'or en poche
		for(Ouvrier o : this.joueur.avoirListeOuvrier()) {
			assertTrue(o.avoirOr()==0);
		}
	}
	
	
	@Test
	public void testDuNombreDePoint() throws Exception {
		assertTrue(this.joueur.avoirPointsOuvrier()==15);
		
		this.joueur.deployerOuvrier(1, 1);
		this.joueur.deployerOuvrier(2, 2);
		this.joueur.deployerOuvrier(3, 3);
		
		//Ici tous nos ouvriers vont recolter 1 de cl�
		this.joueur.recolteressourceOuvrier(1);
		
		//On va convertir tout ce bl� en OR
		this.joueur.convertirLOrTousOuvriers();
		
		//On va r�cuper l'or des ouvrier
		this.joueur.recolteOr();
		
		//On va v�rifier maintenant que le joueur a recuper� 6 points
		assertTrue(this.joueur.avoirPointsOuvrier()==21);
	
		
		
	}
	
	
	public void testPayerTousLesOuvriers() throws Exception {
		assertTrue(this.joueur.avoirOrOuvrier()==15);
		
		this.joueur.deployerOuvrier(1, 1);
		this.joueur.deployerOuvrier(2, 2);
		this.joueur.deployerOuvrier(3, 3);
		//On v�rifie que tous nos ouvrier sont en vies
		for(Ouvrier o : this.joueur.avoirListeOuvrier()) {
			assertTrue(o.estEnVie());
		}
		this.joueur.payerOuvrier();
		//On v�rifie qu'on a bien �tait d�bit� de 1 or par ouvrier
		assertTrue(this.joueur.avoirOrOuvrier()==12);
		
		//On v�rifie que tous nos ouvrier sont encore en vies
		for(Ouvrier o : this.joueur.avoirListeOuvrier()) {
			assertTrue(o.estEnVie());
		}
	}
	
	
	/////////////////////////////////////Test des m�thodes li�es au jeu des Arm�es

	
	@Test(expected = Exception.class)
	public void testExcpeptionQuandOnDeploieUneArmeeSurUneTuileInexistante() throws Exception{
		
		this.joueur.deployerArmee(3,5, 5);
	
	}
	
	@Test(expected = Exception.class)
	public void testExcpeptionQuandOnDeploieUneArmeeTropGrandeSurUneTuile() throws Exception{
		this.joueur.avoirPlateau().modifierTuile(1, 2, new Montagne(1,2));
		this.joueur.deployerArmee(4,1, 2);
	
	}
	
	@Test
	public void testDeploieUneArmeeSurUneTuile() throws Exception{
		this.joueur.deployerArmee(1, 1,4);
		this.joueur.deployerArmee(2, 2,2);
		this.joueur.deployerArmee(3, 3,1);

		int i=0;
		//Cette boucle nous permet de savoir si on a bien les bonnes Arm�es � la bonne case
		for(Armee a : this.joueur.avoirListeArmee()) {
			i++;
			assertSame(a,this.joueur.avoirPlateau().avoirTuile(i, i).avoirPersonnage());	
		}						
		
	}
	
	@Test
	public void TestRecolterTouteLaNourritureArmee() throws Exception {
		//On v�rfie qu'on commence bien avec 0 or en stock
		assertTrue(this.joueur.avoirOrArmee()==0);
		this.joueur.deployerArmee(1, 1,1);
		this.joueur.deployerArmee(2, 2,2);
		this.joueur.deployerArmee(3, 3,3);

		
		//On verifie que toutes les arm�es n'ont pas d'or en poche avant la r�colte
		for(Armee  a: this.joueur.avoirListeArmee()) {
			assertTrue(a.avoirOr()==0);
		}
		
		//Ici toutes nos arm�es vont recolter 1 de cl�
		this.joueur.recolteressourceArmee(1);;
		
		//On va convertir tout ce bl� en OR
		this.joueur.convertitRessources();
		
		//On verifie que tous les ouvrier ont 2 d'or en poche apr�s la r�colte
		for(Armee a : this.joueur.avoirListeArmee()) {
			assertTrue(a.avoirNourritureStock()==5);

		}
		
		//On va r�cuper la nourriture des Arm�es
		this.joueur.recupereNourriture();
		
		assertTrue(this.joueur.avoirNourritureStock()==25);
		
		
		//On verifie que toutes les arm�es n'ont plus de nourriture en poche
		for(Armee a : this.joueur.avoirListeArmee()) {
			assertTrue(a.avoirNourritureStock()==0);

		}
	}
	
	
	
	
	
	
	@Test
	public void testDeNourrireNosUnite() throws Exception {
		
		//On a initialement 10 unit�s de nourriture en stock, ce qui nous permet de nourrir au total 10 unit�s d'arm�e qui sont sur des tuiles plaine
		this.joueur.deployerArmee(1, 1,3);
		this.joueur.deployerArmee(2, 2,3);
		this.joueur.deployerArmee(3, 3,3);
		
		//On a plac� un total de 9 guerriers
		for(Armee a : this.joueur.avoirListeArmee()) {
			assertTrue(a.tailleUnitee()==3);
		}
		
		assertTrue(this.joueur.avoirNourritureStock()==10);

		//On nourrit tout le monde
		this.joueur.nourritSesArmees();
		
		//On a v�rifi qu'ils sont encore tous vivant
		for(Armee a : this.joueur.avoirListeArmee()) {
			assertTrue(a.tailleUnitee()==3);
		}
		
		assertTrue(this.joueur.avoirNourritureStock()==1);

		//Maintenant on va les renourrir
		this.joueur.nourritSesArmees();

		//parmit la premi�re arm�e, seul un seul militaire a pu �tre nourrit avec le dernier bl� en stock
		assertTrue(this.joueur.avoirListeArmee().get(0).tailleUnitee()==1);
		assertTrue(this.joueur.avoirListeArmee().get(0).estEnVie());

		//Il n y avait plus de nourriture pour les autres
		assertTrue(this.joueur.avoirListeArmee().get(1).tailleUnitee()==0);
		assertFalse(this.joueur.avoirListeArmee().get(1).estEnVie());
		assertTrue(this.joueur.avoirListeArmee().get(2).tailleUnitee()==0);
		assertFalse(this.joueur.avoirListeArmee().get(2).estEnVie());	
	}
	
	
	@Test
	public void testBaisseNombreDeGuerrier() throws Exception {
		Joueur Conqueror = new Joueur("GENGHIS KHAN");
		
		assertTrue(Conqueror.avoirNombreGuerrierStock()==35);
		
		Plateau p = new Plateau(2,3);
		for(int i=0;i<2;i++) {
			for(int a=0;a<3;a++) {
				p.modifierTuile(i, a, new Plaine(i,a));
			}
		}
		
		Conqueror.ajoutPlateau(p);
		
		for(int i=0;i<2;i++) {
			for(int a=0;a<3;a++) {
				Conqueror.deployerArmee(i, a, 5);
			}
		}
		assertTrue(Conqueror.avoirNombreGuerrierStock()==5);
		
		//On va tuer une arm�e pour d�ployer nos dernier 5 guerriers
		
		((Armee) Conqueror.avoirPlateau().avoirTuile(1, 1).avoirPersonnage()).detruitUnite(5);
		
		assertFalse(Conqueror.avoirPlateau().avoirTuile(1, 1).avoirPersonnage().estEnVie());
		
		Conqueror.deployerArmee(1, 1, 5);
		
		assertTrue(Conqueror.avoirPlateau().avoirTuile(1, 1).avoirPersonnage().estEnVie());
		
		assertTrue(Conqueror.avoirNombreGuerrierStock()==0);
			
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeDeployerTropArmee() throws Exception{
		Joueur Conqueror = new Joueur("Napoleon");
		
		assertTrue(Conqueror.avoirNombreGuerrierStock()==35);
		
		Plateau p = new Plateau(3,3);
		for(int i=0;i<3;i++) {
			for(int a=0;a<3;a++) {
				p.modifierTuile(i, a, new Plaine(i,a));
			}
		}
		
		Conqueror.ajoutPlateau(p);
		
		for(int i=0;i<3;i++) {
			for(int a=0;a<3;a++) {
				Conqueror.deployerArmee(i, a, 5);
			}
		}
		
	}
	
	
	@Test
	public void testCompteDePoints() throws Exception {
		Joueur Conqueror = new Joueur("Attila the hun");	
		assertTrue(Conqueror.avoirPointsArmee()==0);
		
		Plateau p = new Plateau(3,3);
		for(int i=0;i<3;i++) {
			for(int a=0;a<3;a++) {
				p.modifierTuile(i, a, new Plaine(i,a));
			}
		}
		
		//On va metter 2 Oceans
		p.modifierTuile(2, 1, new Ocean(2,1));
		p.modifierTuile(0, 0, new Ocean(0,0));

		//On va mettre un Desert
		p.modifierTuile(1, 2, new Desert(1,2));

		Conqueror.ajoutPlateau(p);
		
		for(int i=0;i<3;i++) {
			for(int a=0;a<3;a++) {
				try {
				Conqueror.deployerArmee(i, a, p.avoirTuile(i, a).avoirUniteetMax());
			}
				catch(IllegalArgumentException e) {
					System.out.println("Tu ne peux pas poser ici" );
				}
			}
		}
		
		//conqueror a 6 tuiles Plaine qui valent 1 pt, 1 Desert qui vaut 4 pts
		assertTrue(Conqueror.avoirPointsArmee()==10);
		assertTrue(Conqueror.avoirListeArmee().get(0).estEnVie());
		//On va d�truire quelque une de ses arm�es pour r�cuperer de l'or
		Conqueror.avoirListeArmee().get(0).detruitUnite(5);
		assertFalse(Conqueror.avoirListeArmee().get(0).estEnVie());
		Conqueror.recolteOr();
		
		assertTrue(Conqueror.avoirOrArmee()==1);
		
		//Comme une arm�e a �tait d�truite alors on a plus le point associ� � une tuile plaine
		assertTrue(Conqueror.avoirPointsArmee()==10);
		
		Conqueror.deployerArmee(0, 1, 2);
		
		//Comme une nouvelle arm�e a �tait d�ploy�e
		assertTrue(Conqueror.avoirPointsArmee()==11);

		
		
	}
	
	
	@Test
	public void TestBonusPoints() throws Exception {
		
		Joueur Conqueror = new Joueur("Hari Singh Nalwa");
		
		
		Plateau p = new Plateau(4,4);
		for(int i=0;i<4;i++) {
			for(int a=0;a<4;a++) {
				p.modifierTuile(i, a, new Plaine(i,a));
			}
		}
		
		Conqueror.ajoutPlateau(p);
		
		assertTrue(Conqueror.avoirPointsArmee()==0);

		for(int i=0;i<4;i++) {
			for(int a=0;a<4;a++) {
				Conqueror.deployerArmee(i, a, 1);
			}
		}
		
		
		assertTrue(Conqueror.avoirPointsArmee()==21);
	}
	
	
 public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            jeu.plateau.JoueurTest.class
        );
    }
	
	
	
}
