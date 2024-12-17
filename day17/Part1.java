import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var data = Files.readString(Paths.get("day17/input.txt")).split("\n\n");
        var registers = data[0].split("\n");
        var regA = Integer.parseInt(registers[0].split(": ")[1]);
        var regB = Integer.parseInt(registers[1].split(": ")[1]);
        var regC = Integer.parseInt(registers[2].split(": ")[1]);
        var program = Arrays.stream(data[1].split(": ")[1].split(",")).map(Integer::parseInt).toList();

        StringBuilder output = new StringBuilder();
        String prefix = "";
        var instructionPointer = 0;
        while (instructionPointer < program.size()) {
            var operand = program.get(instructionPointer + 1);
            var comboOperand = getComboOperand(operand, regA, regB, regC);
            var skipInstrctionPointerMove = false;

            switch (program.get(instructionPointer)) {
                case 0 -> regA = regA / (int) Math.pow(2, comboOperand);
                case 1 -> regB = regB ^ operand;
                case 2 -> regB = comboOperand % 8;
                case 3 -> {
                    if (regA != 0) {
                        instructionPointer = operand;
                        skipInstrctionPointerMove = true;
                    }
                }
                case 4 -> regB = regB ^ regC;
                case 5 -> {
                    output.append(prefix);
                    prefix = ",";
                    output.append(comboOperand % 8);
                }
                case 6 -> regB = regA / (int) Math.pow(2, comboOperand);
                case 7 -> regC = regA / (int) Math.pow(2, comboOperand);
                default -> System.out.println("Unknown command");

            }
            if (!skipInstrctionPointerMove) {
                instructionPointer += 2;
            }
        }

        System.out.println(output);

    }

    private static Integer getComboOperand(Integer operand, int regA, int regB, int regC) {
        return switch (operand) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> regA;
            case 5 -> regB;
            case 6 -> regC;
            default -> null;
        };
    }
}