package TSP;

import java.util.*;
import TSP.model.City;
import TSP.model.TspSolve;
import TSP.Algo.Greedy;
import TSP.Algo.LocalSearch;
import TSP.Algo.SimulatedAnnealing;
import TSP.readtspfile.ReadFile;


public class TspMain {

    public static void main(String[] args) throws Exception {

        String path = "berlin52.tsp";

        List<City> cities = ReadFile.read(path);

        System.out.println("Cities: " + cities.size());

        System.out.println("\n=== GREEDY ===");

        TspSolve greedy = Greedy.run(cities);

        System.out.println(
                "Distance = " + greedy.getDistance()
        );

        System.out.println("\n=== LOCAL SEARCH ===");

        TspSolve local =LocalSearch.run(greedy,true);

        System.out.println(
                "Distance = " + local.getDistance()
        );

        System.out.println("\n=== SIMULATED ANNEALING ===");

        TspSolve sa =SimulatedAnnealing.run(
                greedy,
                10000,
                0.995,
                100000
        );

        System.out.println(
                "Distance = " + sa.getDistance()
        );
    }
}
