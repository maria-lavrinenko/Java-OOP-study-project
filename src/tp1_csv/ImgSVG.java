package tp1_csv;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ImgSVG {

	private double imgWidth;
	private double imgHeight;
	private ArrayList<Forme> formes = new ArrayList<>();
	
	public ImgSVG (double imgWidth, double imgHeight, ArrayList<Forme> formes) {
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.formes = formes;
		
	}
//	la methode pour pouvoir modifier les composants du diagramme par la suite
	
	public ArrayList<Forme> getObjList() {
		return formes;
	}
	
	public void setObjList(ArrayList<Forme> formes) {
		this.formes = formes;
	}
	
	public void saveImgSVG(String fileName) {
		try {
			
			
			PrintWriter pw = new PrintWriter(fileName);
			pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
			pw.println(" version=\"1.1\" width=\"" + imgWidth + "\" height=\"" + imgHeight + "\">\n");
			
			for (int i = 0; i < formes.size(); i++) {
				pw.println(formes.get(i).toString());
					
			}
			pw.println("</svg>");
			pw.close();
			
		 }catch(FileNotFoundException ex){
			System.out.println("Nom de fichier incorrect " + ex.getMessage());
			}
	}
}
