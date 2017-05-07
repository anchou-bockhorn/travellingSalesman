package life.connect_it.travellingsalesman.salespoint.factories;

import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPointHashMapUuid;

public class UuidSalesPointFactory implements SalesPointFactory {

    public SalesPoint getSalesPoint(double xCoordinate, double yCoordinate) {
        return new SalesPointHashMapUuid(xCoordinate, yCoordinate);
    }
}
