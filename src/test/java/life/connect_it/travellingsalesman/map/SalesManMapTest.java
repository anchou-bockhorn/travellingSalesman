package life.connect_it.travellingsalesman.map;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.*;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;

import life.connect_it.travellingsalesman.salespoint.SalesPoint;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;

public class SalesManMapTest {
    private SalesManMap salesManMap;
    private IMocksControl mocksControl = EasyMock.createControl();

    @BeforeMethod
    public void setup() {
        salesManMap = new SalesManMap(null);
    }

    @AfterMethod
    public void cleanUp() {
        salesManMap = null;
        mocksControl.verify();
        mocksControl.reset();
    }

    @DataProvider
    public Object[][] getSalesPointsData() {
        return new Object[][]{
            {new double[][]{{1.0, 3.0}}, new double[]{1.0, 3.0}},
            {new double[][]{{1.0, 3.1}, {5.9, 2.1}, {9.0, 0.2}, {8.0, 3.1}}, new double[]{9.0, 3.1}},
            {new double[][]{{0.1, 2.9}, {0.0, 0.0}}, new double[]{0.1, 2.9}},
        };
    }

    @Test()
    public void testAddDuplicateSalesPoint() throws Exception {
        SalesPoint salesPoint = mocksControl.createMock(SalesPoint.class);
        expect(salesPoint.getXCoordinate()).andReturn(0.0);
        expect(salesPoint.getYCoordinate()).andReturn(0.0);

        mocksControl.replay();

        salesManMap.addSalesPoint(salesPoint);
        salesManMap.addSalesPoint(salesPoint);

        assertEquals(salesManMap.getSalesPoints().size(), 1);
        assertEquals(salesManMap.getXBorder(), 0.0);
        assertEquals(salesManMap.getYBorder(), 0.0);
    }

    @Test(dataProvider = "getSalesPointsData")
    public void testAddSalesPoint(double[][] salesPointCoordinates, double[] maxCoordinates) throws Exception {
        List<SalesPoint> salesPoints = getSalesPointMocksList(salesPointCoordinates);

        mocksControl.replay();
        salesPoints.forEach(salesPoint -> {
            salesManMap.addSalesPoint(salesPoint);
        });

        assertEquals(salesManMap.getSalesPoints().size(), salesPointCoordinates.length);
        assertEquals(salesManMap.getXBorder(), maxCoordinates[0]);
        assertEquals(salesManMap.getYBorder(), maxCoordinates[1]);
    }

    @Test(dataProvider = "getSalesPointsData")
    public void testAddSalesPointByConstructor(double[][] salesPointCoordinates, double[] maxCoordinates) throws Exception {
        List<SalesPoint> salesPoints = getSalesPointMocksList(salesPointCoordinates);

        mocksControl.replay();
        SalesManMap salesManMap = new SalesManMap(salesPoints);

        assertEquals(salesManMap.getSalesPoints().size(), salesPointCoordinates.length);
        assertEquals(salesManMap.getXBorder(), maxCoordinates[0]);
        assertEquals(salesManMap.getYBorder(), maxCoordinates[1]);
    }

    private List<SalesPoint> getSalesPointMocksList(double[][] salesPointCoordinates) {
        return Arrays.stream(salesPointCoordinates)
            .map(salesPointCoordinate ->
                createAndExpectSalesPointMocks(salesPointCoordinate, salesPointCoordinates.length))
            .collect(Collectors.toList());
    }

    private SalesPoint createAndExpectSalesPointMocks(double[] salesPointCoordinate, int salesPointsNumber) {
        SalesPoint salesPoint = mocksControl.createMock(SalesPoint.class);
        expect(salesPoint.getXCoordinate()).andReturn(salesPointCoordinate[0]);
        expect(salesPoint.getYCoordinate()).andReturn(salesPointCoordinate[1]);
        expectAddTargetCalls(salesPoint, salesPointsNumber - 1);
        return salesPoint;
    }

    private void expectAddTargetCalls(SalesPoint salesPoint, int expectedCallsNumber) {
        if (expectedCallsNumber > 0) {
            SalesPoint anySalesPoint = anyObject(SalesPoint.class);
            expect(salesPoint.addTarget(anySalesPoint)).andReturn(anySalesPoint).times(expectedCallsNumber);
        }
    }
}