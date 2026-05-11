package TSP.Algo;

import java.util.*;

import TSP.model.City;
import TSP.model.TspSolve;

public class LocalSearch {

    public static TspSolve run(
            TspSolve initialSolution,
            boolean best_improvement
    ) {
        // we start from the initial solution and we try to improve it by swapping two cities
        TspSolve current = initialSolution.copy();

        boolean improved = true;
        // we loop until we cannot improve the solution 
        while (improved) {

            improved = false;

            TspSolve bestNeighbor = current;
            double bestDistance = current.getDistance();
            // we loop through all the pairs of cities and we swap them to get a neighbor solution
            for (int i = 1; i < current.tour.size() - 1; i++) {

                for (int j = i + 1; j < current.tour.size(); j++) {

                    TspSolve neighbor = swap(current, i, j);

                    double neighborDistance = neighbor.getDistance();

                    if (neighborDistance < bestDistance) {

                        if (!best_improvement) {
                            current = neighbor;
                            improved = true;
                            break;
                        }

                        bestNeighbor = neighbor;
                        bestDistance = neighborDistance;
                        improved = true;
                    }
                }

                if (improved && !best_improvement) {
                    break;
                }
            }

            if (best_improvement && improved) {
                current = bestNeighbor;
            }
        }

        return current;
    }

    private static TspSolve swap(
            TspSolve solution,
            int i,
            int j
    ) {

        List<City> newTour = new ArrayList<>(solution.tour);

        Collections.swap(newTour, i, j);

        return new TspSolve(newTour);
    }
}
