import java.util.*;

class UndergroundSystem {

    // id -> (station, time)
    private Map<Integer, Pair> checkInMap;

    // route -> (totalTime, count)
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

        String start = p.station;
        int startTime = p.time;

        String route = start + "->" + stationName;
        int travelTime = t - startTime;

        routeMap.putIfAbsent(route, new int[2]);

        routeMap.get(route)[0] += travelTime; // total time
        routeMap.get(route)[1] += 1;          // count

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "->" + endStation;
        int[] data = routeMap.get(route);

        return (double) data[0] / data[1];
    }

    // Helper class
    class Pair {
        String station;
        int time;

        Pair(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }
}