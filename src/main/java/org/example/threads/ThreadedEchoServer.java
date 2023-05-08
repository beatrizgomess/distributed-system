	/**
 * @version 1.10 1997-06-27
 * @author Cay Horstmann
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList; 


public class ThreadedEchoServer{    

	static ArrayList<Socket> list = new ArrayList<Socket>();
	public static void main(String[] args ){  
	int i = 1;
      	try{  
      	
      		ServerSocket s = new ServerSocket(8189);

         	for (;;){  
         	Socket incoming = s.accept();
            	System.out.println("Spawning " + i);
            	list.add(incoming);
            
            new ThreadedEchoHandler(incoming, i,list).start();
            i++;
         }
      }
      catch (Exception e)
      {  System.out.println(e);
      }
   }
}


class ThreadedEchoHandler extends Thread{
	  private Socket incoming;
	  private int counter;
	  private ArrayList<Socket> list;
	   
	public ThreadedEchoHandler(Socket i, int c ,ArrayList<Socket> l){ 
	incoming = i; counter = c; list = l;
			
   }

   public void run()
   {  try
      {  BufferedReader in = new BufferedReader
            (new InputStreamReader(incoming.getInputStream()));
         PrintWriter out = new PrintWriter
            (incoming.getOutputStream(), true /* autoFlush */);

         out.println( "Hello! Enter BYE to exit." );

         boolean done = false;
         while (!done)
         {  String str = in.readLine();
             System.out.println (" - " +str);
             for(int j = 0; j<list.size(); j++){
            	 Socket clientes = list.get(j);
                 PrintWriter outTodos = new PrintWriter(clientes.getOutputStream(), true /* autoFlush */);
                 	outTodos.println("Cliente (" + counter + "): " + str);
                 }
             
             
            if (str == null) done = true;
            else
            {  out.println("Sua mensagem (" + counter + "): " + str);

               if (str.trim().equals("BYE"))
                  done = true;
            }
         }
         incoming.close();
      }
      catch (Exception e)
      {  System.out.println(e);
      }
   }

 

}
