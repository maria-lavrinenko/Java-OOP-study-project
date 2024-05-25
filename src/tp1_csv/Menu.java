package tp1_csv;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	static int selectedCharType = 0;
	
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

		            if (choice == 1) {
		                selectedCharType = 1;
		                displayMenu(3, scan);
		                
		            } else if (choice == 2) {
		                selectedCharType = 2;
		                displayMenu(3, scan);
		            } else if (choice == 3) {
		            	selectedCharType = 3;
		            	displayMenu(3, scan);
		            } else if(choice == 0) {
		            	displayMenu(2, scan);
		            }
		            
		        } catch (NumberFormatException e) {
		            System.out.println("Entrée incorrecte (type)");
		            displayMenu(menu, scan); 
		            System.out.print("Votre choix: ");
		        }
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		TableauLu tableau = new TableauLu();
		ColumnChart columnChart = new ColumnChart(tableau);
		LineChart lineChart = new LineChart(tableau);
		LineWithDotsChart lineDotsChart = new LineWithDotsChart(tableau);
		ImgSVG newImgSVG = null;
        ArrayList<Forme> formesToModif = null;
		boolean parametersChanged = false;
		
		int currentMenu = 1;
	
		do {
		try {
		
			displayMenu(currentMenu, scan);		
            System.out.print("Votre choix: ");
            int choice = scan.nextInt();
            scan.nextLine();
		
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
                
			} catch 
			 (FileNotFoundException e) {
	            System.out.println("Le fichier est introuvable.");
	            
	        }
			
		}
		
		else if (choice == 2) {
			currentMenu = 4;
		}
		else if(choice == 3) {

			displayMenu(10, scan);
			System.out.println("Paramètres du graphique");
            
            if (selectedCharType == 1) {

                    formesToModif = updateColumnChartParameters(scan, columnChart, tableau);
                    
                    parametersChanged = true;
                    currentMenu = 3;
            }
			else if (selectedCharType == 2) {
								
				formesToModif = updateLineChartParameters(scan, lineChart, tableau);
                parametersChanged = true;
                currentMenu = 3;
			}
			else if (selectedCharType == 3) {
							
				formesToModif = updateLineWithDotsChartParameters(scan, lineDotsChart, tableau);
                parametersChanged = true;
                currentMenu = 3;
			}
		}
		
		else if (choice == 4) {
			System.out.println("Entrez le nom du fichier SVG ");
			String fileName = scan.nextLine();
			
            if (selectedCharType == 1) {
            	
                newImgSVG = columnChart.generateSVGChart(tableau);
            } else if (selectedCharType == 2) {
                newImgSVG = lineChart.generateSVGChart(tableau);
            } else {
                newImgSVG = lineDotsChart.generateSVGChart(tableau);
                
            }
            if (parametersChanged) {
  
            newImgSVG = columnChart.generateSVGChart(tableau);
            newImgSVG.setObjList(formesToModif);
            }
            newImgSVG.saveImgSVG(fileName);
            System.out.println("Fichier est généré et sauvegardé !");
            currentMenu = 3;
		}

		} catch (IllegalArgumentException e) {
			System.out.println("Entrée incorrecte (argument)");
			scan.next();
			displayMenu(currentMenu, scan);

			
		}
		catch (InputMismatchException e) {
			System.out.println("Entrée incorrecte (type)");
			scan.next();
			displayMenu(currentMenu, scan);

		}
		
		} 
		while(currentMenu != 0);

		scan.close();
			
		}

	private static ArrayList<Forme> updateColumnChartParameters(Scanner scan, ColumnChart columnChart, TableauLu tableau) {
        ImgSVG newImgSVG = columnChart.generateSVGChart(tableau);
        ArrayList<Forme> formesToModif = new ArrayList<>(newImgSVG.getObjList());
        String titlesColor = "";
        String columnColor = "";
        boolean parametersChanged = false;
        System.out.println("1 - La hauteur du graphique: " + columnChart.getAxisVert());
        System.out.println("2 - La marge des colonnes: " + columnChart.getColMargin());
        System.out.println("3 - La largeur des colonnes: " + columnChart.getColWidth());

        for (int i = 0; i < formesToModif.size(); i ++) {
            if (formesToModif.get(i) instanceof Texte) {
            	titlesColor = formesToModif.get(i).getColor();
                
            } else if (formesToModif.get(i) instanceof Rectangle) {
            	columnColor = formesToModif.get(i).getColor();
            }
        }
        System.out.println("4 - La couleur des entêtes: " + titlesColor);
        System.out.println("5 - La couleur des colonnes: " + columnColor);
        
        return handleParameterChanges(scan, formesToModif, newImgSVG, columnChart, parametersChanged);
    }

    private static ArrayList<Forme> updateLineChartParameters(Scanner scan, LineChart lineChart, TableauLu tableau) {
        ImgSVG newImgSVG = lineChart.generateSVGChart(tableau);
        ArrayList<Forme> formesToModif = new ArrayList<>(newImgSVG.getObjList());
        String titlesColor = "";
        String lineColor = "";
        int strokeWidth = 0;
        boolean parametersChanged = false;
        System.out.println("1 - La hauteur du graphique: " + lineChart.getAxisVert());
        System.out.println("2 - La marge des points: " + lineChart.getPointMargin());

        for (int i = 0; i < formesToModif.size(); i ++) {
            if (formesToModif.get(i)  instanceof Texte) {
            	titlesColor = formesToModif.get(i) .getColor();
            } else if (formesToModif.get(i)  instanceof SimpleLine) {
            	lineColor = formesToModif.get(i) .getColor();
                strokeWidth = ((SimpleLine) formesToModif.get(i) ).getStrokeWidth();
            }
        }
        System.out.println("3 - La largeur de la ligne: " + strokeWidth);
        System.out.println("4 - La couleur des entêtes: " + titlesColor);
        System.out.println("5 - La couleur de la ligne: " + lineColor);
         
        
        return handleParameterChanges(scan, formesToModif, newImgSVG, lineChart, parametersChanged);
    }

    private static ArrayList<Forme> updateLineWithDotsChartParameters(Scanner scan, LineWithDotsChart lineDotsChart, TableauLu tableau) {
        ImgSVG newImgSVG = lineDotsChart.generateSVGChart(tableau);
        ArrayList<Forme> formesToModif = new ArrayList<>(newImgSVG.getObjList());
        String titlesColor = "";
        String lineColor = "";
        int strokeWidth = 0;
        String pointColor = "";
        boolean parametersChanged = false;
        System.out.println("1 - La hauteur du graphique: " + lineDotsChart.getAxisVert());
        System.out.println("2 - La marge des points: " + lineDotsChart.getPointMargin());

        for (Forme forme : formesToModif) {
            if (forme instanceof Texte) {
            	titlesColor = forme.getColor();
                
            } else if (forme instanceof SimpleLine) {
            	lineColor = forme.getColor();
                strokeWidth = ((SimpleLine) forme).getStrokeWidth();
                
            } else if (forme instanceof Circle) {
            	pointColor = forme.getColor();
                
            }
        }
        System.out.println("3 - La largeur de la ligne: " + strokeWidth);
        System.out.println("4 - La couleur des entêtes: " + titlesColor);
        System.out.println("5 - La couleur de la ligne: " + lineColor);
        System.out.println("6 - La couleur des points: " + pointColor);
        
        return handleParameterChanges(scan, formesToModif, newImgSVG, lineDotsChart, parametersChanged);
    }

	
    
    private static ArrayList<Forme> handleParameterChanges(Scanner scan, ArrayList<Forme> formesToModif, ImgSVG newImgSVG, AxisCharts chart, boolean parametersChanged) {
    	while (true) {
    		
    	
    	System.out.println("Choisissez un paramètre à modifier (0 pour terminer): ");
    	System.out.print("Votre choix: ");
    	int choiceParam = scan.nextInt();
        scan.nextLine(); 

        if (choiceParam == 0) {
            break;  
        }
        
        if (choiceParam == 1) {
            
                System.out.println("Rentrez la nouvelle hauteur du graphique: ");
                double newAxisHeight = scan.nextDouble();
                chart.setAxisVert(newAxisHeight);
                parametersChanged = true;
        }
        else if (choiceParam == 2) {
                if (chart instanceof ColumnChart) {
                    System.out.println("Rentrez la nouvelle marge de colonnes: ");
                    double colMargin = scan.nextDouble();
                   
                    ((ColumnChart) chart).setColMargin(colMargin);
                    parametersChanged = true;
                } else if (chart instanceof LineChart) {
                    System.out.println("Rentrez la nouvelle marge des points: ");
                    double pointMargin = scan.nextDouble();
                    
                    ((LineChart) chart).setPointMargin(pointMargin);
                } else if (chart instanceof LineWithDotsChart) {
                    System.out.println("Rentrez la nouvelle marge des points: ");
                    double pointMargin = scan.nextDouble();
                    
                    ((LineWithDotsChart) chart).setPointMargin(pointMargin);
                }
        }
        else if (choiceParam == 3) {
            if (chart instanceof ColumnChart) {
                System.out.println("Rentrez la nouvelle largeur de colonnes: ");
                double colWidth = scan.nextInt();
                
                ((ColumnChart) chart).setColWidth(colWidth);
            } else if (chart instanceof LineChart) {
                System.out.println("Rentrez la nouvelle largeur de la ligne: ");
                int lineWidth = scan.nextInt();
                
                for (int i = 0; i < formesToModif.size(); i ++) {
                    if (formesToModif.get(i)  instanceof SimpleLine) {
                    	((SimpleLine) formesToModif.get(i)).setStrokeWidth(lineWidth); 
                    }
                }
                
            } else if (chart instanceof LineWithDotsChart) {
            	System.out.println("Rentrez la nouvelle largeur de la ligne: ");
                int lineWidth = scan.nextInt();
                
                for (int i = 0; i < formesToModif.size(); i ++) {
                    if (formesToModif.get(i)  instanceof SimpleLine) {
                    	((SimpleLine) formesToModif.get(i)).setStrokeWidth(lineWidth); 
                    }
                }
            }
        } else if (choiceParam == 4) {
            	System.out.println("Rentrez la nouvelle couleur des entêtes: ");
            	String titlesColor = scan.nextLine();

            	for (int i = 0; i < formesToModif.size(); i ++) {
                    if (formesToModif.get(i)  instanceof Texte) {
                    	((Texte) formesToModif.get(i)).setColor(titlesColor); 
                    }
                }
            }
            else if (choiceParam == 5) {
            	if (chart instanceof ColumnChart) {
                    System.out.println("Rentrez la nouvelle couleur des colonnes: ");
                    String colColor = scan.nextLine();

                    for (int i = 0; i < formesToModif.size(); i ++) {
                        if (formesToModif.get(i)  instanceof Rectangle) {
                        	((Rectangle) formesToModif.get(i)).setColor(colColor); 
                        }
                    }
                   
                } else {
                    System.out.println("Rentrez la nouvelle couleur de la ligne: ");
                    String lineColor = scan.nextLine();
                  
                    for (int i = 0; i < formesToModif.size(); i ++) {
                        if (formesToModif.get(i)  instanceof SimpleLine) {
                        	((SimpleLine) formesToModif.get(i)).setColor(lineColor); 
                        }
                    }
                
                }
            }
            else if (choiceParam == 6) {
            	if (chart instanceof LineWithDotsChart) {
                    System.out.println("Rentrez la nouvelle couleur des points: ");
                    String pointColor = scan.nextLine();
                    
                    for (int i = 0; i < formesToModif.size(); i ++) {
                        if (formesToModif.get(i)  instanceof Circle) {
                        	((Circle) formesToModif.get(i)).setColor(pointColor); 
                        }
                    }
            }
         
    }
            else {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    	return formesToModif;
    }
}
