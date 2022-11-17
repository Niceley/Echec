package joueur;

import java.util.ArrayList;

import Géométrie.Coordonnées;
import pièces.PièceVide;
import plateau.IPièce;
import plateau.Plateau;

public class Joueur {
	private Couleur couleur;
	//Liste des pièces que le joueur possède
	private ArrayList<IPièce> pièces = new ArrayList<IPièce>();
	
	public Joueur (Couleur c, Plateau p) {
		this.couleur = c;
		
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public ArrayList<IPièce> getListePièce() {
		return pièces;
	}
	
	/**
	 * Fait jouer la pièce aux coordonnées d'origines données, et la déplace aux coordonnées d'arrivée donnée
	 * @param origine les coordonnées d'origine
	 * @param arrivée les coordonnées d'arrivée
	 */
	public void Jouer( Coordonnées origine, Coordonnées arrivée){
		getPièce(origine).déplacer(arrivée);
	}
	
	/**
	 * retourne une pièce parmis les pièces du joueur aux coordonnées données
	 * @param c les coordonnées
	 * @return la pièce s'il le trouve, une pièce vide sinon
	 */
	public IPièce getPièce(Coordonnées c) {
		for(IPièce i : pièces) 
			if(c.getColonne() == i.getCoord().getColonne()
					&& c.getLigne() == i.getCoord().getLigne())
				return i;			
		return new PièceVide();
	}


}
