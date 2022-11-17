package pièces;

import Géométrie.Coordonnées;
import joueur.Couleur;
import joueur.Joueur;
import plateau.Plateau;

public class Cavalier extends Pièce {
	
	public Cavalier( Coordonnées c, Plateau p, Joueur j) {
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
        return( peutAller(c) || peutAller1(c)) 
            && (this.getPlateau().getPièce(c).getCouleur()!= this.getCouleur());
    }
    
    public boolean peutAller(Coordonnées c) {
        if(c.Valide())
            return (c.getColonne()== getCoord().getColonne()+2 || c.getColonne()== getCoord().getColonne()-2)
                    && (c.getLigne()== getCoord().getLigne()+1 || c.getLigne()== getCoord().getLigne()-1);
        return false;  
    }
    
    public boolean peutAller1(Coordonnées c) {
        if(c.Valide())
            return (c.getColonne()== getCoord().getColonne()+1 || c.getColonne()== getCoord().getColonne()-1)
                    && (c.getLigne()== getCoord().getLigne()+2 ||c.getLigne()== getCoord().getLigne()-2);
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
			return "c";
		else return "C";
	}


}
