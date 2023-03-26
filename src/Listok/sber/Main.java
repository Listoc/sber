package Listok.sber;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void readCities(List<City> listOfCities, String path) throws IOException {
        var in = new Scanner(Path.of(path));
        String[] infoArray;
        while(in.hasNext()) {
            infoArray = in.nextLine().split(";");
            if(infoArray.length < 6) {
                listOfCities.add(new City(
                        infoArray[1],
                        infoArray[2],
                        infoArray[3],
                        Integer.parseInt(infoArray[4]),
                        ""));
            } else {
                listOfCities.add(new City(
                        infoArray[1],
                        infoArray[2],
                        infoArray[3],
                        Integer.parseInt(infoArray[4]),
                        infoArray[5]));
            }
        }
    }

    public static void printCities(List<City> listOfCities) {
        for(var city : listOfCities) {
            System.out.println(city);
        }
    }

    public static void sortByName(List<City> listOfCities) {
        listOfCities.sort((city1, city2) -> city1.getName().compareToIgnoreCase(city2.getName()));
    }

    public static void sortByDistrict(List<City> listOfCities) {

        listOfCities.sort(new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                if(city1.getDistrict().equals(city2.getDistrict())) return city1.getName().compareTo(city2.getName());
                return city1.getDistrict().compareTo(city2.getDistrict());
            }
        });

    }

    public static void main(String[] args) {
        var listOfCities = new ArrayList<City>();
        try {
            readCities(listOfCities, "citiesList.csv");
            sortByName(listOfCities);
            printCities(listOfCities);
            for(int i = 0; i < 100; ++i) System.out.println("-".repeat(100));
            sortByDistrict(listOfCities);
            printCities(listOfCities);
        } catch (IOException e) {
            System.out.println("Problems with file!");
        }
    }

}
