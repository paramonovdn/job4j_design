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
        Scanner in = new Scanner(System.in);
        String msg = null;

        while (!Objects.equals(msg, OUT)) {
            msg = in.nextLine();
            if (msg.length()<1) {
                continue;
            }
            String botMsg = readPhrases().get((int)(Math.random() * readPhrases().size()));

            if (msg.equals(OUT)) {
                msgForLog.add("user:-" + msg);
                saveLog(msgForLog);
                msgForLog = new ArrayList<>();
                continue;
            }
            if (msg.equals(STOP)) {
                while (!msg.equals(CONTINUE)) {
                    msg =in.nextLine();
                    if (msg.equals(CONTINUE)) {
                        break;
                    }
                    msgForLog.add("user:-" + msg);
                    saveLog(msgForLog);
                    msgForLog = new ArrayList<>();
                }
            }
            System.out.print(botMsg);
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
            br.lines().map(s -> s + System.lineSeparator()).forEach(list::add);
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
        ConsoleChat cc = new ConsoleChat( "C:\\projects\\job4j_design\\data\\ConsoleChatLogs.txt",
                "C:\\projects\\job4j_design\\data\\ConsoleChat.txt");
        cc.run();
    }
}