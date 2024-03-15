package org.thermoweb.aoc.commands;

import org.thermoweb.aoc.AOC;
import org.thermoweb.aoc.RunnerException;
import org.thermoweb.aoc.Scaffold;

import picocli.CommandLine;

@CommandLine.Command(name = "scaffold", description = "create class and tests for the day.")
public class ScaffoldCommand implements Runnable {

    @CommandLine.ParentCommand
    private AOC aoc;

    @Override
    public void run() {
        try {
            Scaffold.builder()
                    .withDay(aoc.getDay())
                    .buildAndExecute();
        } catch (RunnerException e) {
            throw new CommandException(e);
        }
    }
}
