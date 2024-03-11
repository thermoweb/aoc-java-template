package org.thermoweb.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayRunner {

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
            System.out.println("part 1: " + result.map(BigInteger::toString).orElse("<None>") + " (" + (end - start) / 1000000 + " ms)");
        } catch (Exception e) {
            System.out.println("exception occured in part 1!");
        }
    }

    private static void runPartTwo(Day dayToRun, String input) {
        try {
            long start = System.nanoTime();
            Optional<BigInteger> result = dayToRun.partTwo(input);
            long end = System.nanoTime();
            System.out.println("part 2: " + result.map(BigInteger::toString).orElse("<None>") + " (" + (end - start) / 1000000 + " ms)");
        } catch (Exception e) {
            System.out.println("exception occured in part 1!");
        }
    }
}
