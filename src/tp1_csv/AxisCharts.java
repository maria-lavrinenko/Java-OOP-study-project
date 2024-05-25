package tp1_csv;

import java.util.ArrayList;
import java.util.List;

public class AxisCharts {

	protected double axisVert = 100;
	protected double marginImg = 10;
	protected double marginTitles = 15;
	protected TableauLu data;
	
	public AxisCharts(TableauLu data) {
		this.data = data;
	}
	
	public double getMarginTitles () {
		return marginTitles;
	}
	
	public void setmarginTitles (double marginTitles) {
		this.marginTitles = marginTitles;
	}
	
	public double getAxisVert() {
		return axisVert;
	}
	
	public void setAxisVert(double axisVert) {
		this.axisVert = axisVert;
		
	}
	
	public double getScaleIndex(TableauLu data, List<Integer> dataContent, double axisVert) {
		double scaleIndex;
		
		int maxN = dataContent.get(0);
		for (int i = 0; i < dataContent.size(); i++) {
			maxN = Math.max(maxN, dataContent.get(i));
		}
		scaleIndex = axisVert / (maxN * 1.0);

		return scaleIndex;
	}
	
	public ArrayList<AxisLine> setAxisLines(double imgWidth) {
		
		AxisLine xAxis = new AxisLine("line", marginImg, (marginImg + axisVert), (imgWidth - marginImg), (marginImg + axisVert));
		AxisLine yAxis = new AxisLine("line", marginImg, marginImg, marginImg, (marginImg + axisVert));
		ArrayList<AxisLine> coordinates = new ArrayList<AxisLine>();
		coordinates.add(yAxis);
		coordinates.add(xAxis);
		
		return coordinates;
	}
	
}
