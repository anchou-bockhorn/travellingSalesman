package life.connect_it.travellingsalesman.map;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import life.connect_it.travellingsalesman.helper.WitnessCalculator;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;
import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;

/**
 * Represents a two dimensional space with nodes of type SalesPoint in it and a rectangular,
 * automatically calculated border just as wide as necessary to contain all added nodes.
 *
 * @author Anchou Bockhorn - anchou.bockhorn@gmail.com
 * @see SalesPoint
 */
public class SalesManMap {
    private static final Logger logger = Logger.getLogger(SalesManMap.class.getName());

    private final WitnessCalculator witnessCalculator;
    private final SalesPointFactory salesPointFactory;

    private ArrayList<SalesPoint> salesPoints = new ArrayList<>();

    public SalesManMap(List<double[]> initialSalesPointsCoordinates,
                       SalesPointFactory salesPointFactory,
                       WitnessCalculator witnessCalculator) {
        this.salesPointFactory = salesPointFactory;
        this.witnessCalculator = witnessCalculator;
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
        if (salesPoints.remove(salesPoint)) {
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

    private void updateDistancesRemovePoint(SalesPoint salesPoint) {
        salesPoints.forEach(point -> point.removeTarget(salesPoint));
    }

    public ArrayList<ArrayList<Integer>> getWitnesses() {
        return witnessCalculator.calculateWitnesses(salesPoints.size());
    }

    private void updateDistancesAddPoint(SalesPoint newSalesPoint) {
        salesPoints.forEach(salesPoint -> {
            newSalesPoint.addTarget(salesPoint);
            salesPoint.addTarget(newSalesPoint);
        });
    }
}
