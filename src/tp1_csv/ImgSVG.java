package tp1_csv;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ImgSVG {

	private double imgWidth;
	private double imgHeight;
	private ArrayList<Forme> formes;
	
	public ImgSVG (double imgWidth, double imgHeight, ArrayList<Forme> formes) {
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.formes = new ArrayList<Forme>(formes);
		
	}
	
	public ArrayList<Forme> getObjList() {
		ArrayList<Forme> copy = new ArrayList<Forme>();
		copy.addAll(formes);
		return copy;
	}
	
	public void setObjList(ArrayList<Forme> formes) {
        this.formes = formes;
    }
	
	public void addForms(ArrayList<Forme> formes) {
        this.formes.addAll(formes);
    }
	 

	 public void updateFormsFromChart(AxisCharts chart) {
	        ArrayList<Forme> formesToModif = new ArrayList<>(getObjList());
	        
	        if (chart instanceof ColumnChart) {
	            ColumnChart colChart = (ColumnChart) chart;
	           ;
	            
	            for (int i = 0; i < formesToModif.size(); i ++) {
	            
                  if (formesToModif.get(i)  instanceof Rectangle) {
                  	formesToModif.get(i).setColor(colChart.getColumnColor());
                  	((Rectangle) formesToModif.get(i)).setColWidth(colChart.getColWidth());
                  } else if (formesToModif.get(i)  instanceof Texte) {
                      	((Texte)formesToModif.get(i)).setColor(colChart.getTitlesColor());
                      } 
	            }
	            setObjList(formesToModif); 
	        } else if (chart instanceof LineChart) {
	        	
	            LineChart lineChart = (LineChart) chart;
	            
	            for (int i = 0; i < formesToModif.size(); i ++) {
		            
	                  if (formesToModif.get(i)  instanceof SimpleLine) {
	                  	formesToModif.get(i).setColor(lineChart.getLineColor());
	                  	((SimpleLine) formesToModif.get(i)).setStrokeWidth(lineChart.getStrokeWidth());
	                  } else if (formesToModif.get(i)  instanceof Texte) {
	                      	formesToModif.get(i).setColor(lineChart.getTitlesColor());
	                      } 
		            }
	            setObjList(formesToModif); 
	        } else if (chart instanceof LineWithDotsChart) {
	        	
	            LineWithDotsChart lineDotsChart = (LineWithDotsChart) chart;
	            
	            for (int i = 0; i < formesToModif.size(); i ++) {
		            
	                  if (formesToModif.get(i)  instanceof SimpleLine) {
	                  	formesToModif.get(i).setColor(lineDotsChart.getLineColor());
	                  	((SimpleLine) formesToModif.get(i)).setStrokeWidth(lineDotsChart.getStrokeWidth());
	                  
	                  }  else if (formesToModif.get(i)  instanceof Circle) {
	                		formesToModif.get(i).setColor(lineDotsChart.getDotsColor());
	                		
	            } else if (formesToModif.get(i)  instanceof Texte) {
	                      	formesToModif.get(i).setColor(lineDotsChart.getTitlesColor());
	                  }
	        }  setObjList(formesToModif); 
	            }

	       
	    }

	 
//	 pour debugger svg
	 
	 public String toString() {
		    StringBuilder sb = new StringBuilder();
		    
		    sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		    sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"")
		      .append(imgWidth)
		      .append("\" height=\"")
		      .append(imgHeight)
		      .append("\">\n");
		    
		    for (int i = 0; i < formes.size(); i++) {
		        sb.append(formes.get(i).toString()).append("\n");
		    }
		    
		    sb.append("</svg>");
		    return sb.toString();
		}
//
	public void saveImgSVG(String fileName) {
		try {
			
			
			PrintWriter pw = new PrintWriter(fileName);
//			String svgContent = this.toString(); 
//			System.out.println(svgContent);
			
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
