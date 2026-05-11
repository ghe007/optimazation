package knapSack.Algo;

import java.util.ArrayList;
import java.util.Random;


import knapSack.models.Item;
import knapSack.models.KnapSack;

public class GreedyAlgo {

    public static KnapSack run(ArrayList<Item> items, KnapSack knapSak,boolean deterministic) {
        
        //Caculate the value-to-weight ratio for each item
        ArrayList<Item> sortedItems = value_to_weight(items);
        if (deterministic) {
            for(Item i : sortedItems){
                if(knapSak.getTotalWeight()+i.getWeight() <= knapSak.getCapacity()){
                    knapSak.addItem(i);
                }
            }
        }else{
            int pick_number = 3;
            Random random = new Random();
            var _items = new ArrayList<>(sortedItems);
                
            for(int i = 0; i<_items.size();i++){
                
               // we pick randomly the min between the pick number 
               // and the number of items left to choose from
               int chose_number = Math.min(pick_number, _items.size());
               int index = random.nextInt(chose_number);
               Item item = _items.get(index);

               if(knapSak.getTotalWeight()+item.getWeight() <= knapSak.getCapacity()){
                    knapSak.addItem(item);
                    _items.remove(index);
                }

                

            }
        }
       
        return knapSak;
    }

    private static ArrayList<Item> value_to_weight(ArrayList<Item> items){
       items.sort((a, b) -> Double.compare(
        b.getValue() / b.getWeight(),
        a.getValue() / a.getWeight()
        ));
        
        return items;
    }
}
