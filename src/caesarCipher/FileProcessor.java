package caesarCipher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private List<String> words = new ArrayList<>();

    public FileProcessor() {
        getData();
    }

    private void getData() {
        try (
          FileReader fileReader = new FileReader(new File("src/caesarCipher/words.txt"));
          BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line.toUpperCase());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getWords() {
        return words;
    }
}
