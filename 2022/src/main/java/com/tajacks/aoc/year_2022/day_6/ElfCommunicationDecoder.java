package com.tajacks.aoc.year_2022.day_6;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public enum ElfCommunicationDecoder {
    INSTANCE;

    public static int findStartOfPacket(String transmission, int START_INDEX) {
        List<Integer> charsForString = transmission.chars().boxed().collect(Collectors.toList());

        List<Integer> recentChars = charsForString.subList(0, START_INDEX);
        for (int i = START_INDEX; i < charsForString.size(); i++) {

            if (new HashSet<>(recentChars).size() == recentChars.size()) {
                return i + 1;
            }

            recentChars.remove(0);
            recentChars.add(charsForString.get(i));
        }
        return charsForString.size();
    }
}
