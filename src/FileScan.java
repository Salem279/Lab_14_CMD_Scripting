import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileScan {

    public static void main(String[] args) {
        File fileToScan;

        // If file passed from command line
        if (args.length > 0) {
            fileToScan = new File(args[0]);
            System.out.println("Command line file argument found.");
        } else {
            // Open file chooser starting at Desktop
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));

            int result = chooser.showOpenDialog(null);

            if (result != JFileChooser.APPROVE_OPTION) {
                System.out.println("No file selected.");
                return;
            }

            fileToScan = chooser.getSelectedFile();
        }

        scanFile(fileToScan);
    }

    private static void scanFile(File file) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try {
            List<String> lines = Files.readAllLines(file.toPath());

            System.out.println("File contents:");
            for (String line : lines) {
                System.out.println(line);
                lineCount++;

                if (!line.trim().isEmpty()) {
                    wordCount += line.trim().split("\\s+").length;
                }

                charCount += line.length();
            }

            System.out.println("\nSummary Report");
            System.out.println("File name: " + file.getName());
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + charCount);

        } catch (IOException e) {
            System.out.println("Error reading file.");
            System.out.println(e.getMessage());
        }
    }
}