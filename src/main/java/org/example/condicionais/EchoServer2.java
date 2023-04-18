package org.example.condicionais;

import java.io.*;
import java.net.*;
import java.util.*; 
public class EchoServer2 {
    public static void main(String[] args) {
        try {
            	ServerSocket serverSocket = new ServerSocket(8189); // porta utilizada pelo servidor
            	
		
            while (true) {
            
                Socket clientSocket = serverSocket.accept(); // aguarda conexão com o cliente
                System.out.println("Servidor em funcionamento");
                // criação de objetos para leitura e escrita de dados
               // DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                //DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                
               // BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		
		InputStream inStream = clientSocket.getInputStream(); 
		OutputStream outStream = clientSocket.getOutputStream(); 

		Scanner in = new Scanner(inStream); 
		PrintWriter out = new PrintWriter(outStream, true /* autoFlush */); 


  		double soma = 0.0, nota = 0.0;
		int count = 0;
		
                // leitura da nota enviada pelo cliente
               
                nota =Double.parseDouble( in.nextLine());
                
                while (nota != -1){
                	soma += nota;
                	count += 1;
                	System.out.println(count + "° Nota: " + nota);
                	
               		nota = Double.parseDouble(in.nextLine());
		}
		
		//out.writeString(String.format("Média: %d", soma/count));
		out.println("Media: "+ soma/count);
		clientSocket.close();
		System.out.println("Conexão encerrada"); 
			            
            }
             
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
