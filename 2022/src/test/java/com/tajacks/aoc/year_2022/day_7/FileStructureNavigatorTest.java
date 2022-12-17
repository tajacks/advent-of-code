package com.tajacks.aoc.year_2022.day_7;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.truth.Truth.assertThat;

public class FileStructureNavigatorTest {

    private final String RESOURCE_PATH = "2022/src/test/Resources/DAY_7.txt";
    private final int PART_ONE_SOLUTION = 1778099;
    private final int PART_TWO_SOLUTION = 1623571;

    @Test
    public void canPassDaySeven() throws IOException {
        String input = Files.readString(Path.of(RESOURCE_PATH));
        Directory PART_ONE_D = FileStructureNavigator.navigateFilesystem(input);
        long PART_ONE = PART_ONE_D.totalSizeOfDownstreamLargerThan(100000);
        assertThat(PART_ONE).isEqualTo(PART_ONE_SOLUTION);
        long PART_TWO = PART_ONE_D.sizeOfDirectoryLargeEnoughForUpdate(70000000L, 30000000L);
        assertThat(PART_TWO).isEqualTo(PART_TWO_SOLUTION);
    }
}