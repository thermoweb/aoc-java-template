package org.thermoweb.aoc;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Download implements AocRunner {

    private static final Logger logger = LoggerFactory.getLogger(Download.class);
    private final int day;
    private final int year;
    private final String token;

    private Download(Integer day, Integer year, String token) {
        this.day = Objects.requireNonNull(day);
        this.year = Objects.requireNonNull(year);
        this.token = token;
    }

    public static DownloadExecutorBuilder builder() {
        return new DownloadExecutorBuilder();
    }

    @Override
    public void execute() throws RunnerException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://adventofcode.com/" + year + "/day/" + day + "/input"))
                .header("Cookie", "session=" + token)
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() / 100 != 2) {
                throw new RunnerException(response.body());
            }
            String input = response.body();
            Files.createDirectories(Paths.get("aoc-solutions/src/main/resources/inputs"));
            String inputFile = "aoc-solutions/src/main/resources/inputs/input_" + (day > 9 ? day : "0" + day) + ".txt";
            Files.write(Path.of(inputFile), input.getBytes(), StandardOpenOption.CREATE);
            logger.atInfo().log("Input file '{}' created", inputFile);

            Files.createDirectories(Paths.get("aoc-solutions/src/main/resources/examples"));
            String exampleFile = "aoc-solutions/src/main/resources/examples/example_" + (day > 9 ? day : "0" + day) + ".txt";
            Files.write(Path.of(exampleFile), "".getBytes(), StandardOpenOption.CREATE);
            logger.atInfo().log("Example file '{}' created", exampleFile);
        } catch (IOException e) {
            throw new RunnerException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RunnerException(e);
        }
    }

    public static class DownloadExecutorBuilder {

        private Integer day;
        private Integer year;
        private String token;

        public Download build() {
            String sessionCookie = Optional.ofNullable(token).orElseGet(() -> getCookieSessionFromFile().orElse(""));
            return new Download(day, year, sessionCookie);
        }

        public void buildAndExecute() throws RunnerException {
            build().execute();
        }

        public DownloadExecutorBuilder withDay(int day) {
            this.day = day;
            return this;
        }

        public DownloadExecutorBuilder withYear(int year) {
            this.year = year;
            return this;
        }

        public DownloadExecutorBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        private Optional<String> getCookieSessionFromFile() {
            Path sessionCookieFile = Path.of(System.getProperty("user.home") + "/.adventofcode.session");
            if (sessionCookieFile.toFile().exists()) {
                try {
                    return Optional.of(Files.readString(sessionCookieFile, Charset.defaultCharset()).replaceAll("[\n\r]+", ""));
                } catch (IOException e) {
                    return Optional.empty();
                }
            }
            return Optional.empty();
        }
    }
}
