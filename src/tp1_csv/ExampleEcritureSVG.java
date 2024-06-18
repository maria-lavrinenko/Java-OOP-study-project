package tp1_csv;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExampleEcritureSVG{

	public static void main(String[] args){

		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez le nom de fichier ");
		String fileName = scan.nextLine();
		System.out.println("Entrez la couleur du cercle ");
		String couleurCercle = scan.nextLine();



		try {
			PrintWriter pw = new PrintWriter(fileName);
			pw.println("<?xml version=\"1.0\" encoding=\"utf−8\"?>");
			pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
			pw.println(" version=\"1.1\" width=\"300\" height=\"200\">");
			pw.println("<circle cx=\"90\" cy=\"80\" r=\"50\" fill=\""+couleurCercle+"\" />");
			pw.println("</svg>");
			pw.close();
		} catch (FileNotFoundException ex){
			System.out.println("Nom de fichier incorrect " + ex.getMessage());
		}
		scan.close();
	}
}
