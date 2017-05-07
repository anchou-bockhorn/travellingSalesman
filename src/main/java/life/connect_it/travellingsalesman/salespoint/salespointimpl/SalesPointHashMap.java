package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import java.util.HashMap;

public class SalesPointHashMap extends SalesPoint {
    private HashMap<SalesPoint, Double> targetDistances = new HashMap<>();

    public SalesPointHashMap(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    @Override
    public SalesPoint addTarget(SalesPoint salesPoint) {
        targetDistances.put(salesPoint, this.calculateDistance(salesPoint));
        return salesPoint;
    }

    @Override
    public Double getTargetDistance(SalesPoint salesPoint) {
        return targetDistances.get(salesPoint);
    }

    @Override
    public int getTargetDistancesNumber() {
        return targetDistances.size();
    }
}
