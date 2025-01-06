package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles implements FileVisitor<Path> {

    private final Predicate<Path> condition;

    private final List<Path> result = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return result;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path directory,
                                             BasicFileAttributes attributes) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) throws IOException {
        if (condition.test(path)) {
            result.add(path);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException exception) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException exception) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}