package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                String userCommand = null;
                boolean firstLine = true;
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (firstLine) {
                            List<String> firstRequestLine = List.of(str.split("="));
                            userCommand = firstRequestLine.get(firstRequestLine.size() - 1).replace(" HTTP/1.1", "");
                            firstLine = false;
                        }
                        System.out.println(str);
                    }
                    if ("Hello".equals(userCommand)) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(userCommand.getBytes());
                        out.flush();
                    } else if ("Exit".equals(userCommand)) {
                        out.write("HTTP/1.1 499 Client Closed Request\r\n\r\n".getBytes());
                        out.write(userCommand.getBytes());
                        out.flush();
                        server.close();
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write("What".getBytes());
                        out.flush();
                    }
                }
            }
        }
    }
}