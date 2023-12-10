import java.io.*;
public class Consecutive_One_Intermediate
{
    public void Intermediate_Side()throws IOException
    {
        FileReader f1=new FileReader("Sender.txt");
        BufferedReader b1=new BufferedReader(f1);
        StringBuilder s1=new StringBuilder();
    
        String ascii;
        while((ascii=b1.readLine())!=null)
        {
           s1.append(ascii);
           s1.append('\n'); 
        }
        OutputStream o1=new FileOutputStream("Intermediate.txt");
        int i=0;
        while(i<s1.length())
        {
            if(i!=s1.length()-2)
            {
            if(s1.charAt(i)== 49 && s1.charAt(i+1)==49 && s1.charAt(i+2)==49)
            {
                o1.write('>');
                o1.write('>');
                o1.write('>');
                o1.write(s1.charAt(i));
                o1.write(s1.charAt(i+1));
                o1.write(s1.charAt(i+2));
                i+=3;
            continue;
                
            }
            
            


        }
            o1.write(s1.charAt(i));
            ++i;
        }
        b1.close();
        o1.close();
    }
    public static void main(String[] args)throws IOException
    {
        Consecutive_One_Intermediate c1=new Consecutive_One_Intermediate();
        c1.Intermediate_Side();
    }
}