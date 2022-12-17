package com.tajacks.aoc.year_2022.day_6;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.truth.Truth.assertThat;

public class ElfCommunicationDecoderTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_6.txt";
    private final int PART_ONE_SOLUTION = 1892;
    private final int PART_TWO_SOLUTION = 2313;

    @Test
    public void canPassDaySix() throws IOException {
        String input = Files.readString(Path.of(RESOURCE_PATH));
        int PART_ONE = ElfCommunicationDecoder.findStartOfPacket(input, 4);
        assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);
        int PART_TWO = ElfCommunicationDecoder.findStartOfPacket(input, 14);
        assertThat(PART_TWO).isEqualTo(PART_TWO_SOLUTION);
    }
}