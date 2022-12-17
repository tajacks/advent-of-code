package com.tajacks.aoc.year_2022.day_8;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.truth.Truth.assertThat;

public class TreeSpotterTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_8.txt";
    private final int PART_ONE_SOLUTION = 1733;
    private final int PART_TWO_SOLUTION = 284648;


    @Test
    public void canPassDayEight() throws IOException {
        String input = Files.readString(Path.of(RESOURCE_PATH));
        int PART_ONE = TreeSpotter.spotTrees(input);
        assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);
        int PART_TWO = TreeSpotter.getHighestScenicScore(input);
        assertThat(PART_TWO).isEqualTo(PART_TWO_SOLUTION);
    }

}