package org.thermoweb.aoc.commands;

import java.util.concurrent.Callable;

import org.thermoweb.aoc.Solve;

import picocli.CommandLine;

@CommandLine.Command(name = "solve", description = "run the solution with the input for the specified day.")
public class SolveDayCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-d", "--day"}, description = "day to solve", required = true)
    private Integer day;

    @Override
    public Integer call() throws Exception {
        Solve.builder().withDay(day).buildAndExecute();
        return 0;
    }
}
