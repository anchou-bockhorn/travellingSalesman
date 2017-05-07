package life.connect_it.travellingsalesman.salespoint.factories;

import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.BasicSalesPoint;

public class BasicSalesPointFactory implements SalesPointFactory {

    public BasicSalesPoint getSalesPoint(double xCoordinate, double yCoordinate) {
        return new BasicSalesPoint(xCoordinate, yCoordinate);
    }
}
