package tp1_csv; 

public class Rectangle extends Forme {
	private int w;
	private int h;
	
	public Rectangle(String type, int x, int y, int w, int h, String couleur) {
		super("rect", x, y, couleur);
		this.w = w;
		this.h = h;
		
	}

	@Override
	public String toString() {
        return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" width=\""+w+"\" height=\""+h+"\" fill=\"" + couleur + "\" />";
    }
}
