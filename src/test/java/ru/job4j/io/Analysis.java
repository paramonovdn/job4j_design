package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> allTimeLine = new ArrayList<>();
        String fileFormat = "beginning;end";

        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String timeLine = null;
                if (line.contains("400") || line.contains("500")) {
                    int startTimePosition = line.indexOf(" ");
                    timeLine = line.substring(startTimePosition + 1, line.length()) + ";";
                    while (line.contains("400") || line.contains("500")) {
                        line = read.readLine();
                    }
                    timeLine += line.substring(startTimePosition + 1, line.length()) + ";";
                } else {
                    continue;
                }
                allTimeLine.add(timeLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(target, true))) {
            out.println(fileFormat);
            for (String l : allTimeLine) {
                out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}