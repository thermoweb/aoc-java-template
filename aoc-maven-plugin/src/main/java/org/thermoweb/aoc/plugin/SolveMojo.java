package org.thermoweb.aoc.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.thermoweb.aoc.RunnerException;
import org.thermoweb.aoc.Solve;

@Mojo(name = "solve")
public class SolveMojo extends AbstractMojo {

    @Parameter(property = "day", required = true)
    private Integer day;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            Solve.builder().withDay(day).buildAndExecute();
        } catch (RunnerException e) {
            throw new MojoExecutionException(e);
        }
    }
}
