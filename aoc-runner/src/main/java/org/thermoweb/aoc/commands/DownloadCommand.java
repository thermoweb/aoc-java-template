package org.thermoweb.aoc.commands;

import org.thermoweb.aoc.Download;
import org.thermoweb.aoc.RunnerException;

import picocli.CommandLine;

@CommandLine.Command(name = "download")
public class DownloadCommand implements Runnable {

    @CommandLine.Option(names = "--day")
    private int day;

    @CommandLine.Option(names = "--year", defaultValue = "2023")
    private int year;

    @CommandLine.Option(names = "--token")
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
