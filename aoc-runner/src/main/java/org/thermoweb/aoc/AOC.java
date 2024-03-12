package org.thermoweb.aoc;

import org.thermoweb.aoc.commands.DownloadCommand;
import org.thermoweb.aoc.commands.ScaffoldCommand;
import org.thermoweb.aoc.commands.SolveDayCommand;

import picocli.CommandLine;

@CommandLine.Command(name = "aoc", description = "run Advent of Code command line tool.", subcommands = {
        SolveDayCommand.class,
        ScaffoldCommand.class,
        DownloadCommand.class,
        CommandLine.HelpCommand.class
})
public class AOC {

    private AOC() {

    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new AOC()).execute(args);
        System.exit(exitCode);
    }
}
