package org.thermoweb.aoc.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.thermoweb.aoc.RunnerException;
import org.thermoweb.aoc.Scaffold;

@Mojo(name = "scaffold")
public class ScaffoldMojo extends AbstractMojo {

    @Parameter(property = "day", required = true)
    private Integer day;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("scaffolding day " + day);
        try {
            Scaffold.builder().withDay(day).buildAndExecute();
        } catch (RunnerException e) {
            throw new MojoExecutionException(e);
        }
    }
}
