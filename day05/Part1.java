import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = Files.readString(Paths.get("day05/input.txt")).split("\n\n");
        var rules = Arrays.stream(input[0].split("\n")).toList();
        var lines = Arrays.stream(input[1].split("\n"))
                .map(x -> x.split(","))
                .toList();
        var result = lines.stream()
                .filter(x -> inOrder(x, rules))
                .map(x -> x[x.length/2])
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println(result);


    }

    private static boolean inOrder(String[] line, List<String> rules) {
        for (int i = 0; i < line.length - 1; i++) {
            if (!rules.contains(line[i] + "|" + line[i + 1])) {
                return false;
            }
        }
        return true;
    }
}