package joueur;

import java.util.ArrayList;

import G�om�trie.Coordonn�es;
import pi�ces.Pi�ceVide;
import plateau.IPi�ce;
import plateau.Plateau;

public class Joueur {
	private Couleur couleur;
	//Liste des pi�ces que le joueur poss�de
	private ArrayList<IPi�ce> pi�ces = new ArrayList<IPi�ce>();
	
	public Joueur (Couleur c, Plateau p) {
		this.couleur = c;
		
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public ArrayList<IPi�ce> getListePi�ce() {
		return pi�ces;
	}
	
	/**
	 * Fait jouer la pi�ce aux coordonn�es d'origines donn�es, et la d�place aux coordonn�es d'arriv�e donn�e
	 * @param origine les coordonn�es d'origine
	 * @param arriv�e les coordonn�es d'arriv�e
	 */
	public void Jouer( Coordonn�es origine, Coordonn�es arriv�e){
		getPi�ce(origine).d�placer(arriv�e);
	}
	
	/**
	 * retourne une pi�ce parmis les pi�ces du joueur aux coordonn�es donn�es
	 * @param c les coordonn�es
	 * @return la pi�ce s'il le trouve, une pi�ce vide sinon
	 */
	public IPi�ce getPi�ce(Coordonn�es c) {
		for(IPi�ce i : pi�ces) 
			if(c.getColonne() == i.getCoord().getColonne()
					&& c.getLigne() == i.getCoord().getLigne())
				return i;			
		return new Pi�ceVide();
	}


}
