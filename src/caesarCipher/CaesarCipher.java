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
        String m = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";
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
