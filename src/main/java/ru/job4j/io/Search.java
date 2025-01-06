package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        validateParams(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateParams(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Start folder and/or file extension are not specified."
            );
        }
        if (!Files.exists(Path.of(args[0]))) {
            throw new IllegalArgumentException(String.format(
                    "Start folder %s does not exist.", args[0])
            );
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException(String.format(
                    "%s is not directory", args[0])
            );
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format(
                    "%s is not file extension. "
                    + "Enter file extension such as: .txt, .java, ...", args[1])
            );
        }
    }
}
