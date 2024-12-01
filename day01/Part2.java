import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Part2 {
    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Paths.get("day01/input.txt"));
        var left = new ArrayList<String>();
        var right = new ArrayList<String>();
        lines.forEach(x -> {
            var splitted = x.split("  ");
            left.add(splitted[0].trim());
            right.add(splitted[1].trim());
        });

        var result = left.stream().map( x ->  Integer.parseInt(x) * Collections.frequency(right, x)).mapToInt(Integer::intValue).sum();

        System.out.println(result);
    }
}