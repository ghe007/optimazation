
package knapSack.models;

import java.util.ArrayList;

public class KnapSack {

    private double capacity = 0;
    private ArrayList<Item> items;
    private double totalWeight = 0;
    private double totalValue = 0;

    
    public KnapSack(){
        items = new ArrayList<>();
    }
   

    public KnapSack(double capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public double getCapacity() {
        return capacity;
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public double getTotalWeight(){
        return totalWeight;
    }

    public void addItem(Item item) {
        items.add(item);
        totalWeight += item.getWeight();
        totalValue += item.getValue();
    }

    public void updateItemsList(ArrayList<Item> items) {
        this.items = items;
        totalWeight = items.stream().mapToDouble(Item::getWeight).sum();
        totalValue = items.stream().mapToDouble(Item::getValue).sum();
    }

    public double getTotalValue(){
        return totalValue;
    }

    

    
}