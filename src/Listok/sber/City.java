package Listok.sber;

public class City {
    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    public City(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    public City(String[] infoArray) {
        this.name = infoArray[1];
        this.region = infoArray[2];
        this.district = infoArray[3];
        this.population = Integer.parseInt(infoArray[4]);
        this.foundation = infoArray[5];
    }

    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }
}
