import java.io.*;
public class CRC_Receiver {
    static String dividend;
    static String divisor;
    public void read_from_file()throws FileNotFoundException,IOException
    {
        FileReader f1=new FileReader("intermediate.txt");
        BufferedReader b1=new BufferedReader(f1);
        String line;
        int i=0;
        while((line=b1.readLine())!=null)
        {
            if(i==0)
            {
                dividend=line;
            }
            else
            {
                divisor=line;
            }
            ++i;
        }
        send(dividend, divisor);

    }
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
    public int perform_XOR(int dividend,int divisor)
    {
        return dividend^divisor;
    }
    public void send(String dividend,String divisor)
    {
        int leng_of_divisor=divisor.length();
        int iterator_of_dividend=0;
        int rem;
        int divisor_decimal=binarytodecimal(divisor);
        int dividend_decimal;
        StringBuffer b1=new StringBuffer();
        while(iterator_of_dividend<dividend.length())
        {
            if(b1.length()<leng_of_divisor)
            {

            
            b1.append(dividend.charAt(iterator_of_dividend));
            ++iterator_of_dividend;
            if(b1.length()!=leng_of_divisor)
            {
                continue;
            }
            }
            dividend_decimal=binarytodecimal(b1.toString());
            
            rem=perform_XOR(dividend_decimal, divisor_decimal);
            b1.delete(0, b1.length());
            b1.append(Integer.toBinaryString(rem));
            
                       
        }
        if(Integer.parseInt(b1.toString())==0)
        {
            System.out.println("No error detected");
        }
        else
        {
            System.out.print("Error detected");
        }
        
        
    }
    public static void main(String[] args)throws FileNotFoundException,IOException
    {
        CRC_Receiver obj=new CRC_Receiver();
        obj.read_from_file();
        
    }
}
