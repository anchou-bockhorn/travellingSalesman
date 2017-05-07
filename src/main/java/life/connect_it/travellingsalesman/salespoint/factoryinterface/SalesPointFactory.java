
package life.connect_it.travellingsalesman.salespoint.factoryinterface;

import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;

public interface SalesPointFactory {

    SalesPoint getSalesPoint(double xCoordinate, double yCoordinate);
}
