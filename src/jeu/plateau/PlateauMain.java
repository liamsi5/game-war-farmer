package jeu.plateau;
import jeu.plateau.emplacement.*;
public class PlateauMain{


    public static void main(String[] args) {
        int x = 4;
        int y= 4;    
        Plateau b = new Plateau(x,y);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(b.avoirTuile(i, j).toString());
            }
            
            System.out.println("\n");
        }

    }



}