package knapSack.models;



public class Item {

    private double weight;
    private double value;

    public Item(double weight, double value) {
        this.weight = weight;
        this.value = value;
    }
    public double getWeight() {
        return weight;
    }
    public double getValue() {
        return value;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Double.compare(item.weight, weight) == 0 &&
               Double.compare(item.value, value) == 0;
    }
}

