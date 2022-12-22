package oneTimePadCipher;

import java.util.Random;

public class OneTimePad {
    private static String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random random = new Random();

    public static void main(String[] args) {
        String s = "My name is Balazs Holczer. I am from Budapest, Hungary. I am qualified as a physicist. At the moment I am working as a simulation engineer at a multinational company. I have been interested in algorithms and data structures and its implementations especially in Java since university. Later on I got acquainted with machine learning techniques, artificial intelligence, numerical methods and recipes such as solving differential equations, linear algebra, interpolation and extrapolation. These things may prove to be very very important in several fields: software engineering, research and development or investment banking. I have a special addiction to quantitative models such as the Black-Scholes model, or the Merton-model.";
        int[] randomKeys = generateKeys(s.length());
        String encrypted = encrypt(s, randomKeys);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted, randomKeys);
        System.out.println(decrypted);
    }

    public static String encrypt(String plainText, int[] keys) {
        plainText = plainText.toUpperCase();
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int keyIndex = keys[i];
            int charIndex = ALPHABET.indexOf(plainText.charAt(i));
            char cipherChar = ALPHABET.charAt(Math.floorMod(charIndex + keyIndex, ALPHABET.length()));

            cipherText.append(cipherChar);
        }

        return cipherText.toString();
    }

    public static String decrypt(String plainText, int[] keys) {
        plainText = plainText.toUpperCase();
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int keyIndex = keys[i];
            int charIndex = ALPHABET.indexOf(plainText.charAt(i));
            char cipherChar = ALPHABET.charAt(Math.floorMod(charIndex - keyIndex, ALPHABET.length()));

            decryptedText.append(cipherChar);
        }

        return decryptedText.toString();
    }

    public static int[] generateKeys(int n) {
        int[] randomSequence = new int[n];

        for (int i = 0; i < n; i++) {
            randomSequence[i] = random.nextInt(0, ALPHABET.length() - 1);
        }

        return randomSequence;
    }
}
