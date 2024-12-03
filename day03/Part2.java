import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) throws IOException {
        var input = Files.readString(Paths.get("day03/input.txt"));

        Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\))");
        Matcher matcher = pattern.matcher(input);
        var correctInstructions = new ArrayList<String>();
        while (matcher.find()) {
            correctInstructions.add(matcher.group());
        }
        var execute = true;
        var result = 0;
        for (var instruction : correctInstructions) {
            if (instruction.startsWith("mul") && execute) {
                var arguments = Arrays.stream(instruction.replace("mul(", "").replace(")", "").split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                result += arguments[0] * arguments[1];
            } else if (instruction.startsWith("don't")) {
                execute = false;
            } else if (instruction.startsWith("do")) {
                execute = true;
            }
        }

        System.out.println(result);
    }
}