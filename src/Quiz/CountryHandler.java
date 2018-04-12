package Quiz;
import java.util.ArrayList;
import java.util.List;

public class CountryHandler {
    List<Country> countries;
    List<Country> countriesInContinent;
    JsonReader jsonReader = new JsonReader();


    /**
     * initialising the lists
     * */
    public CountryHandler(){
       countries = jsonReader.readQuestions("src/Quiz/Questions.json"); // populates from the json file
       countriesInContinent = new ArrayList<>();
    }

    /**
     * Adds a country too the list
     * */
    public void addCountry(Country c){
        countries.add(c);
    }

    /**
     * Gets a country from the list that matches the parameter and returns that country
     * */
    public Country getCountry(String countryName) {
        for (Country c : countries) {
            if (countryName.equals(c.getCountryName())) {
                return c;
            }

        }
        return null;
    }

    /**
     * Returns a list of all the countries
     * */
    public List<Country> getAllCountries(){
        return countries;
    }

    /**
     * Makes a list of all the countries in a continent and returns the list
     * */
    public List<Country> getAllCountriesInContinent(String continent){
        countriesInContinent = new ArrayList<>();

        for(Country c : countries){
            if(continent.equals(c.getContinent())){
                countriesInContinent.add(c);
            }
        }
        System.out.println("Allcontries in continent: "+countriesInContinent.size());
        return countriesInContinent;
    }


}
