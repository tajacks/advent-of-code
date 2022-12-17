package com.tajacks.aoc.year_2022.day_4;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.tajacks.aoc.year_2022.day_4.OverlappingElfRangeDeterminer.Range;

public class OverlappingElfRangeDeterminerTest {

    private final int PART_ONE_SOLUTION = 582;
    private final int PART_TWO_SOLUTION = 893;
    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_4.txt";

    @Test
    public void canPassDayFour() throws IOException {
        try (Stream<String> SOURCES = Files.lines(Path.of(RESOURCE_PATH))) {
            List<String> DATA = SOURCES.toList();
            long PART_ONE = DATA.stream()
                                .map(line -> line.split(","))
                                .map(arr -> new Range[]{ new Range(arr[0]), new Range(arr[1]) })
                                .filter(ranges -> ranges[0].containedWithin(ranges[1]) || ranges[1].containedWithin(ranges[0]))
                                .count();

            assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);

            long PART_TWO = DATA.stream()
                                .map(line -> line.split(","))
                                .map(arr -> new Range[]{ new Range(arr[0]), new Range(arr[1]) })
                                .filter(ranges -> ranges[0].overlapsWith(ranges[1]))
                                .count();

            assertThat(PART_TWO).isEqualTo(PART_TWO_SOLUTION);
        }
    }
}