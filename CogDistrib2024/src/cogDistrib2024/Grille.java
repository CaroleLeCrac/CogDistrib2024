package cogDistrib2024;
/*
* Classe qui représente une grille, dans chaque grille il y a un certain nombre d'éléments avec la présence
* ou non de la cible. Les cases de grille contiennent soit un élément soit la valeur null.
*/

public class Grille {
	private final static int taille=10; 
	private int[][] grille; // 2 = distracteur, 1 = cible, 0 = aucun élément
	private final int nbrDistracteurs; // 5 ou 15 ou 30
	private final int similariteElements; // 0 = peu similaires, 1 = une caractéristique en commun, 2 = très similaires
	// correspond à la similarité entre les distracteurs et la cible
	private final boolean presence; //est-ce utile sachant que la cible est tjs présente ? 
	
	public Grille(int nbrDistracteurs, int similariteElements) {
		grille= new int[taille][taille];
		this.nbrDistracteurs=nbrDistracteurs;
		this.similariteElements=similariteElements;
		presence=true;
		initialisationGrille();
	}

	private void initialisationGrille() {
		//initialisation de la grille
		int[][] indiceCible=indiceCible();
		int[][] indicesDistracteurs=indicesDistracteurs(indiceCible);
		for (int i=0; i<taille; i++) {
			for (int j=0; j<taille; j++) {
				if (i==indiceCible[0][0] && j==indiceCible[0][1])
					grille[i][j]=1; //cible
				else if(contient(indicesDistracteurs,i,j))
					grille[i][j]=2; //distracteur
			}
		}
	}

	private int[][] indicesDistracteurs(int[][] indiceCible){
		//création aléatoire des indices des distracteurs
		int[][] indicesDistracteurs = new int[nbrDistracteurs][2];
		for (int i=0; i<nbrDistracteurs; i++) {
			int indiceLigne, indiceColonne;
			do { 
				indiceLigne=(int)(Math.random()*taille);
				indiceColonne=(int)(Math.random()*taille);
			}while (contient(indicesDistracteurs,indiceLigne, indiceColonne) || contient(indiceCible,indiceLigne,indiceColonne));
			indicesDistracteurs[i][0]=indiceLigne;
			indicesDistracteurs[i][1]=indiceColonne;
		}
		return indicesDistracteurs;
	}

	private int[][] indiceCible() {	
		//création aléatoire des indices de la cible
		int[][] indiceCible = new int[1][2];
		indiceCible[0][0]=(int)(Math.random()*taille);
		indiceCible[0][1]=(int)(Math.random()*taille);
		return indiceCible;
	}

	private boolean contient(int[][] tab, int ligne, int colonne) {
		//return true si tab contient valeur
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
			res+="ont une caractéristique en commun avec ";
		if (similariteElements==2)
			res+="sont très similaires à ";
		res+="la cible.\nVoici la grille :\n";
		for (int i=0; i<taille; i++) {
			for (int j=0; j<taille; j++) {
				res+=grille[i][j]+" ";
			}
			res+="\n";
		}
		return res;
	}
	
	public static void main(String[] args) {
		Grille grille = new Grille(30,2);
		System.out.println(grille);
	}
}
