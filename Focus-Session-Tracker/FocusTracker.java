import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FocusTracker {

    private static final String FILE_NAME = "focus_log.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Focus Session Tracker ===");
            System.out.println("1. Start Focus Session");
            System.out.println("2. View Focus History");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    startSession(scanner);
                    break;
                case 2:
                    viewHistory();
                    break;
                case 3:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private static void startSession(Scanner scanner) throws IOException {
        System.out.print("Enter focus duration (minutes): ");
        int minutes = scanner.nextInt();
        scanner.nextLine();

        LocalDateTime startTime = LocalDateTime.now();

        System.out.println("Focus session started at: " + startTime);
        System.out.println("(Session simulated — no real waiting)");

        saveSession(startTime, minutes);

        System.out.println("Session logged successfully.");
    }

    private static void saveSession(LocalDateTime startTime, int minutes) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write("Session: " + startTime + " | Duration: " + minutes + " minutes\n");
        writer.close();
    }

    private static void viewHistory() throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No focus sessions recorded yet.");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        System.out.println("\n=== Focus History ===");

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
    }
}