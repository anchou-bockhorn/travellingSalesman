package life.connect_it.travellingsalesman.salespoint;

import java.util.HashMap;
import java.util.UUID;

public class SalesPointHashMapUuid extends SalesPoint {
    private final UUID uuid = UUID.randomUUID();
    private HashMap<UUID, Double> targetDistances = new HashMap<>();

    public SalesPointHashMapUuid(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    public SalesPoint addTarget(SalesPoint salesPoint) {
        if (!(salesPoint instanceof SalesPointHashMapUuid)) {
            throw new IllegalArgumentException("Not allowed to mix SalesPointHashMapUuid with SalesPoints without UUID");
        }
        UUID salesPointUuid = ((SalesPointHashMapUuid) salesPoint).getUuid();
        targetDistances.put(salesPointUuid, this.calculateDistance(salesPoint));
        return salesPoint;
    }

    public UUID getUuid() {
        return uuid;
    }

    public HashMap<UUID, Double> getTargetDistances() {
        return targetDistances;
    }
}
