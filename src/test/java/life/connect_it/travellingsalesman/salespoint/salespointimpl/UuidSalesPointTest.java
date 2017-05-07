package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import org.testng.annotations.*;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;

public class UuidSalesPointTest {
    @Test
    public void testAddTarget() throws Exception {
        UuidSalesPoint salesPointMock = EasyMock.mock(UuidSalesPoint.class);
        expect(salesPointMock.getXCoordinate()).andReturn(0.0);
        expect(salesPointMock.getYCoordinate()).andReturn(0.0);

        replay(salesPointMock);

        UuidSalesPoint salesPoint = new UuidSalesPoint(0.0, 0.0);

        salesPoint.addTarget(salesPointMock);

        verify(salesPointMock);
        assertEquals(salesPoint.getTargetDistancesNumber(), 1);
        assertNotNull(salesPoint.getTargetDistance(salesPointMock));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddTargetWithoutUuid() throws Exception {
        BasicSalesPoint salesPointMock = EasyMock.mock(BasicSalesPoint.class);

        UuidSalesPoint salesPoint = new UuidSalesPoint(0.0, 0.0);

        salesPoint.addTarget(salesPointMock);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetTargetDistance() throws Exception {
        BasicSalesPoint salesPointMock = EasyMock.mock(BasicSalesPoint.class);

        UuidSalesPoint salesPoint = new UuidSalesPoint(0.0, 0.0);

        salesPoint.getTargetDistance(salesPointMock);
    }
}