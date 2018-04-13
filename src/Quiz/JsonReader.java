package Quiz;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class JsonReader {

    JSONParser parser = new JSONParser();

    public JsonReader() {
    }

    /**
     * Reads a json file and gets some spesific data. puts the data into different meters.
     * @param FilePath
     * @return
     */
    public ArrayList<Country> readQuestions(String FilePath){
        ArrayList<Country> countries;
        countries = new ArrayList<>();
        try {

            Object obj = parser.parse(new FileReader(FilePath));
            JSONArray jsonArray = (JSONArray) obj;
            for(Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                String countryName = (String) jsonObject.get("countryName");
                String capital = (String) jsonObject.get("capital");
                String continent = (String) jsonObject.get("continent");
                String imageFilePath = (String) jsonObject.get("imageFilePath");

                String fixedCountryName = removeSpecialChars(countryName);
                String fixedCapitalName = removeSpecialChars(capital);

                    countries.add(new Country(fixedCountryName,fixedCapitalName,continent,imageFilePath));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    public String removeSpecialChars(String wordToChange){
        String changedWord = "";
        for(char c : wordToChange.toCharArray()){
            if(c == '_'){
                c = ' ';
            }
            changedWord += c;
        }
        return changedWord;
    }
}
