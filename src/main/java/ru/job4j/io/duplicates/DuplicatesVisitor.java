package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    HashMap<FileProperty, List<Path>> fileMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty property = new FileProperty(Files.size(path),
                path.getFileName().toString());
        fileMap.computeIfAbsent(property, key -> new ArrayList<>())
                .add(path.toAbsolutePath());
        return super.visitFile(path, attributes);
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> fileProperty : fileMap.entrySet()) {
            if (fileProperty.getValue().size() > 1) {
                System.out.println(String.format("%s %.4f Kb",
                        fileProperty.getKey().getName(),
                        fileProperty.getKey().getSize() / (1024.0)
                        ));
                for (Path path : fileProperty.getValue()) {
                    System.out.println(String.format("   %s", path.toAbsolutePath()));
                }
            }
        }
    }
}
