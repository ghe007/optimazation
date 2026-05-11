package knapSack.Algo;



import java.util.ArrayList;
import knapSack.Utils.Helper;
import knapSack.models.Item;
import knapSack.models.KnapSack;

public class LocalSearch {
    public static KnapSack run(KnapSack knapSak , ArrayList<Item> items , int iterations){
        return run(knapSak, items, iterations, true);
    }

    public static KnapSack run(KnapSack knapSak , ArrayList<Item> items , int iterations, boolean best_improvment){
    //    ArrayList<Item> initial_solution = Helper.InitialFeasibleSolution(knapSak, items);
        ArrayList<Item> initial_solution = GreedyAlgo.run(items, knapSak, true).getItems();    
        if (initial_solution.isEmpty()) return knapSak; 

       ArrayList<Item> best_solution = initial_solution; 
       // update knapsack with initial solution 
       knapSak.updateItemsList(best_solution); 


       boolean improvement = true;
       while (improvement && iterations > 0) {
            improvement = false;
            // all feasible neighboring solutions of best_solution using 2-2ExchangeNeighborhood
            ArrayList<ArrayList<Item>> neighborSets = Helper.generate2ExchangeNeighborSets(knapSak, items);
            // get the heighest total value solution in neighborSets
            ArrayList<Item> S = Helper.getBestFromNeighborhood(neighborSets);
            // if S > best_solution
            if (Helper.calculateValue(S) > Helper.calculateValue(best_solution)){
                best_solution = S;
                knapSak.updateItemsList(best_solution);
                improvement = true;
            }
            iterations--;
            
        }
        // return sack with best solution
        return  knapSak;
    }

     

}



