package cogDistrib2024;
/*
* Classe qui représente une grille, dans chaque grille il y a un certain nombre d'éléments avec la présence
* ou non de la cible. Les cases de grille contiennent soit un élément soit la valeur null.
*/

public class Grille {
	private final static int taille=20; 
	private Element[][] grille;
	private int nbrElement; 
	private boolean presence; 
	
	public Grille() {
		grille= new Element[taille][taille];
		nbrElement=11; // 10 distracteurs et 1 cible
		presence=true;
		initialisationGrille(); // ne fonctionne pas encore
	}

	private void initialisationGrille() {
		int indiceCible = (int)(Math.random()*20);
		int[] indicesDistracteurs= new int[10];
		for (int i=0; i<10; i++) {
			int indice;
			do { 
				indice=(int)(Math.random()*10);
			}while (contient(indicesDistracteurs,indice) || indice==indiceCible);
			indicesDistracteurs[i]=indice;
		}
		for (int i=0; i<taille; i++) {
			for (int j=0; j<taille; j++) {
				if (i==indiceCible)
					grille[i][j]= new Element(1,0); // comment on choisit similarité ?
				// ajouter les distracteurs
			}
		}
	}

	private boolean contient(int[] tab, int valeur) {
		boolean res=false;
		for(int i=0; i<tab.length;i++) {
			if (tab[i]==valeur)
				res=true;
			if(res)
				break;
		}
		return res;
	}
}
