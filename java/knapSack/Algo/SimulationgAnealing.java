package knapSack.Algo;

import java.util.ArrayList;
import java.util.Random;

import knapSack.Utils.Helper;
import knapSack.models.Item;
import knapSack.models.KnapSack;


public class SimulationgAnealing {
    

public static KnapSack solve(ArrayList<Item> allItems, KnapSack knapSack, double initialTemp, double coolingRate, int maxIterations) {
    Random random = new Random();
    KnapSack current = new KnapSack(knapSack.getCapacity());
    current.updateItemsList(Helper.InitialFeasibleSolution(current, allItems));  

    KnapSack best = new KnapSack(knapSack.getCapacity());
    best.updateItemsList(current.getItems());

    double temp = initialTemp;

    for (int iter = 0; iter < maxIterations; iter++) {

        ArrayList<ArrayList<Item>> neighbourSets =
                Helper.generateOneExchangeNeighborSets(current, allItems);

        if (neighbourSets.isEmpty()) {
            temp *= coolingRate;
            continue;
        }

        ArrayList<Item> chosenItems =
                neighbourSets.get(random.nextInt(neighbourSets.size()));

        KnapSack neighbour = new KnapSack(knapSack.getCapacity());
        neighbour.updateItemsList(chosenItems);

        double delta = neighbour.getTotalValue() - current.getTotalValue();

        if (delta > 0 || acceptanceProbability(delta, temp) > random.nextDouble()) {
            current.updateItemsList(neighbour.getItems());
        }

        if (current.getTotalValue() > best.getTotalValue()) {
            best.updateItemsList(current.getItems());
        }

        temp *= coolingRate;
    }

    return best;
}

private static double acceptanceProbability(double delta, double temp) {
        return Math.exp(delta / temp);
    }

}
