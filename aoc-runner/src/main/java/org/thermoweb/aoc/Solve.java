package org.thermoweb.aoc;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class Solve implements AocRunner {

    private static final Logger logger = LoggerFactory.getLogger(Solve.class);
    private final int day;
    public Solve(int day) {
        this.day = day;
    }

    public static SolveExecutorBuilder builder() {
        return new SolveExecutorBuilder();
    }

    @Override
    public void execute() throws RunnerException {
        logger.info("solving day {}", day);
        Reflections reflections = new Reflections("org.thermoweb.aoc");
        Set<Class<?>> daySolvers = reflections.get(SubTypes.of(TypesAnnotated.with(DaySolver.class)).asClass());
        Class<?> daySolver = daySolvers.stream()
                .filter(ds -> ds.getAnnotation(DaySolver.class).value() == day && Arrays.stream(ds.getInterfaces()).anyMatch(i -> i == Day.class))
                .findFirst()
                .orElseThrow();
        Constructor<?> constructor = Arrays.stream(daySolver.getDeclaredConstructors())
                .filter(c -> c.getParameterCount() == 0)
                .findFirst()
                .orElseThrow();
        try {
            Day dayRunner = (Day) constructor.newInstance();
            DayRunner.runDay(dayRunner, day);
        } catch (IOException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RunnerException(e);
        }
    }

    public static class SolveExecutorBuilder {
        private Integer day;

        public SolveExecutorBuilder withDay(int day) {
            this.day = day;
            return this;
        }

        public Solve build() {
            return new Solve(day);
        }

        public void buildAndExecute() throws RunnerException {
            build().execute();
        }
    }
}
