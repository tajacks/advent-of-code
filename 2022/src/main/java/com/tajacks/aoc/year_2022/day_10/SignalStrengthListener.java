package com.tajacks.aoc.year_2022.day_10;

import java.util.List;
import java.util.ArrayList;

public enum SignalStrengthListener {
    INSTANCE;


    public static int sumSignalStrengths(String puzzleInput, int... cycles) {
        String[] rowsStr = puzzleInput.split("\\r?\\n");
        List<Integer> totals = new ArrayList<>();
        for (int cycle : cycles) {
            totals.add(signalStrength(cycle, rowsStr));
        }
        return totals.stream().reduce(0, Integer::sum);
    }

    public static void drawMessage(String puzzleInput) {
        String[] rowsStr = puzzleInput.split("\\r?\\n");
        StringBuilder bob = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 40; j++) {
                int xReg = xRegister((j + 1) + (40 * i), rowsStr);
                if (xReg == j || xReg - 1 == j || xReg + 1 == j) {
                    bob.append("#");
                } else {
                    bob.append(".");
                }
            }
            bob.append(System.lineSeparator());
        }
        System.out.println(bob);
    }

    public static int signalStrength(int cycle, String[] cycleInstructions) {
        return signalStrength_(cycle, 0, 1, 1, 1, cycleInstructions);
    }

    public static int xRegister(int cycle, String[] cycleInstructions) {
        return xRegister_(cycle, 0, 1, 1, 1, cycleInstructions);
    }


    public static int signalStrength_(int cycleTarget, int commandIndex, int counter, int accumulator, int pause, String[] input) {
        if (cycleTarget == counter) {
            return accumulator * cycleTarget;
        }

        String instructions = input[commandIndex];

        if ("noop".equals(instructions)) {
            return signalStrength_(cycleTarget, commandIndex + 1,counter + 1, accumulator, pause, input);
        }

        if (pause == 0) {
            return signalStrength_(cycleTarget, commandIndex + 1, counter + 1, accumulator + Integer.parseInt(instructions.split(" ")[1]), 1, input);
        }

        return signalStrength_(cycleTarget, commandIndex,counter + 1, accumulator, pause - 1, input);
    }

    public static int xRegister_(int cycleTarget, int commandIndex, int counter, int accumulator, int pause, String[] input) {
        if (cycleTarget == counter) {
            return accumulator;
        }

        String instructions = input[commandIndex];

        if ("noop".equals(instructions)) {
            return xRegister_(cycleTarget, commandIndex + 1,counter + 1, accumulator, pause, input);
        }

        if (pause == 0) {
            return xRegister_(cycleTarget, commandIndex + 1, counter + 1, accumulator + Integer.parseInt(instructions.split(" ")[1]), 1, input);
        }

        return xRegister_(cycleTarget, commandIndex,counter + 1, accumulator, pause - 1, input);
    }
}
