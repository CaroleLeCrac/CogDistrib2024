package cogDistrib2024;
/*
 * La classe qui représente les éléments à voir, soit des distracteur soit la cible
 * Attribut : int etat (1 ou 2) 1la cible 2les distracteur
 * 			  int similarité : représente la similarité entre les distracteur et la cible. + elle est élevé plus ils sont similaire
 * 				à voir si similarité c'est pas plutôt un attribut à mettre pour la grille. 
 */
public class Element {
	private int etat;
	private int similarite; 
	
	public Element(int etat, int similarite) {
		this.etat=etat;
		this.similarite=similarite;
	}
	
	public int getEtat(){
		return etat;
	}
	
	public int getSimilarite() {
		return similarite; 
	}
}
