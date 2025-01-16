package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9000);
        while (!server.isClosed()) {
            Socket socket = server.accept();
            try (OutputStream output = socket.getOutputStream();
                 BufferedReader input = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {
                String queryParams = input.readLine().split(" ")[1];
                String serverAnswer = "What";
                if ("/?msg=Hello".equals(queryParams)) {
                    serverAnswer = "Hello";
                } else if ("/?msg=Exit".equals(queryParams)) {
                    server.close();
                    continue;
                }
                output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                output.write(serverAnswer.getBytes());
                output.flush();
            }
        }
    }
}
