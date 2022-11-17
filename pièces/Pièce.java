package pièces;

import Géométrie.Coordonnées;
import joueur.Couleur;
import joueur.Joueur;
import plateau.IPièce;
import plateau.Plateau;

public abstract class Pièce implements IPièce {
	private Coordonnées coord;
	private Couleur couleur;
	private Plateau p;
	private boolean mangé = false;
	
	public Pièce() {
		
	}
	
	public Coordonnées getCoord() {
		return this.coord;
	}
	public boolean getMangé() {
		return this.mangé;
	}
	public Plateau getPlateau() {
		return p;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public Pièce (Coordonnées c, Plateau p, Joueur j) {
		this.couleur = j.getCouleur();
		this.coord = c;
		this.p = p;
		//ajoute cette pièce à la liste des pièce du joueur j
		j.getListePièce().add(this);
		//remplace la pièce vide dans le tableau par cette pièce
		p.getPiècesPlateau()[coord.getColonne()-1][8-coord.getLigne()] = this;
		//ajoute cette pièce dans la liste des pièces du plateau
		this.p.ajouterPièce(this);
	}
	
	/** Mange une pièce  donnée en paramètre
	 * @param pièce la pièce mangée
	 */
	public void manger(IPièce pièce) {
		pièce.estMangé();
		pièce = new PièceVide();
	}
	
	/** Une pièce est mangée
	 */
	public void estMangé() {
		this.mangé = true ;
		p.getPiècesRestantes().remove(this);
	}
	
	/** Une pièce se déplace aux nouvelles coordonnées, laissant une pièce vide à son ancien emplacement
	 * et actualise ses nouvelles coordonnées
	 * @param nouvelle les nouvelles coordonnées
	 */
	public void seDéplace(Coordonnées nouvelle) {
		p.getPiècesPlateau()[coord.getColonne()-1][8-coord.getLigne()]= new PièceVide();
		p.getPiècesPlateau()[nouvelle.getColonne()-1][8-nouvelle.getLigne()] = this;
		this.coord = nouvelle;
	}

	/** Une pièce se déplace aux nouvelles coordonnées si elle le peut, et mange la pièce ennemie si elle se trouve aux nouvelles coordonnées
	 * si elle ne peut pas y aller ,des exception seront jetées et attrapées, et des messages s'afficheront, et le tour ne sera pas validé
	 * @param nouvelle les nouvelles coordonnées
	 */
	public void déplacer( Coordonnées nouvelle) {
		try {
			if(peutAllerà(nouvelle)&& mangé==false){
				if(p.getPiècesRestantes().contains(p.getPièce(nouvelle))) {
					manger(p.getPièce(nouvelle));
				}
				seDéplace(nouvelle);
				p.tourValidé();
			}
			else if(mangé== true) {
				throw new PièceNonValideException();
			}
			else if(!peutAllerà(nouvelle))
				throw new PièceNonDéplacableException();		
		}
		catch(PièceNonValideException e) {
			p.tourNonValidé();
			System.out.println("Cette pièce n'existe plus");
		}

		catch(PièceNonDéplacableException e) {
			p.tourNonValidé();
			System.out.println("Cette pièce ne peut pas aller ici");
		}			
	}
	
	public abstract boolean peutAllerà(Coordonnées c);

}
