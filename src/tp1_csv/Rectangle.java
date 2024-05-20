package tp1_csv; 

public class Rectangle extends Forme {
	private double w;
	private double h;
	
	public Rectangle(String type, double x, double y, double w, double h, String couleur) {
		super("rect", x, y, couleur);
		this.w = w;
		this.h = h;
		
	}

	@Override
	public String toString() {
        return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" width=\""+w+"\" height=\""+h+"\" fill=\"" + couleur + "\" />";
    }
}
