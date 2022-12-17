package com.tajacks.aoc.year_2022.day_5;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.truth.Truth.assertThat;

public class CrateArrangementDecoderTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_5.txt";
    private final String PART_ONE_SOLUTION = "TLNGFGMFN";
    private final String PART_TWO_SOLUTION = "FGLQJCMBD";

    @Test
    public void canPassDay5() throws IOException {
        String input = Files.readString(Path.of(RESOURCE_PATH));
        var columns = CrateArrangementDecoder.parseDiagram(input);
        var instructions = CrateArrangementDecoder.parseInstructions(input);
        String PART_ONE = CrateArrangementDecoder.executeInstructions(instructions, columns);
        assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);

        String PART_TWO = CrateArrangementDecoder.executeInstructionsPartTwo(instructions, CrateArrangementDecoder.parseDiagram(input));
        assertThat(PART_TWO).isEqualTo(PART_TWO_SOLUTION);
    }
}