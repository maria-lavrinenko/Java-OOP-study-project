package tp1_csv;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class LineWithDotsChart extends LineChart{
	
	protected String dotsColor = "black";
	
	public LineWithDotsChart(TableauLu data) {
        super(data);
    }

    public String getDotsColor() {
        return dotsColor;
    }

    public void setDotsColor(String dotsColor) {
        this.dotsColor = dotsColor;
    }
    
    @Override
	public void updateParameters(Scanner scan) {
    	
    	System.out.println("1 - La hauteur du graphique: " + getAxisVert());
        System.out.println("2 - La marge des points: " + getPointMargin());
        System.out.println("3 - La largeur de la ligne: " + getStrokeWidth());
        System.out.println("4 - La couleur des entêtes: " + getTitlesColor());
        System.out.println("5 - La couleur de la ligne: " + getLineColor());
        System.out.println("6 - La couleur des points: " + getDotsColor());
        
        System.out.println("Choisissez un paramètre à modifier (0 pour terminer): ");
        int choice = scan.nextInt();
        scan.nextLine();

        try {
        	if (choice == 1) {
        
            System.out.println("Rentrez la nouvelle hauteur du graphique: ");
            this.setAxisVert(scan.nextDouble());
        } else if (choice == 2) {
            System.out.println("Rentrez la nouvelle marge des points: ");
            this.setPointMargin(scan.nextDouble());
        } else if (choice == 3) {
        	System.out.println("Rentrez la nouvelle largeur de la ligne: ");
            this.setStrokeWidth(scan.nextDouble());
        }  else if (choice == 4) {
        	System.out.println("Rentrez la nouvelle couleur des entêtes: ");
        	this.setTitlesColor(scan.nextLine());
        } else if (choice == 5) {
        	System.out.println("Rentrez la nouvelle couleur de la ligne: ");
        	this.setLineColor(scan.nextLine());
        } else if (choice == 6) {
        	System.out.println("Rentrez la nouvelle couleur des points: ");
        	this.setDotsColor(scan.nextLine());
        }
    }
     catch (InputMismatchException e) {
		System.out.println("Entrée incorrecte (type)");
		scan.next();
    }
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
        
        
        double scaleIndex = getScaleIndex(data, dataContent);
        ArrayList<Forme> points = new ArrayList<>();
        ArrayList<Forme> titles = new ArrayList<>();
        ArrayList<Forme> lines = new ArrayList<>();
        
//      ajoute les points
        
        double varX1 = marginImg + pointMargin;
        
        for (int i = 0; i < dataContent.size(); i ++) {
        	double varX1FromXtoPoint = dataContent.get(i) * scaleIndex;
        	double varY1 = marginImg + (axisVert - varX1FromXtoPoint);
        	Circle point = new Circle("circle", varX1, varY1, 3);
            point.setColor(dotsColor);  
            points.add(point);
        	
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
        
  ArrayList<Forme> coordinates = new ArrayList<>(setAxisLines(imgWidth));
        
        ImgSVG imgSVG = new ImgSVG (imgWidth, imgHeight, coordinates);
        imgSVG.addForms(lines);
        imgSVG.addForms(points);
        imgSVG.addForms(titles);
		
        return imgSVG;
	} 
	

}
	
