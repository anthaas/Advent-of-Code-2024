import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var data = Arrays.stream(Files.readString(Paths.get("day11/input.txt")).split(" ")).toList();

        for (int i = 0; i < 25; i++) {
            var nextIteration = new ArrayList<String>();
            for (String stone : data) {
                if ("0".equals(stone)) {
                    nextIteration.add("1");
                } else if (stone.length() % 2 == 0) {
                    var left = stone.substring(0, stone.length() / 2);
                    var right = stone.substring(stone.length() / 2);
                    nextIteration.add(String.valueOf(Long.parseLong(left)));
                    nextIteration.add(String.valueOf(Long.parseLong(right)));
                } else {
                    nextIteration.add(String.valueOf(Long.parseLong(stone) * 2024));
                }
            }
            data = nextIteration;
        }

        System.out.println(data.size());

    }
}