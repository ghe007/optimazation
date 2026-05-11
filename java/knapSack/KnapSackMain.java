package knapSack;



import java.util.ArrayList;
import knapSack.Algo.GreedyAlgo;
import knapSack.Algo.LocalSearch;
import knapSack.Utils.ItemProvider;
import knapSack.Utils.KnapSackProvider;
import knapSack.models.Item;
import knapSack.models.KnapSack;


public class KnapSackMain {
    public static void main(String[] args) {
      int numberOfItems = 700;
      double range_weight = 100.0;
      double range_value = 150.0;

      ArrayList<Item> items = ItemProvider.createItem(range_weight, range_value, numberOfItems);
    // create Knapsack with Capacity  = 5*n/4 : n = number of items
      KnapSack knapSak = KnapSackProvider.createKnapSack((5*numberOfItems)/4.0);
      GreedyAlgo.run(items, knapSak,false);
      System.out.println("\nGreedy Solution:");
      System.out.println("============");
      System.out.println("knapsack Capacity "+knapSak.getCapacity());
      System.out.printf("total weight in knapsack %.3f \n",knapSak.getTotalWeight());
      System.out.println("number of items "+knapSak.getNumberOfItems());
      System.out.printf("total value in knapsack %.3f \n",knapSak.getTotalValue());
      System.out.println("============");


       System.out.println("\nLocal Search Solution:");
       KnapSack knapSak2 = KnapSackProvider.createKnapSack((5*numberOfItems)/4.0);
       KnapSack result =  LocalSearch.run(knapSak2, items, 30);
       System.out.println("============");
       System.out.println("knapsack Capacity "+result.getCapacity());
       System.out.printf("total weight in knapsack %.3f \n",result.getTotalWeight());
       System.out.println("number of items "+result.getNumberOfItems());
       System.out.printf("total value in knapsack %.3f \n",result.getTotalValue());
       System.out.println("============");
      
    }
}

