import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = Files.readString(Paths.get("day03/input.txt"));

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(input);
        var correctInstructions = new ArrayList<String>();
        while (matcher.find()) {
            correctInstructions.add(matcher.group().replace("mul(", "").replace(")",""));
        }
        var result = correctInstructions.stream()
                .map(x -> x.split(","))
                .map(x -> Integer.parseInt(x[0]) * Integer.parseInt(x[1]))
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(result);
    }
}