package life.connect_it.travellingsalesman.salespoint.salespointimpl;

import java.util.HashMap;
import java.util.UUID;

public class SalesPointHashMapUuid extends SalesPoint {
    private final UUID uuid = UUID.randomUUID();
    private HashMap<UUID, Double> targetDistances = new HashMap<>();

    public SalesPointHashMapUuid(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    @Override
    public SalesPoint addTarget(SalesPoint salesPoint) {
        if (!(salesPoint instanceof SalesPointHashMapUuid)) {
            throw new IllegalArgumentException("Not allowed to mix SalesPointHashMapUuid with SalesPoints without UUID");
        }
        UUID salesPointUuid = ((SalesPointHashMapUuid) salesPoint).getUuid();
        targetDistances.put(salesPointUuid, this.calculateDistance(salesPoint));
        return salesPoint;
    }

    @Override
    public Double getTargetDistance(SalesPoint salesPoint) {
        if (!(salesPoint instanceof SalesPointHashMapUuid)) {
            throw new IllegalArgumentException("Not allowed to mix SalesPointHashMapUuid with SalesPoints without UUID");
        }
        return targetDistances.get(((SalesPointHashMapUuid) salesPoint).getUuid());
    }

    @Override
    public int getTargetDistancesNumber() {
        return targetDistances.size();
    }

    private UUID getUuid() {
        return uuid;
    }
}
