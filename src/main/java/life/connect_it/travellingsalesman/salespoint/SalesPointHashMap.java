package life.connect_it.travellingsalesman.salespoint;

import java.util.HashMap;

public class SalesPointHashMap extends SalesPoint {
    private HashMap<SalesPoint, Double> targetDistances = new HashMap<>();

    public SalesPointHashMap(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    public SalesPoint addTarget(SalesPoint salesPoint) {
        targetDistances.put(salesPoint, this.calculateDistance(salesPoint));
        return salesPoint;
    }

    public HashMap<SalesPoint, Double> getTargetDistances() {
        return targetDistances;
    }
}
