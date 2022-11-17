package pi�ces;

import G�om�trie.Coordonn�es;
import joueur.Couleur;
import joueur.Joueur;
import plateau.Plateau;

public class Pions extends Pi�ce {
	
	private boolean premierTour=true;
	
	public Pions( Coordonn�es c, Plateau p, Joueur j) {
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
        return (peutAvancer(c)|| peutManger(c))
                && (this.getPlateau().getPi�ce(c).getCouleur()!= this.getCouleur());
    }
    
	public boolean peutAvancer(Coordonn�es c) {
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
        
    public boolean peutManger(Coordonn�es c) {
        if(c.Valide() && getPlateau().getPi�cesRestantes().contains(getPlateau().getPi�ce(c)))
            return (c.getColonne() == getCoord().getColonne()-1 ||c.getColonne() == getCoord().getColonne()+1)
                    && c.getLigne() == getCoord().getLigne()+1;
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
			return "p";
		else return "P";
	}


}
