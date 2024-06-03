package LockeFiles;

import java.util.ArrayList;
import java.util.List;

public class MenuOptions {
	private static final List<Option> mainMenuOptions = new ArrayList<>();// This is list of the Main Menu Options
    private static final List<Option> fileMenuOptions = new ArrayList<>(); // This is a list of the File Menu Options

    static {
        // This is where the main menu options are created
        mainMenuOptions.add(new Option("Display all files inside \"main\" folder"));
        mainMenuOptions.add(new Option("Show menu for File operations"));
        mainMenuOptions.add(new Option("Exit Application"));

        // This is where the file menu options are created
        
        fileMenuOptions.add(new Option("Add a file to \"main\" folder"));
        fileMenuOptions.add(new Option("Delete a file from \"main\" folder"));
        fileMenuOptions.add(new Option("Search for a file from \"main\" folder"));
        fileMenuOptions.add(new Option("Go Back to Main Menu"));
        fileMenuOptions.add(new Option("Exit Application"));
    }

    public static void printWelcomeScreen(String appName, String developerName) {
        String companyDetails = String.format("*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*\n"
                + "** Welcome to %s. **\n" + "** This application was developed by %s.**\n"
                + "*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*\n", appName, developerName);
        String appFunction = "The application can be used to :-\n"
                + "• DISPLAY and RETRIEVE all file names from \"main\" folder\n"
                + "• SEARCH, ADD, or DELETE files from\"main\" folder.\n"
                + "\n**PLEASE be careful to ensure the correct filename is provided for SEAECHING or DELETING files.**\n";
        System.out.println(companyDetails);
        System.out.println(appFunction);
    }
     //This is where the user then picks the option in the menu they want to choose
    public static void displayMenu(List<Option> options) {
        System.out.println("\n\n****** Select any number from options below and press Enter ******\n\n");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ") " + options.get(i).getDescription());
        }
    }

    public static void displayMainMenu() {
        displayMenu(mainMenuOptions); // This is where the Main Menu is displayed
    }

    public static void displayFileMenuOptions() {
        displayMenu(fileMenuOptions); // This is where the File Menu is displayed
    }

    private static class Option {
        private String description;

        public Option(String description) {
            this.description = description;// This where we write what the option is
        }

        public String getDescription() {
            return description; //This is where we able to read what the option is
        }
    
        }


}
