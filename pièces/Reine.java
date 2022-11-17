package pièces;

import Géométrie.Coordonnées;
import joueur.Couleur;
import joueur.Joueur;
import plateau.Plateau;

public class Reine extends Pièce {
	
	public Reine( Coordonnées c, Plateau p, Joueur j) {
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
		return ( peutAllerDiago(c) || peutAllerHV(c))
                && (this.getPlateau().getPièce(c).getCouleur()!= this.getCouleur());
    }
	
	public boolean peutAllerDiago(Coordonnées c) {
        if(c.Valide())
            return (c.getColonne() > getCoord().getColonne() || c.getColonne() < getCoord().getColonne())
                    && (c.getLigne() == getCoord().getLigne() + Math.abs(c.getColonne()- getCoord().getColonne()));
        return false;
    }
	public boolean peutAllerHV(Coordonnées c) {
	        if(c.Valide())
	            return c.getLigne()==getCoord().getLigne() || c.getColonne() == getCoord().getColonne();
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
			return "re";
		else return "Re";
	}


}
