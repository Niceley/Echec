package pièces;

import Géométrie.Coordonnées;

public class PièceVide extends Pièce{
	public PièceVide() {
		
	}
	
	public String symbole() {
		return " ";
	}

	@Override
	public void déplacer( Coordonnées nouvelle) {
	}

	@Override
	public boolean peutAllerà(Coordonnées c) {
		return false;
	}

}
