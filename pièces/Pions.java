package pièces;

import Géométrie.Coordonnées;
import joueur.Couleur;
import joueur.Joueur;
import plateau.Plateau;

public class Pions extends Pièce {
	
	private boolean premierTour=true;
	
	public Pions( Coordonnées c, Plateau p, Joueur j) {
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
        return (peutAvancer(c)|| peutManger(c))
                && (this.getPlateau().getPièce(c).getCouleur()!= this.getCouleur());
    }
    
	public boolean peutAvancer(Coordonnées c) {
        if(c.Valide()) {
            if(this.getCouleur()== Couleur.BLANC) {
                if(premierTour) {
                    premierTour = false;
                    return c.getLigne() == getCoord().getLigne()+2 || c.getLigne() == getCoord().getLigne() +1;
                    
                }
                return c.getLigne() == getCoord().getLigne() +1;
            }
            else {
                if(premierTour) {
                    premierTour = false;
                    return c.getLigne() == getCoord().getLigne()-2 || c.getLigne() == getCoord().getLigne() -1;
                    
                }
                return c.getLigne() == getCoord().getLigne()-1;
            }
        }
        return false;
        
    }
        
    public boolean peutManger(Coordonnées c) {
        if(c.Valide() && getPlateau().getPiècesRestantes().contains(getPlateau().getPièce(c)))
            return (c.getColonne() == getCoord().getColonne()-1 ||c.getColonne() == getCoord().getColonne()+1)
                    && c.getLigne() == getCoord().getLigne()+1;
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
			return "p";
		else return "P";
	}


}
