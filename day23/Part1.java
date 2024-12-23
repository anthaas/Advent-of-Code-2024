import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var networkMap = Files.readAllLines(Paths.get("day23/input.txt"));
        var nodes = new HashMap<String, List<String>>();
        var pairs = new HashSet<String>();

        for (var x : networkMap) {
            var n = x.split("-");
            putIfNotExists(n[0], n[1], nodes);
            putIfNotExists(n[1], n[0], nodes);
            pairs.add(x);
        }

        var threeInnerConnectedComputers = new HashSet<String>();
        for (var n1 : nodes.keySet()) {
            for (var pair : pairs) {
                var n2 = pair.split("-")[0];
                var n3 = pair.split("-")[1];
                if (n1.startsWith("t") || n2.startsWith("t") || n3.startsWith("t")) {
                    if (nodes.get(n2).contains(n1) && nodes.get(n3).contains(n1)) {
                        var connectedComputers = String.join("-", Stream.of(n1, n2, n3).sorted().toList());
                        threeInnerConnectedComputers.add(connectedComputers);
                    }
                }
            }
        }


        System.out.println(threeInnerConnectedComputers.size());

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