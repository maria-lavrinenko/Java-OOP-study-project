package tp1_csv;

public class Line extends Forme {
	
	private double x2;
	private double y2;
	private int strokeWidth;

	public Line(String type, double margeImg, double xAxisY, double x2, double y2, String couleur, int strokeWidth) {
		super("line", margeImg, xAxisY, couleur);
		this.x2 = x2;
		this.y2 = y2;
		this.strokeWidth = strokeWidth;
		
	}
	@Override
	public String toString() {
        return "<" + type + " x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + x2 + "\" y2=\"" + y2 +"\" stroke-width=\"" + strokeWidth + "\" stroke=\"" +couleur+ "\"/>";
    }
	

}
