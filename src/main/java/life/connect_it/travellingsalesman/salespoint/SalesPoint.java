package life.connect_it.travellingsalesman.salespoint;

public abstract class SalesPoint {
    double xCoordinate;
    double yCoordinate;

    public abstract SalesPoint addTarget(SalesPoint salesPoint);
    public abstract Double getTargetDistance(SalesPoint salesPoint);
    public abstract int getTargetDistancesNumber();

    public SalesPoint(double xCoordinate, double yCoordinate) {
        if (xCoordinate < 0 || yCoordinate < 0) {
            throw new IllegalArgumentException("Negative values are not supported for SalesPoint coordinates");
        }
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public double calculateDistance(SalesPoint target) {
        double xDistance = Math.pow(this.getXCoordinate() - target.getXCoordinate(), 2);
        double yDistance = Math.pow(this.getYCoordinate() - target.getYCoordinate(), 2);
        return Math.sqrt(xDistance + yDistance);
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
