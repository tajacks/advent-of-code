package com.tajacks.aoc.year_2022.day_11;

import com.tajacks.libraries.common.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Monkey implements Comparable<Monkey> {

    private final Function<Long, Long> operation;
    private final Function<Long, Boolean> test;
    private final List<Long> items;
    private final int number;
    private final int ifTrue;
    private final int ifFalse;
    private final int op;
    private long inspected = 0;

    public Monkey(Function<Long, Long> operation, Function<Long, Boolean> test, List<Long> startingItems, int number, int ifTrue, int ifFalse, int op) {
        this.operation = operation;
        this.test = test;
        this.items = new ArrayList<>(startingItems);
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
        this.number = number;
        this.op = op;
    }

    public void performTurn(List<Monkey> monkeys, boolean shouldDivide, int mod) {
        for (long workingItemWorryLevel : items) {
            // Inspect
            workingItemWorryLevel = operation.apply(workingItemWorryLevel);
            // After each monkey inspects an item but before it tests your worry level,
            // your relief that the monkey's inspection didn't damage the item causes your worry level
            // to be divided by three and rounded down to the nearest integer
            if (shouldDivide) {
                workingItemWorryLevel = workingItemWorryLevel / 3;
            }
            // I'll be honest I had to look this up
            workingItemWorryLevel = workingItemWorryLevel % mod;
            int throwTo = test.apply(workingItemWorryLevel) ? ifTrue : ifFalse;
            long finalWorkingItemWorryLevel = workingItemWorryLevel;
            // Probably should use a map
            monkeys.stream().filter(monkey -> monkey.number == throwTo).findAny().ifPresent(m -> m.catchItem(finalWorkingItemWorryLevel));
            this.inspected += 1;
        }
        this.items.clear();;
    }

    public void printStatus() {
        System.out.println("Monkey " + number + " inspected items " + inspected + " times");
    }

    public void catchItem (long item) {
        this.items.add(item);
    }

    public Function<Long, Long> getOperation() {
        return operation;
    }

    public Function<Long, Boolean> getTest() {
        return test;
    }

    public List<Long> getItems() {
        return items;
    }

    public int getNumber() {
        return number;
    }

    public int getIfTrue() {
        return ifTrue;
    }

    public int getIfFalse() {
        return ifFalse;
    }

    public long getInspected() {
        return inspected;
    }

    public int getOp () {
        return op;
    }

    @Override
    public String toString() {
        return "Monkey{" +
               "operation=" + operation +
               ", test=" + test +
               ", items=" + items +
               ", number=" + number +
               ", ifTrue=" + ifTrue +
               ", ifFalse=" + ifFalse +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Monkey monkey = (Monkey) o;
        return getNumber() == monkey.getNumber() && getIfTrue() == monkey.getIfTrue() && getIfFalse() == monkey.getIfFalse() && getOperation().equals(monkey.getOperation()) && getTest().equals(monkey.getTest()) && getItems().equals(monkey.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperation(), getTest(), getItems(), getNumber(), getIfTrue(), getIfFalse());
    }

    @Override
    public int compareTo(Monkey o) {
        return Long.compare(o.inspected, inspected);
    }
}
