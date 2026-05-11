package knapSack.Utils;



import java.util.ArrayList;
import java.util.Random;

import knapSack.models.Item;



public class ItemProvider {

    public static ArrayList<Item> createItem(double range_weight, double range_value, int numberOfItems) {
        ArrayList<Item> items = new ArrayList<>();
        Random random = new Random(123);
        for (int i = 0; i < numberOfItems; i++) {
            double weight = random.nextDouble(range_weight+1);
            double value =  random.nextDouble(range_value+1);
            items.add(new Item(weight, value));
        }  
       return items;
    }
}
