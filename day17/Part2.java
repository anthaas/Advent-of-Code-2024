import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        var data = Files.readString(Paths.get("day17/input.txt")).split("\n\n");
        var program = Arrays.stream(data[1].split(": ")[1].split(",")).map(Integer::parseInt).toList();
        var result = findMatchingOutput(program, program);
        System.out.println(result);

    }

    private static long findMatchingOutput(List<Integer> program, List<Integer> target) {
        //3bit digits
        var initA = target.size() == 1 ? 0 : 8 * findMatchingOutput(program, target.subList(1, target.size()));
        while (!runProgram(program, initA).equals(target)) {
            initA++;
        }
        return initA;
    }

    private static List<Integer> runProgram(List<Integer> program, long initA) {
        long regA = initA;
        long regB = 0L;
        long regC = 0L;

        var output = new ArrayList<Integer>();
        var instructionPointer = 0;
        while (instructionPointer < program.size()) {
            var operand = program.get(instructionPointer + 1);
            var comboOperand = getComboOperand(operand, regA, regB, regC);
            var skipInstrctionPointerMove = false;

            switch (program.get(instructionPointer)) {
                case 0 -> regA = regA / (long) Math.pow(2, comboOperand);
                case 1 -> regB = regB ^ operand;
                case 2 -> regB = comboOperand % 8;
                case 3 -> {
                    if (regA != 0) {
                        instructionPointer = operand;
                        skipInstrctionPointerMove = true;
                    }
                }
                case 4 -> regB = regB ^ regC;
                case 5 -> output.add((int) (comboOperand % 8));
                case 6 -> regB = regA / (long) Math.pow(2, comboOperand);
                case 7 -> regC = regA / (long) Math.pow(2, comboOperand);
            }
            if (!skipInstrctionPointerMove) {
                instructionPointer += 2;
            }
        }
        return output;
    }

    private static Long getComboOperand(int operand, long regA, long regB, long regC) {
        return switch (operand) {
            case 0, 1, 2, 3 -> (long) operand;
            case 4 -> regA;
            case 5 -> regB;
            case 6 -> regC;
            default -> null;
        };
    }
}