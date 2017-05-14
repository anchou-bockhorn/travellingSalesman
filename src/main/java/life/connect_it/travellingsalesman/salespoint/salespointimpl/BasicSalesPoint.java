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
    public SalesPoint removeTarget(SalesPoint target) {
        Double removedDistance = targetDistances.remove(target);
        if (removedDistance == null) {
            throw new IllegalArgumentException("Removed SalesPoint: " + target.toString() + "is not present in" +
                " targets collection");
        }
        return this;
    }

    @Override
    public Double getTargetDistance(SalesPoint salesPoint) {
        return targetDistances.get(salesPoint);
    }

    @Override
    public String toString() {
        return "BasicSalesPoint{" +
            "xCoordinate=" + xCoordinate +
            ", yCoordinate=" + yCoordinate +
            '}';
    }
}
