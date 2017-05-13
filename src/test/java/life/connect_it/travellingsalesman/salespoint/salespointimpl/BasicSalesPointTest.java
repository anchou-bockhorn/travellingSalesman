package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import org.testng.annotations.*;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;

public class BasicSalesPointTest {

    @Test
    public void testAddTarget() throws Exception {
        BasicSalesPoint salesPoint1 = new BasicSalesPoint(0.0, 0.0);
        BasicSalesPoint salesPoint2 = new BasicSalesPoint(0.1, 0.1);

        salesPoint1.addTarget(salesPoint2);

        assertNotNull(salesPoint1.getTargetDistance(salesPoint2));
    }

    @Test
    public void testRemoveTarget() throws Exception {
        BasicSalesPoint salesPoint1 = new BasicSalesPoint(0.0, 0.0);
        BasicSalesPoint salesPoint2 = new BasicSalesPoint(0.1, 0.1);

        salesPoint1.addTarget(salesPoint2);
        salesPoint1.removeTarget(salesPoint2);

        assertNull(salesPoint1.getTargetDistance(salesPoint2));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveNotExistingTarget() throws Exception {
        BasicSalesPoint salesPoint1 = new BasicSalesPoint(0.0, 0.0);
        BasicSalesPoint salesPoint2 = new BasicSalesPoint(0.1, 0.1);

        salesPoint1.removeTarget(salesPoint2);
    }
}