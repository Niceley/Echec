package pi�ces;

import G�om�trie.Coordonn�es;

public class Pi�ceVide extends Pi�ce{
	public Pi�ceVide() {
		
	}
	
	public String symbole() {
		return " ";
	}

	@Override
	public void d�placer( Coordonn�es nouvelle) {
	}

	@Override
	public boolean peutAller�(Coordonn�es c) {
		return false;
	}

}
