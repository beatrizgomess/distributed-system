package org.example;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8189); // porta utilizada pelo servidor

            while (true) {
                Socket clientSocket = serverSocket.accept(); // aguarda conexão com o cliente
                System.out.println("Servidor em funcionamento");
                // criação de objetos para leitura e escrita de dados
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // leitura da mensagem enviada pelo cliente
                String message = in.readLine();

                // tratamento da mensagem e envio da resposta
                if (message.equals("1")) {
                    out.println("Hora do sistema: " + new java.util.Date());
                } else if (message.equals("2")) {
                    File file = new File("/");
                    long freeSpace = file.getFreeSpace() / (1024*1024); // espaço livre em megabytes
                    out.println("Espaço livre no HD: " + freeSpace + " MB");
                } else if (message.equals("3")) {
                    int count = 0;
                    for (Thread t : Thread.getAllStackTraces().keySet()) {
                        if (t.getName().startsWith("main")) {
                            count++;
                        }
                    }
                    out.println("Quantidade de aplicações em Java: " + count);
                } else {
                    out.println("Opção inválida!");
                }

                // fechamento de conexão com o cliente
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}