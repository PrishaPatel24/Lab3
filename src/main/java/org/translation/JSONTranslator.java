package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {
    HashMap<String, HashMap<String, String>> translation = new HashMap<>();

    public static void main(String[] args) {
        JSONTranslator translator = new JSONTranslator();
        System.out.println(translator.getCountryLanguages("can"));
    }

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {
            String jsonString = Files.readString(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                HashMap<String, String> languageMap = new HashMap<>();
                for (String key : obj.keySet()) {
                    if (!key.equals("alpha2") && !key.equals("alpha3") && !key.equals("id")) {
                        languageMap.put(key, obj.getString(key));
                    }
                }
                translation.put(obj.getString("alpha3"), languageMap);
            }

            // TODO Task: use the data in the jsonArray to populate your instance variables
            //            Note: this will likely be one of the most substantial pieces of code you write in this lab.

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // TODO Task: return an appropriate list of language codes,
        //            but make sure there is no aliasing to a mutable object
        HashMap<String, String> languageMap = translation.get(country);
        return new ArrayList<>(languageMap.keySet());
    }

    @Override
    public List<String> getCountries() {
        // TODO Task: return an appropriate list of country codes,
        //            but make sure there is no aliasing to a mutable object
        return new ArrayList<>(translation.keySet());
    }

    @Override
    public String translate(String country, String language) {
        HashMap<String, String> languageMap = translation.get(country);
        return languageMap.get(language);
    }
}
