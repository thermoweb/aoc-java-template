package org.thermoweb.aoc.commands;

import java.util.concurrent.Callable;

import org.thermoweb.aoc.AOC;
import org.thermoweb.aoc.Solve;

import picocli.CommandLine;

@CommandLine.Command(name = "solve", description = "run the solution with the input for the specified day.")
public class SolveDayCommand implements Callable<Integer> {

    @CommandLine.ParentCommand
    private AOC aoc;

    @Override
    public Integer call() throws Exception {
        Solve.builder().withDay(aoc.getDay()).buildAndExecute();
        return 0;
    }
}
