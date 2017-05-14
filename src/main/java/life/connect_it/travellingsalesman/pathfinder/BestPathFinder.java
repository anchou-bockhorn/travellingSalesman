package life.connect_it.travellingsalesman.pathfinder;

import java.util.List;

import life.connect_it.travellingsalesman.map.SalesManMap;
import life.connect_it.travellingsalesman.map.SalesManMapFactory;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

public class BestPathFinder implements PathFinder {
    private static final SalesManMapFactory MAP_FACTORY = new SalesManMapFactory();

    private final SalesManMap basicMap;
    private final SalesManMap uuidMap;

    public BestPathFinder() {
        this.basicMap = MAP_FACTORY.getSalesManMap(null);
        this.uuidMap = MAP_FACTORY.getSalesManMapUuid(null);
    }

    @Override
    public List<SalesPoint> findPath(SalesManMap salesManMap) {
        List<List<Integer>> witnesses = salesManMap.getWitnesses();

        List<Integer> currentlyBestPath = witnesses.get(0);
        double bestDistance = calculateDistance(currentlyBestPath, salesManMap);

        for (int i = 1; i < witnesses.size(); i++) {
            if (bestDistance > calculateDistance(witnesses.get(i), salesManMap)) {
                currentlyBestPath = witnesses.get(i);
            }
        }

        return null;
    }

    private double calculateDistance(List<Integer> path, SalesManMap map) {
        double distance = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += map.getSalesPoint(path.get(i)).getTargetDistance(map.getSalesPoint(path.get(i + 1)));
        }
        return distance;
    }
}
