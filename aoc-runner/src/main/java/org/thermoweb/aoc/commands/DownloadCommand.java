package org.thermoweb.aoc.commands;

import org.thermoweb.aoc.Download;
import org.thermoweb.aoc.RunnerException;

import picocli.CommandLine;

@CommandLine.Command(name = "download", description = "download input file and create an empty example file.")
public class DownloadCommand implements Runnable {

    @CommandLine.Option(names = "--day", description = "day to download")
    private int day;

    @CommandLine.Option(names = "--year", defaultValue = "2023", description = "if you want to change the year of aoc")
    private int year;

    @CommandLine.Option(names = "--token", description = "if you do not have a .adventofcode.session in your home folder, you can specify it in the command line.")
    private String session;

    @Override
    public void run() {
        try {
            Download.builder()
                    .withDay(day)
                    .withYear(year)
                    .withToken(session)
                    .buildAndExecute();
        } catch (RunnerException e) {
            throw new RuntimeException(e);
        }
    }
}
