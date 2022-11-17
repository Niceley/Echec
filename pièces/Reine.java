package pi�ces;

import G�om�trie.Coordonn�es;
import joueur.Couleur;
import joueur.Joueur;
import plateau.Plateau;

public class Reine extends Pi�ce {
	
	public Reine( Coordonn�es c, Plateau p, Joueur j) {
		super( c, p, j);
	}

	public void d�placer (Coordonn�es nouvelle){
		super.d�placer(nouvelle);
	}
	
	/** V�rifie que la pi�ce peut aller � la ligne des coordonn�es c
	 * @param c les coordonn�es
	 * @return true s'il peut , false sinon
	 */
	@Override
	public boolean peutAller�(Coordonn�es c) {
		return ( peutAllerDiago(c) || peutAllerHV(c))
                && (this.getPlateau().getPi�ce(c).getCouleur()!= this.getCouleur());
    }
	
	public boolean peutAllerDiago(Coordonn�es c) {
        if(c.Valide())
            return (c.getColonne() > getCoord().getColonne() || c.getColonne() < getCoord().getColonne())
                    && (c.getLigne() == getCoord().getLigne() + Math.abs(c.getColonne()- getCoord().getColonne()));
        return false;
    }
	public boolean peutAllerHV(Coordonn�es c) {
	        if(c.Valide())
	            return c.getLigne()==getCoord().getLigne() || c.getColonne() == getCoord().getColonne();
	        return false;
	                
	    }

	/** retourne le symbole de la pi�ce
	 * @return le symbole de la pi�ce
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
