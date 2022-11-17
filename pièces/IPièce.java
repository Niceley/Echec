package pièces;

public interface IPièce {
	
	
	public abstract void déplacer( Coordonnées nouvelle)throws PièceNonDéplacableException;
	
	public abstract boolean peutAllerà(Coordonnées c);
	
	public  String symbole();

}
