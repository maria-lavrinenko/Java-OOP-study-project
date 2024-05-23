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
		ImgSVG newImgSVG;
		ArrayList<Forme> formesToModif = null;
		 boolean parametersChanged = false;
		
		int currentMenu = 1;
		String titlesColor = "";
		String columnColor = "";
		String lineColor = "";
		String dotsColor = "";
		int lineWidth = 0;
		
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
			currentMenu = 10;
			System.out.println("Paramètres du graphique");
            
            if (selectedCharType == 1) {
                columnChart = new ColumnChart(tableau);
                newImgSVG = columnChart.generateSVGChart(tableau);
                formesToModif = new ArrayList<>(newImgSVG.getObjList());

                System.out.println("1 - La hauteur du graphique: " + columnChart.getAxisVert());
                System.out.println("2 - La marge des colonnes: " + columnChart.getColMargin());
                System.out.println("3 - La largeur des colonnes: " + columnChart.getColWidth());
                
				for (int i = 0; i < formesToModif.size(); i++) {
					
					if (formesToModif.get(i) instanceof Texte) {
						titlesColor = formesToModif.get(i).getColor();
					
					}
				else if (formesToModif.get(i) instanceof Rectangle) {
					columnColor = formesToModif.get(i).getColor();
				}
					
			}
				System.out.println("4 - La couleur des entêtes: " + titlesColor);
				System.out.println("5 - La couleur des colonnes: " + columnColor);	
				
				parametersChanged = (handleParameterChanges(scan, formesToModif, newImgSVG, columnChart, parametersChanged));
	
				displayMenu(3, scan);
            }
			else if (selectedCharType == 2) {
				
				lineChart = new LineChart(tableau);
                newImgSVG = lineChart.generateSVGChart(tableau);
                formesToModif = new ArrayList<>(newImgSVG.getObjList());

                System.out.println("1 - La hauteur du graphique: " + lineChart.getAxisVert());
				System.out.println("2 - La marge des points: " + lineChart.getPointMargin());
				
				for (int i = 0; i < formesToModif.size(); i++) {
				
					if (formesToModif.get(i) instanceof Texte) {
						titlesColor = formesToModif.get(i).getColor();
					}
				
				else if (formesToModif.get(i) instanceof SimpleLine) {
					lineColor = formesToModif.get(i).getColor();
					lineWidth = ((SimpleLine) formesToModif.get(i)).getStrokeWidth();
					
				}
				
			}
				System.out.println("3 - La largeur de la ligne: " + lineWidth);
				System.out.println("4 - La couleur des entêtes: " + titlesColor);
				System.out.println("5 - La couleur de la ligne: " + lineColor);
				
				parametersChanged = (handleParameterChanges(scan, formesToModif, newImgSVG, lineChart, parametersChanged));
				
			}
			else if (selectedCharType == 3) {
				
				lineDotsChart = new LineWithDotsChart(tableau);
                newImgSVG = lineDotsChart.generateSVGChart(tableau);
                formesToModif = new ArrayList<>(newImgSVG.getObjList());

            	System.out.println("1 - La hauteur du graphique: " + lineDotsChart.getAxisVert());
                System.out.println("2 - La marge des points: " + lineDotsChart.getPointMargin());
				
				for (int i = 0; i < formesToModif.size(); i++) {
				
					if (formesToModif.get(i) instanceof Texte) {
						titlesColor = formesToModif.get(i).getColor();
					
					}
				else if (formesToModif.get(i) instanceof Circle) {
					dotsColor = formesToModif.get(i).getColor();
					
				}
				
				else if (formesToModif.get(i) instanceof SimpleLine) {
					lineColor = formesToModif.get(i).getColor();
					lineWidth = ((SimpleLine) formesToModif.get(i)).getStrokeWidth();
				}
				
			}
				System.out.println("3 - La largeur de la ligne: " + lineWidth);
				System.out.println("4 - La couleur des entêtes: " + titlesColor);
				System.out.println("5 - La couleur de la ligne: " + lineColor);
				System.out.println("6 - La couleur des points: " + dotsColor);	
				
				parametersChanged = (handleParameterChanges(scan, formesToModif, newImgSVG, lineDotsChart, parametersChanged));
//				parametersChanged = true;
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
            newImgSVG.setObjList(formesToModif);
            }
            newImgSVG.saveImgSVG(fileName);
            System.out.println("Fichier est généré et sauvegardé !");
            currentMenu = 3;
		}

		} catch (IllegalArgumentException e) {
			System.out.println("Entrée incorrecte (argument)");
			displayMenu(currentMenu, scan);
			scan.nextLine();
			
		}
		catch (InputMismatchException e) {
			System.out.println("Entrée incorrecte (type)");
			displayMenu(currentMenu, scan);
			scan.nextLine();
		}
		
		} 
		while(currentMenu != 0);

		scan.close();
			
		}
	
	private static boolean handleParameterChanges(Scanner scan, ArrayList<Forme> formesToModif, ImgSVG newImgSVG, AxisCharts chart, boolean parametersChanged)   {
        try {
            System.out.print("Votre choix: ");
            String choiceStr = scan.nextLine();
            int choiceParam = Integer.parseInt(choiceStr);

            if (choiceParam == 1) {
            	System.out.println("Rentrez la nouvelle hauteur du graphique: ");
                int val = scan.nextInt();
                chart.setAxisVert(val);
                parametersChanged = true;
            }
            
            else if (choiceParam == 2) {
            	if(chart instanceof ColumnChart) {
            		System.out.println("Rentrez la nouvelle marge de colonnes: ");
                    int val = scan.nextInt();
                    ((ColumnChart) chart).setColMargin(val);
                    parametersChanged = true;
            	} else if (chart instanceof LineChart) {
            		int val = scan.nextInt();
                    ((LineChart) chart).setPointMargin(val);
                    parametersChanged = true;
                } else if (chart instanceof LineWithDotsChart) {
                    System.out.println("Rentrez la nouvelle marge des points: ");
                    int val = scan.nextInt();
                    ((LineWithDotsChart) chart).setPointMargin(val);
                    parametersChanged = true;
            	}
            }
             

             else if (choiceParam == 3) {
                if (chart instanceof ColumnChart) {
                    System.out.println("Rentrez la nouvelle largeur des colonnes: ");
                    int val = scan.nextInt();
                    ((ColumnChart) chart).setColWidth(val);
                    parametersChanged = true;
                } else if (chart instanceof LineChart || chart instanceof LineWithDotsChart) {
                    System.out.println("Rentrez la nouvelle largeur de la ligne: ");
                    int val = scan.nextInt();
                    for ( int i = 0; i < formesToModif.size(); i ++) {
                    	if (formesToModif.get(i) instanceof SimpleLine) {
                    		((SimpleLine) formesToModif.get(i)).setStrokeWidth(val);
                        }
                    }                   
                    parametersChanged = true;
                }

            } else if (choiceParam == 4) {
                
                    System.out.println("Rentrez la nouvelle couleur pour les entêtes: ");
                    String val = scan.next();
                    for ( int i = 0; i < formesToModif.size(); i ++) {
                    	if (formesToModif.get(i) instanceof Texte) {
                    		((Texte) formesToModif.get(i)).setColor(val);
                        }
                    }
                    
                    parametersChanged = true;
                    
                } else if (choiceParam == 5) {
                	if(chart instanceof ColumnChart) {
                		System.out.println("Rentrez la nouvelle couleur des colonnes: ");
                		String val = scan.nextLine();
                		for ( int i = 0; i < formesToModif.size(); i ++) {
                        	if (formesToModif.get(i) instanceof Rectangle) {
                        		((Rectangle) formesToModif.get(i)).setColor(val);
                            }
                        }
 
                        parametersChanged = true;
                	} else if (chart instanceof LineChart || chart instanceof LineWithDotsChart) {
                		System.out.println("Rentrez la nouvelle couleur de la ligne: ");
                		String val = scan.nextLine();
                		for ( int i = 0; i < formesToModif.size(); i ++) {
                        	if (formesToModif.get(i) instanceof SimpleLine) {
                        		((SimpleLine) formesToModif.get(i)).setColor(val);
                            }
                        }
 
                        parametersChanged = true;
                	}
                }
                else if (choiceParam == 6) {
                	if (chart instanceof LineWithDotsChart) {
                		System.out.println("Rentrez la nouvelle couleur des points: ");
                		String val = scan.nextLine();
                		for ( int i = 0; i < formesToModif.size(); i ++) {
                        	if (formesToModif.get(i) instanceof Circle) {
                        		((Circle) formesToModif.get(i)).setColor(val);
                            }
                        }

                        parametersChanged = true;
                	}
                }
            
        } catch (NumberFormatException e) {
            System.out.println("Entrée incorrecte (type)");
        } 
        
        return parametersChanged;
        
	}
}