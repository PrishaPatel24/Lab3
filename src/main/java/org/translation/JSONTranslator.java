package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    // TODO Task: pick appropriate instance variables for this class
    Map<String, String> innerMap = new HashMap<>();
    Map<String, Map<String, String>> JSONMap = new HashMap<>();

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

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            // TODO Task: use the data in the jsonArray to populate your instance variables
            //            Note: this will likely be one of the most substantial pieces of code you write in this lab.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                for (String key in jsonObject.keys()) {
//                    if key.!equals("id") && key.equals!("alpha2") && key.!equals("alpha3") {
//                        innerMap.put(key, jsonObject.getString(key));
//                    }
//                }
//
//                }
                innerMap.put("ar", jsonObject.getString("ar"));
                innerMap.put("bg", jsonObject.getString("bg"));
                innerMap.put("cs", jsonObject.getString("cs"));
                innerMap.put("da", jsonObject.getString("da"));
                innerMap.put("de", jsonObject.getString("de"));
                innerMap.put("el", jsonObject.getString("el"));
                innerMap.put("en", jsonObject.getString("en"));
                innerMap.put("eo", jsonObject.getString("eo"));
                innerMap.put("es", jsonObject.getString("es"));
                innerMap.put("et", jsonObject.getString("et"));
                innerMap.put("eu", jsonObject.getString("eu"));
                innerMap.put("fa", jsonObject.getString("fa"));
                innerMap.put("fi", jsonObject.getString("fi"));
                innerMap.put("fr", jsonObject.getString("fr"));
                innerMap.put("hr", jsonObject.getString("hr"));
                innerMap.put("hu", jsonObject.getString("hu"));
                innerMap.put("hy", jsonObject.getString("hy"));
                innerMap.put("it", jsonObject.getString("it"));
                innerMap.put("ja", jsonObject.getString("ja"));
                innerMap.put("ko", jsonObject.getString("ko"));
                innerMap.put("lt", jsonObject.getString("lt"));
                innerMap.put("nl", jsonObject.getString("nl"));
                innerMap.put("no", jsonObject.getString("no"));
                innerMap.put("pl", jsonObject.getString("pl"));
                innerMap.put("pt", jsonObject.getString("pt"));
                innerMap.put("ro", jsonObject.getString("ro"));
                innerMap.put("ru", jsonObject.getString("ru"));
                innerMap.put("sk", jsonObject.getString("sk"));
                innerMap.put("sl", jsonObject.getString("sl"));
                innerMap.put("sr", jsonObject.getString("sr"));
                innerMap.put("sv", jsonObject.getString("sv"));
                innerMap.put("th", jsonObject.getString("th"));
                innerMap.put("uk", jsonObject.getString("uk"));
                innerMap.put("zh", jsonObject.getString("zh"));
                innerMap.put("zh-tw", jsonObject.getString("zh-tw"));

                JSONMap.put(jsonObject.getString("alpha3"), innerMap);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // TODO Task: return an appropriate list of language codes,
        //            but make sure there is no aliasing to a mutable object
        List<String> lst = new ArrayList<>(JSONMap.get(country).keySet());
        return lst;
    }

    @Override
    public List<String> getCountries() {
        // TODO Task: return an appropriate list of country codes,
        //            but make sure there is no aliasing to a mutable object
        List<String> lst = new ArrayList<>(JSONMap.keySet());
        return lst;
    }

    @Override
    public String translate(String country, String language) {
        // TODO Task: complete this method using your instance variables as needed
//        return JSONMap.get(country).get(language);
        Map<String, String> languages = JSONMap.get(country);
        if (languages != null) {
            return languages.get(language);
        }
        return null;
    }
}
