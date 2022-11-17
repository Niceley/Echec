package plateau;

import G�om�trie.Coordonn�es;
import joueur.Couleur;

public interface IPi�ce {
	
	
	public abstract void d�placer( Coordonn�es nouvelle);
	
	public abstract boolean peutAller�(Coordonn�es c);
	
	public  String symbole();

	public abstract void estMang�();
	
	public Couleur getCouleur();
	
	public Coordonn�es getCoord();


}
