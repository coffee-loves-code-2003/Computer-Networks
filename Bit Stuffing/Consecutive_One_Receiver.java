import java.io.*;
public class Consecutive_One_Receiver
{
    public void Receiver_Side()throws IOException
    {
        FileReader f1=new FileReader("Intermediate.txt");
        BufferedReader b1=new BufferedReader(f1);
        int ascii;
        OutputStream o1=new FileOutputStream("Receiver.txt");
        while((ascii=b1.read())!=-1)
        {
            if((char)ascii!='>')
            {
                o1.write((char)ascii);
            }

        }
        b1.close();
        o1.close();
    }
    public static void main(String[] args)throws IOException
    {
        Consecutive_One_Receiver c1=new Consecutive_One_Receiver();
        c1.Receiver_Side();
    }
}