import java.io.*;
import java.util.*;

public class Bit_Stuffing {
    public void Intermediate_File() throws IOException {
        StringBuilder obj = new StringBuilder();
        FileReader f1 = new FileReader("Sender.txt");
        BufferedReader f2 = new BufferedReader(f1);
        String line;
        while ((line = f2.readLine()) != null) {

            obj.append(line);
            
            obj.append("\n");//appending the new line to indicate the insertion of next bits

        }
        int count = 0;
        StringBuilder o1 = new StringBuilder();
        o1.append("01111110"); //Insertion of first flag
        for (int i = 0; i < obj.length(); i++) {

            if(count==5)
            {
                o1.append('0');
                count=0;
                
                o1.append(obj.charAt(i));
            }
            else if(obj.charAt(i)=='1')
            {
                ++count;
                o1.append(obj.charAt(i));
            }
            else
            {
                count=0;
                o1.append(obj.charAt(i));
            }
            if(i!=obj.length()-1)
            {
                if(obj.charAt(i+1)=='\n')
                {
                    o1.append("01111110");
                
                    o1.append('\n');
                    
                    o1.append("01111110");
                    count=0;
                    i+=1;
                }
            }
            

        }
        System.out.println(o1);
        o1.delete(o1.length()-8, o1.length());
        System.out.print(o1);


        OutputStream obj1 = new FileOutputStream("Intermediate.txt");
        for (int i = 0; i < o1.length(); i++) {
            obj1.write(o1.charAt(i));
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner rs = new Scanner(System.in);
        Bit_Stuffing obj = new Bit_Stuffing();
        obj.Intermediate_File();
    }
}