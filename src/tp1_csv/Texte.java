package tp1_csv;

public class Texte extends Forme {
	
	private String textAnchor;
	
	public Texte(String type, int x, int y, String couleur, String textAnchor) {
		super("texte", x, y, couleur);
		this.textAnchor = textAnchor;
		
	}

	@Override
	public String toString() {
        return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" fill=\"" +couleur+ "\" text-anchor=\"" +textAnchor+"/>";
    }
}
