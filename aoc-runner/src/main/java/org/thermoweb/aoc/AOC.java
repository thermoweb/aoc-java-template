package org.thermoweb.aoc;

import java.util.Arrays;

import org.thermoweb.aoc.commands.DownloadCommand;
import org.thermoweb.aoc.commands.ScaffoldCommand;
import org.thermoweb.aoc.commands.SolveDayCommand;

import picocli.CommandLine;

@CommandLine.Command(name = "aoc", subcommands = {
        SolveDayCommand.class,
        ScaffoldCommand.class,
        DownloadCommand.class
})
public class AOC {

    private AOC() {

    }

    public static void main(String[] args) {
        System.out.println("Launched with : " + Arrays.toString(args));
        int exitCode = new CommandLine(new AOC()).execute(args);
        System.exit(exitCode);
    }
}
