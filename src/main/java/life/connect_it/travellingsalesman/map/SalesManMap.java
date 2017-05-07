package life.connect_it.travellingsalesman.map;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sun.istack.internal.Nullable;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;
import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;

public class SalesManMap {
    private static final Logger logger = Logger.getLogger(SalesManMap.class.getName());

    private final SalesPointFactory salesPointFactory;

    private double xBorder = 0;
    private double yBorder = 0;

    @Nullable private ArrayList<SalesPoint> salesPoints = new ArrayList<>();

    public SalesManMap(List<double[]> salesPointsCoordinates, SalesPointFactory salesPointFactory) {
        this.salesPointFactory = salesPointFactory;
        if (salesPointsCoordinates != null) {
            salesPointsCoordinates
                .forEach(salesPointCoordinates -> addSalesPoint(salesPointCoordinates[0], salesPointCoordinates[1]));
        }
    }

    public SalesPoint addSalesPoint(double xCoordinate, double yCoordinate) {
        SalesPoint salesPoint = salesPointFactory.getSalesPoint(xCoordinate, yCoordinate);
        if (!salesPoints.contains(salesPoint)) {
            reevaluateMapBorder(xCoordinate, yCoordinate);
            updateSalesPointsDistances(salesPoint);
            salesPoints.add(salesPoint);
        } else {
            logger.info("Duplicate SalesPoint added: " + salesPoint.toString());
        }
        return salesPoint;
    }

    public double getXBorder() {
        return xBorder;
    }

    public double getYBorder() {
        return yBorder;
    }

    public ArrayList<SalesPoint> getSalesPoints() {
        return salesPoints;
    }

    private void updateSalesPointsDistances(SalesPoint newSalesPoint) {
        salesPoints.forEach(salesPoint -> {
            newSalesPoint.addTarget(salesPoint);
            salesPoint.addTarget(newSalesPoint);
        });
    }

    private void reevaluateMapBorder(double xCoordinate, double yCoordinate) {
        xBorder = updateCoordinate(xCoordinate, xBorder);
        yBorder = updateCoordinate(yCoordinate, yBorder);
    }

    private double updateCoordinate(double xCoordinate, double xBorder) {
        if (xCoordinate > xBorder) {
            return xCoordinate;
        }
        return xBorder;
    }
}
