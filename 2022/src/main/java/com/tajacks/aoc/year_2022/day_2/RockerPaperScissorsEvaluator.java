package com.tajacks.aoc.year_2022.day_2;

import java.util.Arrays;
import java.util.function.Predicate;

public enum RockerPaperScissorsEvaluator {
    INSTANCE;

    public static int evaluateGameOutcome(String game) {
        String opponent = game.split(" ")[0];
        String myself = game.split(" ")[1];
        return MyChoice.valueOf(myself).points(opponent);
    }

    public static int evaluateGameOutcomePartTwo(String game) {
        String opponent = game.split(" ")[0];
        String requiredOutcome = game.split(" ")[1];
        MyChoice choice = MyChoice.valueOf(requiredOutcome);
        return choice.requiredPlayToMeetOutcome(opponent).points(opponent);
    }

    private enum DesiredOutcome {
        WIN,
        DRAW,
        LOSE
    }

    private enum MyChoice {
        X(1, "A", "B", "C", DesiredOutcome.LOSE),
        Y(2, "B", "C", "A", DesiredOutcome.DRAW),
        Z(3, "C", "A", "B", DesiredOutcome.WIN);

        private final int val;
        private final String equivalent;
        private final String losesTo;
        private final String beats;
        private final DesiredOutcome desiredOutcome;

        MyChoice(int val, String equivalent, String losesTo, String beats, DesiredOutcome desiredOutcome) {
            this.val = val;
            this.equivalent = equivalent;
            this.losesTo = losesTo;
            this.beats = beats;
            this.desiredOutcome = desiredOutcome;
        }

        public int points(String against) {
            return this.val + winPoints(against);
        }

        private int winPoints(String against) {
            return losesTo.equals(against) ? 0 : equivalent.equals(against) ? 3 : 6;
        }

        public MyChoice requiredPlayToMeetOutcome(String against) {
            Predicate<MyChoice> matchPredicate = this.desiredOutcome.equals(DesiredOutcome.WIN)
                                                 ? c -> c.beats.equals(against)
                                                 : this.desiredOutcome.equals(DesiredOutcome.DRAW)
                                                   ? c -> c.equivalent.equals(against)
                                                   : c -> c.losesTo.equals(against);

            return Arrays.stream(values()).filter(matchPredicate).findAny().orElseThrow();
        }
    }
}
