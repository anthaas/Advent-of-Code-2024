import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var robots = Files.readAllLines(Paths.get("day14/input.txt")).stream().map(Robot::new).toList();
        var boardWidth = 101;
        var boardHeight = 103;
        var seconds = 100;

        robots.forEach(x -> x.makeMove(seconds, boardWidth, boardHeight));
        var filteredRobots = robots.stream()
                .filter(x -> x.posX != boardWidth / 2 && x.posY != boardHeight / 2)
                .map(x -> getQuadrant(x, boardWidth, boardHeight))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet();
        var result = 1;
        for (var entry : filteredRobots) {
            result *= entry.getValue();
        }


        System.out.println(result);

    }

    private static int getQuadrant(Robot robot, int boardWidth, int boardHeight) {
        var borderX = boardWidth / 2;
        var borderY = boardHeight / 2;

        if (robot.posX < borderX) {
            if (robot.posY < borderY) {
                return 1;
            } else {
                return 4;
            }
        } else {
            if (robot.posY < borderY) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}