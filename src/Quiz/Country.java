package Quiz;

import java.io.FileInputStream;

public class Country {
    String countryName;
    String capital;
    String continent;
    String imageFilePath;

    public Country(String countryName, String capital, String continent,String imageFilePath){
        setCountryName(countryName);
        setImageFilePath(imageFilePath);
        setContinent(continent);
        setCapital(capital);
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName.toUpperCase();
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nCountry Name: ["+getCountryName()+"]\n");
        sb.append("Country Capital: ["+getCapital()+"]\n");
        sb.append("Continent: ["+getContinent()+"]\n");
        sb.append("Country Image FilePath: ["+getImageFilePath()+"]\n");
        return sb.toString();
    }
}
