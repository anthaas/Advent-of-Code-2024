import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Part2 {
    public static List<String> rules;

    public static void main(String[] args) throws IOException {
        var input = Files.readString(Paths.get("day05/input.txt")).split("\n\n");
        rules = Arrays.stream(input[0].split("\n")).toList();
        var lines = Arrays.stream(input[1].split("\n"))
                .map(x -> x.split(","))
                .toList();
        var result = lines.stream()
                .filter(x -> !inOrder(x))
                .map(x -> Arrays.stream(x)
                        .sorted(new PagesComparator())
                        .toList())
                .map(x -> x.get(x.size() / 2))
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println(result);
    }

    private static boolean inOrder(String[] line) {
        for (int i = 0; i < line.length - 1; i++) {
            if (!rules.contains(line[i] + "|" + line[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public static class PagesComparator implements Comparator<String> {
        @Override
        public int compare(String x, String y) {
            if (rules.contains(x + "|" + y)) {
                return -1;
            }
            if (rules.contains(y + "|" + x)) {
                return 1;
            }
            return 0;
        }
    }
}