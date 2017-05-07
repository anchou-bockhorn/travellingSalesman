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
        if (!(target instanceof UuidSalesPoint)) {
            throw new IllegalArgumentException("Not allowed to mix SalesPointHashMapUuid with SalesPoints without UUID");
        }
        UUID salesPointUuid = ((UuidSalesPoint) target).getUuid();
        targetDistances.put(salesPointUuid, this.calculateDistance(target));
        return this;
    }

    @Override
    public Double getTargetDistance(SalesPoint salesPoint) {
        if (!(salesPoint instanceof UuidSalesPoint)) {
            throw new IllegalArgumentException("Not allowed to mix SalesPointHashMapUuid with SalesPoints without UUID");
        }
        return targetDistances.get(((UuidSalesPoint) salesPoint).getUuid());
    }

    private UUID getUuid() {
        return uuid;
    }
}
