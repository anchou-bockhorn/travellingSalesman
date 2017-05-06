package life.connect_it.travellingsalesman.salespoint;

public abstract class SalesPoint {
    double xCoordinate;
    double yCoordinate;

    public abstract SalesPoint addTarget(SalesPoint salesPoint);

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
    public boolean equals(Object object) {
        if (!(object instanceof SalesPointHashMap)) {
            return false;
        }
        if (this == object) {
            return true;
        }

        return xCoordinate == ((SalesPoint) object).xCoordinate
            && yCoordinate == ((SalesPoint) object).yCoordinate;
    }

    @Override
    public int hashCode() {
        int result = 17 * 31 + Double.valueOf(xCoordinate).intValue();
        return result * 31 + Double.valueOf(xCoordinate).intValue();
    }
}
