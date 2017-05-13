package life.connect_it.travellingsalesman.pathfinder;

import java.util.ArrayList;

import life.connect_it.travellingsalesman.map.SalesManMap;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

public interface PathFinder {
    ArrayList<SalesPoint> findPath(SalesManMap salesManMap);
}
