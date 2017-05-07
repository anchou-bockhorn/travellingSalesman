package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import org.testng.annotations.*;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;

public class SalesPointTest {

    @DataProvider
    public Object[][] getCalculateDistanceData() {
        return new Object[][]{
            {new double[]{1.0, 3.0}, Math.sqrt(10.0)},
            {new double[]{3.0, 2.0}, Math.sqrt(13.0)}
        };
    }

    @Test(dataProvider = "getCalculateDistanceData")
    public void testCalculateDistance(double[] targetCoordinates, double solution) throws Exception {
        SalesPoint salesPointMock = EasyMock.mock(SalesPoint.class);
        expect(salesPointMock.getXCoordinate()).andReturn(targetCoordinates[0]);
        expect(salesPointMock.getYCoordinate()).andReturn(targetCoordinates[1]);

        replay(salesPointMock);

        SalesPoint salesPoint = new BasicSalesPoint(0.0, 0.0);

        double distance = salesPoint.calculateDistance(salesPointMock);

        verify(salesPointMock);
        assertEquals(distance, solution);
    }

    @DataProvider
    public Object[][] getIllegalConstructorArgumentExceptionData() {
        return new Object[][]{
            {-1.0, 1.0},
            {1.0, -1.0}
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
        dataProvider = "getIllegalConstructorArgumentExceptionData")
    public void testIllegalConstructorArgumentException(double[] illegalArguments) {
        new BasicSalesPoint(illegalArguments[0], illegalArguments[1]);
    }

    @Test
    public void testEquals() {
        SalesPoint salesPoint1 = new BasicSalesPoint(0.0, 0.0);
        SalesPoint salesPoint2 = new BasicSalesPoint(0.0, 0.0);
        assertTrue(salesPoint1.equals(salesPoint2) && salesPoint2.equals(salesPoint1));
        assertTrue(salesPoint1.hashCode() == salesPoint2.hashCode());
    }

    @Test
    public void testEqualsDifferentClasses() {
        SalesPoint salesPoint1 = new BasicSalesPoint(0.0, 0.0);
        SalesPoint salesPoint2 = new UuidSalesPoint(0.0, 0.0);
        assertTrue(salesPoint1.equals(salesPoint2) && salesPoint2.equals(salesPoint1));
        assertTrue(salesPoint1.hashCode() == salesPoint2.hashCode());
    }

    @Test
    public void testNotEquals() {
        SalesPoint salesPoint1 = new BasicSalesPoint(0.1, 0.0);
        SalesPoint salesPoint2 = new BasicSalesPoint(0.0, 0.0);
        assertFalse(salesPoint1.equals(salesPoint2) || salesPoint2.equals(salesPoint1));
        assertFalse(salesPoint1.hashCode() == salesPoint2.hashCode());

        SalesPoint salesPoint3 = new BasicSalesPoint(0.0, 0.000000001);
        SalesPoint salesPoint4 = new BasicSalesPoint(0.0, 0.0);
        assertFalse(salesPoint3.equals(salesPoint4) || salesPoint4.equals(salesPoint3));
        assertFalse(salesPoint3.hashCode() == salesPoint4.hashCode());
    }
}