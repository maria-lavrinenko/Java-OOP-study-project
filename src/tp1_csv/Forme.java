package tp1_csv;

public class Forme {
	protected String type;
	protected double x;
	protected double y;
	protected String color = "black";

	public Forme(String type, double x, double y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public String getFormType() {
		return type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" fill=\"" + color + "\" />";
	}

}
