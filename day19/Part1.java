import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var data = Files.readString(Paths.get("day19/input.txt")).split("\n\n");
        var patterns = Arrays.stream(data[0].split(",")).map(String::trim).toList();
        var designs = Arrays.stream(data[1].split("\n")).toList();
        var result = designs.stream().map(x -> possiblePattern(x, patterns)).filter(Boolean::booleanValue).count();

        System.out.println(result);

    }

    private static boolean possiblePattern(String design, List<String> patterns) {
        if (design.isEmpty()) {
            return true;
        }
        for (String pattern : patterns) {
            if (design.startsWith(pattern)
                    && possiblePattern(design.substring(pattern.length()), patterns)) {
                return true;
            }
        }
        return false;
    }
}