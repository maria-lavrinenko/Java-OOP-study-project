package tp1_csv;

public class AxisLine extends Forme {

	protected double x2;
	protected double y2;
	private double strokeWidth = 2;

	public AxisLine(String type, double x, double y, double x2, double y2) {
		super("line", x, y);
		this.x2 = x2;
		this.y2 = y2;

	}

	public double getStrokeWidth() {
		return strokeWidth;
	}
	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	@Override
	public String toString() {
		return "<" + type + " x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + x2 + "\" y2=\"" + y2 +"\" stroke-width=\"" + strokeWidth + "\" stroke=\"" +color+ "\"/>";
	}



}
