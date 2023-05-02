import java.net.*;

public class ServidorUDP {
    public static void main(String args[]) throws Exception {
        DatagramSocket servidorSocket = new DatagramSocket(9876);
        byte[] receberDados = new byte[1024];
        byte[] enviarDados = new byte[1024];

        while (true) {
            DatagramPacket receberPacote = new DatagramPacket(receberDados, receberDados.length);
            servidorSocket.receive(receberPacote);
            String mensagem = new String(receberPacote.getData());
            InetAddress enderecoIP = receberPacote.getAddress();
            int porta = receberPacote.getPort();
            
            if (mensagem.trim().equals("1")) {
                String hora = new java.util.Date().toString();
                enviarDados = hora.getBytes();
                DatagramPacket enviarPacote = new DatagramPacket(enviarDados, enviarDados.length, enderecoIP, porta);
                servidorSocket.send(enviarPacote);
            }
        }
    }
}
