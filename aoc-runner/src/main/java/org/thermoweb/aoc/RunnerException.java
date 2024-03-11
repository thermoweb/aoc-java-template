package org.thermoweb.aoc;

public class RunnerException extends Exception {
    public RunnerException(Exception e) {
        super(e);
    }

    public RunnerException(String message) {
        super(message);
    }
}
