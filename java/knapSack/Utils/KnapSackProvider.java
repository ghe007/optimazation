package knapSack.Utils;


import knapSack.models.KnapSack;

public class KnapSackProvider {
    public static KnapSack createKnapSack(double capacity) {
        return new KnapSack(capacity);
    }
}

