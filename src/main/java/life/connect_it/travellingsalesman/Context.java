package life.connect_it.travellingsalesman;

import java.util.ArrayList;
import java.util.List;

import life.connect_it.travellingsalesman.map.SalesManMap;
import life.connect_it.travellingsalesman.pathfinder.PathFinder;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

public class Context {
    PathFinder pathFinder;

    public List<Integer> executePathFinder(List<double[]> initialSalesPoints) {
        return pathFinder.findPath(initialSalesPoints);
    }

    public Context setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
        return this;
    }
}
