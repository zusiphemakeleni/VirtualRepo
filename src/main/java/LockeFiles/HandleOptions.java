package LockeFiles;
import java.util.List;
import java.util.Scanner;


public class HandleOptions {
	public static void handleWelcomeScreenInput() {//This is where we handle the input of welcome screen
		boolean running = true;                 // we keep our code running until we done
		Scanner sc = new Scanner(System.in);
		do {                                   //The start of the options functionalities
			try {                                 // TO stay ready in case things go wrong
				MenuOptions.displayMainMenu();   // This displays all the Menu options available
				int input = sc.nextInt();       //This is where the user picks the option they want

				switch (input) {                //This is where we check what is being picked , so we can decide the condition
				case 1:
					FileOperations.displayAllFiles("main"); //This where the file options are displayed
					break;
				case 2:
					HandleOptions.handleFileMenuOptions(); //This where the file the add, delete and etc options show
					break;
				case 3:
					System.out.println("Application exited successfully."); //This is where the program is closed
					running = false;
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Please select a valid option from above.");//If user selects wrong input, its prompt to select a valid one
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleWelcomeScreenInput();
			} 
		} while (running == true);
	}
	
	public static void handleFileMenuOptions() {
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				MenuOptions.displayFileMenuOptions();
				FileOperations.createMainFolderIfNotPresent("main");
				
				int input = sc.nextInt();
				switch (input) {
				case 1:
					// File Add
					System.out.println("Enter the name of the file to be added to the \"main\" folder");
					String fileToAdd = sc.next();
					
					FileOperations.createFile(fileToAdd, sc);
					
					break;
				case 2:
					// File/Folder delete
					System.out.println("Enter the name of the file to be deleted from \"main\" folder");
					String fileToDelete = sc.next();
					
					FileOperations.createMainFolderIfNotPresent("main");
					List<String> filesToDelete = FileOperations.displayFileLocations(fileToDelete, "main");
					
					String deletionPrompt = "\n Enter 1 to delete the file."
							+ "\n(Enter 0 if you want to delete all elements)";
					System.out.println(deletionPrompt);
				
					int idx = sc.nextInt();
					
					if (idx != 0) {
						FileOperations.deleteFileRecursively(filesToDelete.get(idx - 1));
					} else {
						
						// If idx == 0, delete all files displayed for the name
						for (String path : filesToDelete) {
							FileOperations.deleteFileRecursively(path);
						}
					}
					

					break;
				case 3:
					// File/Folder Search
					System.out.println("Enter the name of the file to be searched from \"main\" folder");
					String fileName = sc.next();
					
					FileOperations.createMainFolderIfNotPresent("main");
					FileOperations.displayFileLocations(fileName, "main");

					
					break;
				case 4:
					// Go to Previous menu
					return;
				case 5:
					// Exit
					System.out.println("Application exited successfully.");
					running = false;
					sc.close();
					System.exit(0);
				default:
					System.out.println("Please select a valid option from above.");
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleFileMenuOptions();
			}
		} while (running == true);
	}

}
