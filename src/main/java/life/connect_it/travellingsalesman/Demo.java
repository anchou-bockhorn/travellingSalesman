package life.connect_it.travellingsalesman;

import java.util.Arrays;
import java.util.List;

import life.connect_it.travellingsalesman.map.Map;
import life.connect_it.travellingsalesman.salespoint.SalesPoint;
import life.connect_it.travellingsalesman.salespoint.SalesPointHashMap;

public class Demo {


    public void execute() {
        List<SalesPoint> initialSalesPoints = Arrays.asList(new SalesPointHashMap(3.0, 2.0));

        Map map = new Map(initialSalesPoints);

        map.addSalesPoint(new SalesPointHashMap(2.1, 3.3));
        map.addSalesPoint(new SalesPointHashMap(0.1, 0.3));

        map.addSalesPoint(new SalesPointHashMap(-0.1, 0.3));
    }
}
