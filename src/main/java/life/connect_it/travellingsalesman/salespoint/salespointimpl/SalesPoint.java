package life.connect_it.travellingsalesman.salespoint.salespointimpl;

/**
 * Represents a node in a two dimensional space, containing a collection of calculated
 * distances to other nodes in this space for better performance
 *
 * @author Anchou Bockhorn - anchou.bockhorn@gmail.com
 */
public abstract class SalesPoint {
    final double xCoordinate;
    final double yCoordinate;

    public SalesPoint(double xCoordinate, double yCoordinate) throws IllegalArgumentException {
        if (xCoordinate < 0 || yCoordinate < 0) {
            throw new IllegalArgumentException("Negative values are not supported for SalesPoint coordinates");
        }
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Calculates the distance to the target node and saves it. The calculated distance can be accessed
     * by calling getTargetDistance function
     *
     * @param target node to be to be added
     * @return this
     */
    public abstract SalesPoint addTarget(SalesPoint target);

    /**
     * Removes the save distance to the target node from collection.
     *
     * @param target node to be removed collection
     * @return this
     */
    public abstract SalesPoint removeTarget(SalesPoint target);

    /**
     * Reads the distance from this to the passed SalesPoint, if the passed SalesPoint was previously
     * registered with addTarget method
     *
     * @param salesPoint node to get the distance from this
     * @return the distance from this to the passed SalesPoint
     */
    public abstract Double getTargetDistance(SalesPoint salesPoint);

    double getXCoordinate() {
        return xCoordinate;
    }

    double getYCoordinate() {
        return yCoordinate;
    }

    double calculateDistance(SalesPoint target) {
        double xDistance = Math.pow(this.getXCoordinate() - target.getXCoordinate(), 2);
        double yDistance = Math.pow(this.getYCoordinate() - target.getYCoordinate(), 2);
        return Math.sqrt(xDistance + yDistance);
    }

    @Override
    public String toString() {
        return "SalesPoint{" +
            "xCoordinate=" + xCoordinate +
            ", yCoordinate=" + yCoordinate +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().getSuperclass().isAssignableFrom(o.getClass())) return false;

        SalesPoint that = (SalesPoint) o;

        if (Double.compare(that.xCoordinate, xCoordinate) != 0) return false;
        return Double.compare(that.yCoordinate, yCoordinate) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(xCoordinate);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yCoordinate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
