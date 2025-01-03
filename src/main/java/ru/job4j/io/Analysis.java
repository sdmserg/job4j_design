package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analysis {

    public void unavailable(String source, String target) {
        List<String> intervals = parseLog(source);
        writeLog(intervals, target);
    }

    private List<String> parseLog(String source) {
        boolean unavailable = false;
        StringBuilder interval = new StringBuilder();
        List<String> intervals = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(
                new FileReader(source)
        )) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] array = line.split(" ");
                if (("400".equals(array[0]) || "500".equals(array[0])) && !unavailable) {
                    unavailable = true;
                    interval.append(array[1]).append(";");
                } else if (("200".equals(array[0]) || "300".equals(array[0])) && unavailable) {
                    unavailable = false;
                    interval.append(array[1]).append(";");
                    intervals.add(interval.toString());
                    interval.setLength(0);
                }
            }
            if (interval.isEmpty()) {
                intervals.add(interval.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return intervals;
    }

    private void writeLog(List<String> intervals, String target) {
        try (PrintWriter output = new PrintWriter(
                new FileOutputStream(target)
        )) {
            for (String interval : intervals) {
                output.println(interval);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analisys = new Analysis();
        analisys.unavailable("data/server.log", "data/target.cvs");
    }
}
