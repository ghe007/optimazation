package TSP.model;

import java.util.*;

 /*this class represents a solution to the TSP problem
  it contains a list of cities and a method to calculate
  the total distance of the tour
  */
public class TspSolve {

    public List<City> tour;

    public TspSolve(List<City> tour) {
        this.tour = new ArrayList<>(tour);
    }

    public double getDistance() {

        double total = 0;

        for (int i = 0; i < tour.size() - 1; i++) {
            total += tour.get(i).distanceTo(tour.get(i + 1));
        }

        total += tour.get(tour.size() - 1).distanceTo(tour.get(0));

        return total;
    }

    public TspSolve copy() {
        return new TspSolve(new ArrayList<>(tour));
    }
}