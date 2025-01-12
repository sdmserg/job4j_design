package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            Path rootPath = sources.get(0).getParent();
            for (Path filePath : sources) {
                String relativePath = rootPath.relativize(filePath).toString();
                zip.putNextEntry(new ZipEntry(relativePath));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(filePath.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName zipArgs) {
        String startDirectory = zipArgs.get("d");
        String extensionFile = zipArgs.get("e");
        String extensionArchive = zipArgs.get("o");
        if (startDirectory == null) {
            throw new IllegalArgumentException("Error: This argument '-d' are not specified.");
        }
        if (extensionFile == null) {
            throw new IllegalArgumentException("Error: This argument '-e' are not specified.");
        }
         if (extensionArchive == null) {
             throw new IllegalArgumentException("Error: This argument '-o' are not specified.");
         }
        if (!Files.exists(Path.of(startDirectory))) {
            throw new IllegalArgumentException(String.format(
                    "Error: This directory %s does not exists", startDirectory)
            );
        }
        if (!Files.isDirectory(Path.of(startDirectory))) {
            throw new IllegalArgumentException(String.format(
                    "Error: %s is not directory", startDirectory)
            );
        }
        if (!extensionFile.startsWith(".")) {
            throw new IllegalArgumentException(String.format(
                    "Error: %s is not file extension. "
                    + "Enter file extension such as: .txt, .java, ...", extensionFile)
            );
        }
        if (!extensionArchive.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format(
                    "Error: The extension %s is invalid. The file extension must be .zip", extensionArchive)
            );
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName zipArgs = ArgsName.of(args);
        validateArgs(zipArgs);
        Path root = Path.of(zipArgs.get("d"));
        List<Path> absolutePaths = Search.search(root, path -> !path.toString().endsWith(zipArgs.get("e")));
        zip.packFiles(absolutePaths, new File(zipArgs.get("o")));
    }
}

