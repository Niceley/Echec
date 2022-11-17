package Géométrie;

public class Coordonnées {
	private int colonne;
	private int ligne;
	
	public Coordonnées(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	public int getColonne() {
		return colonne;
	}
	
	public int getLigne() {
		return ligne;
	}
	
	public boolean Valide() {
		return colonne>0 && colonne<=8 &&
				ligne>0 && ligne<=8;
	}
	
	public String toString() {
		return "(" + colonne + ", " + ligne + ")";
	}
	

}
