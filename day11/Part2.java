import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Part2 {

    record CachedBlink(String stone, Integer i) {
    }

    public static void main(String[] args) throws IOException {
        var data = Arrays.stream(Files.readString(Paths.get("day11/input.txt")).split(" ")).toList();

        var memory = new HashMap<CachedBlink, Long>();
        var result = 0L;
        for (var stone : data) {
            result += blink(stone, 75, memory);
        }

        System.out.println(result);
        System.out.println("Memory items: " + memory.size());

    }

    private static long blink(String stone, int blinks, Map<CachedBlink, Long> memory) {
        if (blinks == 0) {
            return 1L;
        } else if (memory.containsKey(new CachedBlink(stone, blinks))) {
            return memory.get(new CachedBlink(stone, blinks));
        } else if ("0".equals(stone)) {
            var result = blink("1", blinks - 1, memory);
            memory.put(new CachedBlink(stone, blinks), result);
            return result;
        } else if (stone.length() % 2 == 0) {
            var left = stone.substring(0, stone.length() / 2);
            var right = stone.substring(stone.length() / 2);
            var result = blink(String.valueOf(Long.parseLong(left)), blinks - 1, memory)
                    + blink(String.valueOf(Long.parseLong(right)), blinks - 1, memory);
            memory.put(new CachedBlink(stone, blinks), result);
            return result;
        } else {
            var result = blink(String.valueOf(Long.parseLong(stone) * 2024), blinks - 1, memory);
            memory.put(new CachedBlink(stone, blinks), result);
            return result;
        }
    }
}