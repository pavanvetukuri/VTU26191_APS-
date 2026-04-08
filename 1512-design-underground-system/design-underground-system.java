import java.util.*;

class UndergroundSystem {

    // store check-in details
    private Map<Integer, Pair> checkInMap;

    // store route data: total time & count
    private Map<String, int[]> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair p = checkInMap.get(id);

        String startStation = p.station;
        int startTime = p.time;

        String route = startStation + "-" + stationName;
        int travelTime = t - startTime;

        routeMap.putIfAbsent(route, new int[2]);

        routeMap.get(route)[0] += travelTime; // total time
        routeMap.get(route)[1] += 1;          // count
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;

        int[] data = routeMap.get(route);

        return (double) data[0] / data[1];
    }
}

// helper class
class Pair {
    String station;
    int time;

    Pair(String station, int time) {
        this.station = station;
        this.time = time;
    }
}