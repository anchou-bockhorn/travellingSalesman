package life.connect_it.travellingsalesman.map;

import java.util.List;

import com.sun.istack.internal.Nullable;
import life.connect_it.travellingsalesman.helper.WitnessCalculator;
import life.connect_it.travellingsalesman.salespoint.factories.BasicSalesPointFactory;
import life.connect_it.travellingsalesman.salespoint.factories.UuidSalesPointFactory;

public class SalesManMapFactory {
    private final WitnessCalculator witnessCalculator = new WitnessCalculator();

    public SalesManMap getSalesManMapUuid(@Nullable List<double[]> salesPointsCoordinates) {
        return new SalesManMap(salesPointsCoordinates,
            new UuidSalesPointFactory(),
            witnessCalculator);
    }

    public SalesManMap getSalesManMap(@Nullable List<double[]> salesPointsCoordinates) {
        return new SalesManMap(salesPointsCoordinates,
            new BasicSalesPointFactory(),
            witnessCalculator);
    }
}
