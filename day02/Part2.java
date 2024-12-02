import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Paths.get("day02/input.txt"))
                .stream()
                .map(x ->
                        Arrays.stream(x.split(" "))
                                .map(Integer::parseInt)
                                .toList())
                .toList();
        var safe = lines.stream()
                .map(Part2::isSafeExt)
                .filter(Boolean::booleanValue)
                .toList()
                .size();
        System.out.println(safe);
    }

    private static boolean isSafeExt(List<Integer> x) {
        for (int i = 0; i < x.size(); i++) {
            var clone = new ArrayList<>(x);
            clone.remove(i);
            var isSafe = isSafe(clone);
            if (isSafe) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafe(List<Integer> x) {
        var line = new ArrayList<Integer>();
        for (int i = 0; i < x.size() - 1; i++) {
            line.add(x.get(i) - x.get(i + 1));
        }
        return (line.stream().filter(i -> i < 0 && i >= -3).toArray().length == line.size())
                || (line.stream().filter(i -> i > 0 && i <= 3).toArray().length == line.size());
    }
}