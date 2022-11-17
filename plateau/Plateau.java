package plateau;

import java.util.ArrayList;

import G�om�trie.Coordonn�es;
import joueur.Couleur;
import joueur.Joueur;
import pi�ces.Pi�ceVide;
import pi�ces.Roi;
import pi�ces.Tour;
import pi�ces.Reine;
import pi�ces.Pions;
import pi�ces.Fou;
import pi�ces.Cavalier;

public class Plateau {
	//le joueur pr�c�dent, qui servira �  faire tourSuivant()
	private Joueur pr�c�dent;
	// le joueur suivant
	private Joueur suivant;
	// le joueur courant
	private Joueur courant;
	// un tableau de lettres pour aider � la traduction des coups saisis
	private char [] lettres = {'a','b','c','d','e','f','g','h'};
	// un tableau � 2 dimensions de pi�ces, qui servira � afficher le plateau
	private IPi�ce plateau[][] = new IPi�ce[8][8];
	// une liste des pi�ces restantes du plateau
	private ArrayList<IPi�ce> pi�cesrestantes = new ArrayList<IPi�ce>();
	// une liste des rois du plateau
	private ArrayList<Roi> rois = new ArrayList<Roi>();
	private boolean tourValide = true;
	private boolean partieFinie = false;
	
	
	public Plateau(Joueur blanc, Joueur noir) {
		this.courant = blanc;
		this.suivant = noir;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				plateau[i][j] = new Pi�ceVide();
			}
		}
		//on initialise les pi�ces du plateau
		new Cavalier(new Coordonn�es(2,1), this, blanc);
		new Cavalier(new Coordonn�es(7,1), this, blanc);
		new Cavalier(new Coordonn�es(2,8), this, noir);
		new Cavalier(new Coordonn�es(7,8), this, noir);
		new Roi( new Coordonn�es(5,1), this, blanc);
		new Roi( new Coordonn�es(5,8), this, noir);		
		new Tour( new Coordonn�es(1,1), this, blanc);
	    new Tour( new Coordonn�es(8,1), this, blanc);
		new Tour(new Coordonn�es(1,8), this, noir);
		new Tour( new Coordonn�es(8,8), this, noir);
		new Fou( new Coordonn�es(3,1), this, blanc);
		new Fou( new Coordonn�es(6,1), this, blanc);
		new Fou(new Coordonn�es(3,8), this, noir);
		new Fou( new Coordonn�es(6,8), this, noir);
	    new Reine(new Coordonn�es(4,1), this, blanc);
		new Reine(new Coordonn�es(4,8), this, noir);
		for(int i=1; i<9; i++) {
			new Pions(new Coordonn�es(i,2),this, blanc);
		}
		for(int i=1; i<9; i++) {
			new Pions(new Coordonn�es(i,7),this, noir);
		}

		;
	}
	
	public Plateau() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				plateau[i][j] = new Pi�ceVide();
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
	
	public void tourValid�() {
		tourValide=true;
	}
	public void tourNonValid�() {
		tourValide=false;
	}
	public void tourSuivant() {
		pr�c�dent = courant;
		courant = suivant;
		suivant = pr�c�dent;
		
	}
	
	public ArrayList<Roi> getRois(){
		return rois;
	}
	
	public void ajouterRoi(Roi r) {
		this.rois.add(r);
	}
	
	public String AfficherPi�ces() {
		String s="";
		for(IPi�ce i : pi�cesrestantes) {
			s+= i.symbole()+", ";
		}
		return s;
	}
	
	/** retourne le roi de la couleur donn�e en param�tre
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
	
	public ArrayList<IPi�ce> getPi�cesRestantes(){
		return this.pi�cesrestantes;
	}
	
	public void ajouterPi�ce(IPi�ce pi�ce) {
		pi�cesrestantes.add(pi�ce);
	}
	
	public IPi�ce[][] getPi�cesPlateau(){
		return this.plateau;
	}
	
	/** affiche l'�tat des rois
	 */
	public void afficherRois() {
		for(Roi roi : this.getRois()) {
			if(roi.estEnEchec())
				System.out.println("Le roi "+ roi.getCouleur() + " est en �checs");
			if(roi.echecEtMat()) {
				System.out.println("Echec et Mat");
			}
		}
	}

	
	/** retourne la pi�ce qui se situe aux coordonn�es donn�es en param�tre
	 * @param c les coordonn�es
	 * @return i la pi�ce
	 */
	public IPi�ce getPi�ce(Coordonn�es c) {
		for(IPi�ce i : pi�cesrestantes) 
			if(c.getColonne() == i.getCoord().getColonne()
					&& c.getLigne() == i.getCoord().getLigne())
				return i;
					
		return new Pi�ceVide();
	}
	
	/** v�rifie que le char donn� en param�tre est contenu dans le tableau de lettre
	 * @return true s'il est dedans, false sinon
	 */
	public boolean estContenu(char c) {
		for(char l : lettres) {
			if(l==c)
				return true;
		}
		return false;
	}
	
	/** v�rifie que la chaine de caract�re saisie est correcte, et affiche un message d'erreur si ce n'est pas une pi�ce de la couleur 
	 * du joueur courant, ou si ce n'est pas l'emplacement d'une pi�ce
	 * @param s la chaine de caract�re
	 * @return true si elle est correcte, false sinon
	 */
	public boolean saisieCorrecte(String s) {
		s.trim();
		if(s.length()==4 && !(courant.getCouleur().equals(
			courant.getPi�ce(traduireOrigine(s)).getCouleur())))
			System.out.println("Ce n'est pas une pi�ce valide");

		return (s.length()==4 && estContenu(s.charAt(0))
				&& estContenu(s.charAt(2)) && 
				Character.isDigit(s.charAt(1)) &&
				Character.isDigit(s.charAt(3)))
	&& courant.getCouleur().equals(
			courant.getPi�ce(traduireOrigine(s)).getCouleur());

		
	}
	
	/** retourne les coordonn�es de la pi�ce que le joueur veut d�placer en traduisant la chaine de caract�re saisie
	 * @param s la chaine de caract�re
	 * @return les coordonn�es
	 */
	public Coordonn�es traduireOrigine(String s) {		
		int colonne=0;
		for(int i=0; i<8; i++) {
			if(s.charAt(0) == lettres[i])
				colonne = i;
		}
		return new Coordonn�es(colonne+1, Character.getNumericValue(s.charAt(1)));
	}
	
	/** retourne les coordonn�es o� le joueur veut d�placer sa pi�ce en traduisant la chaine de caract�re saisie
	 * @param s la chaine de caract�re
	 * @return les coordonn�es
	 */
	public Coordonn�es traduireArrivee(String s) {		
		int colonne=0;
		for(int i=0; i<8; i++) {
			if(s.charAt(2) == lettres[i])
				colonne = i;
		}
		return new Coordonn�es(colonne+1, Character.getNumericValue(s.charAt(3)));
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
