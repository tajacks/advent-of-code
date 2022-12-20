package com.tajacks.aoc.year_2022.day_11;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

class MonkeyParserTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_11.txt";
    private final int PART_ONE_SOLUTION = 90294;
    private final long PART_TWO_SOLUTION = 18170818354L;

    @Test
    void canPassDayEleven() throws Exception {
        String input = Files.readString(Path.of(RESOURCE_PATH));
        long PART_ONE = MonkeyParser.getMonkeyBusiness(input, 20, true);
        assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);
        long PART_TWO = MonkeyParser.getMonkeyBusiness(input, 10000, false);
        assertThat(PART_TWO).isEqualTo(PART_TWO_SOLUTION);
    }


    @Test
    void canSplitOnEmptyLine() throws Exception {
        String input = Files.readString(Path.of(RESOURCE_PATH));
        assertThat(MonkeyParser.splitOnEmptyLine(input)).hasLength(8);
    }

    private final String MONKEY = "Monkey 0:\n" +
                                  "  Starting items: 66, 59, 64, 51\n" +
                                  "  Operation: new = old * 3\n" +
                                  "  Test: divisible by 2\n" +
                                  "    If true: throw to monkey 1\n" +
                                  "    If false: throw to monkey 4";

    @Test
    void canParseMonkey() {
        Monkey monk = MonkeyParser.parseMonkey(MONKEY);
        assertThat(monk.getNumber()).isEqualTo(0);
        assertThat(monk.getItems()).containsExactly(66, 59, 64, 51);
        assertThat(monk.getOperation().apply(3L)).isEqualTo(9);
        assertThat(monk.getTest().apply(3L)).isFalse();
        assertThat(monk.getTest().apply(4L)).isTrue();
        assertThat(monk.getIfTrue()).isEqualTo(1);
        assertThat(monk.getIfFalse()).isEqualTo(4);

    }

}