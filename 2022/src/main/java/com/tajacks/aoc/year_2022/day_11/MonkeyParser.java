package com.tajacks.aoc.year_2022.day_11;

import com.tajacks.libraries.common.Function;

import java.util.Arrays;
import java.util.List;

public enum MonkeyParser {
    INSTANCE;


    public static long getMonkeyBusiness(String puzzleInput, int numRounds, boolean shouldDivide) {
        String[] puzzleInputSplit = splitOnEmptyLine(puzzleInput);
        List<Monkey> monkeys = Arrays.stream(puzzleInputSplit).map(MonkeyParser::parseMonkey).toList();
        int superMod = monkeys.stream().mapToInt(Monkey::getOp).reduce((a,b) -> a*b).getAsInt();
        for (int i = 0; i < numRounds; i++) {
            for (Monkey monkey: monkeys) {
                monkey.performTurn(monkeys, shouldDivide, superMod);
            }
        }
        monkeys.stream().sorted().forEach(Monkey::printStatus);
        return monkeys.stream()
                .sorted()
                .limit(2)
                .mapToLong(Monkey::getInspected)
                .reduce(1L, (a, b) -> a * b);
    }

    public static Monkey parseMonkey(String monkeySource) {
        String[] monkeyParts = monkeySource.split("\\r?\\n");

        int monkeyNumber = Integer.parseInt(monkeyParts[0].split(" ")[1].replace(":", ""));
        List<Long> startingItems = Arrays.stream(monkeyParts[1].replace("Starting items: ", "").split(", ")).map(String::trim).map(Long::parseLong).toList();
        String[] opParts = monkeyParts[2].split("=")[1].trim().split(" ");
        Function<Long, Long> operation = getOperation(opParts[1].trim(), opParts[2].trim());
        int testNum = Integer.parseInt(monkeyParts[3].trim().replace("Test: divisible by ", "").trim());
        Function<Long, Boolean> test = i -> (i % testNum) == 0;
        int ifTrue = Integer.parseInt(monkeyParts[4].split("monkey")[1].trim());
        int ifFalse = Integer.parseInt(monkeyParts[5].split("monkey")[1].trim());
        return  new Monkey(operation, test, startingItems, monkeyNumber, ifTrue, ifFalse, testNum);
    }

    public static Function<Long, Long> getOperation (String operation, String byTarget) {
        if (byTarget.equals("old")) {
            return switch (operation) {
                case "*" -> i -> i * i;
                case "/" -> i -> 1L;
                case "+" -> i -> i + i;
                case "-" -> i -> 0L;
                default -> throw new UnsupportedOperationException();
            };
        }
        int by = Integer.parseInt(byTarget);
        return switch (operation) {
            case "*" -> i -> i * by;
            case "/" -> i -> i / by;
            case "+" -> i -> i + by;
            case "-" -> i -> i - by;
            default -> throw new UnsupportedOperationException();
        };
    }

    public static String[] splitOnEmptyLine(String input) {
        return input.split("\n\\s*\n");
    }
}
