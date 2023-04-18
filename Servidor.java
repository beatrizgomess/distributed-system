import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Servidor iniciado na porta 5000");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostName());

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensagem recebida do cliente: " + inputLine);
            System.out.print("> ");
            String responseLine = stdin.readLine();
            out.println(responseLine);
        }

        clientSocket.close();
        serverSocket.close();
    }
}

