package life.connect_it.travellingsalesman.helper;

import java.util.ArrayList;
import java.util.List;

import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

public class WitnessCalculator {

    public static ArrayList<ArrayList<Integer>> calculateWitnesses(int salesPointsNumber) {
        ArrayList<Integer> basicList = new ArrayList<>();
        for (int i = 0; i < salesPointsNumber; i++) {
            basicList.add(i);
        }
        return createPermutation(basicList);
    }

    private static ArrayList<ArrayList<Integer>> createPermutation(ArrayList<Integer> basicList) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<>();
        permutations.add(basicList);
        for (int i = 0; i < basicList.size() - 1; i++) {
            int permutationSize = permutations.size();
            for (int k = 0; k < i + 1; k++) {
                for (int j = 0; j < permutationSize; j++) {
                    ArrayList newList = new ArrayList(permutations.get(j));
                    permutations.add(newList);
                    swapElements(newList, i + 1, k);
                }
            }
        }
        return permutations;
    }

    private static void swapElements(ArrayList<Integer> arrayList, int currentElement, int indexToSwap) {
        Integer elementToSwap = arrayList.get(currentElement);
        arrayList.set(currentElement, arrayList.get(indexToSwap));
        arrayList.set(indexToSwap, elementToSwap);
    }
}
