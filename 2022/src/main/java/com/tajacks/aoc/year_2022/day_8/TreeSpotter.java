package com.tajacks.aoc.year_2022.day_8;

import java.util.TreeSet;
import java.util.function.Function;

public enum TreeSpotter {
    INSTANCE;

    private final static Function<Integer, Integer> ADD = i -> i + 1;
    private final static Function<Integer, Integer> SUB = i -> i - 1;

    public static int spotTrees(String puzzleInput) {
        String[] rowsStr = puzzleInput.split("\\r?\\n");
        int[][] rows = generateGrid(rowsStr);
        int visibleTrees = 0;
        // Foreach row
        for (int rowIndicator = 0; rowIndicator < rows.length; rowIndicator++) {
            int[] row = rows[rowIndicator];
            for (int columnIndicator = 0; columnIndicator < row.length; columnIndicator++) {
                int tree = row[columnIndicator];
                // If first or last row
                if (rowIndicator == 0 || rowIndicator == rows.length - 1) {
                    visibleTrees += 1;
                    continue;
                }
                // If first or last in the column
                if (columnIndicator == 0 || columnIndicator == row.length - 1) {
                    visibleTrees += 1;
                    continue;
                }

                boolean isLeftVisible = isVisible(tree, rowIndicator, columnIndicator - 1, rows, Function.identity(), SUB);
                boolean isRightVisible = isVisible(tree, rowIndicator, columnIndicator + 1, rows, Function.identity(), ADD);
                boolean isUpwardsVisible = isVisible(tree, rowIndicator - 1, columnIndicator, rows, SUB, Function.identity());
                boolean isDownwardsVisible = isVisible(tree, rowIndicator + 1, columnIndicator, rows, ADD, Function.identity());

                if (isLeftVisible || isRightVisible || isUpwardsVisible || isDownwardsVisible) {
                    visibleTrees += 1;
                }
            }
        }
        return visibleTrees;
    }

    public static int getHighestScenicScore(String puzzleInput) {
        String[] rowsStr = puzzleInput.split("\\r?\\n");
        int[][] rows = generateGrid(rowsStr);
        TreeSet<Integer> scenicScores = new TreeSet<>();
        for (int rowIndicator = 0; rowIndicator < rows.length; rowIndicator++) {
            int[] row = rows[rowIndicator];
            for (int columnIndicator = 0; columnIndicator < row.length; columnIndicator++) {
                int tree = row[columnIndicator];
                scenicScores.add(scenicScore(tree, rowIndicator, columnIndicator, rows));
            }
        }
        return scenicScores.last();
    }

    private static boolean isVisible(int height, int row, int col, int[][] grid, Function<Integer, Integer> rowMapper, Function<Integer, Integer> colMapper) {
        // If reached edge of grid
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length) {
            return true;
        }
        int toCompare = grid[row][col];
        return toCompare < height && isVisible(height, rowMapper.apply(row), colMapper.apply(col), grid, rowMapper, colMapper);
    }

    private static int scenicScore(int height, int row, int col, int[][] grid) {
        if (row == 0 || col == 0 || row == grid.length - 1 || col == grid[row].length - 1) {
            return 0;
        }
        return scenicScore_(1, height, row, col - 1, grid, Function.identity(), SUB)
               * scenicScore_(1, height, row, col + 1, grid, Function.identity(), ADD)
               * scenicScore_(1, height, row - 1, col, grid, SUB, Function.identity())
               * scenicScore_(1, height, row + 1, col, grid, ADD, Function.identity());
    }

    private static int scenicScore_(int counter, int height, int row, int col, int[][] grid, Function<Integer, Integer> rowMapper, Function<Integer, Integer> colMapper) {
        // If reached edge of grid
        if (row <= 0 || col <= 0 || row >= grid.length - 1 || col >= grid[row].length - 1) {
            return counter;
        }

        int toCompare = grid[row][col];
        return toCompare < height ? scenicScore_(counter + 1, height, rowMapper.apply(row), colMapper.apply(col), grid, rowMapper, colMapper) : counter;
    }

    private static int[][] generateGrid(String[] rows) {
        int[][] results = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            results[i] = rows[i].chars()
                                .mapToObj(c -> String.valueOf((char) c))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }
        return results;
    }
}
