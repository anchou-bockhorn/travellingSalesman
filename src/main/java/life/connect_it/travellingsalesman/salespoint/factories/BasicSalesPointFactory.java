package life.connect_it.travellingsalesman.salespoint.factories;

import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPointHashMap;

public class BasicSalesPointFactory implements SalesPointFactory {

    public SalesPoint getSalesPoint(double xCoordinate, double yCoordinate) {
        return new SalesPointHashMap(xCoordinate, yCoordinate);
    }
}
