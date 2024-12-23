import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws IOException {
        var networkMap = Files.readAllLines(Paths.get("day23/input.txt"));
        var nodes = new HashMap<String, List<String>>();

        for (var x : networkMap) {
            var n = x.split("-");
            putIfNotExists(n[0], n[1], nodes);
            putIfNotExists(n[1], n[0], nodes);
        }

        var largestNetwork = new HashSet<String>();
        for (var entry : nodes.entrySet()) {
            var k = entry.getKey();
            var v = entry.getValue();
            for (int i = 0; i < v.size(); i++) {
                var lan = new HashSet<String>();
                lan.add(k);
                lan.add(v.get(i));
                for (int j = i + 1; j < v.size(); j++) {
                    var in = true;
                    for (var s : lan) {
                        if (!nodes.get(s).contains(v.get(j))) {
                            in = false;
                            break;
                        }
                    }
                    if (in) {
                        lan.add(v.get(j));
                    }
                }
                if (lan.size() > largestNetwork.size()) {
                    largestNetwork = lan;
                }
            }
        }

        System.out.println(String.join(",", largestNetwork.stream().sorted().toList()));

    }

    private static void putIfNotExists(String key, String value, Map<String, List<String>> nodes) {
        if (!nodes.containsKey(key)) {
            var newValue = new ArrayList<String>();
            newValue.add(value);
            nodes.put(key, newValue);
        } else {
            nodes.get(key).add(value);
        }
    }


}