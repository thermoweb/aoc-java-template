package org.thermoweb.aoc.commands;

import org.thermoweb.aoc.AOC;
import org.thermoweb.aoc.Download;
import org.thermoweb.aoc.RunnerException;

import picocli.CommandLine;

@CommandLine.Command(name = "download", description = "download input file and create an empty example file.")
public class DownloadCommand implements Runnable {

    @CommandLine.ParentCommand
    private AOC aoc;

    @CommandLine.Option(names = "--year", defaultValue = "2024", description = "if you want to change the year of aoc")
    private int year;

    @CommandLine.Option(names = "--token", description = "if you do not have a .adventofcode.session in your home folder, you can specify it in the command line.")
    private String session;

    @Override
    public void run() {
        try {
            Download.builder()
                    .withDay(aoc.getDay())
                    .withYear(year)
                    .withToken(session)
                    .buildAndExecute();
        } catch (RunnerException e) {
            throw new CommandException(e);
        }
    }
}
