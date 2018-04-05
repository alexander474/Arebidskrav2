package Quiz;

import java.io.FileInputStream;

public class Country {
    String countryName;
    String capital;
    String imageFilePath;

    public Country(String countryName, String capital,String imageFilePath){
        setCountryName(countryName);
        setImageFilePath(imageFilePath);
        setCapital(capital);
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
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
        sb.append("Country Image FilePath: ["+getImageFilePath()+"]\n");
        return sb.toString();
    }
}
