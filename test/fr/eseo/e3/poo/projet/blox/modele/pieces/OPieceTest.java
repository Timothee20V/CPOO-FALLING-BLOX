package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class OPieceTest {

    @Test
    public void testConstructeur() {
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        assertEquals(new Coordonnees(1, 2), oPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(2, 2), oPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(1, 1), oPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(2, 1), oPiece.getElements().get(3).getCoordonnees());
        assertEquals(Couleur.BLEU, oPiece.getElements().get(0).getCouleur());
        assertEquals(Couleur.BLEU, oPiece.getElements().get(1).getCouleur());
        assertEquals(Couleur.BLEU, oPiece.getElements().get(2).getCouleur());
        assertEquals(Couleur.BLEU, oPiece.getElements().get(3).getCouleur(), "Test constructeur");
    }

    @Test
    public void testToString() {
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        assertEquals("OPiece :\n\t(1, 2) - BLEU\n\t(2, 2) - BLEU\n\t(1, 1) - BLEU\n\t(2, 1) - BLEU\n",
                oPiece.toString(), "Test toString");
    }

    @Test
    public void testSetElements() {
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        oPiece.setElements(new Coordonnees(2, 3), Couleur.ROUGE);
        assertEquals(new Coordonnees(2, 3), oPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(3, 3), oPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(2, 2), oPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(3, 2), oPiece.getElements().get(3).getCoordonnees());
        assertEquals(Couleur.ROUGE, oPiece.getElements().get(0).getCouleur());
        assertEquals(Couleur.ROUGE, oPiece.getElements().get(1).getCouleur());
        assertEquals(Couleur.ROUGE, oPiece.getElements().get(2).getCouleur());
        assertEquals(Couleur.ROUGE, oPiece.getElements().get(3).getCouleur(), "Test setElements");
    }

    @Test
    public void testGetElements() {
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        List<Element> elements = new ArrayList<>();
        elements.add(new Element(new Coordonnees(1, 2), Couleur.BLEU));
        elements.add(new Element(new Coordonnees(2, 2), Couleur.BLEU));
        elements.add(new Element(new Coordonnees(1, 1), Couleur.BLEU));
        elements.add(new Element(new Coordonnees(2, 1), Couleur.BLEU));
        assertEquals(elements, oPiece.getElements(), "Test getElements");
    }

    @Test
    public void testSetPosition() {
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        oPiece.setPosition(2, 3);
        assertEquals(new Coordonnees(2, 3), oPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(3, 3), oPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(2, 2), oPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(3, 2), oPiece.getElements().get(3).getCoordonnees());
        assertEquals(Couleur.BLEU, oPiece.getElements().get(0).getCouleur());
        assertEquals(Couleur.BLEU, oPiece.getElements().get(1).getCouleur());
        assertEquals(Couleur.BLEU, oPiece.getElements().get(2).getCouleur());
        assertEquals(Couleur.BLEU, oPiece.getElements().get(3).getCouleur(), "Test setPosition");
    }

    @Test
    public void testGetPuits() {
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        assertNull(oPiece.getPuits(), "Test getPuits");
    }

    @Test
    public void testSetPuits() {
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        Puits puits = new Puits();

        oPiece.setPuits(puits);
        assertEquals(puits, oPiece.getPuits(), "Test setPuits");
    }

    @Test
    public void testDeplacerDe() throws BloxException {
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        oPiece.setPosition(2, 3);

        oPiece.deplacerDe(1, 1);
        assertEquals(new Coordonnees(3, 4), oPiece.getElements().get(0).getCoordonnees());

        try {
            oPiece.deplacerDe(-1, -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Le déplacement ne peut se faire que vers le bas, la droite et la gauche",
                    e.getMessage(), "La coordonnée y doit être positive");
        }

        try {
            oPiece.deplacerDe(2, -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Le déplacement ne peut se faire que vers le bas, la droite et la gauche",
                    e.getMessage(), "La coordonnée |x| doit être inférieure ou égale à 1");
        }

        try {
            oPiece.deplacerDe(-2, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Le déplacement ne peut se faire que vers le bas, la droite et la gauche",
                    e.getMessage(), "La coordonnée |x| doit être inférieure ou égale à 1");
        }

        try {
            oPiece.deplacerDe(0, 2);
        } catch (IllegalArgumentException e) {
            assertEquals("Le déplacement ne peut se faire que vers le bas, la droite et la gauche",
                    e.getMessage(), "La coordonnée y doit être inférieure ou égale à 1");
        }
    }

    @Test
    public void testDeplacerException() {
        Puits puits = new Puits(10,15);
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        oPiece.setPuits(puits);
        oPiece.setPosition(0, 0);
        try {
            oPiece.deplacerDe(-1, 0);
        } catch (BloxException e) {
            assertEquals("Le déplacement ne peut se faire en dehors du puits",
                    e.getMessage(), "Le déplacement ne peut se faire en dehors du puits");
        }
        oPiece.setPosition(puits.getLargeur() - 1 , 0);
        try {
            oPiece.deplacerDe(1, 0);
        } catch (BloxException e) {
            assertEquals("Le déplacement ne peut se faire en dehors du puits",
                    e.getMessage(), "Le déplacement ne peut se faire en dehors du puits");
        }
        oPiece.setPosition(0, puits.getProfondeur());
        try {
            oPiece.deplacerDe(0, 1);
        } catch (BloxException e) {
            assertEquals("Collision avec le fond du puits",
                    e.getMessage(), "Collision avec le fond du puits");
        }
        Puits puits1 = new Puits(10,15, 29, 3);
        oPiece.setPuits(puits1);
        oPiece.setPosition(0, 12);
        try {
            oPiece.deplacerDe(0, 1);
        } catch (BloxException e) {
            assertEquals("Collision avec un élément du tas",
                    e.getMessage(), "Collision avec un élément du tas");
        }
    }

    @Test
    public void testTrouner(){
        OPiece oPiece = new OPiece(new Coordonnees(2, 2), Couleur.ORANGE);

        oPiece.tourner(true);
        assertEquals(new Coordonnees(2, 2), oPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(3, 2), oPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(2, 1), oPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(3, 1), oPiece.getElements().get(3).getCoordonnees());

        oPiece.tourner(false);
        assertEquals(new Coordonnees(2, 2), oPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(3, 2), oPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(2, 1), oPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(3, 1), oPiece.getElements().get(3).getCoordonnees());

    }
}
