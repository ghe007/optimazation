package knapSack.Utils;



import java.util.ArrayList;
import java.util.List;

import knapSack.models.Item;
import knapSack.models.KnapSack;

// helper class to provide utility methods for the algorithms
public class Helper {
        public static ArrayList<Item> InitialFeasibleSolution(KnapSack knapSak , ArrayList<Item> items){
        ArrayList<Item> feasible_solution = new ArrayList<>(); 
        double currentWeight = 0;
         for (int i =0 ; i < items.size();i++){
            Item item  = items.get(i);;
            if (currentWeight+item.getWeight() <= knapSak.getCapacity()){
                feasible_solution.add(item);
                currentWeight += item.getWeight();
            }
    
         }
        
        return feasible_solution;

    }


   public static ArrayList<ArrayList<Item>> generate2ExchangeNeighborSets(KnapSack currentSolution, List<Item> allItems) {
    ArrayList<ArrayList<Item>> neighborSets = new ArrayList<>();
    
    ArrayList<Item> inKnapsack = currentSolution.getItems();
    ArrayList<Item> outKnapsack = new ArrayList<>(allItems);
    outKnapsack.removeAll(inKnapsack);

    double currentWeight = currentSolution.getTotalWeight();
    double capacity = currentSolution.getCapacity();


    // we should have at least 2 items inside and 2 items outside to swap
    if (inKnapsack.size() < 2 || outKnapsack.size() < 2) {
        return neighborSets; 
    }

    // Iterate through pairs to remove (i and j)
    for (int i = 0; i < inKnapsack.size(); i++) {
        for (int j = i + 1; j < inKnapsack.size(); j++) {
            Item r1 = inKnapsack.get(i);
            Item r2 = inKnapsack.get(j);

            // Iterate through pairs to add (k and l)
            for (int k = 0; k < outKnapsack.size(); k++) {
                for (int l = k + 1; l < outKnapsack.size(); l++) {
                    Item a1 = outKnapsack.get(k);
                    Item a2 = outKnapsack.get(l);

                    // Calculate weight delta
                    double weightAfter = currentWeight - r1.getWeight() - r2.getWeight() 
                                         + a1.getWeight() + a2.getWeight();

                    if (weightAfter <= capacity) {
                        ArrayList<Item> neighbor = new ArrayList<>(inKnapsack);
                        neighbor.remove(r1);
                        neighbor.remove(r2);
                        neighbor.add(a1);
                        neighbor.add(a2);
                        neighborSets.add(neighbor);
                    }
                }
            }
        }
    }

    return neighborSets;
}


/**
 * Finds the highest value solution from a list of candidate item sets.
 */
public static ArrayList<Item> getBestFromNeighborhood(ArrayList<ArrayList<Item>> neighborSets) {
    // 1. Guard clause for empty input
    if (neighborSets == null || neighborSets.isEmpty()) {
        return new ArrayList<Item>(); // Return empty list rather than null to avoid crashes
    }

    ArrayList<Item> bestSet = neighborSets.get(0);
    double maxValue = calculateValue(bestSet);

    // 2. Compare every candidate solution in the list
    for (int i = 1; i < neighborSets.size(); i++) {
        ArrayList<Item> currentSet = neighborSets.get(i);
        double currentTotalValue = calculateValue(currentSet);

        // 3. Keep the one with the highest value
        if (currentTotalValue > maxValue) {
            maxValue = currentTotalValue;
            bestSet = currentSet;
        }
    }

    return bestSet;
}

/**
 * Helper method to sum values of items in a list
 */
public static double calculateValue(ArrayList<Item> items) {
    double total = 0;
    for (Item item : items) {
        total += item.getValue();
    }
    return total;
}

public static ArrayList<ArrayList<Item>> generateOneExchangeNeighborSets(
        KnapSack currentSolution, List<Item> allItems) {

    ArrayList<ArrayList<Item>> neighborSets = new ArrayList<>();

    ArrayList<Item> inKnapsack  = currentSolution.getItems();
    ArrayList<Item> outKnapsack = new ArrayList<>(allItems);
    outKnapsack.removeAll(inKnapsack);

    double currentWeight = currentSolution.getTotalWeight();
    double capacity      = currentSolution.getCapacity();

    // Need at least 1 in and 1 out to swap
    if (inKnapsack.isEmpty() || outKnapsack.isEmpty()) {
        return neighborSets;
    }

    // Remove 1 item (r) and add 1 item (a)
    for (int i = 0; i < inKnapsack.size(); i++) {
        Item r = inKnapsack.get(i);

        for (int k = 0; k < outKnapsack.size(); k++) {
            Item a = outKnapsack.get(k);

            double weightAfter = currentWeight - r.getWeight() + a.getWeight();

            if (weightAfter <= capacity) {
                ArrayList<Item> neighbor = new ArrayList<>(inKnapsack);
                neighbor.remove(r);
                neighbor.add(a);
                neighborSets.add(neighbor);
            }
        }
    }

    return neighborSets;
}

}


