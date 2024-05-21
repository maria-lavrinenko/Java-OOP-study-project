package tp1_csv;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColumnChart {

	private double axisVert;
	private double marginCol;
	private double marginImg;
	private double colWidth;
	private double marginTitles;
	private TableauLu data;
	
	
	public ColumnChart (TableauLu data) {
		
		this.axisVert = 100;
		this.marginCol = 5;
		this.marginImg = 10;
		this.colWidth = 20;
		this.data = data;
		this.marginTitles = 15;
		
	}
	
	public double getScaleIndex(TableauLu data, List<Integer> dataContent) {
		double scaleIndex;
		
		int maxN = dataContent.get(0);
		for (int i = 0; i < dataContent.size(); i++) {
			maxN = Math.max(maxN, dataContent.get(i));
		}
		scaleIndex = axisVert / (maxN * 1.0);

		return scaleIndex;
	}
	
	
	public double getMarginTitles () {
		return marginTitles;
	}
	
	public void setmarginTitles (double marginTitles) {
		this.marginTitles = marginTitles;
	}
	
	
	public double getmarginCol() {
		return marginCol;
	}
	
	public void setmarginCol (double marginCol) {
		this.marginCol = marginCol;
	}
	
	public double getAxisVert() {
		return axisVert;
	}
	
	public void setAxisVert(double axisVert) {
		this.axisVert = axisVert;
	}
	
	public ImgSVG generateSVGChart(TableauLu data, int fontSize, String colColor, String textColor) { 
		
        double imgWidth = (marginImg * 2 + (data.getNombreLignes() * colWidth) + (data.getNombreLignes() * marginCol * 2));
        double imgHeight = (axisVert * 1.5);
        
        List<Integer> dataContent = new ArrayList<>();
		
		int nombreLignes = data.getNombreLignes();
		for (int i = 0; i < nombreLignes; i++) {
			dataContent.add(data.getContenu(i, 0));
		}
		
//      ajoute des axes x-y
        
      Line xAxis = new Line("line", marginImg, (marginImg + axisVert), (imgWidth - marginImg), (marginImg + axisVert), "black", 2);
      Line yAxis = new Line("line", marginImg, marginImg, marginImg, (marginImg + axisVert), "black", 2);
      
//		ajoute les rectangles de donnees 
		
        double scaleIndex = getScaleIndex(data, dataContent);
        ArrayList<Forme> rects = new ArrayList<>();
        ArrayList<Forme> entetes = new ArrayList<>();
        
        
        double rectX = marginImg + marginCol;
        
        for (int i = 0; i < dataContent.size(); i ++) {
        	double rectH = dataContent.get(i) * scaleIndex;
        	double rectY = marginImg + (axisVert - rectH);
        	
        	rects.add(new Rectangle("rectangle", rectX, rectY, colWidth, rectH, colColor));
        	
//          ajoute les entetes 
        	String enteteLigne = data.getEnteteLigne(i);
        	entetes.add(new Texte("text", (rectX + (colWidth /2)), (rectY + rectH + marginTitles), textColor, "middle", enteteLigne, fontSize));
        	rectX = rectX + colWidth + (marginCol * 2) ;
}
        
        	
        ArrayList<Forme> objectsSVG = new ArrayList<>();
        objectsSVG.add(xAxis);
        objectsSVG.add(yAxis);
        objectsSVG.addAll(rects);
        objectsSVG.addAll(entetes);
        ImgSVG imgSVG = new ImgSVG (imgWidth, imgHeight, objectsSVG);
        
		return imgSVG;
        
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		String cheminFichier = "fichiers/data.txt";
		TableauLu tableau = TableauLu.lireFichierCSV(cheminFichier);
		ColumnChart diagrammeCol = new ColumnChart(tableau);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Rentrez la couleur des colonnes");
		String colColor = sc.next();
		
		System.out.println("Rentrez la couleur du texte");
		String textColor = sc.next();
		
		
		

		ImgSVG newImgSVG = diagrammeCol.generateSVGChart(tableau, 10, colColor, textColor );
		newImgSVG.saveImgSVG();
		
	}
	
	
}
