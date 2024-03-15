package org.thermoweb.aoc;

import org.thermoweb.aoc.commands.DownloadCommand;
import org.thermoweb.aoc.commands.ScaffoldCommand;
import org.thermoweb.aoc.commands.SolveDayCommand;

import picocli.CommandLine;

@CommandLine.Command(name = "aoc", description = "run Advent of Code command line tool.", subcommands = {
        ScaffoldCommand.class,
        DownloadCommand.class,
        SolveDayCommand.class,
        CommandLine.HelpCommand.class
})
public class AOC {

    int day;

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    private AOC() {

    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new AOC()).execute(args);
        System.exit(exitCode);
    }

    @CommandLine.Option(names = {"--day", "-d"}, scope = CommandLine.ScopeType.INHERIT)
    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new CommandLine.ParameterException(spec.commandLine(),
                    String.format("Invalid value '%s' for option '--day': value is not between 1 and 31.", day));
        }
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
