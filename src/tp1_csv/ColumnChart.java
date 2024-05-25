package tp1_csv;

import java.util.ArrayList;
import java.util.List;


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
		
        double scaleIndex = getScaleIndex(data, dataContent, axisVert);
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
		
	
}
