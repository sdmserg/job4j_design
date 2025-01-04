package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.io.*;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalysisTest {

    @Test
    void whenTwodUnavailableIntervalThenTwoEntriesInResultLog(
            @TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.cvs").toFile();
        analysis.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(
                new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat(result.toString()).isEqualTo("10:57:01;10:59:01;11:01:02;11:02:02;");
    }

    @Test
    void whenOneUnavailableIntervalThenOneEntryInResultLog(
            @TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.cvs").toFile();
        analysis.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(
                new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat(result.toString()).isEqualTo("10:57:01;11:02:02;");
    }

    @Test
    void whenUnavailableIntervalWithoutEndTimeThenEntryWithStartTimeInLog(
            @TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("300 11:02:02");
            output.println("400 11:03:02");
            output.println("400 11:05:02");
        }
        File target = tempDir.resolve("target.cvs").toFile();
        analysis.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(
                new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat(result.toString()).isEqualTo("10:57:01;11:02:02;11:03:02;");
    }
}
