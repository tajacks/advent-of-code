package com.tajacks.aoc.year_2022.day_3;

import com.tajacks.aoc.year_2022.day_2.RockerPaperScissorsEvaluator;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

public class RuckSackDuplicateItemIdentifierTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_3.txt";
    private final int PART_ONE_SOLUTION = 7428;
    private final int PART_TWO_SOLUTION = 2650;

    @Test
    public void canPassDayThree() throws IOException {
        try (Stream<String> SOURCES = Files.lines(Path.of(RESOURCE_PATH))) {
            List<String> DATA = SOURCES.toList();
            int PART_ONE = DATA.stream().mapToInt(RuckSackDuplicateItemIdentifier::prioritize).sum();
            assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);
            int PART_TWO = IntStream.range(0, (DATA.size() + 2) / 3)
                    .mapToObj(i -> DATA.subList(i * 3, Math.min(DATA.size(), (i + 1) * 3)))
                    .mapToInt(RuckSackDuplicateItemIdentifier::prioritizeChunked)
                    .sum();
            assertThat(PART_TWO).isEqualTo(PART_TWO_SOLUTION);
        }
    }
}