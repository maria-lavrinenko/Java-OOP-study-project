package tp1_csv;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ColumnChart extends AxisCharts {

	private double colMargin = 5;
	private double colWidth = 20;
    
    private String titlesColor = "black";
    private String columnColor = "black";
	
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
	  public void setColWidth(double colWidth) {
	        this.colWidth = colWidth;
	    }
	    
	   
	    public String getTitlesColor() {
	        return titlesColor;
	    }
	    public void setTitlesColor(String titlesColor) {
	        this.titlesColor = titlesColor;
	    }
	    
	    public String getColumnColor() {
	        return columnColor;
	    }
	    public void setColumnColor(String columnColor) {
	        this.columnColor = columnColor;
	    }
	    

	    
	public void updateParameters(Scanner scan) {
        System.out.println("1 - La hauteur du graphique: " + getAxisVert());
        System.out.println("2 - La marge des colonnes: " + getColMargin());
        System.out.println("3 - La largeur des colonnes: " + getColWidth());
        System.out.println("4 - La couleur des entetes: " + getTitlesColor());
        System.out.println("5 - La couleur des colonnes: " + getColumnColor());
        
        System.out.println("Choisissez un paramètre à modifier (0 pour terminer): ");
        int choice = scan.nextInt();
        scan.nextLine();
        try {
        
        if (choice == 1) {
            System.out.println("Rentrez la nouvelle hauteur du graphique: ");
            this.setAxisVert(scan.nextDouble());
        } else if (choice == 2) {
            System.out.println("Rentrez la nouvelle marge de colonnes: ");
            this.setColMargin(scan.nextDouble());
        } else if (choice == 3) {
        	System.out.println("Rentrez la nouvelle largeur des colonnes: ");
        	this.setColWidth(scan.nextDouble());
        } else if (choice == 4) {
        	System.out.println("Rentrez la nouvelle couleur des entêtes: ");
        	this.setTitlesColor(scan.nextLine());
        } else if (choice == 5) {
        	System.out.println("Rentrez la nouvelle couleur des colonnes: ");
        	this.setColumnColor(scan.nextLine());
        };
        } catch (InputMismatchException e) {
			System.out.println("Entrée incorrecte (type)");
			scan.next();
        }
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
        
        ArrayList<Forme> coordinates = new ArrayList<>(setAxisLines(imgWidth));
        
        ImgSVG imgSVG = new ImgSVG (imgWidth, imgHeight, coordinates);
        imgSVG.addForms(rects);
        imgSVG.addForms(titles);
        
        
		return imgSVG;
        
	}
		
	
}
