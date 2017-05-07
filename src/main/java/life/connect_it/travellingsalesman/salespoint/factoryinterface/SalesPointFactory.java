package life.connect_it.travellingsalesman.salespoint.factoryinterface;

import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

/**
 *
 *
 * @author Anchou Bockhorn - anchou.bockhorn@gmail.com
 * @see SalesPoint
 */
public interface SalesPointFactory {

    /**
     * Generates a new SalesPoints
     *
     * @param xCoordinate x-coordinate of de newly generated SalesPoint
     * @param yCoordinate y-coordinate of de newly generated SalesPoint
     * @return new instance of SalesPoint
     * @throws IllegalArgumentException if negative double is passed as one of the params
     */
    SalesPoint getSalesPoint(double xCoordinate, double yCoordinate) throws IllegalArgumentException;
}
