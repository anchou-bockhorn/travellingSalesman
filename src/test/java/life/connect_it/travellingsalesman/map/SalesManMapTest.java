package life.connect_it.travellingsalesman.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.*;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;

import life.connect_it.travellingsalesman.helper.WitnessCalculator;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;
import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.powermock.api.extension.proxyframework.ProxyFrameworkImpl;

import static org.easymock.EasyMock.*;

@PrepareForTest(WitnessCalculator.class)
public class SalesManMapTest extends PowerMockTestCase {
    private IMocksControl mocksControl = EasyMock.createControl();

    @BeforeMethod
    public void setup() {
        mocksControl.reset();
    }

    @AfterMethod
    public void cleanUp() {
        mocksControl.verify();
    }

    @DataProvider
    public Object[][] getSalesPointsData() {
        return new Object[][]{
            {Arrays.asList(new double[]{3.0, 2.0})},
            {Arrays.asList(new double[]{1.0, 3.1}, new double[]{5.9, 2.1}, new double[]{8.0, 3.1}, new double[]{9.0, 0.2})},
            {Arrays.asList(new double[]{0.1, 2.9})},
        };
    }

    @Test(dataProvider = "getSalesPointsData")
    public void testAddSalesPoint(List<double[]> salesPointCoordinates) throws Exception {
        SalesPointFactory salesPointFactory = mocksControl.createMock(SalesPointFactory.class);
        createSalesPointMocksList(salesPointCoordinates, salesPointFactory);

        mocksControl.replay();

        SalesManMap salesManMap = new SalesManMap(null, salesPointFactory);
        salesPointCoordinates.forEach(coordinate -> salesManMap.addSalesPoint(coordinate[0], coordinate[1]));
    }

    @Test(dataProvider = "getSalesPointsData")
    public void testAddSalesPointOnInstantiate(List<double[]> salesPointCoordinates) throws Exception {
        SalesPointFactory salesPointFactory = mocksControl.createMock(SalesPointFactory.class);
        createSalesPointMocksList(salesPointCoordinates, salesPointFactory);

        mocksControl.replay();
        new SalesManMap(salesPointCoordinates, salesPointFactory);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddDuplicateSalesPoint() throws Exception {
        SalesPoint salesPoint = mocksControl.createMock(SalesPoint.class);

        SalesPointFactory salesPointFactory = mocksControl.createMock(SalesPointFactory.class);
        expect(salesPointFactory.getSalesPoint(0.0, 0.0)).andReturn(salesPoint).times(2);

        mocksControl.replay();

        SalesManMap salesManMap = new SalesManMap(null, salesPointFactory);

        salesManMap.addSalesPoint(0.0, 0.0);
        salesManMap.addSalesPoint(0.0, 0.0);
    }

    @Test(dataProvider = "getSalesPointsData")
    public void testRemoveSalesPoint(List<double[]> salesPointCoordinates) throws Exception {
        SalesPointFactory salesPointFactory = mocksControl.createMock(SalesPointFactory.class);
        List<SalesPoint> salesPoints = createSalesPointMocksList(salesPointCoordinates, salesPointFactory);
        expect(salesPointFactory.getSalesPoint(salesPointCoordinates.get(0)[0], salesPointCoordinates.get(0)[1]))
            .andReturn(salesPoints.get(0));

        SalesPoint pointToRemove = salesPoints.get(0);
        salesPoints.remove(pointToRemove);
        salesPoints.forEach(salesPoint -> expect(salesPoint.removeTarget(pointToRemove)).andReturn(salesPoint));

        mocksControl.replay();
        new SalesManMap(salesPointCoordinates, salesPointFactory)
            .removeSalesPoint(salesPointCoordinates.get(0)[0], salesPointCoordinates.get(0)[1]);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveNotExistingSalesPoint() throws Exception {
        SalesPointFactory salesPointFactory = mocksControl.createMock(SalesPointFactory.class);
        expect(salesPointFactory.getSalesPoint(0.0, 0.0))
            .andReturn(mocksControl.createMock(SalesPoint.class));

        mocksControl.replay();

        new SalesManMap(null, salesPointFactory)
            .removeSalesPoint(0.0, 0.0);
    }

    @Test
    public void testGetWitnesses() throws Exception {
        PowerMock.mockStatic(WitnessCalculator.class);
        expect(WitnessCalculator.calculateWitnesses(0))
            .andReturn(new ArrayList<>());

        PowerMock.replay(WitnessCalculator.class);
        mocksControl.replay();

        new SalesManMap(null, null).getWitnesses();

        PowerMock.verify(WitnessCalculator.class);
    }

    private List<SalesPoint> createSalesPointMocksList(List<double[]> salesPointCoordinates,
                                                       SalesPointFactory salesPointFactory) {
        return salesPointCoordinates.stream().map(salesPointCoordinate ->
            createAndExpectSalesPointMocks(salesPointCoordinate, salesPointCoordinates.size(), salesPointFactory))
            .collect(Collectors.toList());
    }

    private SalesPoint createAndExpectSalesPointMocks(double[] salesPointCoordinate,
                                                      int salesPointsNumber,
                                                      SalesPointFactory salesPointFactory) {
        SalesPoint salesPoint = mocksControl.createMock(SalesPoint.class);
        expectAddTargetCalls(salesPoint, salesPointsNumber - 1);

        expect(salesPointFactory.getSalesPoint(salesPointCoordinate[0], salesPointCoordinate[1])).andReturn(salesPoint);
        return salesPoint;
    }

    private void expectAddTargetCalls(SalesPoint salesPoint, int expectedCallsNumber) {
        if (expectedCallsNumber > 0) {
            SalesPoint anySalesPoint = anyObject(SalesPoint.class);
            expect(salesPoint.addTarget(anySalesPoint)).andReturn(anySalesPoint).times(expectedCallsNumber);
        }
    }
}