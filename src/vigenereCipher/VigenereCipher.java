package vigenereCipher;

public class VigenereCipher {
    private static String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        String s = "Hello world my name is John Doe hahahah";
        System.out.println(encrypt(s, "cat"));
        System.out.println(decrypt(encrypt(s, "cat"), "cat"));
    }

    public static String encrypt(String plaintext, String secret) {
        plaintext = plaintext.toUpperCase();
        int secretIndex = 0;
        StringBuilder encryptedString = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (secretIndex == secret.length()) {
                secretIndex = 0;
            }

            if (ALPHABET.indexOf(ch) != -1) {
                int index = Math.floorMod(ALPHABET.indexOf(ch)
                  + ALPHABET.indexOf(secret.charAt(secretIndex++)), ALPHABET.length());
                encryptedString.append(ALPHABET.charAt(index));
            }
        }

        return encryptedString.toString();
    }

    public static String decrypt(String plaintext, String secret) {
        plaintext = plaintext.toUpperCase();
        int secretIndex = 0;
        StringBuilder decryptedString = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (secretIndex == secret.length()) {
                secretIndex = 0;
            }
            int index = Math.floorMod(ALPHABET.indexOf(ch)
              - ALPHABET.indexOf(secret.charAt(secretIndex++)), ALPHABET.length());
            decryptedString.append(ALPHABET.charAt(index));
        }

        return decryptedString.toString();
    }
}
