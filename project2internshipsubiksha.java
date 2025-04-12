import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class URLShortener {
    // Maps to store long <-> short URL relationships
    private Map<String, String> longToShortMap;
    private Map<String, String> shortToLongMap;
    private final String domain = "https://sho.rt/";
    private final String characters = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int codeLength = 7;
    private Random random;

    public URLShortener() {
        longToShortMap = new HashMap<>();
        shortToLongMap = new HashMap<>();
        random = new Random();
    }

    // Create a short code
    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    // Shorten method
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return domain + longToShortMap.get(longURL);
        }

        String shortCode;
        do {
            shortCode = generateCode();
        } while (shortToLongMap.containsKey(shortCode));

        longToShortMap.put(longURL, shortCode);
        shortToLongMap.put(shortCode, longURL);

        return domain + shortCode;
    }

    // Expand method
    public String expandURL(String shortURL) {
        if (!shortURL.startsWith(domain)) {
            return "⚠ Invalid URL format!";
        }

        String code = shortURL.replace(domain, "");

        if (shortToLongMap.containsKey(code)) {
            return shortToLongMap.get(code);
        } else {
            return "❌ No original URL found for this code.";
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        URLShortener shortener = new URLShortener();

        System.out.println("📌 Welcome to the Java URL Shortener!");

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Shorten a URL");
            System.out.println("2. Expand a short URL");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            int option = input.nextInt();
            input.nextLine();  // clear the buffer

            switch (option) {
                case 1:
                    System.out.print("🔗 Enter the long URL: ");
                    String longURL = input.nextLine();
                    String shortened = shortener.shortenURL(longURL);
                    System.out.println("✅ Shortened URL: " + shortened);
                    break;

                case 2:
                    System.out.print("🔎 Enter the short URL: ");
                    String shortURL = input.nextLine();
                    String original = shortener.expandURL(shortURL);
                    System.out.println("📍 Original URL: " + original);
                    break;

                case 3:
                    System.out.println("👋 Exiting. Thanks for using the shortener!");
                    return;

                default:
                    System.out.println("❗ Please enter a valid choice (1-3).");
            }
        }
    }
}

