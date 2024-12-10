import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Part2 {

    record Coords(int x, int y) {
    }

    public static void main(String[] args) throws IOException {
        char[][] data = Files.readAllLines(Paths.get("day08/input.txt"))
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        var antennas = new HashMap<Character, List<Coords>>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != '.') {
                    if (!antennas.containsKey(data[i][j])) {
                        antennas.put(data[i][j], new ArrayList<>());
                    }
                    antennas.get(data[i][j]).add(new Coords(i, j));
                }
            }
        }

        var antiNodes = new HashSet<Coords>();

        for (var entry : antennas.keySet()) {
            var list = antennas.get(entry);
            for (int i = 0; i < list.size() - 1; i++) {
                var first = list.get(i);
                for (int j = 0; j < list.size(); j++) {
                    var second = list.get(j);

                    var ax = first.x;
                    var ay = first.y;
                    var bx = second.x;
                    var by = second.y;
                    var diffx = Math.abs(bx - ax);
                    var diffy = Math.abs(by - ay);

                    for (int k = -50; k < 50; k++) {

                        var newAx = ax - diffx * k;
                        var newAy = ay - diffy * k;
                        var newBx = bx + diffx * k;
                        var newBy = by + diffy * k;

                        if (ax > bx) {
                            newAx = ax + diffx * k;
                            newBx = bx - diffx * k;
                        }

                        if (ay > by) {
                            newAy = ay + diffy * k;
                            newBy = by - diffy * k;
                        }


                        if (newAx >= 0 && newAy >= 0 && newAx < data.length && newAy < data.length) {
                            antiNodes.add(new Coords(newAx, newAy));
                        }
                        if (newBx >= 0 && newBy >= 0 && newBx < data.length && newBy < data.length) {
                            antiNodes.add(new Coords(newBx, newBy));
                        }
                    }
                }
            }
        }

        System.out.println(antiNodes.size());
    }
}