package TSP.Algo;

import java.util.*;

import TSP.model.City;
import TSP.model.TspSolve;



public class SimulatedAnnealing {

    public static TspSolve run(
            TspSolve initialSolution,
            double temperature,
            double coolingRate,
            int iterations
    ) {
        // we start from the initial solution and we try to improve it by swapping two cities
        Random random = new Random();

        TspSolve current = initialSolution.copy();
        TspSolve best = current.copy();
        // we loop for a given number of iterations and 
        // we try to find a better solution by swapping two cities
        for (int k = 0; k < iterations; k++) {

            int i = random.nextInt(current.tour.size());
            int j = random.nextInt(current.tour.size());

            TspSolve neighbor = swap(current, i, j);

            double currentDistance = current.getDistance();
            double neighborDistance = neighbor.getDistance();

            double delta = neighborDistance - currentDistance;

            if (delta < 0 ||
                    Math.random() < Math.exp(-delta / temperature)) {

                current = neighbor;
            }

            if (current.getDistance() < best.getDistance()) {
                best = current.copy();
            }

            temperature *= coolingRate;

            if (temperature < 0.0001) {
                break;
            }
        }

        return best;
    }

    private static TspSolve swap(
            TspSolve solution,
            int i,
            int j
    ) {
        List<City> newTour = new ArrayList<>(solution.tour);
        
        // we create a new tour by swapping the cities at positions i and j
        Collections.swap(newTour, i, j);

        return new TspSolve(newTour);
    }
}
