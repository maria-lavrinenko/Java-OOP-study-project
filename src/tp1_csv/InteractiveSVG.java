package tp1_csv;

import java.io.*;
import java.util.*;
public class InteractiveSVG{
	
	private Scanner scan;
	private ArrayList<Forme> formes = new ArrayList<>();
	
	public int[] saisirCoordonnees(){
		
		int[] res = new int[2];
		System.out.print("Entrez la coordonnée X: ");
		res[0] = scan.nextInt();
		System.out.print("Entrez la coordonnée Y: ");
		res[1] = scan.nextInt();
		scan.nextLine();
		return res;
	}
	public String saisirCouleur(){
		String res;
		System.out.print("Entrez la couleur que vous voulez: ");
		res = scan.nextLine();
		return res;
	}
	public int menu(){
		int res;
		System.out.println("1− Ajouter un cercle");
		System.out.println("2− Ajouter un rectangle");
		System.out.println("3− Créer l’image");
		do{
			System.out.print("Votre choix: ");
			res = scan.nextInt();
			scan.nextLine();
			if (res<1 || res>3)
	
		System.out.println("Choix incorrect: " + res);
		} while(res<1 || res>3);
		return res;
	}
	
	public void creeImage(){
		scan = new Scanner(System.in);
		
		int choix;
		do{
			choix = menu();
			
			if (choix == 1){
				int[] coords = saisirCoordonnees();
                System.out.print("Entrez le rayon du cercle: ");
                int radius = scan.nextInt();
                
                scan.nextLine();
                
                String couleur = saisirCouleur();
                
                formes.add(new Cercle("cercle", coords[0], coords[1], radius, couleur));
			
			} else if (choix == 2){
				
				int[] coords = saisirCoordonnees();
				
                System.out.print("Entrez la largeur (width) du rectangle: ");
                int w = scan.nextInt();
                
                scan.nextLine();
                
                System.out.print("Entrez la hauteur (height) du rectangle: ");
                int h = scan.nextInt();

                scan.nextLine();
                
                String couleur = saisirCouleur();
                
                formes.add(new Rectangle("rectangle", coords[0], coords[1], w, h, couleur));
			}
			
			else if (choix == 3){
				try{
					System.out.println("Entrez le nom de fichier ");
				String fileName = scan.nextLine();
				
				PrintWriter pw = new PrintWriter(fileName);
				pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
				pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
				pw.println(" version=\"1.1\" width=\"300\" height=\"200\">");
				
				for (int i = 0; i < formes.size(); i++) {
					pw.println(formes.get(i).toString());
						
				}
				pw.println("</svg>");
				pw.close();
				}catch(FileNotFoundException ex){
					System.out.println("Nom de fichier incorrect " + ex.getMessage());
					}
				
			}
			
		} while(choix !=3);
	}

	public static void main(String[] args){
	InteractiveSVG isvg = new InteractiveSVG();
	isvg.creeImage();
}
}