package org.example.trocaMensagem;

import java.io.*;
import java.net.*;
public class Cliente
{ public static void main(String[] arg)
  { double numero;
    int c, total = (int)(10*Math.random());
    Socket s = null;
    try
    { System.out.println("Conectando...");
      s = new Socket("127.0.0.1",6789);
      DataInputStream in = new DataInputStream(s.getInputStream());
      DataOutputStream out = new DataOutputStream(s.getOutputStream());
      System.out.println("Conectado. Enviando "+total+" numeros...");
      for(c=0; c<total; c++)
      { 
        numero = 100.0*Math.random();
        System.out.println("Enviando "+numero);
        out.writeDouble(numero);
      }
      out.flush();
      out.writeDouble(-1.0);
      System.out.println("Somatorio = "+in.readDouble());
      }
      catch(Exception e)
      { System.out.println("Erro: "+e.getMessage()); }
      finally
      { try{if(s!=null) s.close();}catch(Exception e2){}    }
      System.out.println("Conexao encerrada");
      try{System.in.read();}catch(Exception e3){}
  }
}
