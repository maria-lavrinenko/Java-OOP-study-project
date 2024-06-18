package tp1_csv;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class LineChart extends AxisCharts{

	protected double pointMargin = 30;
	protected double strokeWidth = 2;

	protected String titlesColor = "black";
	protected String lineColor = "black";


	public LineChart(TableauLu data) {
		super(data);

	}

	public double getPointMargin() {
		return pointMargin;
	}

	public void setPointMargin(double pointMargin) {
		this.pointMargin = pointMargin;
	}

	public double getStrokeWidth() {
		return strokeWidth;
	}
	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public String getTitlesColor() {
		return titlesColor;
	}
	public void setTitlesColor(String titlesColor) {
		this.titlesColor = titlesColor;
	}

	public String getLineColor() {
		return lineColor;
	}
	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	public void updateParameters(Scanner scan) {
		System.out.println("1 - La hauteur du graphique: " + getAxisVert());
		System.out.println("2 - La marge des points: " + getPointMargin());
		System.out.println("3 - La largeur de la ligne: " + getStrokeWidth());
		System.out.println("4 - La couleur des entêtes: " + getTitlesColor());
		System.out.println("5 - La couleur de la ligne: " + getLineColor());

		System.out.println("Choisissez un paramètre à modifier (0 pour terminer): ");
		int choice = scan.nextInt();
		scan.nextLine();

		try {
			if (choice == 1) {
				System.out.println("Rentrez la nouvelle hauteur du graphique: ");
				setAxisVert(scan.nextDouble());
			} else if (choice == 2) {
				System.out.println("Rentrez la nouvelle marge des points: ");
				setPointMargin(scan.nextDouble());
			} else if (choice == 3) {
				System.out.println("Rentrez la nouvelle largeur de la ligne: ");
				setStrokeWidth(scan.nextDouble());
			}  else if (choice == 4) {
				System.out.println("Rentrez la nouvelle couleur des entêtes: ");
				setTitlesColor(scan.nextLine());
			} else if (choice == 5) {
				System.out.println("Rentrez la nouvelle couleur de la ligne: ");
				setLineColor(scan.nextLine());

			}
		} catch (InputMismatchException e) {
			System.out.println("Entrée incorrecte (type)");
			scan.next();
		}

	}



	public ImgSVG generateSVGChart(TableauLu data) {

		List<Integer> dataContent = new ArrayList<>();

		int nombreLignes = data.getNombreLignes();
		for (int i = 0; i < nombreLignes; i++) {
			dataContent.add(data.getContenu(i, 0));
		}
		double imgWidth = (marginImg * 2 + (nombreLignes * pointMargin) + pointMargin);
		double imgHeight = (axisVert * 1.5);


		double scaleIndex = getScaleIndex(data, dataContent);
		ArrayList<Forme> titles = new ArrayList<>();
		ArrayList<Forme> lines = new ArrayList<>();

		//      ajoute les points

		double varX1 = marginImg + pointMargin;

		for (int i = 0; i < dataContent.size(); i ++) {
			double varX1FromXtoPoint = dataContent.get(i) * scaleIndex;
			double varY1 = marginImg + (axisVert - varX1FromXtoPoint);

			//        	ajoute les lignes entre les points

			if (i != dataContent.size() - 1) {
				double varX2FromXtoPoint = dataContent.get(i + 1) * scaleIndex;
				lines.add(new SimpleLine("line", varX1, varY1, varX1 + pointMargin, (marginImg + (axisVert - varX2FromXtoPoint))));
			}

			//          ajoute les entetes 
			String enteteLigne = data.getEnteteLigne(i);
			titles.add(new Texte("text", varX1, (varY1 + varX1FromXtoPoint + marginTitles), "middle", enteteLigne));
			varX1 += pointMargin ;

		}

		ArrayList<Forme> coordinates = new ArrayList<>(setAxisLines(imgWidth));

		ImgSVG imgSVG = new ImgSVG (imgWidth, imgHeight, coordinates);
		imgSVG.addForms(lines);
		imgSVG.addForms(titles);

		return imgSVG;
	}

}