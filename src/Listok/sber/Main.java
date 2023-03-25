package Listok.sber;

import java.io.IOException;
import java.nio.file.Path;
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

    public static void main(String[] args) {
        var listOfCities = new ArrayList<City>();
        try {
            readCities(listOfCities, "citiesList.csv");
            printCities(listOfCities);
        } catch (IOException e) {
            System.out.println("Problems with file!");
        }
    }

}
