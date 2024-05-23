package tp1_csv;

public class SimpleLine extends AxisLine{
	
	private int strokeWidth = 1;
	
	public SimpleLine(String type, double x1, double y1, double x2, double y2) {
		super("line", x1, y1, x2, y2);
	}
	
	@Override
	
	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	
	@Override
	public String toString() {
        return "<" + type + " x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + x2 + "\" y2=\"" + y2 +"\" stroke-width=\"" + strokeWidth + "\" stroke=\"" +color+ "\"/>";
    }

}
