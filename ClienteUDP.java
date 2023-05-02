import java.net.*;
import java.io.*;  

public class ClienteUDP {
    public static void main(String args[]) throws Exception {
 	InputStreamReader ip = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(ip);
        DatagramSocket clienteSocket = new DatagramSocket();
        InetAddress enderecoIP = InetAddress.getByName("localhost");
        byte[] enviarDados = new byte[1024];
        byte[] receberDados = new byte[1024];

        System.out.print("Digite 1 para obter a hora do servidor: ");
        String mensagem = teclado.readLine();
        enviarDados = mensagem.getBytes();
        DatagramPacket enviarPacote = new DatagramPacket(enviarDados, enviarDados.length, enderecoIP, 9876);
        clienteSocket.send(enviarPacote);
        
        DatagramPacket receberPacote = new DatagramPacket(receberDados, receberDados.length);
        clienteSocket.receive(receberPacote);
        String hora = new String(receberPacote.getData());
        System.out.println("Hora do servidor: " + hora.trim());
        
        clienteSocket.close();
    }
}
