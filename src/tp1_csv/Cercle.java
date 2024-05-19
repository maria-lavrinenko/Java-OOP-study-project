package tp1_csv;

public class Cercle extends Forme{

	private int rayon;
	
	public Cercle(String type, int x, int y, int rayon, String couleur) {
		super ("circle", x, y, couleur);
		this.rayon = rayon;
		
	}
	@Override
	public String toString() {
        return "<" + type + " cx=\"" + x + "\" cy=\"" + y + "\" r=\""+rayon+"\" fill=\"" + couleur + "\" />";
    }
	
}
