package life.connect_it.travellingsalesman.salespoint.factories;

import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.UuidSalesPoint;

public class UuidSalesPointFactory implements SalesPointFactory {

    public UuidSalesPoint getSalesPoint(double xCoordinate, double yCoordinate) throws IllegalArgumentException {
        return new UuidSalesPoint(xCoordinate, yCoordinate);
    }
}
