package appli;

import java.util.Scanner;

import joueur.Couleur;
import joueur.Joueur;

import plateau.Plateau;

public class Application {
	public static void main(String[] args)  {
		// initialisation du plateau et des joueurs
		Plateau plateau = new Plateau();
		Joueur blanc = new Joueur(Couleur.BLANC, plateau);
		Joueur noir = new Joueur(Couleur.NOIR, plateau);
		plateau = new Plateau(blanc,noir);
		
		
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String s="";
		
		System.out.println(plateau.toString());
		System.out.println(plateau.afficherTourJoueur());
		
		while (!plateau.partieFinie()) {
			System.out.print("> ");
			s = sc.nextLine();
			if(s.equals("abandon"))
				break;
			
			while(!plateau.saisieCorrecte(s)) {
				System.out.println("Saisie incorrecte, veuillez recommencer");
				System.out.println(plateau.toString());
				System.out.println(plateau.afficherTourJoueur());
				System.out.print("> ");
				s = sc.nextLine();	
			}
			
			plateau.getCourant().Jouer(plateau.traduireOrigine(s), plateau.traduireArrivee(s));	
			
			while(!plateau.getValide()) {
				System.out.println("Coup non Valide");
				System.out.println(plateau.toString());
				System.out.println(plateau.afficherTourJoueur());
				System.out.print("> ");
				s = sc.nextLine();			
				plateau.getCourant().Jouer(plateau.traduireOrigine(s), plateau.traduireArrivee(s));
			}
			
			plateau.tourSuivant();
			plateau.afficherRois();
			System.out.println(plateau.toString());
			System.out.println(plateau.afficherTourJoueur());		
		}
		System.out.println("Partie finie, le joueur "+ plateau.getSuivant().getCouleur()+ " a gagné");
	}
	
		
}




