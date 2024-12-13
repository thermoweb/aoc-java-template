package org.thermoweb.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayRunner {

    private static final Logger logger = LoggerFactory.getLogger(DayRunner.class);

    private DayRunner() {

    }

    public static void runDay(Day dayToRun, int day) throws IOException {
        String input = getInput(day);
        runPartOne(dayToRun, input);
        runPartTwo(dayToRun, input);
    }

    public static String getInput(int day) throws IOException {
        return getResourceContent("inputs/input_" + (day > 9 ? day : "0" + day) + ".txt");
    }

    public static String getExample(int day) throws IOException {
        return getResourceContent("examples/example_" + (day > 9 ? day : "0" + day) + ".txt");
    }

    public static String getResourceContent(String resourceName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(classloader.getResourceAsStream(resourceName)));
             BufferedReader reader = new BufferedReader(isr)) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private static void runPartOne(Day dayToRun, String input) {
        try {
            long start = System.nanoTime();
            Optional<BigInteger> result = dayToRun.partOne(input);
            long end = System.nanoTime();
            logger.atInfo().log("part 1: {} ({}) ms", result.map(BigInteger::toString).orElse("<None>"), (end - start) / 1000000);
        } catch (Exception e) {
            logger.atError().log("exception occurred in part 1!");
        }
    }

    private static void runPartTwo(Day dayToRun, String input) {
        try {
            long start = System.nanoTime();
            Optional<BigInteger> result = dayToRun.partTwo(input);
            long end = System.nanoTime();
            logger.atInfo().log("part 2: {} ({}) ms", result.map(BigInteger::toString).orElse("<None>"), (end - start) / 1000000);
        } catch (Exception e) {
            logger.atError().log("exception occurred in part 2!");
        }
    }
}
