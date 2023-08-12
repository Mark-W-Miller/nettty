package com.moondance.nettty.scripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetttyYamlFileWriter {
    public static final String NETTY_SCRIPT_DIR = "./scripts";

    static public void writeNetttyFile(String name, StringBuilder stringBuilder) throws IOException {
        final Path scriptDir = Files.createDirectories(Paths.get(NETTY_SCRIPT_DIR));
        Files.write(scriptDir.resolve(name + ".yaml"), stringBuilder.toString().getBytes());
    }

    public static List<String> getFile(String fileName) {

        List<String> result = new ArrayList<String>();
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().startsWith("#"))
                    result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
