import java.io.*;
public class LRC_Receiver {
    public void Detect_LRC()throws IOException
    {
        FileReader obj=new FileReader("Transmitter.txt");
        BufferedReader b1=new BufferedReader(obj);
        StringBuilder s1=new StringBuilder();
        int ascii;
        int rows=0;
        int iter=0;
        while((ascii=b1.read())!=-1)
        {
            if((char)ascii=='.')
            {
                ++rows;
                s1.append('.');
            }
            else
            {
                s1.append((char)ascii);
            }
        }
        int arr[][]=new int[rows+1][7];

        int i,j;
        i=0;
        j=0;    
        while(i<rows)
        {
            j=0;
            while(j<7)
            {
                if(s1.charAt(iter)!='.')
                {
                arr[i][j]=s1.charAt(iter)-'0';
                ++j;
                }
                ++iter;
            }
            ++i;
        }
        i=0;
      j=0;
      int xor;
      while(i<7)
      {
         j=0;
         xor=arr[j][i];
         while(j<rows)
         {
            if(j!=0)
            {
            xor=arr[j][i]^xor;
            }
            ++j;
         }
         arr[rows][i]=xor;
        ++i;
      }
      int count=0;
      for(j=0;j<7;j++)
      {
        if(arr[rows][j]==0)
        {
            ++count;
        }
      }
      if(count==7)
      {
        System.out.print("No error detected");
      }
      else if(count<=5)
      {
        System.out.print("Burst Error detected");
      }
      else
      {
        System.out.print("Error detected");
      }


    }
    public static void main(String[] args)throws IOException
    {
        LRC_Receiver obj=new LRC_Receiver();
        obj.Detect_LRC();
    }
}
