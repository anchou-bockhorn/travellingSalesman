package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import java.util.HashMap;

public class BasicSalesPoint extends SalesPoint {
    private HashMap<SalesPoint, Double> targetDistances = new HashMap<>();

    public BasicSalesPoint(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    @Override
    public SalesPoint addTarget(SalesPoint target) {
        targetDistances.put(target, this.calculateDistance(target));
        return this;
    }

    @Override
    public Double getTargetDistance(SalesPoint salesPoint) {
        return targetDistances.get(salesPoint);
    }
}
