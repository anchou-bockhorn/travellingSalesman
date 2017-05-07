package life.connect_it.travellingsalesman.map;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sun.istack.internal.Nullable;
import life.connect_it.travellingsalesman.salespoint.SalesPoint;

public class SalesManMap {
    private double xBorder = 0;
    private double yBorder = 0;

    @Nullable private ArrayList<SalesPoint> salesPoints = new ArrayList<>();

    private static final Logger logger = Logger.getLogger(SalesManMap.class.getName());

    public SalesManMap(@Nullable List<SalesPoint> salesPoints) {
        if (salesPoints != null) {
            salesPoints.forEach(salesPoint -> addSalesPoint(salesPoint));
        }
    }

    public SalesPoint addSalesPoint(SalesPoint salesPoint) {
        if (!salesPoints.contains(salesPoint)) {
            reevaluateMapBorder(salesPoint);
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

    private void reevaluateMapBorder(SalesPoint salesPoint) {
        xBorder = updateCoordinate(salesPoint.getXCoordinate(), xBorder);
        yBorder = updateCoordinate(salesPoint.getYCoordinate(), yBorder);
    }

    private double updateCoordinate(double xCoordinate, double xBorder) {
        if (xCoordinate > xBorder) {
            return xCoordinate;
        }
        return xBorder;
    }


}
