package pièces;

import java.util.ArrayList;

import Géométrie.Coordonnées;
import joueur.Couleur;
import joueur.Joueur;
import plateau.IPièce;
import plateau.Plateau;

public class Roi extends Pièce{
	
	public Roi( Coordonnées c, Plateau p, Joueur j) {
		super( c, p, j);
		// on ajoute le roi dans la liste des rois du plateau
		this.getPlateau().ajouterRoi(this);
	}


	public void déplacer (Coordonnées nouvelle){
		try {
			// si le roi sera en échec là où il veut aller, il ne pourra pas y aller
			if(getPlateau().getRoi(getCouleur()).seraEnEchec(nouvelle)) {
				throw new RoiEnEchecsException();
			}
			super.déplacer(nouvelle);	
		}
		catch(RoiEnEchecsException e) {
			getPlateau().tourNonValidé();
			System.out.println("Tu ne peux pas aller ici car ton roi sera en échecs");
		}
	}


	/** Vérifie que la pièce peut aller aux coordonnées c
	 * @param c les coordonnées
	 * @return true s'il peut , false sinon
	 */
	@Override
	public boolean peutAllerà(Coordonnées c) {
		return peutAllerColonne(c)&& peutAllerLigne(c)
				&& (this.getPlateau().getPièce(c).getCouleur()!= this.getCouleur());
	}
	
	/** Vérifie que la pièce peut aller à la colonne des coordonnées c
	 * @param c les coordonnées
	 * @return true s'il peut , false sinon
	 */
	public boolean peutAllerColonne(Coordonnées c) {
		if (c.Valide())
			return c.getColonne()>=getCoord().getColonne()-1 &&
					c.getColonne()<=getCoord().getColonne()+1;
		return false;
	}
	
	/** Vérifie que la pièce peut aller à la ligne des coordonnées c
	 * @param c les coordonnées
	 * @return true s'il peut , false sinon
	 */
	public boolean peutAllerLigne(Coordonnées c) {
		if (c.Valide())
			return c.getLigne()>=getCoord().getLigne()-1 &&
					c.getLigne()<=getCoord().getLigne()+1;
		return false;
	}
	
	/** Renvoie une List de toutes les coordonnées où le roi peut aller
	 * @return coordPossible la liste des coordonnées valides
	 */
	public ArrayList<Coordonnées> getCoordPossibles(){
		ArrayList<Coordonnées> coordPossible = new ArrayList<Coordonnées>();
		for(int i=1; i<=8; i++) {
			for(int j=1; j<=8; j++) {
				Coordonnées c = new Coordonnées(i,j);
				if(peutAllerà(c)) {
					coordPossible.add(c);
				}
			}
		}
		return coordPossible;
	}

	/** retourne le symbole de la pièce
	 * @return le symbole de la pièce
	 */
	@Override
	public String symbole() {
		if(this == null)
			return " ";
		else if (this.getCouleur() == Couleur.NOIR)
			return "r";
		else return "R";
	}
	
	/** Vérifie si un roi est en echec et mat
	 * @return true s'il l'est , false sinon
	 */
	public boolean echecEtMat() {
		if(estEnEchec()) {
			for(Coordonnées c : this.getCoordPossibles()) {
				if(!seraEnEchec(c))
					return false;
			}
			getPlateau().setPartieFinie();
			return true;
		}
		return false;
		
	}
	
	/** Vérifie si un roi sera en Echec aux nouvelles coordonnées donnée
	 * @param nouvelle les nouvelles coordonnées
	 * @return true s'il le sera , false sinon
	 */
	public boolean seraEnEchec(Coordonnées nouvelle) {
		for(IPièce i : getPlateau().getPiècesRestantes()) 
			if(i.peutAllerà(nouvelle)&& i.getCouleur()!= this.getCouleur()) 
				return true;
		return false;
		
	}
	
	/** Vérifie si un roi est en echec
	 * @return true s'il l'est , false sinon
	 */
	public boolean estEnEchec() {
		for(IPièce i : getPlateau().getPiècesRestantes()) 
			if(i.peutAllerà(getCoord())) 
				return true;
		return false;
	}
	

}
