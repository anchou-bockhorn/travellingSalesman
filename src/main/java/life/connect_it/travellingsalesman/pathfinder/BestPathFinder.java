package life.connect_it.travellingsalesman.pathfinder;

import java.util.ArrayList;

import life.connect_it.travellingsalesman.map.SalesManMap;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

public class BestPathFinder implements PathFinder {

    @Override
    public ArrayList<SalesPoint> findShortestPath(SalesManMap salesManMap) {
        ArrayList<ArrayList<SalesPoint>> witnesses = salesManMap.getWitnesses();

        return null;
    }
}
