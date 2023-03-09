package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                boolean flag = false;
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("msg=Bye")) {
                            flag = true;
                        }
                        System.out.println(str);
                    }
                    if (!flag) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.flush();
                    } else {
                        out.write("HTTP/1.1 499 Client Closed Request\r\n\r\n".getBytes());
                        out.flush();
                        server.close();
                    }
                }
            }
        }
    }
}