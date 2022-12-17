package com.tajacks.aoc.year_2022.day_4;

public enum OverlappingElfRangeDeterminer {
    INSTANCE;

    public record Range(int lower, int upper) {

        public Range(String range) {
            this(Integer.parseInt(range.split("-")[0]), Integer.parseInt(range.split("-")[1]));
        }

        public boolean containedWithin(Range range) {
            return this.lower >= range.lower && this.upper <= range.upper;
        }

        public boolean overlapsWith(Range range) {
            return !(this.lower > range.upper || this.upper < range.lower);
        }
    }
}
