import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Conectado ao servidor");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        String inputLine;
        while (true) {
            System.out.print("> ");
            inputLine = stdin.readLine();
            if (inputLine == null) {
                break;
            }
            out.println(inputLine);
            String responseLine = in.readLine();
            System.out.println("Resposta do servidor: " + responseLine);
        }

        socket.close();
    }
}

