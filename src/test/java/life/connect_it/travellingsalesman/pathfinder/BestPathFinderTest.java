package life.connect_it.travellingsalesman.pathfinder;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.*;

import life.connect_it.travellingsalesman.helper.WitnessCalculator;

public class BestPathFinderTest {
    @Test
    public void testFindPath() throws Exception {
        List<List<Integer>> witnesses = WitnessCalculator.calculateWitnesses(5);
        List<List<Integer>> witnessesCopy = new ArrayList<>(witnesses);
        witnesses.forEach(witness -> {
            witnessesCopy.remove(witness);
            if (witnessesCopy.contains(witness)){
                System.out.println(witness.toString());
            }
        });
    }

}