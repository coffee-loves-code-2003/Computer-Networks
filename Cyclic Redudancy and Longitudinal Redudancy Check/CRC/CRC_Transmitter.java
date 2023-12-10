import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CRC_Transmitter
{
    static int highest_power;
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
    public String send(String dividend,String divisor)
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
        return b1.toString();
        
    }
    public String Divisor_Extraction()throws FileNotFoundException,IOException
    {
        StringBuilder s1=new StringBuilder();
        StringBuilder s2=new StringBuilder();
        int ascii;
        int iter=0;
        FileReader f1=new FileReader("Divisor.txt");
        BufferedReader b1=new BufferedReader(f1);
        while((ascii=b1.read())!=-1)
        {
            
            s1.append((char)ascii);
        }
        Pattern pattern = Pattern.compile("[x\\^](\\d+)");
        Matcher matcher = pattern.matcher(s1.toString());
        
        String power[]=new String[10];
        int count=0;
        while (matcher.find()) {
            power[iter]= matcher.group(1);
            ++iter;
            ++count;
        }
        iter=0;
        highest_power=Integer.parseInt(power[0]);

        for(int i=highest_power;i>=0;i--)
        {
            if(iter<count)
            {

            if(i==Integer.parseInt(power[iter]))
            {
                ++iter;
                s2.append('1');
            }
            else
            {            

                s2.append('0');
            }
            }
            else
            {
                    s2.append('0');

            }



        }
        return s2.toString();

    }
    public static void main(String[] args)throws FileNotFoundException,IOException
    {
        String dividend;
        String divisor;
        Scanner rs=new Scanner(System.in);
        StringBuilder s1=new StringBuilder();
        System.out.print("Enter the Dividend\n");
        dividend=rs.next();
        StringBuilder s2=new StringBuilder(dividend);

        CRC_Transmitter obj=new CRC_Transmitter();
        divisor=obj.Divisor_Extraction();
        for(int i=0;i<CRC_Transmitter.highest_power;i++)
        {
            s2.append('0');
        }
        String remainder_binary=obj.send(s2.toString(),divisor);
        int remainder_with_dividend=obj.binarytodecimal(s2.toString())+obj.binarytodecimal(remainder_binary);
        obj.write_in_file(Integer.toBinaryString(remainder_with_dividend), divisor);


        

    }
    public void write_in_file(String remainder_with_dividend,String divisor)throws FileNotFoundException,IOException
    {
        FileOutputStream o1=new FileOutputStream("Intermediate.txt");
        byte b[]=remainder_with_dividend.getBytes();
        o1.write(b);
        byte b1[]=divisor.getBytes();
        o1.write('\n');
        o1.write(b1);

    }
}