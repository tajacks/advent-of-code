package com.tajacks.aoc.year_2022.day_10;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

public class SignalStrengthListenerTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_10.txt";

    private final String TEST_DATA = "noop\n" +
                                     "noop\n" +
                                     "addx 5\n" +
                                     "addx 21\n" +
                                     "addx -16\n" +
                                     "noop\n" +
                                     "addx 1\n" +
                                     "noop\n" +
                                     "noop\n" +
                                     "addx 4\n" +
                                     "addx 1\n" +
                                     "addx 4\n" +
                                     "addx 1";

    private final int PART_ONE_SOLUTION = 17020;

    @Test
    public void canPassDayTen() throws IOException {
        String input = Files.readString(Path.of(RESOURCE_PATH));
        int PART_ONE = SignalStrengthListener.sumSignalStrengths(input, 20, 60, 100, 140, 180, 220);
        assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);
        SignalStrengthListener.drawMessage(input);
    }


    @Test
    public void canGetCycles_PartwayThrough() {
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 1)).isEqualTo(1);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 2)).isEqualTo(2);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 3)).isEqualTo(3);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 4)).isEqualTo(4);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 5)).isEqualTo(30);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 6)).isEqualTo(36);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 7)).isEqualTo(189);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 8)).isEqualTo(216);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 9)).isEqualTo(99);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 10)).isEqualTo(110);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 11)).isEqualTo(121);
        assertThat(SignalStrengthListener.sumSignalStrengths(TEST_DATA, 20)).isEqualTo(420);
    }
}