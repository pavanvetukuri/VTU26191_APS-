import java.util.*;

class ThroneInheritance {

    private Map<String, List<String>> family;
    private Set<String> dead;
    private String king;

    public ThroneInheritance(String kingName) {
        family = new HashMap<>();
        dead = new HashSet<>();
        king = kingName;
        family.put(king, new ArrayList<>());
    }

    public void birth(String parentName, String childName) {
        family.putIfAbsent(parentName, new ArrayList<>());
        family.get(parentName).add(childName);
        family.put(childName, new ArrayList<>());
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> result = new ArrayList<>();
        dfs(king, result);
        return result;
    }

    private void dfs(String person, List<String> result) {
        // add only if alive
        if (!dead.contains(person)) {
            result.add(person);
        }

        // visit children
        for (String child : family.get(person)) {
            dfs(child, result);
        }
    }
}