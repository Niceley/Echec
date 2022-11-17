package plateau;

import java.util.ArrayList;

import Géométrie.Coordonnées;
import joueur.Couleur;
import joueur.Joueur;
import pièces.PièceVide;
import pièces.Roi;
import pièces.Tour;
import pièces.Reine;
import pièces.Pions;
import pièces.Fou;
import pièces.Cavalier;

public class Plateau {
	//le joueur précédent, qui servira à  faire tourSuivant()
	private Joueur précédent;
	// le joueur suivant
	private Joueur suivant;
	// le joueur courant
	private Joueur courant;
	// un tableau de lettres pour aider à la traduction des coups saisis
	private char [] lettres = {'a','b','c','d','e','f','g','h'};
	// un tableau à 2 dimensions de pièces, qui servira à afficher le plateau
	private IPièce plateau[][] = new IPièce[8][8];
	// une liste des pièces restantes du plateau
	private ArrayList<IPièce> piècesrestantes = new ArrayList<IPièce>();
	// une liste des rois du plateau
	private ArrayList<Roi> rois = new ArrayList<Roi>();
	private boolean tourValide = true;
	private boolean partieFinie = false;
	
	
	public Plateau(Joueur blanc, Joueur noir) {
		this.courant = blanc;
		this.suivant = noir;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				plateau[i][j] = new PièceVide();
			}
		}
		//on initialise les pièces du plateau
		new Cavalier(new Coordonnées(2,1), this, blanc);
		new Cavalier(new Coordonnées(7,1), this, blanc);
		new Cavalier(new Coordonnées(2,8), this, noir);
		new Cavalier(new Coordonnées(7,8), this, noir);
		new Roi( new Coordonnées(5,1), this, blanc);
		new Roi( new Coordonnées(5,8), this, noir);		
		new Tour( new Coordonnées(1,1), this, blanc);
	    new Tour( new Coordonnées(8,1), this, blanc);
		new Tour(new Coordonnées(1,8), this, noir);
		new Tour( new Coordonnées(8,8), this, noir);
		new Fou( new Coordonnées(3,1), this, blanc);
		new Fou( new Coordonnées(6,1), this, blanc);
		new Fou(new Coordonnées(3,8), this, noir);
		new Fou( new Coordonnées(6,8), this, noir);
	    new Reine(new Coordonnées(4,1), this, blanc);
		new Reine(new Coordonnées(4,8), this, noir);
		for(int i=1; i<9; i++) {
			new Pions(new Coordonnées(i,2),this, blanc);
		}
		for(int i=1; i<9; i++) {
			new Pions(new Coordonnées(i,7),this, noir);
		}

		;
	}
	
	public Plateau() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				plateau[i][j] = new PièceVide();
			}
		}
	}
	
	public void setPartieFinie() {
		partieFinie = true;
	}	
	
	public boolean partieFinie() {
		return partieFinie;
	}
	
	public boolean getValide() {
		return tourValide;
	}
	
	public void tourValidé() {
		tourValide=true;
	}
	public void tourNonValidé() {
		tourValide=false;
	}
	public void tourSuivant() {
		précédent = courant;
		courant = suivant;
		suivant = précédent;
		
	}
	
	public ArrayList<Roi> getRois(){
		return rois;
	}
	
	public void ajouterRoi(Roi r) {
		this.rois.add(r);
	}
	
	public String AfficherPièces() {
		String s="";
		for(IPièce i : piècesrestantes) {
			s+= i.symbole()+", ";
		}
		return s;
	}
	
	/** retourne le roi de la couleur donnée en paramètre
	 * @param c la couleur
	 * @return r le roi
	 */
	public Roi getRoi(Couleur c) {
		for(Roi r : rois) {
			if(r.getCouleur().equals(c))
				return r;
		}
		return null;
	}
	
	public Joueur getCourant() {
		return courant;
	}
	
	public Joueur getSuivant() {
		return suivant;
	}
	
	public ArrayList<IPièce> getPiècesRestantes(){
		return this.piècesrestantes;
	}
	
	public void ajouterPièce(IPièce pièce) {
		piècesrestantes.add(pièce);
	}
	
	public IPièce[][] getPiècesPlateau(){
		return this.plateau;
	}
	
	/** affiche l'état des rois
	 */
	public void afficherRois() {
		for(Roi roi : this.getRois()) {
			if(roi.estEnEchec())
				System.out.println("Le roi "+ roi.getCouleur() + " est en échecs");
			if(roi.echecEtMat()) {
				System.out.println("Echec et Mat");
			}
		}
	}

	
	/** retourne la pièce qui se situe aux coordonnées données en paramètre
	 * @param c les coordonnées
	 * @return i la pièce
	 */
	public IPièce getPièce(Coordonnées c) {
		for(IPièce i : piècesrestantes) 
			if(c.getColonne() == i.getCoord().getColonne()
					&& c.getLigne() == i.getCoord().getLigne())
				return i;
					
		return new PièceVide();
	}
	
	/** vérifie que le char donné en paramètre est contenu dans le tableau de lettre
	 * @return true s'il est dedans, false sinon
	 */
	public boolean estContenu(char c) {
		for(char l : lettres) {
			if(l==c)
				return true;
		}
		return false;
	}
	
	/** vérifie que la chaine de caractère saisie est correcte, et affiche un message d'erreur si ce n'est pas une pièce de la couleur 
	 * du joueur courant, ou si ce n'est pas l'emplacement d'une pièce
	 * @param s la chaine de caractère
	 * @return true si elle est correcte, false sinon
	 */
	public boolean saisieCorrecte(String s) {
		s.trim();
		if(s.length()==4 && !(courant.getCouleur().equals(
			courant.getPièce(traduireOrigine(s)).getCouleur())))
			System.out.println("Ce n'est pas une pièce valide");

		return (s.length()==4 && estContenu(s.charAt(0))
				&& estContenu(s.charAt(2)) && 
				Character.isDigit(s.charAt(1)) &&
				Character.isDigit(s.charAt(3)))
	&& courant.getCouleur().equals(
			courant.getPièce(traduireOrigine(s)).getCouleur());

		
	}
	
	/** retourne les coordonnées de la pièce que le joueur veut déplacer en traduisant la chaine de caractère saisie
	 * @param s la chaine de caractère
	 * @return les coordonnées
	 */
	public Coordonnées traduireOrigine(String s) {		
		int colonne=0;
		for(int i=0; i<8; i++) {
			if(s.charAt(0) == lettres[i])
				colonne = i;
		}
		return new Coordonnées(colonne+1, Character.getNumericValue(s.charAt(1)));
	}
	
	/** retourne les coordonnées où le joueur veut déplacer sa pièce en traduisant la chaine de caractère saisie
	 * @param s la chaine de caractère
	 * @return les coordonnées
	 */
	public Coordonnées traduireArrivee(String s) {		
		int colonne=0;
		for(int i=0; i<8; i++) {
			if(s.charAt(2) == lettres[i])
				colonne = i;
		}
		return new Coordonnées(colonne+1, Character.getNumericValue(s.charAt(3)));
	}
	
	
	public String afficherTourJoueur() {
		return "Tour du joueur "+ courant.getCouleur() + " de jouer\n";
	}
	
	public String toString() {
		String s = "    a   b   c   d   e   f   g   h    \n";

		for(int i=8; i>0; i--) {
			s+= "   --- --- --- --- --- --- --- ---\n";
			s+=i+" | ";
			for(int j=0; j<8; j++) {
				s+= plateau[j][8-i].symbole()+" | ";	
				if (j==7)
					s+= i+"\n";
			}
		}
		s+= "   --- --- --- --- --- --- --- ---\n" ;
		s+= "    a   b   c   d   e   f   g   h    \n";
		return s;
		
	}

}
