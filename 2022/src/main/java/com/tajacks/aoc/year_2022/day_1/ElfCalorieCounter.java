package com.tajacks.aoc.year_2022.day_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public final class ElfCalorieCounter {
    private final List<String> elfCaloricBreakdown;
    private final BiFunction<List<List<String>>, String, List<List<String>>> SPLIT_ON_EMPTY = (s, e) -> {
        if (e.isEmpty()) {
            s.add(new ArrayList<>());
        } else {
            s.get(s.size() - 1).add(e);
        }
        return s;
    };

    public ElfCalorieCounter(List<String> elfCaloricBreakdown) {
        this.elfCaloricBreakdown = new ArrayList<>(elfCaloricBreakdown);
    }

    public long largestElfByCalories() {
        return largestElvesByCalories(1).get(0);
    }

    public List<Long> largestElvesByCalories(int n) {
        return elfTotals().stream().limit(n).toList();
    }

    public long largestElvesByCaloriesSummed(int n) {
        return largestElvesByCalories(n).stream().reduce(0L, Long::sum);
    }

    // Alternate method for calculating elf totals
    public List<Long> elfTotalsStream() {
        List<List<String>> accumulator = new ArrayList<>();
        accumulator.add(new ArrayList<>());
        return elfCaloricBreakdown.stream()
                                  .reduce(accumulator, SPLIT_ON_EMPTY, (l1, l2) -> Collections.emptyList())
                                  .stream()
                                  .filter(Predicate.not(List::isEmpty))
                                  .map(s -> s.stream().map(Long::parseLong).reduce(0L, Long::sum))
                                  .toList();
    }

    public List<Long> elfTotals() {
        return _elfTotal(new ArrayList<>(), 0, 0).stream().sorted(Comparator.reverseOrder()).toList();
    }

    private List<Long> _elfTotal(List<Long> accumulator, long currentElfSize, int index) {
        if (index >= elfCaloricBreakdown.size()) {
            return accumulator;
        }

        String workingValue = elfCaloricBreakdown.get(index);

        if (workingValue.trim().isEmpty()) {
            accumulator.add(currentElfSize);
            return _elfTotal(accumulator, 0, index + 1);
        }

        long value = Long.parseLong(workingValue);
        return _elfTotal(accumulator, currentElfSize + value, index + 1);
    }
}
