package plateau;

import Géométrie.Coordonnées;
import joueur.Couleur;

public interface IPièce {
	
	
	public abstract void déplacer( Coordonnées nouvelle);
	
	public abstract boolean peutAllerà(Coordonnées c);
	
	public  String symbole();

	public abstract void estMangé();
	
	public Couleur getCouleur();
	
	public Coordonnées getCoord();


}
