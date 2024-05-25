package tp1_csv;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LineWithDotsChart extends LineChart{
	
	public LineWithDotsChart(TableauLu data) {
		super(data);
	}
	
	@Override
	
	public ImgSVG generateSVGChart(TableauLu data) {
		
		List<Integer> dataContent = new ArrayList<>();
		
		int nombreLignes = data.getNombreLignes();
		for (int i = 0; i < nombreLignes; i++) {
			dataContent.add(data.getContenu(i, 0));
		}
		double imgWidth = (marginImg * 2 + (nombreLignes * pointMargin) + pointMargin);
        double imgHeight = (axisVert * 1.5);
        
        
        double scaleIndex = getScaleIndex(data, dataContent, axisVert);
        ArrayList<Forme> points = new ArrayList<>();
        ArrayList<Forme> titles = new ArrayList<>();
        ArrayList<Forme> lines = new ArrayList<>();
        
//      ajoute les points
        
        double varX1 = marginImg + pointMargin;
        
        for (int i = 0; i < dataContent.size(); i ++) {
        	double varX1FromXtoPoint = dataContent.get(i) * scaleIndex;
        	double varY1 = marginImg + (axisVert - varX1FromXtoPoint);
        	points.add(new Circle("circle", varX1, varY1, 3));
        	
//        	ajoute les lignes entre les points
        	
        	if (i != dataContent.size() - 1) {
                double varX2FromXtoPoint = dataContent.get(i + 1) * scaleIndex;
                lines.add(new SimpleLine("line", varX1, varY1, varX1 + pointMargin, (marginImg + (axisVert - varX2FromXtoPoint))));
            }
        	
//          ajoute les entetes 
        	String enteteLigne = data.getEnteteLigne(i);
        	titles.add(new Texte("text", varX1, (varY1 + varX1FromXtoPoint + marginTitles), "middle", enteteLigne));
        	varX1 += pointMargin ;
        	
}
        ArrayList<Forme> objectsSVG = new ArrayList<>();
        
        objectsSVG.addAll(setAxisLines(imgWidth));
        objectsSVG.addAll(lines);
        objectsSVG.addAll(points);
        objectsSVG.addAll(titles);
        ImgSVG imgSVG = new ImgSVG (imgWidth, imgHeight, objectsSVG);
		
        return imgSVG;
	} 
	

	

	public static void main(String[] args) throws FileNotFoundException {
		String cheminFichier = "fichiers/data.txt";
		TableauLu tableau = TableauLu.lireFichierCSV(cheminFichier);
		LineWithDotsChart diagrammeCourbes = new LineWithDotsChart(tableau);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez le nom de fichier ");
		String fileName = scan.nextLine();
		

		
//		System.out.println("Rentrez la couleur du texte");
//		String textColor = sc.next();
//		

		ImgSVG newImgSVG = diagrammeCourbes.generateSVGChart(tableau);
		newImgSVG.saveImgSVG(fileName);
}}
