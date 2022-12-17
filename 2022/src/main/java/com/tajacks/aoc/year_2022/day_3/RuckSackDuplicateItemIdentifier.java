package com.tajacks.aoc.year_2022.day_3;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum RuckSackDuplicateItemIdentifier {
    INSTANCE;

    public static int prioritize(String rucksackPockets) {
        String[] pockets = splitInHalf(rucksackPockets);
        Set<Integer> left = pockets[0].chars().boxed().collect(Collectors.toUnmodifiableSet());
        Set<Integer> right = pockets[1].chars().boxed().collect(Collectors.toUnmodifiableSet());
        int oddOneOut = left.stream().filter(right::contains).findAny().orElseThrow();
        return getPriority(oddOneOut);
    }

    private static String[] splitInHalf(String s) {
        return new String[]{ s.substring(0, s.length() / 2), s.substring(s.length() / 2) };
    }

    private static int getPriority(int item) {
        return item > 96 ? item - 96 : item - 38;
    }

    public static int prioritizeChunked(List<String> elfGroup) {
        Set<Integer> elfOne = elfGroup.get(0).chars().boxed().collect(Collectors.toUnmodifiableSet());
        Set<Integer> elfTwo = elfGroup.get(1).chars().boxed().collect(Collectors.toUnmodifiableSet());
        return elfGroup.get(2).chars()
                       .filter(elfOne::contains)
                       .filter(elfTwo::contains)
                       .boxed()
                       .findAny()
                       .map(RuckSackDuplicateItemIdentifier::getPriority)
                       .orElseThrow();
    }
}
