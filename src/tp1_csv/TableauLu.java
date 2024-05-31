package tp1_csv;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableauLu {
	private List<String> entetesLignes;
    private List<String> entetesColonnes;
    private List<List<Integer>> contenu;

    public TableauLu() {
        entetesLignes = new ArrayList<>();
        entetesColonnes = new ArrayList<>();
        contenu = new ArrayList<>();
    }
	
	
    public void addEntetesLigne(String entete) {
        entetesLignes.add(entete);
    }

    public void addEntetesColonne(String entete) {

        entetesColonnes.add(entete);
    }

    public void addContenu(List<Integer> contenus) {
        contenu.add(contenus);
    }
	
	
	public static TableauLu lireFichierCSV(String chemin) throws FileNotFoundException {
		
			TableauLu tableau = new TableauLu();
		
		
			File file = new File(chemin);
			Scanner scanFichier = new Scanner(file);
			 
			String ligneActuelle = scanFichier.nextLine();
		    String[] ligneActuelleSplit = ligneActuelle.split(",");
		    
		    int nombreDeColonne = ligneActuelleSplit.length - 1;
		    
		     
		    for (int i = 0; i < (nombreDeColonne); i++) {
		    	String valeurString = ligneActuelleSplit[i + 1].trim();
		        tableau.addEntetesColonne(valeurString);
		    }
		        
	        
		    while (scanFichier.hasNextLine()) {
	            ligneActuelle = scanFichier.nextLine();
	            ligneActuelleSplit = ligneActuelle.split(",");

	            tableau.addEntetesLigne(ligneActuelleSplit[0].trim());

	            List<Integer> contenusLigneList = new ArrayList<>();
	            for (int i = 0; i < nombreDeColonne; i++) {
	                String valeurString = ligneActuelleSplit[i + 1].trim();
	                contenusLigneList.add(Integer.parseInt(valeurString));
	            }
	            tableau.addContenu(contenusLigneList);
	        }
		    
		    scanFichier.close();
	        return tableau;

	
}
	public int getNombreLignes() {
        return entetesLignes.size();
    }

    public int getNombreCol() {
        return entetesColonnes.size();
    }

    public String getEnteteLigne(int numLigne) {
        return entetesLignes.get(numLigne);
    }

    public String getEnteteCol(int numCol) {
        return entetesColonnes.get(numCol);
    }

    public int getContenu(int numLigne, int numCol) {
        return contenu.get(numLigne).get(numCol);
    }
	
    
    public static void main(String[] args) {
    	
    	Scanner scanClavier = new Scanner(System.in);
		
		System.out.println("Entrez le nom du fichier CSV: ");
		String nomDuFichier = scanClavier.nextLine();
		
		scanClavier.close();
		
//		String cheminFichier = "fichiers/data.txt";
		String cheminFichier = "fichiers/" + nomDuFichier;
		
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


