package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {
    private final Map<String, String> codeToLanguage = new HashMap<>();
    private final Map<String, String> languageToCode = new HashMap<>();

    // TODO Task: pick appropriate instance variables to store the data necessary for this class

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] res = line.split("\\s+");
                if (res.length > 2) {
                    for (int j = 1; j < res.length - 1; j++) {
                        res[0] = res[0] + " " + res[j];
                    }
                }
                codeToLanguage.put(res[res.length - 1], res[0]);
                languageToCode.put(res[0], res[res.length - 1]);
            }

            // TODO Task: use lines to populate the instance variable
            //           tip: you might find it convenient to create an iterator using lines.iterator()
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }
    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */

    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Returns the name of the language for the given language code.
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        // TODO Task: update this code to use your instance variable to return the correct value
        return codeToLanguage.get(code);
    }

    /**
     * Returns the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        // TODO Task: update this code to use your instance variable to return the correct value
        return languageToCode.get(language);
    }

    /**
     * Returns how many languages are included in this code converter.
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        // TODO Task: update this code to use your instance variable to return the correct value
        return codeToLanguage.size();
    }
}
