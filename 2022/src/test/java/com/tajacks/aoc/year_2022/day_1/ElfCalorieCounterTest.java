package com.tajacks.aoc.year_2022.day_1;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

public class ElfCalorieCounterTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_1.txt";
    private final Long PART_ONE_SOLUTION = 71124L;
    private final Long PART_TWO_SOLUTION = 204639L;

    @Test
    public void canPassDayOne() throws IOException {
        try (Stream<String> SOURCES = Files.lines(Path.of(RESOURCE_PATH))) {
            List<String> SOURCE = SOURCES.toList();
            ElfCalorieCounter calorieCounter = new ElfCalorieCounter(SOURCE);
            assertThat(calorieCounter.largestElfByCalories()).isEqualTo(PART_ONE_SOLUTION);
            assertThat(calorieCounter.largestElvesByCaloriesSummed(3)).isEqualTo(PART_TWO_SOLUTION);
        }
    }
}