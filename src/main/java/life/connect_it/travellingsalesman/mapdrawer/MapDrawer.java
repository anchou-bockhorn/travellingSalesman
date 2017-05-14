package life.connect_it.travellingsalesman.mapdrawer;

import java.util.List;

public interface MapDrawer {
    void drawMap(List<double[]> coordinates, List<Integer> order);
}
