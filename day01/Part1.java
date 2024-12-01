import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Paths.get("day01/input.txt"));
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();
        lines.forEach(x -> {
            var splitted = x.split("  ");
            left.add(Integer.parseInt(splitted[0].trim()));
            right.add(Integer.parseInt(splitted[1].trim()));
        });

        Collections.sort(left);
        Collections.sort(right);

        var result = 0;
        for (int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }
        System.out.println(result);
    }
}