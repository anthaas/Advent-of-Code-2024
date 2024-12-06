import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Part2 {
    public static void main(String[] args) throws IOException {
        char[][] input = Files.readAllLines(Paths.get("day06/input.txt"))
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);


        var posX = 0;
        var posY = 0;
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

        var result = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                //bruteforce try every position
                if (input[i][j] == '.') {
                    input[i][j] = '#';
                    System.out.println(i + "," + j);
                    if (stuckedInLoop(input, posX, posY)) {
                        result++;
                    }
                    input[i][j] = '.';
                }
            }
        }

        System.out.println(result);
    }

    private static boolean stuckedInLoop(char[][] input, int posX, int posY) {
        var incX = -1;
        var incY = 0;
        var direction = "^";
        var visitedPositions = new ArrayList<String>();
        try {
            while (true) {
                var visitedEncoded = direction + "," + posX + "," + posY;
                if (visitedPositions.contains(visitedEncoded)) {
                    return true;
                }
                //test next position
                if (input[posX + incX][posY + incY] == '#') {
                    //change direction
                    if (incX == -1 && incY == 0) {
                        incX = 0;
                        incY = 1;
                        direction = ">";
                        visitedPositions.add(visitedEncoded);
                    } else if (incX == 0 && incY == 1) {
                        incX = 1;
                        incY = 0;
                        direction = "v";
                        visitedPositions.add(visitedEncoded);
                    } else if (incX == 1 && incY == 0) {
                        incX = 0;
                        incY = -1;
                        direction = "<";
                        visitedPositions.add(visitedEncoded);
                    } else if (incX == 0 && incY == -1) {
                        incX = -1;
                        incY = 0;
                        direction = "^";
                        visitedPositions.add(visitedEncoded);
                    }
                }
                //make move
                posX += incX;
                posY += incY;
            }
        } catch (Exception e) {
            //out of the board - exit
            return false;
        }
    }
}