package tp1_csv;

public class Forme {
	protected String type;
	protected int x;
	protected int y;
	protected String couleur;
	
	public Forme(String type, int x, int y, String couleur) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}

	public String toString() {
        return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" fill=\"" + couleur + "\" />";
    }

}
