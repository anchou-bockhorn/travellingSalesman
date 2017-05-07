package life.connect_it.travellingsalesman.salespoint;

import org.testng.annotations.*;
import org.easymock.EasyMock;

import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPoint;
import life.connect_it.travellingsalesman.salespoint.salespointimpl.SalesPointHashMap;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;

public class SalesPointHashMapTest {

    @Test
    public void testAddTarget() throws Exception {
        SalesPoint salesPointMock = EasyMock.mock(SalesPoint.class);
        expect(salesPointMock.getXCoordinate()).andReturn(0.0);
        expect(salesPointMock.getYCoordinate()).andReturn(0.0);

        replay(salesPointMock);

        SalesPointHashMap salesPoint = new SalesPointHashMap(0.0, 0.0);

        salesPoint.addTarget(salesPointMock);

        verify(salesPointMock);
        assertEquals(salesPoint.getTargetDistancesNumber(), 1);
        assertNotEquals(salesPoint.getTargetDistance(salesPointMock), null);
    }
}