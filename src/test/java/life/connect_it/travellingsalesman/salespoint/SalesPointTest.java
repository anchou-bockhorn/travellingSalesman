package life.connect_it.travellingsalesman.salespoint;

import org.testng.annotations.*;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.assertEquals;

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

        SalesPoint salesPoint = new SalesPointHashMap(0.0, 0.0);

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
        new SalesPointHashMap(illegalArguments[0], illegalArguments[1]);
    }
}