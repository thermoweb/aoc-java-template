package org.thermoweb.aoc.plugin;

import java.time.YearMonth;
import java.util.Optional;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.thermoweb.aoc.Download;
import org.thermoweb.aoc.RunnerException;

@Mojo(name = "download")
public class DownloadMojo extends AbstractMojo {

    @Parameter(property = "day", required = true)
    private Integer day;

    @Parameter(property = "year")
    private Integer year;

    @Parameter(property = "token")
    private String token;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Integer yearToDownload = Optional.ofNullable(year).orElseGet(() -> YearMonth.now().plusMonths(1).getYear() - 1);
        getLog().info("download day " + day + " of year " + yearToDownload);
        try {
            Download.builder()
                    .withDay(day)
                    .withYear(yearToDownload)
                    .withToken(token)
                    .buildAndExecute();
        } catch (RunnerException e) {
            throw new MojoExecutionException(e);
        }
    }
}
