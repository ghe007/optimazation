package TSP.Algo;

import java.util.*;

import TSP.model.City;
import TSP.model.TspSolve;

public class Greedy {

    public static TspSolve run(List<City> cities) {
        // we start from the first city and always go to the nearest unvisited city
        List<City> unvisited = new ArrayList<>(cities);
        List<City> tour = new ArrayList<>();
        
        City current = unvisited.remove(0);
        // we add the first city to the tour
        tour.add(current);
        // we loop until we have visited all the cities
        while (!unvisited.isEmpty()) {

            City nearest = null;
            double minDistance = Double.MAX_VALUE;

            for (City city : unvisited) {

                double distance = current.distanceTo(city);

                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = city;
                }
            }

            tour.add(nearest);
            unvisited.remove(nearest);

            current = nearest;
        }

        return new TspSolve(tour);
    }
}
