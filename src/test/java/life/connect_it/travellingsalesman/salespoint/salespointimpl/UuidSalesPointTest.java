package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import org.testng.annotations.*;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;

public class UuidSalesPointTest {

    @Test
    public void testAddTarget() throws Exception {
        UuidSalesPoint salesPoint1 = new UuidSalesPoint(0.0, 0.0);
        UuidSalesPoint salesPoint2 = new UuidSalesPoint(0.1, 0.1);

        salesPoint1.addTarget(salesPoint2);

        assertNotNull(salesPoint1.getTargetDistance(salesPoint2));
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

    @Test
    public void testRemoveTarget() throws Exception {
        UuidSalesPoint salesPoint1 = new UuidSalesPoint(0.0, 0.0);
        UuidSalesPoint salesPoint2 = new UuidSalesPoint(0.1, 0.1);

        salesPoint1.addTarget(salesPoint2);
        salesPoint1.removeTarget(salesPoint2);

        assertNull(salesPoint1.getTargetDistance(salesPoint2));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveNotExistingTarget() throws Exception {
        UuidSalesPoint salesPoint1 = new UuidSalesPoint(0.0, 0.0);
        UuidSalesPoint salesPoint2 = new UuidSalesPoint(0.1, 0.1);

        salesPoint1.removeTarget(salesPoint2);
    }
}