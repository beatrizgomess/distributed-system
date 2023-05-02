import java.io.*;
import java.net.*;

public class ClienteUDP {
    public static void main(String args[]) throws Exception {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clienteSocket = new DatagramSocket();
        InetAddress enderecoIP = InetAddress.getByName("localhost");
        byte[] enviarDados = new byte[1024];
        byte[] receberDados = new byte[1024];

        while (true) {
            System.out.println("Digite 1 para obter a hora do servidor");
            System.out.println("Digite 2 para obter o espaço livre no HD");
            System.out.println("Digite 3 para obter o sistema operacional em uso: ");
            String mensagem = teclado.readLine();
            enviarDados = mensagem.getBytes();
            DatagramPacket enviarPacote = new DatagramPacket(enviarDados, enviarDados.length, enderecoIP, 9876);
            clienteSocket.send(enviarPacote);
            
            DatagramPacket receberPacote = new DatagramPacket(receberDados, receberDados.length);
            int cont = 0;

            while (cont < 5) {
                try {
                    clienteSocket.receive(receberPacote);
                    String resposta = new String(receberPacote.getData()).trim();
                    System.out.println("Resposta do servidor: " + resposta);
                    break;
                } catch (SocketTimeoutException e) {
                    cont++;
                    if (cont == 5) {
                        System.out.println("Não foi possível obter resposta do servidor após 5 tentativas.");
                    } else {
                        System.out.println("Tentativa " + cont + " de 5.");
                    }
                }
            }
        }
    }
}
