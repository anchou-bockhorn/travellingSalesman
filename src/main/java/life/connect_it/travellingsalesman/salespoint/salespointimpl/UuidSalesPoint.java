package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import java.util.HashMap;
import java.util.UUID;

public class UuidSalesPoint extends SalesPoint {
    private final UUID uuid = UUID.randomUUID();
    private HashMap<UUID, Double> targetDistances = new HashMap<>();

    public UuidSalesPoint(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    @Override
    public SalesPoint addTarget(SalesPoint target) {
        UUID salesPointUuid = castTarget(target).getUuid();
        targetDistances.put(salesPointUuid, this.calculateDistance(target));
        return this;
    }

    @Override
    public SalesPoint removeTarget(SalesPoint target) {
        Double removedDistance = targetDistances.remove(castTarget(target).getUuid());
        if (removedDistance == null) {
            throw new IllegalArgumentException("Removed SalesPoint: " + target.toString() + "is not present in" +
                " targets collection");
        }
        return this;
    }

    @Override
    public Double getTargetDistance(SalesPoint target) {
        return targetDistances.get(castTarget(target).getUuid());
    }

    private UuidSalesPoint castTarget(SalesPoint target) {
        if (!(target instanceof UuidSalesPoint)) {
            throw new IllegalArgumentException("Not allowed to mix SalesPointHashMapUuid with SalesPoints without UUID");
        }
        return (UuidSalesPoint) target;
    }

    private UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "UuidSalesPoint{" +
            "uuid=" + uuid +
            ", xCoordinate=" + xCoordinate +
            ", yCoordinate=" + yCoordinate +
            '}';
    }
}
