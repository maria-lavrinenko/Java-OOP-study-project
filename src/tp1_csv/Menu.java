package tp1_csv;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	static int selectedCharType;
	static boolean parametersChanged = false;
	static TableauLu tableau;
	static AxisCharts axisChart = null;

	public static void displayMenu(int menu, Scanner scan) throws FileNotFoundException {

		if (menu == 0) {
			System.out.println("Fin de programme");
		}
		if (menu == 10) {
			System.out.println("0 - quitter");
		}

		if (menu == 1) {
			displayMenu(10, scan);
			System.out.println("1 - lire les données");
		}
		if (menu == 2) {
			displayMenu(1, scan);
			System.out.println("2 - choisir le type de graphique");
		}

		if (menu == 3) {
			displayMenu(2, scan);
			System.out.println("3 - modifier les paramètres");
			System.out.println("4 - créer le fichier");

		}
		if (menu == 4) {

			System.out.println("1 - Graphique à colonnes");
			System.out.println("2 - Graphique en courbes");
			System.out.println("3 - Graphique en courbes avec points ");
			displayMenu(10, scan);
			System.out.print("Votre choix: ");

			String choiceStr = scan.nextLine(); 

			try {
				int choice = Integer.parseInt(choiceStr);  

				if (choice == 1 || choice == 2 || choice == 3) {
					selectedCharType = choice;

					if (selectedCharType == 1) {
						axisChart = new ColumnChart(tableau);
					}
					else if (selectedCharType == 2) {
						axisChart = new LineChart(tableau);
					}
					else if (selectedCharType == 3) {
						axisChart = new LineWithDotsChart(tableau);
					}
					displayMenu(3, scan);
				} else if(choice == 0) {
					displayMenu(2, scan);
				} else {
					throw new IllegalArgumentException();
				}

			} catch (NumberFormatException e) {
				System.out.println("Entrée incorrecte (type)");
				displayMenu(menu, scan); 
				System.out.print("Votre choix: ");
			}
		}

	}

	private static boolean isValidChoice(int menu, int choice) {
		if (menu == 1) {
			return choice >= 0 && choice <= 1;
		} else if (menu == 2) {
			return choice >= 0 && choice <= 2;
		} else if (menu == 3) {
			return choice >= 0 && choice <= 4;
		} else if (menu == 4) {
			return choice >= 0 && choice <= 3;
		} else if (menu == 10) {
			return choice == 0;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		ImgSVG newImgSVG = null;
		int currentMenu = 1;

		do {
			try {

				displayMenu(currentMenu, scan);		
				System.out.print("Votre choix: ");
				int choice = scan.nextInt();
				scan.nextLine();

				if (isValidChoice(currentMenu, choice)) { 
					if (choice == 0 && currentMenu != 10) {
						currentMenu -=1;

						if (currentMenu == 0) {
							displayMenu(0, scan);
						}

					} else if (choice == 0 && currentMenu == 10) {
						currentMenu = 3;

					} else if (choice == 1) {

						System.out.println("Entrez le nom du fichier CSV: ");
						String nomDuFichier = scan.nextLine();
						String cheminFichier = "fichiers/" + nomDuFichier;

						try {
							tableau = TableauLu.lireFichierCSV(cheminFichier);
							System.out.println("Fichier bien lu !");
							currentMenu = 2;

						} catch (FileNotFoundException e) {
							System.out.println("Le fichier est introuvable.");
						}
					}

					else if (choice == 2) {
						selectedCharType = 0;
						parametersChanged = false;
						currentMenu = 4;
					}
					else if(choice == 3) {

						displayMenu(10, scan);
						System.out.println("Paramètres du graphique");

						if (axisChart != null) {
							axisChart.updateParameters(scan);
							newImgSVG = axisChart.generateSVGChart(tableau);
							newImgSVG.updateFormsFromChart(axisChart);
							parametersChanged = true;
						}
						currentMenu = 3;
					}

					else if (choice == 4) {
						System.out.println("Entrez le nom du fichier SVG ");
						String fileName = scan.nextLine();

						if (axisChart != null) {
							if (!parametersChanged) {
								newImgSVG = axisChart.generateSVGChart(tableau);
								newImgSVG.updateFormsFromChart(axisChart);
							}
							newImgSVG.saveImgSVG(fileName);
							System.out.println("Fichier est généré et sauvegardé !");
							newImgSVG = null;
						}
						currentMenu = 3;
					}
				} else {
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Entrée incorrecte (argument)");

			} catch (InputMismatchException e) {
				System.out.println("Entrée incorrecte (type)");
				scan.next();
			}

		} 
		while(currentMenu != 0);

		scan.close();

	}
}