package com.tajacks.aoc.year_2022.day_2;


import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

public class RockerPaperScissorsEvaluatorTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_2.txt";
    private final int PART_ONE_SOLUTION = 9651;
    private final int PART_TWO_SOLUTION = 10560;

    @Test
    public void canPassDayTwo() throws IOException {
        try (Stream<String> SOURCES = Files.lines(Path.of(RESOURCE_PATH))) {
            List<String> DATA = SOURCES.toList();
            int PART_ONE = DATA.stream().mapToInt(RockerPaperScissorsEvaluator::evaluateGameOutcome).sum();
            assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);
            int PART_TWO = DATA.stream().mapToInt(RockerPaperScissorsEvaluator::evaluateGameOutcomePartTwo).sum();
            assertThat(PART_TWO).isEqualTo(PART_TWO_SOLUTION);
        }
    }
}