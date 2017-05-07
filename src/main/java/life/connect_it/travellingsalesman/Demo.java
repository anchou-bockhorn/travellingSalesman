package life.connect_it.travellingsalesman;

import java.util.Arrays;
import java.util.List;

import life.connect_it.travellingsalesman.map.SalesManMap;
import life.connect_it.travellingsalesman.map.SalesManMapFactory;

public class Demo {


    public void execute() {
        List<double[]> initialSalesPoints = Arrays.asList(new double[]{3.0, 2.0});

        SalesManMap salesManMap = new SalesManMapFactory().getSalesManMapUuid(null);

        salesManMap.addSalesPoint(2.1, 3.3);
        salesManMap.addSalesPoint(0.1, 0.3);

        salesManMap.addSalesPoint(-0.1, 0.3);
    }
}
