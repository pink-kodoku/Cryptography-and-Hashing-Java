package caesarCipher;

import java.util.ArrayList;
import java.util.List;

public class DetectEnglishLang {
    private FileProcessor fileProcessor = new FileProcessor();
    private List<String> englishWords;

    public DetectEnglishLang() {
        englishWords = fileProcessor.getWords();
    }

    public int countEnglishWords(String text) {
        text = text.toUpperCase();
        String[] words = text.split(" ");
        int matches = 0;

        for (String s : words) {
            if (englishWords.contains(s)) {
                matches++;
            }
        }

        return matches;
    }

    public boolean isEnglish(String text) {
        int matches = countEnglishWords(text);

        if ((float) matches / text.split(" ").length * 100 >= 60) {
            return true;
        }

        return false;
    }
}
