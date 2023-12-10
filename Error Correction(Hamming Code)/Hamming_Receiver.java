import java.io.*;
public class Hamming_Receiver {
    public int binarytodecimal(String binary)
    {
        int sum=0;
        int j=0;
        int num;
        for(int i=binary.length()-1;i>=0;i--)
        {
            num=binary.charAt(i)-'0';
            sum=sum+(num *(int)Math.pow(2,j));
            j++;
        }
        return sum;
    }
    public void Error_Check(String input)
    {
        int xor=0;
        String xor_data;
        int first_xor=0;
        int power=0;
        int number_of_zeroes=0;
        StringBuilder b1=new StringBuilder();
        for(int i=0;i<input.length();i++)
        {
            if(i+1==(int)Math.pow(2,power))
            {
                for(int j=i+1;j<=input.length();j++)
                {
                    xor_data=Integer.toBinaryString(j);
                    if(xor_data.charAt(xor_data.length()-power-1)=='1')
                    {

                        if(first_xor==0) 
                        {
                            xor=input.charAt(j-1)-'0';
                            ++first_xor;

                        }
                        else
                        {
                            xor=xor^(input.charAt(j-1)-'0');
                        }
                        
                    }
                }
                first_xor=0;
                if(xor==0)
                {
                    ++number_of_zeroes;
                }
                b1.append(xor);
                xor=0;
                ++power;
            }

        }
        System.out.println(binarytodecimal(b1.reverse().toString()));
        if(power==number_of_zeroes)
        {
            System.out.println("No error detected");
        }
        else
        {
            System.out.println("Error detected");
        }
    }
    public static void main(String[] args)throws FileNotFoundException,IOException
    {
        Hamming_Receiver obj=new Hamming_Receiver();
        StringBuilder s1=new StringBuilder();
        FileReader f1=new FileReader("Intermediate.txt");
        BufferedReader b1=new BufferedReader(f1);
        String line;
        while((line=b1.readLine())!=null)
        {
            s1.append(line);
        }
        obj.Error_Check(s1.reverse().toString());
    }
}
