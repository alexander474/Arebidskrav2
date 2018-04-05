package Quiz;

import java.util.ArrayList;
import java.util.List;

public class CountryHandler {
    List<Country> countries;


    public CountryHandler(){
       countries = new ArrayList<>();
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


}
