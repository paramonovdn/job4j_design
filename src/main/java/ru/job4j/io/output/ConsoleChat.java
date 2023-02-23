package ru.job4j.io.output;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> msgForLog = new ArrayList<>();
        List<String> botAnswers = readPhrases();
        Scanner in = new Scanner(System.in);
        String msg = null;

        while (!OUT.equals(msg)) {
            msg = in.nextLine();
            if (msg.length() < 1) {
                continue;
            }
            String botMsg = botAnswers.get((int) (Math.random() * botAnswers.size()));

            if (OUT.equals(msg)) {
                msgForLog.add("user:-" + msg);
                saveLog(msgForLog);
                msgForLog = new ArrayList<>();
                continue;
            }
            if (STOP.equals(msg)) {
                msgForLog.add("user:-" + msg);
                while (!CONTINUE.equals(msg)) {
                    msg = in.nextLine();
                    if (CONTINUE.equals(msg)) {
                        break;
                    }
                    if (OUT.equals(msg)) {
                        msgForLog.add("user:-" + msg);
                        saveLog(msgForLog);
                        System.exit(0);
                    }
                    msgForLog.add("user:-" + msg);
                    saveLog(msgForLog);
                    msgForLog = new ArrayList<>();
                }
            }
            System.out.println(botMsg);
            msgForLog.add("user:-" + msg);
            msgForLog.add("bot:-" + botMsg);
            saveLog(msgForLog);
            msgForLog = new ArrayList<>();
        }
        in.close();
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\data\\ConsoleChatLogs.txt",
                "C:\\projects\\job4j_design\\data\\ConsoleChat.txt");
        cc.run();
    }
}