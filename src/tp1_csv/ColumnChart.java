package tp1_csv;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColumnChart extends AxisCharts {

	private double colMargin = 5;
	private double colWidth = 20;
	
	public ColumnChart (TableauLu data) {
		super(data);
	}
	
	public double getColMargin() {
		return colMargin;
	}
	
	public void setColMargin (double colMargin) {
		this.colMargin = colMargin;
	}
	
	public double getColWidth() {
		return colWidth;
	}
	
	public void setColWidth (double colWidth) {
		this.colWidth = colWidth;
	}
	
	public ImgSVG generateSVGChart(TableauLu data) { 
		
        List<Integer> dataContent = new ArrayList<>();
		
		int nombreLignes = data.getNombreLignes();
		for (int i = 0; i < nombreLignes; i++) {
			dataContent.add(data.getContenu(i, 0));
		}
		
		double imgWidth = (marginImg * 2 + (nombreLignes * colWidth) + (nombreLignes * colMargin * 2));
        double imgHeight = (axisVert * 1.5);
		   
//		ajoute les rectangles de donnees 
		
        double scaleIndex = getScaleIndex(data, dataContent);
        ArrayList<Forme> rects = new ArrayList<>();
        ArrayList<Forme> titles = new ArrayList<>();
        
        
        double rectX = marginImg + colMargin;
        
        for (int i = 0; i < dataContent.size(); i ++) {
        	double rectH = dataContent.get(i) * scaleIndex;
        	double rectY = marginImg + (axisVert - rectH);
        	
        	rects.add(new Rectangle("rectangle", rectX, rectY, colWidth, rectH));
        	
//          ajoute les entetes 
        	String enteteLigne = data.getEnteteLigne(i);
        	titles.add(new Texte("text", (rectX + (colWidth /2)), (rectY + rectH + marginTitles), "middle", enteteLigne));
        	rectX = rectX + colWidth + (colMargin * 2) ;
}
        
        	
        ArrayList<Forme> objectsSVG = new ArrayList<>();
        
        objectsSVG.addAll(setAxisLines(imgWidth));
        objectsSVG.addAll(rects);
        objectsSVG.addAll(titles);
        ImgSVG imgSVG = new ImgSVG (imgWidth, imgHeight, objectsSVG);
        
		return imgSVG;
        
	}
	
	
//	public static void main(String[] args) throws FileNotFoundException {
//		String cheminFichier = "fichiers/data.txt";
//		TableauLu tableau = TableauLu.lireFichierCSV(cheminFichier);
//		ColumnChart diagrammeCol = new ColumnChart(tableau);
//		
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Entrez le nom de fichier ");
//		String fileName = scan.nextLine();
//		
//
//		
////		System.out.println("Rentrez la couleur du texte");
////		String textColor = sc.next();
////		
//
//		ImgSVG newImgSVG = diagrammeCol.generateSVGChart(tableau);
//		newImgSVG.saveImgSVG(fileName);
//		System.out.println("OK");
//		
//		System.out.println("Rentrez la couleur des colonnes");
//		String colColor = scan.nextLine();
//		
//		System.out.println("Rentrez la couleur des entetes");
//		String titleColor = scan.nextLine();
//		
//		System.out.println("Rentrez la couleur des axes");
//		String axisColor = scan.nextLine();
//		
//		scan.close();
//		
//		
//		
//		ArrayList<Forme> formesToModif = new ArrayList<>(newImgSVG.getObjList());
//		for (int i = 0; i < formesToModif.size(); i++) {
//			if (formesToModif.get(i) instanceof Rectangle) {
//				formesToModif.get(i).setColor(colColor);
//				
//			} else if (formesToModif.get(i) instanceof Texte) {
//				formesToModif.get(i).setColor(titleColor);
//				
//			} else if (formesToModif.get(i) instanceof AxisLine) {
//				formesToModif.get(i).setColor(axisColor);
//		}
//		}
//		newImgSVG.setObjList(formesToModif);
//		newImgSVG.saveImgSVG(fileName);
//		
		
		
	
	
	
}
