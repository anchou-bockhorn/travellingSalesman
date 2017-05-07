package life.connect_it.travellingsalesman;

import java.util.Arrays;
import java.util.List;

import life.connect_it.travellingsalesman.map.SalesManMap;
import life.connect_it.travellingsalesman.map.SalesManMapFactory;
import life.connect_it.travellingsalesman.pathfinder.BestPathFinder;

public class Demo {


    public void runDemo() {
        List<double[]> initialSalesPoints = Arrays.asList(new double[]{3.0, 2.0});

        SalesManMap salesManMap = new SalesManMapFactory().getSalesManMapUuid(null);

        salesManMap.addSalesPoint(2.1, 3.3);
        salesManMap.addSalesPoint(0.1, 0.3);

        salesManMap.addSalesPoint(-0.1, 0.3);

        new Context().setPathFinder(new BestPathFinder()).executePathFinder(salesManMap);
    }
}
