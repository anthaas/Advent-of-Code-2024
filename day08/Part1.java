import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Part1 {
    public static void main(String[] args) throws IOException {
        char[][] data = Files.readAllLines(Paths.get("day08/input.txt"))
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        var antennas = new HashMap<Character, HashSet<String>>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != '.') {
                    if (!antennas.containsKey(data[i][j])) {
                        var newSet = new HashSet<String>();
                        newSet.add(i + "," + j);
                        antennas.put(data[i][j], newSet);
                    } else {
                        antennas.get(data[i][j]).add(i + "," + j);
                    }
                }
            }
        }

        var antiNodes = new HashSet<String>();

        for (var entry : antennas.values()) {
            var list = new ArrayList<>(entry);
            for (int i = 0; i < list.size(); i++) {
                for (int j = i+1; j < list.size(); j++) {
                    var ax = Integer.parseInt(list.get(i).split(",")[0]);
                    var ay = Integer.parseInt(list.get(i).split(",")[1]);
                    var bx = Integer.parseInt(list.get(j).split(",")[0]);
                    var by = Integer.parseInt(list.get(j).split(",")[1]);
                    var diffx = Math.abs(bx - ax);
                    var diffy = Math.abs(by - ay);

                    var newAx = ax - diffx;
                    var newAy = ay - diffy;
                    var newBx = bx + diffx;
                    var newBy = by + diffy;

                    if (ax > bx) {
                        newAx = ax + diffx;
                        newBx = bx - diffx;
                    }

                    if (ay > by) {
                        newAy = ay + diffy;
                        newBy = by - diffy;
                    }


                    if (newAx >= 0 && newAy >= 0 && newAx < data.length && newAy < data.length) {
                        antiNodes.add(newAx + "," + newAy);
                    }
                    if (newBx >= 0 && newBy >= 0 && newBx < data.length && newBy < data.length) {
                        antiNodes.add(newBx + "," + newBy);
                    }
                }
            }
        }

        System.out.println(antiNodes.size());
    }
}