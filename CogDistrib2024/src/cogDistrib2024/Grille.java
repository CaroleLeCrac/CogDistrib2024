package cogDistrib2024;
/*
* Classe qui représente une grille, dans chaque grille il y a un certain nombre d'éléments avec la présence
* ou non de la cible. Les cases de grille contiennent soit un élément soit la valeur null.
*/

public class Grille {
	private final static int TAILLE = 10; 
	private final int[][] grille; // 2 = distracteur, 1 = cible, 0 = aucun élément
	private final int nbrDistracteurs; // 5 ou 15 ou 30
	private final int similariteElements; // 0 = peu similaires, 1 = très similaires
	// correspond à la similarité entre les distracteurs et la cible
	private final int[][] indiceCible;
	private final int[][] indicesDistracteurs;
	
	public Grille(int nbrDistracteurs, int similariteElements) {
		grille= new int[TAILLE][TAILLE];
		this.nbrDistracteurs=nbrDistracteurs;
		this.similariteElements=similariteElements;
		indiceCible = indiceCible();
		indicesDistracteurs = indicesDistracteurs(indiceCible);
		initialisationGrille(indiceCible, indicesDistracteurs);
	}

	public int getTaille() {
		return TAILLE;
	}

	public int[][] getGrille() {
		return grille;
	}

	public int getNbDistracteurs() {
		return nbrDistracteurs;
	}

	public int getSimilarite() {
		return similariteElements;
	}
	
	public int getContenuCase(int ligne, int colonne) {
		return grille[ligne][colonne];
	}
	
	public int[][] getCible(){
		return indiceCible;
	}

	public boolean estCible(int[][] indice) {
		return (indice == indiceCible);
	}

	private void initialisationGrille(int[][] indiceCible, int[][] indicesDistracteurs) {
		//initialisation de la grille
		for (int i=0; i<TAILLE; i++) {
			for (int j=0; j<TAILLE; j++) {
				if (i==indiceCible[0][0] && j==indiceCible[0][1])
					grille[i][j]=1; //cible
				else if(contient(indicesDistracteurs,i,j))
					grille[i][j]=2; //distracteur
			}
		}
	}

	private int[][] indicesDistracteurs(int[][] indiceCible){
		//création aléatoire des indices des distracteurs
		int[][] indicesDistracteursTemp = new int[nbrDistracteurs][2];
		for (int i=0; i<nbrDistracteurs; i++) {
			int indiceLigne, indiceColonne;
			do { 
				indiceLigne=(int)(Math.random()*TAILLE);
				indiceColonne=(int)(Math.random()*TAILLE);
			}while (contient(indicesDistracteursTemp,indiceLigne, indiceColonne) || contient(indiceCible,indiceLigne,indiceColonne));
			indicesDistracteursTemp[i][0]=indiceLigne;
			indicesDistracteursTemp[i][1]=indiceColonne;
		}
		return indicesDistracteursTemp;
	}

	private int[][] indiceCible() {	
		//création aléatoire des indices de la cible
		int[][] indiceCibleTemp = new int[1][2];
		indiceCibleTemp[0][0]=(int)(Math.random()*TAILLE);
		indiceCibleTemp[0][1]=(int)(Math.random()*TAILLE);
		return indiceCibleTemp;
	}

	private boolean contient(int[][] tab, int ligne, int colonne) {
		//return true si tab contient les couples de valeurs ligne et colonne
		boolean res=false;
        for (int[] tab1 : tab) {
                if (tab1[0] == ligne && tab1[1] == colonne) {
                    res=true;
                }
                if(res)
                    break;
        }
		return res;
	}

	public String toString() {
		String res = "Nombre de distracteurs : "+nbrDistracteurs+"\n"
			+"Les distracteurs ";
		if(similariteElements==0)
			res+="sont peu similaires à ";
		if (similariteElements==1)
			res+="sont très similaires à ";
		res+="la cible.\nVoici la grille :\n";
		for (int i=0; i<TAILLE; i++) {
			for (int j=0; j<TAILLE; j++) {
				res+=grille[i][j]+" ";
			}
			res+="\n";
		}
		return res;
	}
	
	public static void main(String[] args) {
		Grille grille = new Grille(10,0);
		System.out.println(grille);
	}
}
