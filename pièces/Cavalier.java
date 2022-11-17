package pi�ces;

import G�om�trie.Coordonn�es;
import joueur.Couleur;
import joueur.Joueur;
import plateau.Plateau;

public class Cavalier extends Pi�ce {
	
	public Cavalier( Coordonn�es c, Plateau p, Joueur j) {
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
        return( peutAller(c) || peutAller1(c)) 
            && (this.getPlateau().getPi�ce(c).getCouleur()!= this.getCouleur());
    }
    
    public boolean peutAller(Coordonn�es c) {
        if(c.Valide())
            return (c.getColonne()== getCoord().getColonne()+2 || c.getColonne()== getCoord().getColonne()-2)
                    && (c.getLigne()== getCoord().getLigne()+1 || c.getLigne()== getCoord().getLigne()-1);
        return false;  
    }
    
    public boolean peutAller1(Coordonn�es c) {
        if(c.Valide())
            return (c.getColonne()== getCoord().getColonne()+1 || c.getColonne()== getCoord().getColonne()-1)
                    && (c.getLigne()== getCoord().getLigne()+2 ||c.getLigne()== getCoord().getLigne()-2);
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
			return "c";
		else return "C";
	}


}
