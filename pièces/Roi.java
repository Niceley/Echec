package pi�ces;

import java.util.ArrayList;

import G�om�trie.Coordonn�es;
import joueur.Couleur;
import joueur.Joueur;
import plateau.IPi�ce;
import plateau.Plateau;

public class Roi extends Pi�ce{
	
	public Roi( Coordonn�es c, Plateau p, Joueur j) {
		super( c, p, j);
		// on ajoute le roi dans la liste des rois du plateau
		this.getPlateau().ajouterRoi(this);
	}


	public void d�placer (Coordonn�es nouvelle){
		try {
			// si le roi sera en �chec l� o� il veut aller, il ne pourra pas y aller
			if(getPlateau().getRoi(getCouleur()).seraEnEchec(nouvelle)) {
				throw new RoiEnEchecsException();
			}
			super.d�placer(nouvelle);	
		}
		catch(RoiEnEchecsException e) {
			getPlateau().tourNonValid�();
			System.out.println("Tu ne peux pas aller ici car ton roi sera en �checs");
		}
	}


	/** V�rifie que la pi�ce peut aller aux coordonn�es c
	 * @param c les coordonn�es
	 * @return true s'il peut , false sinon
	 */
	@Override
	public boolean peutAller�(Coordonn�es c) {
		return peutAllerColonne(c)&& peutAllerLigne(c)
				&& (this.getPlateau().getPi�ce(c).getCouleur()!= this.getCouleur());
	}
	
	/** V�rifie que la pi�ce peut aller � la colonne des coordonn�es c
	 * @param c les coordonn�es
	 * @return true s'il peut , false sinon
	 */
	public boolean peutAllerColonne(Coordonn�es c) {
		if (c.Valide())
			return c.getColonne()>=getCoord().getColonne()-1 &&
					c.getColonne()<=getCoord().getColonne()+1;
		return false;
	}
	
	/** V�rifie que la pi�ce peut aller � la ligne des coordonn�es c
	 * @param c les coordonn�es
	 * @return true s'il peut , false sinon
	 */
	public boolean peutAllerLigne(Coordonn�es c) {
		if (c.Valide())
			return c.getLigne()>=getCoord().getLigne()-1 &&
					c.getLigne()<=getCoord().getLigne()+1;
		return false;
	}
	
	/** Renvoie une List de toutes les coordonn�es o� le roi peut aller
	 * @return coordPossible la liste des coordonn�es valides
	 */
	public ArrayList<Coordonn�es> getCoordPossibles(){
		ArrayList<Coordonn�es> coordPossible = new ArrayList<Coordonn�es>();
		for(int i=1; i<=8; i++) {
			for(int j=1; j<=8; j++) {
				Coordonn�es c = new Coordonn�es(i,j);
				if(peutAller�(c)) {
					coordPossible.add(c);
				}
			}
		}
		return coordPossible;
	}

	/** retourne le symbole de la pi�ce
	 * @return le symbole de la pi�ce
	 */
	@Override
	public String symbole() {
		if(this == null)
			return " ";
		else if (this.getCouleur() == Couleur.NOIR)
			return "r";
		else return "R";
	}
	
	/** V�rifie si un roi est en echec et mat
	 * @return true s'il l'est , false sinon
	 */
	public boolean echecEtMat() {
		if(estEnEchec()) {
			for(Coordonn�es c : this.getCoordPossibles()) {
				if(!seraEnEchec(c))
					return false;
			}
			getPlateau().setPartieFinie();
			return true;
		}
		return false;
		
	}
	
	/** V�rifie si un roi sera en Echec aux nouvelles coordonn�es donn�e
	 * @param nouvelle les nouvelles coordonn�es
	 * @return true s'il le sera , false sinon
	 */
	public boolean seraEnEchec(Coordonn�es nouvelle) {
		for(IPi�ce i : getPlateau().getPi�cesRestantes()) 
			if(i.peutAller�(nouvelle)&& i.getCouleur()!= this.getCouleur()) 
				return true;
		return false;
		
	}
	
	/** V�rifie si un roi est en echec
	 * @return true s'il l'est , false sinon
	 */
	public boolean estEnEchec() {
		for(IPi�ce i : getPlateau().getPi�cesRestantes()) 
			if(i.peutAller�(getCoord())) 
				return true;
		return false;
	}
	

}
