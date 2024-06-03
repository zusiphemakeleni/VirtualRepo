package LockeFiles;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class FileOperations {
	public static void createMainFolderIfNotPresent(String folderName) {
        Path path = Paths.get(folderName); // This is where the path to file is shown
        if (!Files.exists(path)) { //We check if the path exists, if it does not we create it
            try {
                Files.createDirectories(path); // This is where the path is created
            } catch (IOException e) { // In  case something goes wrong we must be prepared to know why
                System.out.println("Failed to create the main folder."); // This is printed when something goes wrong
                e.printStackTrace(); //If something goes wrong this lists all the things that can give us a clue where we went wrong
            }
        }
    }

    public static void displayAllFiles(String path) { //This is where we have a look at all the files in the box
        createMainFolderIfNotPresent("main"); //If the main folder is not present we create it
        System.out.println("Displaying all files with directory structure in ascending order\n");

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {//This is where each path is looked at one by one
            paths.forEach(filePath -> {  //This where the path of the name  is given 
                String fileName = filePath.toString().replace(path, ""); // This is where we replace the path name to file name.
                System.out.println(fileName.isEmpty() ? "main" : fileName); // if the path name is too long, we just write main
            });
        } catch (IOException e) {
            System.out.println("Failed to display files."); // If something goes we let the user know what happend
            e.printStackTrace(); // Tells exactly what went wrong so that we try and fix it
        }
    }

    public static void createFile(String fileToAdd, Scanner sc) { // This we where create a file a give it a name
        createMainFolderIfNotPresent("main");        // When create a file we want make sure main exists
        Path pathToFile = Paths.get("main", fileToAdd);  // This is where the new file is place under main folder
        try {
            Files.createDirectories(pathToFile.getParent());// this make sure there is space for file in the main folder
            Files.createFile(pathToFile);    // The new file is created and put in the main folder
            System.out.println(fileToAdd + " created successfully");

            System.out.println("Would you like to add some content to the file? (Y/N)");
            String choice = sc.next().toLowerCase(); 
            sc.nextLine();

            if (choice.equals("y")) {
                System.out.println("\n\nInput content and press enter\n");
                String content = sc.nextLine();  // This is where the content is written
                Files.write(pathToFile, content.getBytes()); // the content is then saved and added in file
                System.out.println("\nContent written to file " + fileToAdd); 
                System.out.println("Content can be read using Notepad or Notepad++");
            }

        } catch (IOException e) {
            System.out.println("Failed to create file " + fileToAdd);
            e.printStackTrace();
        }
    }

    public static List<String> displayFileLocations(String fileName, String path) { // This is where files are loacated
        List<String> fileListNames = new ArrayList<>();  // We look for the location of the main folder
        try (Stream<Path> paths = Files.walk(Paths.get(path))) { //We go into the main folder and look for the file
            paths.filter(Files::isRegularFile) // While in the main folder we only interested in where files are kept not empty spaces
                 .filter(filePath -> filePath.toString().contains(fileName)) // Only looking at places of where the name searched is in
                 .forEach(filePath -> fileListNames.add(filePath.toString())); // When we find the location of file we write down so we remember
        } catch (IOException e) {
            System.out.println("Failed to search for files.");
            e.printStackTrace();
        }

        if (fileListNames.isEmpty()) { //After looking if we can not find the file, we tell the user that we cant find it
            System.out.println("\n\n***** Couldn't find any file with given file name \"" + fileName + "\" *****\n\n");
        } else {
            System.out.println("\n\nFound file at below location(s):");
            fileListNames.forEach(System.out::println); //We tell everyone where it is , and where it is loacated
        }

        return fileListNames; // give the user the path to get to the file
    }

    public static void deleteFileRecursively(String path) {
        try {
            Files.walk(Paths.get(path))
                 .sorted(Comparator.reverseOrder())
                 .map(Path::toFile)
                 .forEach(File::delete);
            System.out.println(" Deleted successfully.");
        } catch (IOException e) {
            System.out.println("Failed to delete files recursively.");
            e.printStackTrace();
        }
    }


}
