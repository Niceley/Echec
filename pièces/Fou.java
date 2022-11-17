package pièces;

import Géométrie.Coordonnées;
import joueur.Couleur;
import joueur.Joueur;
import plateau.Plateau;

public class Fou extends Pièce {
	
	public Fou( Coordonnées c, Plateau p, Joueur j) {
		super( c, p, j);
	}

	public void déplacer (Coordonnées nouvelle){
		super.déplacer(nouvelle);
	}
	
	/** Vérifie que la pièce peut aller à la ligne des coordonnées c
	 * @param c les coordonnées
	 * @return true s'il peut , false sinon
	 */
	@Override
	public boolean peutAllerà(Coordonnées c) {
		return (peutAllerColonne(c) && peutAllerLigne(c)) && 
				(this.getPlateau().getPièce(c).getCouleur()!= this.getCouleur());
	}
	
	/** Vérifie que la pièce peut aller à la colonne des coordonnées c
	 * @param c les coordonnées
	 * @return true s'il peut , false sinon
	 */
	public boolean peutAllerColonne(Coordonnées c) {
        if(c.Valide())
            return c.getColonne() > getCoord().getColonne() || c.getColonne() < getCoord().getColonne();
        return false;
    }
	
	/** Vérifie que la pièce peut aller à la ligne des coordonnées c
	 * @param c les coordonnées
	 * @return true s'il peut , false sinon
	 */
	public boolean peutAllerLigne(Coordonnées c) {
	     if(c.Valide())
	            return c.getLigne() > getCoord().getLigne() || c.getLigne() < getCoord().getLigne();
	        return false;
	                
	    }
	
	/** retourne le symbole de la pièce
	 * @return le symbole de la pièce
	 */
	@Override
	public String symbole() {
		if(this == null)
			return " ";
		else if (this.getCouleur() == Couleur.NOIR)
			return "f";
		else return "F"
				+ "";
	}


}
