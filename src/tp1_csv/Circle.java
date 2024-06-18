package tp1_csv;

public class Circle extends Forme{

	private int radius;

	public Circle(String type, double x, double y, int radius) {
		super ("circle", x, y);
		this.radius = radius;

	}

	private int getRadius() {
		return radius;
	}

	private void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "<" + type + " cx=\"" + x + "\" cy=\"" + y + "\" r=\""+radius+"\" fill=\"" + color + "\" />";
	}

}
