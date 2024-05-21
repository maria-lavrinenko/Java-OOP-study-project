package tp1_csv;

public class Texte extends Forme {
	
	private String textAnchor;
	private String content;
	private int fontSize;
	
	public Texte(String type, double x, double y, String couleur, String textAnchor, String content, int fontSize) {
		super("text", x, y, couleur);
		this.textAnchor = textAnchor;
		this.content = content;
		this.fontSize = fontSize;
		
	}
	
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	@Override
	public String toString() {
        return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" fill=\"" +couleur+ "\" font-size=\"" + fontSize +"\" text-anchor=\"" +textAnchor+"\">"+content+"</text>";
    }
}
