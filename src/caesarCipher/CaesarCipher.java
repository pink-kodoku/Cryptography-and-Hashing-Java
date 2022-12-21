package caesarCipher;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CaesarCipher {
    private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int KEY = 3;

    public static void main(String[] args) {
        String m = "My name is Balazs Holczer. I am from Budapest, Hungary. I am qualified as a physicist. At the moment I am working as a simulation engineer at a multinational company. I have been interested in algorithms and data structures and its implementations especially in Java since university. Later on I got acquainted with machine learning techniques, artificial intelligence, numerical methods and recipes such as solving differential equations, linear algebra, interpolation and extrapolation. These things may prove to be very very important in several fields: software engineering, research and development or investment banking. I have a special addiction to quantitative models such as the Black-Scholes model, or the Merton-model.";
        String encryptedText = encrypt(m);

        frequency_analysis_crack(encryptedText);
    }

    public static String encrypt(String str) {
        StringBuilder encryptedString = new StringBuilder();
        str = str.toUpperCase();

        for (char ch : str.toCharArray()) {
            int index = ALPHABET.indexOf(ch);
            if (index != -1) {
                index = Math.floorMod((index + KEY), ALPHABET.length());
                encryptedString.append(ALPHABET.charAt(index));
            }
        }

        return encryptedString.toString();
    }

    public static String decrypt(String str) {
        StringBuilder decryptedString = new StringBuilder();

        for (char ch : str.toCharArray()) {
            int index = ALPHABET.indexOf(ch);
            index = Math.floorMod((index - KEY), ALPHABET.length());

            decryptedString.append(ALPHABET.charAt(index));
        }

        return decryptedString.toString();
    }

    public static void bruteForceAttack(String encryptedText) {
        for (int i = 0; i < ALPHABET.length(); i++) {
            StringBuilder plainText = new StringBuilder();
            for (char ch : encryptedText.toCharArray()) {
                int index = ALPHABET.indexOf(ch);
                index = Math.floorMod(index - i, ALPHABET.length());
                plainText.append(ALPHABET.charAt(index));
            }
            System.out.println(plainText);
        }
    }

    public static Map<Character, Integer> frequencyAnalysis(String text) {
        HashMap<Character, Integer> frequencies = new HashMap<>();

        for (char ch : text.toCharArray()) {
            if (!frequencies.containsKey(ch)) {
                frequencies.put(ch, 0);
            }
            if (ALPHABET.indexOf(ch) != -1) {
                frequencies.put(ch, frequencies.get(ch) + 1);
            }
        }

        return frequencies;
    }

    public static void frequency_analysis_crack(String encryptedText) {
        Map<Character, Integer> freq = frequencyAnalysis(encryptedText);
        var list = freq.entrySet()
          .stream()
          .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).toList();

        var mostRepeated1 = list.get(0);
        var mostRepeated2 = list.get(0);

        System.out.println("The key is " + (ALPHABET.indexOf(mostRepeated1.getKey()) - ALPHABET.indexOf("E"))
          + " or " + (ALPHABET.indexOf(mostRepeated2.getKey()) - ALPHABET.indexOf("E")));
    }
}
