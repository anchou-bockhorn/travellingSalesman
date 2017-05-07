package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import org.testng.annotations.*;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;

public class BasicSalesPointTest {

    @Test
    public void testAddTarget() throws Exception {
        SalesPoint salesPointMock = EasyMock.mock(SalesPoint.class);
        expect(salesPointMock.getXCoordinate()).andReturn(0.0);
        expect(salesPointMock.getYCoordinate()).andReturn(0.0);

        replay(salesPointMock);

        BasicSalesPoint salesPoint = new BasicSalesPoint(0.0, 0.0);

        salesPoint.addTarget(salesPointMock);

        verify(salesPointMock);
        assertNotNull(salesPoint.getTargetDistance(salesPointMock));
    }
}