package Quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CountryHandler {
    List<Country> countries;
    List<Country> countriesInContinent;
    JsonReader jsonReader = new JsonReader();


    public CountryHandler(){
       //countries = new ArrayList<>();
       countries = jsonReader.readQuestions("src/Quiz/Questions.json");
       countriesInContinent = new ArrayList<>();
    }

    public void addCountry(Country c){
        countries.add(c);
    }

    public Country getCountry(String countryName) {
        for (Country c : countries) {
            if (countryName.equals(c.getCountryName())) {
                return c;
            }

        }
        return null;
    }

    public List<Country> getAllCountries(){
        return countries;
    }

    public List<Country> getAllCountriesInContinent(String continent){
        for(Country c : countries){
            if(continent.equals(c.continent)){
                countriesInContinent.add(c);
            }
        }
        return countriesInContinent;
    }


}
