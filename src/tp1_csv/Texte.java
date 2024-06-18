package tp1_csv;

public class Texte extends Forme {

	private String textAnchor;
	private String content;
	private int fontSize = 10;

	public Texte(String type, double x, double y, String textAnchor, String content) {
		super("text", x, y);
		this.textAnchor = textAnchor;
		this.content = content;

	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}


	@Override
	public String toString() {
		return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" fill=\"" +color+ "\" font-size=\"" + fontSize +"\" text-anchor=\"" +textAnchor+"\">"+content+"</text>";
	}
}
