package pi�ces;

import G�om�trie.Coordonn�es;
import joueur.Couleur;
import joueur.Joueur;
import plateau.IPi�ce;
import plateau.Plateau;

public abstract class Pi�ce implements IPi�ce {
	private Coordonn�es coord;
	private Couleur couleur;
	private Plateau p;
	private boolean mang� = false;
	
	public Pi�ce() {
		
	}
	
	public Coordonn�es getCoord() {
		return this.coord;
	}
	public boolean getMang�() {
		return this.mang�;
	}
	public Plateau getPlateau() {
		return p;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public Pi�ce (Coordonn�es c, Plateau p, Joueur j) {
		this.couleur = j.getCouleur();
		this.coord = c;
		this.p = p;
		//ajoute cette pi�ce � la liste des pi�ce du joueur j
		j.getListePi�ce().add(this);
		//remplace la pi�ce vide dans le tableau par cette pi�ce
		p.getPi�cesPlateau()[coord.getColonne()-1][8-coord.getLigne()] = this;
		//ajoute cette pi�ce dans la liste des pi�ces du plateau
		this.p.ajouterPi�ce(this);
	}
	
	/** Mange une pi�ce  donn�e en param�tre
	 * @param pi�ce la pi�ce mang�e
	 */
	public void manger(IPi�ce pi�ce) {
		pi�ce.estMang�();
		pi�ce = new Pi�ceVide();
	}
	
	/** Une pi�ce est mang�e
	 */
	public void estMang�() {
		this.mang� = true ;
		p.getPi�cesRestantes().remove(this);
	}
	
	/** Une pi�ce se d�place aux nouvelles coordonn�es, laissant une pi�ce vide � son ancien emplacement
	 * et actualise ses nouvelles coordonn�es
	 * @param nouvelle les nouvelles coordonn�es
	 */
	public void seD�place(Coordonn�es nouvelle) {
		p.getPi�cesPlateau()[coord.getColonne()-1][8-coord.getLigne()]= new Pi�ceVide();
		p.getPi�cesPlateau()[nouvelle.getColonne()-1][8-nouvelle.getLigne()] = this;
		this.coord = nouvelle;
	}

	/** Une pi�ce se d�place aux nouvelles coordonn�es si elle le peut, et mange la pi�ce ennemie si elle se trouve aux nouvelles coordonn�es
	 * si elle ne peut pas y aller ,des exception seront jet�es et attrap�es, et des messages s'afficheront, et le tour ne sera pas valid�
	 * @param nouvelle les nouvelles coordonn�es
	 */
	public void d�placer( Coordonn�es nouvelle) {
		try {
			if(peutAller�(nouvelle)&& mang�==false){
				if(p.getPi�cesRestantes().contains(p.getPi�ce(nouvelle))) {
					manger(p.getPi�ce(nouvelle));
				}
				seD�place(nouvelle);
				p.tourValid�();
			}
			else if(mang�== true) {
				throw new Pi�ceNonValideException();
			}
			else if(!peutAller�(nouvelle))
				throw new Pi�ceNonD�placableException();		
		}
		catch(Pi�ceNonValideException e) {
			p.tourNonValid�();
			System.out.println("Cette pi�ce n'existe plus");
		}

		catch(Pi�ceNonD�placableException e) {
			p.tourNonValid�();
			System.out.println("Cette pi�ce ne peut pas aller ici");
		}			
	}
	
	public abstract boolean peutAller�(Coordonn�es c);

}
