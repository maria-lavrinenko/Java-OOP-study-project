package tp1_csv; 

public class Rectangle extends Forme {
	private double w;
	private double h;
	
	public Rectangle(String type, double x, double y, double w, double h) {
		super("rect", x, y);
		this.w = w;
		this.h = h;
		
	}
	public double getColWidth() {
		return this.w;
	}
	
	public void setColWidth(double colWidth) {
		this.w = colWidth;
	}
	
	@Override
	public String toString() {
        return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" width=\""+w+"\" height=\""+h+"\" fill=\"" + color + "\" />";
    }
}
