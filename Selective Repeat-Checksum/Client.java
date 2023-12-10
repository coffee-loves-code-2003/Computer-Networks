import java.io.*;
import java.net.*;
import java.util.*;
public class Client {
    private Socket socket =         null;
    private DataOutputStream out =  null;
    private Scanner input=null;
    private DataInputStream in=null;
    int window_size=5;
    static int packet_size=20;
    int base=0;
    int sequence_number=1;
    int acknowledged_number=0;
    static boolean acknowledged[]=new boolean[packet_size];
    int not_acknowledged[]=new int[packet_size];
    int index=0;
    int index1=0;
    int count=0;
    public Client(String address, int port)
    {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
 
 
            
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }
 
        
        int j=0;
        while(base<packet_size)
        {

        
        for(int j1=base;j1<base+window_size && j1<packet_size;j1++) {
                    String line = "";
                    input=new Scanner(System.in);

            try {
                out = new DataOutputStream(
                socket.getOutputStream());
                  out.writeInt(sequence_number);
                line = input.nextLine();
                out = new DataOutputStream(
                socket.getOutputStream());
                out.writeUTF(line);
                in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
                acknowledged_number=in.readInt();
                System.out.print(sequence_number+"\n");
                System.out.print(acknowledged_number+"\n");

                if(acknowledged_number==sequence_number+1)
                {
                    System.out.print("Packet Sented and Received by the server unsuccessfully:\n");
                    acknowledged[sequence_number]=false;
                    not_acknowledged[index]=sequence_number;
                    ++index;
                }
                else
                {

                    System.out.print("Packet Sented and Received by the server successfully:\n");

                    acknowledged[sequence_number]=true;

                }


            }
            catch (IOException i) {
                System.out.println(i);
            }
            ++sequence_number;
        }

            while (base < packet_size ) {
                if(count==window_size)
                {
                    System.out.print(count);

                    count=0;
                    break;
                }
                if(acknowledged[base])
                {
                base++;
                ++count;

                }
                else if(acknowledged[base]==false)
                {
                    try
                    {
                        out = new DataOutputStream(
                socket.getOutputStream());
                out.writeInt(not_acknowledged[index1]);
                                        String line = "";

                line = input.nextLine();
                out = new DataOutputStream(
                socket.getOutputStream());
                out.writeUTF(line);
                in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
                acknowledged_number=in.readInt();
                if(not_acknowledged[index1]==acknowledged_number)
                {
                    ++index1;
                }
                else
                {
                    continue;
                }
                    }
                            catch (IOException i) {
            System.out.println(i);
        }
                    
                
            }
        
    }
  }  // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    
}
 
    public static void main(String args[])
    {
        for(int i=0;i<packet_size;i++)
        {
            acknowledged[i]=false;
        }
        Client client = new Client("127.0.0.1", 5000);
    }
}