package pi�ces;

public interface IPi�ce {
	
	
	public abstract void d�placer( Coordonn�es nouvelle)throws Pi�ceNonD�placableException;
	
	public abstract boolean peutAller�(Coordonn�es c);
	
	public  String symbole();

}
