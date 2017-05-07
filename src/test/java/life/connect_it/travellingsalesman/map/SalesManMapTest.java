package life.connect_it.travellingsalesman.map;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.*;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;

import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;
import life.connect_it.travellingsalesman.salespoint.factoryinterface.SalesPointFactory;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;

public class SalesManMapTest {
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
            {
                Arrays.asList(new double[]{3.0, 2.0}),
                new double[]{3.0, 2.0}
            },
            {
                Arrays.asList(new double[]{1.0, 3.1},
                    new double[]{5.9, 2.1},
                    new double[]{8.0, 3.1},
                    new double[]{9.0, 0.2}),
                new double[]{9.0, 3.1}},
            {
                Arrays.asList(new double[]{0.1, 2.9}, new double[]{0.0, 0.0}),
                new double[]{0.1, 2.9}}
        };
    }

    @Test()
    public void testAddDuplicateSalesPoint() throws Exception {
        SalesPoint salesPoint = mocksControl.createMock(SalesPoint.class);

        SalesPointFactory salesPointFactory = mocksControl.createMock(SalesPointFactory.class);
        expect(salesPointFactory.getSalesPoint(0.0, 0.0)).andReturn(salesPoint).times(2);

        mocksControl.replay();

        SalesManMap salesManMap = new SalesManMap(null, salesPointFactory);

        salesManMap.addSalesPoint(0.0, 0.0);
        salesManMap.addSalesPoint(0.0, 0.0);

        assertEquals(salesManMap.getSalesPoints().size(), 1);
        assertEquals(salesManMap.getXBorder(), 0.0);
        assertEquals(salesManMap.getYBorder(), 0.0);
    }

    @Test(dataProvider = "getSalesPointsData")
    public void testAddSalesPoint(List<double[]> salesPointCoordinates, double[] maxCoordinates) throws Exception {
        SalesPointFactory salesPointFactory = mocksControl.createMock(SalesPointFactory.class);
        createSalesPointMocksList(salesPointCoordinates, salesPointFactory);

        mocksControl.replay();

        SalesManMap salesManMap = new SalesManMap(null, salesPointFactory);
        salesPointCoordinates.forEach(coordinate -> salesManMap.addSalesPoint(coordinate[0], coordinate[1]));

        assertEquals(salesManMap.getSalesPoints().size(), salesPointCoordinates.size());
        assertEquals(salesManMap.getXBorder(), maxCoordinates[0]);
        assertEquals(salesManMap.getYBorder(), maxCoordinates[1]);
    }

    @Test(dataProvider = "getSalesPointsData")
    public void testAddSalesPointByConstructor(List<double[]> salesPointCoordinates, double[] maxCoordinates) throws Exception {
        SalesPointFactory salesPointFactory = mocksControl.createMock(SalesPointFactory.class);
        createSalesPointMocksList(salesPointCoordinates, salesPointFactory);

        mocksControl.replay();
        SalesManMap salesManMap = new SalesManMap(salesPointCoordinates, salesPointFactory);

        assertEquals(salesManMap.getSalesPoints().size(), salesPointCoordinates.size());
        assertEquals(salesManMap.getXBorder(), maxCoordinates[0]);
        assertEquals(salesManMap.getYBorder(), maxCoordinates[1]);
    }

    private List<SalesPoint> createSalesPointMocksList(List<double[]> salesPointCoordinates, SalesPointFactory salesPointFactory) {
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