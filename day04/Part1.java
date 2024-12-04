import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part1 {
    public static void main(String[] args) throws IOException {
        char[][] input = Files.readAllLines(Paths.get("day04/input.txt"))
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        var count = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                StringBuilder word = new StringBuilder();
                try {
                    word.append(input[row + 0][col + 0]).append(input[row + 0][col + 1]).append(input[row + 0][col + 2]).append(input[row + 0][col + 3]);
                    if ("XMAS".contentEquals(word) || "SAMX".contentEquals(word)) {
                        count++;
                    }
                } catch (Exception e) {
                }

                try {
                    word = new StringBuilder();
                    word.append(input[row + 0][col + 0]).append(input[row + 1][col + 0]).append(input[row + 2][col + 0]).append(input[row + 3][col + 0]);
                    if ("XMAS".contentEquals(word) || "SAMX".contentEquals(word)) {
                        count++;
                    }
                } catch (Exception e) {
                }
                try {
                    word = new StringBuilder();
                    word.append(input[row + 0][col + 0]).append(input[row + 1][col + 1]).append(input[row + 2][col + 2]).append(input[row + 3][col + 3]);
                    if ("XMAS".contentEquals(word) || "SAMX".contentEquals(word)) {
                        count++;
                    }
                } catch (Exception e) {
                }
                try {
                    word = new StringBuilder();
                    word.append(input[row + 0][col + 3]).append(input[row + 1][col + 2]).append(input[row + 2][col + 1]).append(input[row + 3][col + 0]);
                    if ("XMAS".contentEquals(word) || "SAMX".contentEquals(word)) {
                        count++;
                    }
                } catch (Exception e) {
                }
            }
        }

        System.out.println(count);
    }
}