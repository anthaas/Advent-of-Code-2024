import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part2 {
    public static void main(String[] args) throws IOException {
        char[][] data = Files.readAllLines(Paths.get("day10/input.txt"))
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        var result = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == '0') {
                    result += getTrailheadRating(data, i, j, '0');
                }
            }
        }

        System.out.println(result);
    }

    private static int getTrailheadRating(char[][] data, int i, int j, char current) {
        if (current == '9') {
            return 1;
        }

        var score = 0;
        var nextI = i + 1;
        var nextJ = j;
        if (inBounds(nextI, nextJ, data)
                && data[nextI][nextJ] == current + 1) {
            score += getTrailheadRating(data, nextI, nextJ, (char) (current + 1));
        }

        nextI = i;
        nextJ = j + 1;
        if (inBounds(nextI, nextJ, data)
                && data[nextI][nextJ] == current + 1) {
            score += getTrailheadRating(data, nextI, nextJ, (char) (current + 1));
        }

        nextI = i - 1;
        nextJ = j;
        if (inBounds(nextI, nextJ, data)
                && data[nextI][nextJ] == current + 1) {
            score += getTrailheadRating(data, nextI, nextJ, (char) (current + 1));
        }

        nextI = i;
        nextJ = j - 1;
        if (inBounds(nextI, nextJ, data)
                && data[nextI][nextJ] == current + 1) {
            score += getTrailheadRating(data, nextI, nextJ, (char) (current + 1));
        }

        return score;
    }

    private static boolean inBounds(int nextI, int nextJ, char[][] data) {
        return nextI >= 0 && nextI < data.length && nextJ >= 0 && nextJ < data[0].length;
    }
}