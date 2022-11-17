package pi�ces;

import G�om�trie.Coordonn�es;
import joueur.Couleur;
import joueur.Joueur;
import plateau.Plateau;

public class Tour extends Pi�ce {
	
	public Tour( Coordonn�es c, Plateau p, Joueur j) {
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
		return (peutAllerColonne(c)|| peutAllerLigne(c)) && 
				(this.getPlateau().getPi�ce(c).getCouleur()!= this.getCouleur());
	}
	
	/** V�rifie que la pi�ce peut aller � la colonne des coordonn�es c
	 * @param c les coordonn�es
	 * @return true s'il peut , false sinon
	 */
	public boolean peutAllerColonne(Coordonn�es c) {
		if (c.Valide())
			return c.getLigne()==getCoord().getLigne();
		System.out.println("colonne");
			return false;
	}
	
	/** V�rifie que la pi�ce peut aller � la ligne des coordonn�es c
	 * @param c les coordonn�es
	 * @return true s'il peut , false sinon
	 */
	public boolean peutAllerLigne(Coordonn�es c) {
		if (c.Valide())
			return c.getColonne() == getCoord().getColonne();
		System.out.println("ligne");
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
			return "t";
		else return "T";
	}


}
