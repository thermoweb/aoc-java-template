package org.thermoweb.aoc.commands;

import org.thermoweb.aoc.RunnerException;
import org.thermoweb.aoc.Scaffold;

import picocli.CommandLine;

@CommandLine.Command(name = "scaffold", description = "create class and tests for the day.")
public class ScaffoldCommand implements Runnable {
    @CommandLine.Option(names = "--day", description = "day to scaffold")
    private int day;


    @Override
    public void run() {
        try {
            Scaffold.builder()
                    .withDay(day)
                    .buildAndExecute();
        } catch (RunnerException e) {
            throw new RuntimeException(e);
        }
    }
}
