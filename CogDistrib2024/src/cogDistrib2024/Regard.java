package cogDistrib2024;

public class Regard {
	public final static double paramTempsDeReconnaissance = 1; // à mettre en milliseconde quand on aura paramétrer avec les passation
	
	public static double regardeGrille(int nbrDistracteurs, int similariteElements) {
		double tempsDeRecherche=0;
		Grille grille = new Grille(nbrDistracteurs, similariteElements);
		if (grille.getSimilarite()==0) {
			popOut(grille);
			return paramTempsDeReconnaissance;
		}
		else {
			for (int i=0; i!=grille.getTaille(); i++) {
				for (int j=0; j!=grille.getTaille();j++) {
					if (grille.getContenuCase(i, j)==2) {
						tempsDeRecherche+=paramTempsDeReconnaissance;
					}
					else if (grille.getContenuCase(i, j)==1) {
						tempsDeRecherche+=paramTempsDeReconnaissance;
						return tempsDeRecherche;
					}
				}
			}
		}
		return tempsDeRecherche;
	}
	
	public static int[][] popOut(Grille grille){
		return grille.getCible();
	}
	
	public static double moyenneRegardGrille(int nbrDeRepetition,int nbrDistracteurs, int similariteElements) {
		double tempsMoy = 0;
		for (int i=0; i!=nbrDeRepetition; i++) {
			tempsMoy+= regardeGrille(nbrDistracteurs, similariteElements);
		}
		System.out.println(tempsMoy/nbrDeRepetition);
		return tempsMoy/nbrDeRepetition; 
	}
	
	public static void main(String[] args) {
		moyenneRegardGrille(25, 15, 1);
	}
}
