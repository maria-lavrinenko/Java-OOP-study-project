package tp1_csv;

public class Line extends Forme {
	
	private int x2;
	private int y2;
	private int strokeWidth;

	public Line(String type, int x, int y, int x2, int y2, String couleur, int strokeWidth) {
		super("line", x, y, couleur);
		this.x2 = x2;
		this.y2 = y2;
		this.strokeWidth = strokeWidth;
		
	}
	@Override
	public String toString() {
        return "<" + type + " x1=\"" + x + "\" y1=\"" + y + " x2=\"" + x2 + "\" y2=\"" + y2 +"\" stroke-width=\"" + strokeWidth + "\" fill=\"" +couleur+ "/>";
    }
	

}
