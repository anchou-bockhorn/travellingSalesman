package life.connect_it.travellingsalesman.pathfinder;

import java.util.List;

import life.connect_it.travellingsalesman.map.SalesManMap;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

public interface PathFinder {
    List<SalesPoint> findPath(SalesManMap salesManMap);
}
