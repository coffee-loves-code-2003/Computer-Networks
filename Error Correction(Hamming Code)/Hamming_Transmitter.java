import java.io.*;
import java.util.*;
public class Hamming_Transmitter
{
    public int Parity_count_Decider(String data)
    {
        int i=0;
        int value;
        int parity_limit;
        while(true)//getting the parity bit counter
        {
            value=(int)Math.pow(2,i);
            if(value>=i+data.length()+1)
            {
                parity_limit=i;
                break;
            }
            ++i;
        }
        return parity_limit;
    }
    public String data_with_parity(char[] data_with_parity,String data)
    {
        int iter_for_parity=0;
        int value;
        int iter_data=data.length()-1;//getting the length of data because of storing the data in reverse order
        StringBuilder sb1=new StringBuilder();
        for(int i=0;i<data_with_parity.length;i++)//Iterating the loop till the count becomes data length+ parity bit length
        {
            value=(int)Math.pow(2,iter_for_parity);
            if(i+1==value)
            {
                data_with_parity[i]='2';
                ++iter_for_parity;
                continue;
            }
            data_with_parity[i]=data.charAt(iter_data);//storing the value of data if the i value does not equal to the power of 2
            --iter_data;// decrementing the value of the data length

        }
        System.out.println(data_with_parity);
        int xor=0;
        String xor_data;
        int first_xor=0;
        int power=0;
        for(int i=0;i<data_with_parity.length;i++)
        {
            if(data_with_parity[i]=='2')
            {
                for(int j=i+2;j<=data_with_parity.length;j++)
                {
                    xor_data=Integer.toBinaryString(j);

                    if(xor_data.charAt(xor_data.length()-power-1)=='1')
                    {
                            
                        if(first_xor==0) 
                        {
                            xor=data_with_parity[j-1]-'0';
                            ++first_xor;

                        }
                        else
                        {
                            xor=xor^(data_with_parity[j-1]-'0');
                        }
                        
                    }
                    
                }
                first_xor=0;
                if(xor==0)
                {
                    data_with_parity[i]='0';
                }
                else if(xor==1)
                {
                    data_with_parity[i]='1';

                }
                
                xor=0;

                ++power;
            }
        }
        sb1.append(data_with_parity);

        String data_parity=(sb1.reverse()).toString(); 
        return data_parity;
    }
    public static void main(String[] args)throws FileNotFoundException,IOException
    {
        Scanner rs=new Scanner(System.in);
        String data;
        System.out.print("Enter the data\n");
        data=rs.next();
        Hamming_Transmitter obj=new Hamming_Transmitter();
        int parity_limit=obj.Parity_count_Decider(data);
        char data_with_parity[]=new char[data.length()+parity_limit];//adding the parity count and data length
        String data_parity=obj.data_with_parity(data_with_parity,data);
        System.out.println("Finalized_Result:"+data_parity);
        OutputStream o1=new FileOutputStream("Intermediate.txt");
        byte[]b=data_parity.getBytes();
        o1.write(b);

    }
}