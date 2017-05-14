package life.connect_it.travellingsalesman.pathfinder;

import java.util.List;

import life.connect_it.travellingsalesman.map.SalesManMap;
import life.connect_it.travellingsalesman.map.SalesManMapFactory;

import static java.lang.System.*;

public class BestPathFinder implements PathFinder {
    private static final SalesManMapFactory MAP_FACTORY = new SalesManMapFactory();

    private final SalesManMap basicMap;
    private final SalesManMap uuidMap;

    public BestPathFinder() {
        this.basicMap = MAP_FACTORY.getSalesManMap(null);
        this.uuidMap = MAP_FACTORY.getSalesManMapUuid(null);
    }

    @Override
    public List<Integer> findPath(List<double[]> initialSalesPoints) {
        SalesManMap currentMap = basicMap;

        initialSalesPoints.forEach(point -> currentMap.addSalesPoint(point[0], point[1]));
        List<List<Integer>> witnesses = currentMap.getWitnesses();

        List<Integer> currentlyBestPath = witnesses.get(0);
        double bestDistance = calculateDistance(currentlyBestPath, currentMap);

        long start = currentTimeMillis();

        for (int i = 1; i < witnesses.size(); i++) {
            double currentDistance = calculateDistance(witnesses.get(i), currentMap);
            if (bestDistance > currentDistance) {
                currentlyBestPath = witnesses.get(i);
                bestDistance = currentDistance;
            }
        }

        System.out.println(Long.valueOf(currentTimeMillis() - start).toString());

        return currentlyBestPath;
    }

    private double calculateDistance(List<Integer> path, SalesManMap map) {
        double distance = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += map.getSalesPoint(path.get(i)).getTargetDistance(map.getSalesPoint(path.get(i + 1)));
        }
        distance += map.getSalesPoint(path.get(path.size() - 1)).getTargetDistance(map.getSalesPoint(path.get(0)));
        return distance;
    }
}
