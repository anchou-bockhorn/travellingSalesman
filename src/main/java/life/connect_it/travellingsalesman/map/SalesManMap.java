package life.connect_it.travellingsalesman.map;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.internal.Nullable;
import life.connect_it.travellingsalesman.helper.WitnessCalculator;
import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

/**
 * Represents a two dimensional space with nodes of type SalesPoint in it and a rectangular,
 * automatically calculated border just as wide as necessary to contain all added nodes.
 *
 * @author Anchou Bockhorn - anchou.bockhorn@gmail.com
 * @see SalesPoint
 */
public class SalesManMap {
    private final SalesPointFactory salesPointFactory;

    private ArrayList<SalesPoint> salesPoints = new ArrayList<>();

    public SalesManMap(@Nullable List<double[]> initialSalesPointsCoordinates,
                       SalesPointFactory salesPointFactory) {
        this.salesPointFactory = salesPointFactory;
        if (initialSalesPointsCoordinates != null) {
            initialSalesPointsCoordinates
                .forEach(salesPointCoordinates -> addSalesPoint(salesPointCoordinates[0], salesPointCoordinates[1]));
        }
    }

    /**
     * Creates and adds a new SalesPoint to the this
     *
     * @param xCoordinate x-coordinate of de newly generated SalesPoint
     * @param yCoordinate y-coordinate of de newly generated SalesPoint
     * @return the newly created SalesPoint
     */
    public SalesPoint addSalesPoint(double xCoordinate, double yCoordinate) {
        SalesPoint salesPoint = salesPointFactory.getSalesPoint(xCoordinate, yCoordinate);
        if (!salesPoints.contains(salesPoint)) {
            updateDistancesAddPoint(salesPoint);
            salesPoints.add(salesPoint);
        } else {
            throw new IllegalArgumentException("Duplicate SalesPoint added: " + salesPoint.toString());
        }
        return salesPoint;
    }

    /**
     * Removes a SalesPoint from this collection and form the SalesPoints targetDistance collections
     *
     * @param xCoordinate x-coordinate of de newly generated SalesPoint
     * @param yCoordinate y-coordinate of de newly generated SalesPoint
     * @return the newly created SalesPoint
     */
    public SalesPoint removeSalesPoint(double xCoordinate, double yCoordinate) {
        SalesPoint salesPoint = salesPointFactory.getSalesPoint(xCoordinate, yCoordinate);
        boolean removalSuccessful = salesPoints.remove(salesPoint);
        if (removalSuccessful) {
            updateDistancesRemovePoint(salesPoint);
        } else {
            throw new IllegalArgumentException("Removed SalesPoint: " + salesPoint.toString() + "is not present in" +
                " collection");
        }
        return salesPoint;
    }

    /**
     * Get SalesPoint from this collection by index
     *
     * @param index index of SalesPoint in collection
     * @return the newly created SalesPoint
     */
    public SalesPoint getSalesPoint(int index) {
        return salesPoints.get(index);
    }

    /**
     * Calculates an
     *
     * @return the newly created SalesPoint
     */
    public List<List<Integer>> getWitnesses() {
        return WitnessCalculator.calculateWitnesses(salesPoints.size());
    }

    private void updateDistancesRemovePoint(SalesPoint pointToRemove) {
        salesPoints.forEach(point -> point.removeTarget(pointToRemove));
    }

    private void updateDistancesAddPoint(SalesPoint newSalesPoint) {
        salesPoints.forEach(salesPoint -> {
            newSalesPoint.addTarget(salesPoint);
            salesPoint.addTarget(newSalesPoint);
        });
    }
}
