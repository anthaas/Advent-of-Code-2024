import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Paths.get("day07/input.txt"));
        var data = lines.stream()
                .map(x -> x.split(": "))
                .toList();

        var result = 0L;
        for (var line : data) {
            var expectedResult = Long.parseLong(line[0]);
            var equation = Stream.of(line[1].split(" ")).mapToLong(Long::parseLong).toArray();
            if (correctEquation(expectedResult, equation)) {
                result += expectedResult;
            }
        }
        System.out.println(result);
    }

    private static boolean correctEquation(Long expectedResult, long[] equation) {
        var operationsPermutation = generatePermutation("", equation.length - 1).stream().map(String::toCharArray).toList();
        for (var permutation : operationsPermutation) {
            var result = equation[0];
            for (int i = 1; i < equation.length; i++) {
                if (permutation[i - 1] == '+') {
                    result += equation[i];
                } else if (permutation[i - 1] == '*') {
                    result *= equation[i];
                }
            }
            if (expectedResult.equals(result)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> generatePermutation(String permutation, int length) {
        if (permutation.length() == length) {
            return List.of(permutation);
        }
        var result = new ArrayList<String>();
        result.addAll(generatePermutation(permutation + "+", length));
        result.addAll(generatePermutation(permutation + "*", length));
        return result;
    }
}