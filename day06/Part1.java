import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Part1 {
    public static void main(String[] args) throws IOException {
        char[][] input = Files.readAllLines(Paths.get("day06/input.txt"))
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        var posX = 0;
        var posY = 0;
        var incX = -1;
        var incY = 0;

        //find the init position
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '^') {
                    posX = i;
                    posY = j;
                    break;
                }
            }
        }

        try {
            while (true) {
                //change current position to visited
                input[posX][posY] = 'X';
                //test next position
                if (input[posX + incX][posY + incY] == '#') {
                    //change direction
                    if (incX == -1 && incY == 0) {
                        incX = 0;
                        incY = 1;
                    } else if (incX == 0 && incY == 1) {
                        incX = 1;
                        incY = 0;
                    } else if (incX == 1 && incY == 0) {
                        incX = 0;
                        incY = -1;
                    } else if (incX == 0 && incY == -1) {
                        incX = -1;
                        incY = 0;
                    }
                }
                //make move
                posX += incX;
                posY += incY;
            }
        } catch (Exception e) {
            //out of the board - exit
        }
        //count path
        var result = Arrays.stream(input)
                .map(String::new)
                .map(x -> x.replaceAll("[^X]", ""))
                .map(String::length)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(result);
    }
}