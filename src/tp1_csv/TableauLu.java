package tp1_csv;
import java.io.*;
import java.util.Scanner;

public class TableauLu {
	private String [] entetesLignes;
	private String [] entetesColonnes;
	private int [][] contenu;
	private int nombreLignes;
	
	
	public TableauLu() {
        entetesLignes = null;
        entetesColonnes = null;
        contenu = null;
        nombreLignes = 0;
    }
	
	
	public void addEntetesLigne(String[] ligneEntetes) {
        entetesLignes = ligneEntetes;
    }

    public void addEntetesColonne(String[] colonneEntetes) {
        entetesColonnes = colonneEntetes;
    }
	
    public void addLignes(String[] contenuEnCours) {
    	if (contenu == null) {

    		contenu = new int[1][contenuEnCours.length];
    		
        } else {
        	if (contenuEnCours.length != entetesColonnes.length) {
                throw new IllegalArgumentException("La longueur de la ligne de données ne correspond pas au nombre d'entêtes de colonne.");
            }
        	
            int[][] nouveauContenu = new int[nombreLignes + 1][contenuEnCours.length];
            for (int i = 0; i < nombreLignes; i++) {
                for (int j = 0; j < contenu[i].length; j++) {
                    nouveauContenu[i][j] = contenu[i][j];
                }
            }
            contenu = nouveauContenu;
        }

        for (int i = 0; i < contenuEnCours.length; i++) {
            if (!contenuEnCours[i].trim().isEmpty()) {
                try {
                    contenu[nombreLignes][i] = Integer.parseInt(contenuEnCours[i].trim());
                } catch (NumberFormatException e) {
                        
                }
            }
        }
        nombreLignes++;
	}
	
	
	
	public static TableauLu lireFichierCSV(String chemin) throws FileNotFoundException {
		
			TableauLu tableau = new TableauLu();
		
		
			File file = new File(chemin);
			Scanner scanFichier = new Scanner(file);
			

			 String entetesLigne = scanFichier.nextLine();
			 String[] entetesColonneArray = entetesLigne.split(",");
			 tableau.addEntetesColonne(entetesColonneArray);
			 tableau.addEntetesLigne(new String[entetesColonneArray.length]);
			    
	        int nombreLignes = 0;
            
	        while (scanFichier.hasNextLine()) {
	            String line = scanFichier.nextLine();
	            String[] ligneDeCol = line.split(",");
	            
	            if (nombreLignes == 0) {
	                
	            	for (int i = 0; i < ligneDeCol.length; i++) {
	                    tableau.entetesLignes[i] = ligneDeCol[i];
	                }
	            }

	            tableau.addLignes(ligneDeCol);
	            nombreLignes++;
        }
	

    scanFichier.close();
    return tableau;
}
	
	
	public int getNombreLignes() {
        return nombreLignes;
    }
	
	public int getNombreCol() {
        if (entetesLignes != null) {
            return entetesLignes.length;
        } else {
            return 0;
        }
    }
	
	public String getEnteteLigne(int numLigne) {
        if (entetesLignes != null && numLigne >= 0 && numLigne < entetesLignes.length) {
            return entetesLignes[numLigne];
        } else {
            throw new IllegalArgumentException("Indice de ligne invalide");
        }
    }

	 public String getEnteteCol(int numCol) {
	        if (entetesColonnes != null && numCol >= 0 && numCol < entetesColonnes.length) {
	            return entetesColonnes[numCol];
	        } else {
	            throw new IllegalArgumentException("Indice de colonne invalide");
	        }
	    }
	
    public int getContenu(int numLigne, int numCol) {
        if (contenu != null && numLigne >= 0 && numLigne < contenu.length && numCol >= 0 && numCol < contenu[0].length) {
            return contenu[numLigne][numCol];
        } else {
            throw new IllegalArgumentException("Indice de ligne ou de colonne invalide");
        }
    }
    
    public static void main(String[] args) {
    	
    	Scanner scanClavier = new Scanner(System.in);
		
		
		System.out.println("Donnez le nom du fichier CSV: ");
		String nomDuFichier = scanClavier.nextLine();
		
		scanClavier.close();
		
		String cheminFichier = "fichiers/data.txt";
		
		try {
			TableauLu tableau = TableauLu.lireFichierCSV(cheminFichier);
            int nombreLignes = tableau.getNombreLignes();
            int nombreColonnes = tableau.getNombreCol();
            
        
            for (int j = 0; j < nombreColonnes; j++) {
                System.out.print(tableau.getEnteteCol(j) + "\t");
            }
            System.out.println();

           
            for (int i = 0; i < nombreLignes; i++) {
                System.out.print(tableau.getEnteteLigne(i) + "\t");
                for (int j = 0; j < nombreColonnes; j++) {
                    System.out.print(tableau.getContenu(i, j) + "\t");
                }
                System.out.println();
            }
            
		} catch (FileNotFoundException e) {
            System.out.println("Le fichier est introuvable.");
        }
    }


}


