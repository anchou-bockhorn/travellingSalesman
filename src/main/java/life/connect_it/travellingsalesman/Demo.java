package life.connect_it.travellingsalesman;

import java.util.Arrays;
import java.util.List;

import life.connect_it.travellingsalesman.mapdrawer.MapDrawerImpl;
import life.connect_it.travellingsalesman.pathfinder.BestPathFinder;

public class Demo {



    public void runDemo() {
        List<double[]> initialSalesPoints = Arrays.asList(
            new double[]{2.5, 1.0},
            new double[]{2.5, 0.0},
            new double[]{2.2, 0.3},
            new double[]{2.0, 1.0},
            new double[]{1.0, 1.0},
            new double[]{1.3, 1.9},
            new double[]{0.3, 2.8},
            new double[]{0.3, 2.1},
            new double[]{2.0, 2.0},
            new double[]{0.0, 0.0}
            );

        List<Integer> theWayToGo = new Context().setPathFinder(new BestPathFinder())
            .executePathFinder(initialSalesPoints);
        System.out.println(theWayToGo.toString());

        MapDrawerImpl mapDrawer = new MapDrawerImpl();
        mapDrawer.drawMap(initialSalesPoints, theWayToGo);
    }

}
