import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = Files.readString(Paths.get("day09/input.txt")).toCharArray();

        var dataId = 0;
        var data = true;
        var disk = new ArrayList<String>();
        for (var instruction : input) {
            if (data) {
                disk.addAll(Collections.nCopies(Integer.parseInt(String.valueOf(instruction)), String.valueOf(dataId)));
                dataId++;
            } else {
                disk.addAll(Collections.nCopies(Integer.parseInt(String.valueOf(instruction)), "."));
            }
            data = !data;
        }

        var left = 0;
        var right = disk.size() - 1;
        var emptyPosition = false;
        var lastPositionToSwap = false;

        while (left < right) {
            //find empty leftmost space
            if (!emptyPosition) {
                if (".".equals(disk.get(left))) {
                    emptyPosition = true;
                } else {
                    left++;
                }
            }
            if (!lastPositionToSwap) {
                if (".".equals(disk.get(right))) {
                    right--;
                } else {
                    lastPositionToSwap = true;
                }
            }
            if (emptyPosition && lastPositionToSwap) {
                disk.set(left, disk.get(right));
                disk.set(right, ".");
                emptyPosition = false;
                lastPositionToSwap = false;
            }
        }

        var result = 0L;
        var filtered = disk.stream()
                .filter(x -> !x.equals("."))
                .toList();
        for (int i = 0; i < filtered.size(); i++) {
            result += i * Long.parseLong(String.valueOf(filtered.get(i)));
        }

        System.out.println(result);
    }
}