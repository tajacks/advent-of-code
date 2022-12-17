package com.tajacks.aoc.year_2022.day_5;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum CrateArrangementDecoder {
    INSTANCE;

    public static Map<Integer, List<String>> parseDiagram(String input) {
        String[] lines = input.split("\\r?\\n");
        Map<Integer, List<String>> results = new HashMap<>();
        for (String line : lines) {
            if (!line.contains("[")) {
                break;
            }

            int colNum = 1;
            for (int i = 2; i < line.length(); i += 4) {
                String columnCharacter = line.substring(i - 1, i);
                if (!columnCharacter.trim().isEmpty()) {
                    results.computeIfAbsent(colNum, k -> new ArrayList<>()).add(columnCharacter);
                }

                colNum += 1;
            }

        }
        return results;
    }

    public static List<String> parseInstructions(String input) {
        String[] lines = input.split("\\r?\\n");
        int index = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().isEmpty()) {
                index = i;
            }
        }

        return Arrays.stream(Arrays.copyOfRange(lines, index, lines.length)).filter(Predicate.not(String::isEmpty)).toList();
    }

    public static String executeInstructions(List<String> instructions, Map<Integer, List<String>> columnMap) {
        Map<Integer, List<String>> workingCopy = new HashMap<>(columnMap);
        for (String instruction : instructions) {
            workingCopy = executeInstruction(instruction, workingCopy);
        }
        return workingCopy.values().stream().filter(Predicate.not(List::isEmpty)).map(l -> l.get(0)).map(c -> c.toString()).collect(Collectors.joining());
    }

    private static Map<Integer, List<String>> executeInstruction(String instruction, Map<Integer, List<String>> toModify) {
        // Take defensive copy
        Map<Integer, List<String>> workingCopy = new HashMap<>(toModify);
        int[] movingInstructions = decodeInstruction(instruction);
        for (int i = 0; i < movingInstructions[0]; i++) {
            List<String> moveFrom = workingCopy.get(movingInstructions[1]);
            List<String> moveInto = workingCopy.get(movingInstructions[2]);
            moveInto.add(0, moveFrom.get(0));
            moveFrom.remove(0);
            workingCopy.put(movingInstructions[2], moveInto);
            workingCopy.put(movingInstructions[1], moveFrom);
        }
        return workingCopy;
    }

    private static int[] decodeInstruction(String instruction) {
        String[] split = instruction.split(" ");
        return new int[]{ Integer.parseInt(split[1]), Integer.parseInt(split[3]), Integer.parseInt(split[5]) };
    }

    public static String executeInstructionsPartTwo(List<String> instructions, Map<Integer, List<String>> columnMap) {
        Map<Integer, List<String>> workingCopy = new HashMap<>(columnMap);
        for (String instruction : instructions) {
            workingCopy = executeInstructionsPartTwo(instruction, workingCopy);
        }
        return workingCopy.values().stream().filter(Predicate.not(List::isEmpty)).map(l -> l.get(0)).map(c -> c.toString()).collect(Collectors.joining());
    }

    private static Map<Integer, List<String>> executeInstructionsPartTwo(String instruction, Map<Integer, List<String>> toModify) {
        // Take defensive copy
        Map<Integer, List<String>> workingCopy = new HashMap<>(toModify);
        int[] movingInstructions = decodeInstruction(instruction);
        List<String> moveFrom = workingCopy.get(movingInstructions[1]);
        List<String> moveInto = workingCopy.get(movingInstructions[2]);

        List<String> moving = moveFrom.subList(0, movingInstructions[0]);
        List<String> remaining = moveFrom.subList(movingInstructions[0], moveFrom.size());

        for (int i = moving.size() - 1; i >= 0; i--) {
            moveInto.add(0, moving.get(i));
        }

        workingCopy.put(movingInstructions[1], remaining);
        workingCopy.put(movingInstructions[2], moveInto);
        return workingCopy;
    }
}
