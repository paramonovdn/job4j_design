package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        List<String> allLineServerLogFile = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("data/server.log"))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                allLineServerLogFile.add(line);
            }
        }

        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            for (String l : allLineServerLogFile) {
                out.println(l);
            }
        }

        File target  = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("beginning;end10:57:01;10:59:01;11:01:02;11:02:02;").isEqualTo(rsl.toString());
    }
}