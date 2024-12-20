import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

    public static void main(String[] args) throws IOException {
        var data = Files.readString(Paths.get("day19/input.txt")).split("\n\n");
        var patterns = Arrays.stream(data[0].split(",")).map(String::trim).toList();
        var designs = Arrays.stream(data[1].split("\n")).toList();
        var result = designs.stream()
                .map(x -> possiblePattern(x, patterns, new HashMap<>()))
                .mapToLong(Long::longValue)
                .sum();

        System.out.println(result);

    }

    private static long possiblePattern(String design, List<String> patterns, Map<String, Long> memory) {
        if (design.isEmpty()) {
            return 1L;
        }
        if (memory.containsKey(design)) {
            return memory.get(design);
        }
        var count = 0L;
        for (String pattern : patterns) {
            if (design.startsWith(pattern)) {
                count += possiblePattern(design.substring(pattern.length()), patterns, memory);
            }
        }
        memory.put(design, count);
        return count;
    }
}