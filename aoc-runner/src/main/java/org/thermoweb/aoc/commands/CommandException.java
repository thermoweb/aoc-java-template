package org.thermoweb.aoc.commands;

public class CommandException extends RuntimeException {
    public CommandException(Exception e) {
        super(e);
    }
}
