import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part2 {
    public static void main(String[] args) throws IOException {
        char[][] input = Files.readAllLines(Paths.get("day04/input.txt"))
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        var count = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                try {
                    StringBuilder word1 = new StringBuilder();
                    StringBuilder word2 = new StringBuilder();
                    word1.append(input[row + 0][col + 0]).append(input[row + 1][col + 1]).append(input[row + 2][col + 2]);
                    word2.append(input[row + 0][col + 2]).append(input[row + 1][col + 1]).append(input[row + 2][col + 0]);

                    if (("MAS".contentEquals(word1) || "SAM".contentEquals(word1)) && ("MAS".contentEquals(word2) || "SAM".contentEquals(word2))) {
                        count++;
                    }
                } catch (Exception e) {
                }
            }
        }

        System.out.println(count);
    }
}