import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String args[]) throws Exception {
        DatagramSocket servidorSocket = new DatagramSocket(9876);
        byte[] receberDados = new byte[1024];
        byte[] enviarDados = new byte[1024];

        while (true) {
            DatagramPacket receberPacote = new DatagramPacket(receberDados, receberDados.length);
            servidorSocket.receive(receberPacote);
            String mensagem = new String(receberPacote.getData()).trim();
            InetAddress enderecoIP = receberPacote.getAddress();
            int porta = receberPacote.getPort();
            String resposta = "";

            if (mensagem.equals("1")) {
                resposta = new java.util.Date().toString();
            } else if (mensagem.equals("2")) {
                File f = new File("/");
                long espacoLivre = f.getFreeSpace() / (1024*1024);
                resposta = "Espaço livre no HD: " + espacoLivre + " MB";
            } else if (mensagem.equals("3")) {
                String os = System.getProperty("os.name");
                resposta = "Sistema operacional em uso: " + os;
            }

            enviarDados = resposta.getBytes();
            DatagramPacket enviarPacote = new DatagramPacket(enviarDados, enviarDados.length, enderecoIP, porta);

            for (int i = 0; i < 5; i++) {
                servidorSocket.send(enviarPacote);
                Thread.sleep(500);
            }
        }
    }
}


