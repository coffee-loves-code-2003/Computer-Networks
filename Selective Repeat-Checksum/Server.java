import java.net.*;
import java.io.*;
import java.util.*;
public class Server
{
  private Socket     socket  = null;
  private ServerSocket  server  = null;
  private DataInputStream in    = null;
  int window_size=5;
  static int packet_size=20;
  int base=0;
  int sequence_number=0;
  int acknowledged_number=0;
  static boolean acknowledged[]=new boolean[packet_size];
  int count1=0;
  public Server(int port)
  {
    try
    {
      server = new ServerSocket(port);
      System.out.println("Server started");
 
      System.out.println("Waiting for a client ...");
 
      socket = server.accept();
      System.out.println("Client accepted");
 
       
       

      while (true)
      {
         
          String line = "";
           
        try
        {
          socket.setSoTimeout(10000);
          in = new DataInputStream(
        new BufferedInputStream(socket.getInputStream()));
          sequence_number=in.readInt();

  DataOutputStream out = new DataOutputStream(socket.getOutputStream());

 if(acknowledged[sequence_number] == false){
  out.writeInt(sequence_number);

  System.out.print("Acknowledged packet\n");
  out.flush();

}
 else {
  System.out.print("Packet already acknowledged\n");
}

           
 
        }
        catch(Exception i)
        {
          System.out.print("Acknowledgement Missed:\n");
          DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          out.writeInt(sequence_number+1);
  
  out.flush();
        }
        ++sequence_number;

         
      
      }
 
    }
    catch(IOException i)
    {
      System.out.println(i);
    }
  }
 
  public static void main(String args[])
  {
    for(int i=0;i<packet_size;i++)
    {
      acknowledged[i]=false;
    }
    Server server = new Server(5000);
  }
}