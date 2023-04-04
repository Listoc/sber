package Listok.sber;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.InputMismatchException;

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

    public static Map<String, Integer> maxPopulation(List<City> listOfCities) {
        var arrayOfCities = listOfCities.toArray(new City[0]);
        int max = 0;
        int indexOfMax = -1;
        for(int i = 0; i < arrayOfCities.length; ++i) {
            if(arrayOfCities[i].getPopulation() > max) {
                max = arrayOfCities[i].getPopulation();
                indexOfMax = i;
            }
        }
        var res = new HashMap<String, Integer>();
        res.put("Index", indexOfMax);
        res.put("Population", max);
        return res;
    }

    public static Map<String, Integer> countOfCitiesInRegions(List<City> listOfCities) {
        var arrayOfCities = listOfCities.toArray(new City[0]);
        var res = new HashMap<String, Integer>();
        for(int i = 0; i < arrayOfCities.length; ++i) {
            if(res.containsKey(arrayOfCities[i].getRegion())) {
                res.put(arrayOfCities[i].getRegion(), res.get(arrayOfCities[i].getRegion()) + 1);
            } else {
                res.put(arrayOfCities[i].getRegion(), 1);
            }
        }
        return res;
    }

    public static void printMenu() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.print("""
                Choose one:
                0 - exit
                1 - print list of cities
                2 - sort list by city name (alphabetical order)
                3 - sort list by district (alphabetical order, case sensitive)
                4 - find city with most population
                5 - count cities by region
                """);
        System.out.print("Your choice: ");
    }

    public static void main(String[] args) {
        var listOfCities = new ArrayList<City>(1200);
        var in = new Scanner(System.in);
        int menuChoice = -1;
        try {
            readCities(listOfCities, "citiesList.csv");
            do {
                printMenu();
                menuChoice = in.nextInt();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                switch(menuChoice) {
                    case 1:
                        printCities(listOfCities);
                        break;

                    case 2:
                        sortByName(listOfCities);
                        break;

                    case 3:
                        sortByDistrict(listOfCities);
                        break;

                    case 4:
                        System.out.println(maxPopulation(listOfCities));
                        break;

                    case 5:
                        System.out.println(countOfCitiesInRegions(listOfCities));
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Wrong choice!");
                }
                System.out.print("Press enter to continue...");
                in.nextLine();
                in.nextLine();
            } while(menuChoice != 0);
        } catch (IOException e) {
            System.out.println("Problems with file!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.print("You need to input number!");
        }

    }

}
