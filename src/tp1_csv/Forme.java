package tp1_csv;

public class Forme {
	protected String type;
	protected double x;
	protected double y;
	protected String couleur;
	
	public Forme(String type, double x, double y, String couleur) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}
	
	public void setColor(String couleur) {
		this.couleur = couleur;
	}

	public String toString() {
        return "<" + type + " x=\"" + x + "\" y=\"" + y + "\" fill=\"" + couleur + "\" />";
    }

}
