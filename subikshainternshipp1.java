import java.util.Scanner;

public class TypingSpeedTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample sentence to type
        String sentence = "She sells sea shells on the sea shore.";
        System.out.println("Type the following sentence exactly as shown:");
        System.out.println("\"" + sentence + "\"");
        System.out.println("\nPress Enter when you're ready...");

        scanner.nextLine(); // Wait for Enter

        long startTime = System.currentTimeMillis();

        System.out.println("\nStart typing:");
        String userInput = scanner.nextLine();

        long endTime = System.currentTimeMillis();

        // Calculate time taken in seconds
        double timeTaken = (endTime - startTime) / 1000.0;

        // Calculate words per minute
        int wordCount = userInput.trim().split("\\s+").length;
        double wpm = (wordCount / timeTaken) * 60;

        // Calculate accuracy
        int correctChars = 0;
        int minLength = Math.min(userInput.length(), sentence.length());
        for (int i = 0; i < minLength; i++) {
            if (userInput.charAt(i) == sentence.charAt(i)) {
                correctChars++;
            }
        }
        double accuracy = ((double) correctChars / sentence.length()) * 100;

        // Results
        System.out.printf("\nTime Taken: %.2f seconds\n", timeTaken);
        System.out.printf("Words Per Minute (WPM): %.2f\n", wpm);
        System.out.printf("Accuracy: %.2f%%\n", accuracy);

        scanner.close();
    }
}
