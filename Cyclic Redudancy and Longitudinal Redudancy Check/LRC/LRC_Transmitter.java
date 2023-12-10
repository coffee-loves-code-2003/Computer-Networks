import java.io.*;
import java.util.*;
public class LRC_Transmitter
{
   public void Convert_LRC(String s1)throws IOException
   {
      char arr[]=s1.toCharArray();
      Arrays.sort(arr);
      String new_s1="";
        OutputStream obj=new FileOutputStream("Transmitter.txt");
      for(int i=0;i<arr.length;i++)
      {
         if(i!=arr.length-1)
         {
            if(arr[i]==arr[i+1])
            {
               continue;
            }

         }
               new_s1=new_s1+arr[i];

        }
        System.out.println(new_s1);
      StringBuilder sb1=new StringBuilder();
      int i=0;
      while(i<new_s1.length())
      {
         sb1.append(Integer.toBinaryString(new_s1.charAt(i)));
         sb1.append('.');
        ++i;
      }   
      int iter=0;
      int arr1[][]=new int[new_s1.length()+1][7];
      int i1,j;
      i1=0;
      j=0;
      while(i1<new_s1.length())
      {
        j=0;
        while(j<7)
        {
           if(sb1.charAt(iter)!='.')
           {
              arr1[i1][j]=sb1.charAt(iter)-'0';
              ++j;
           }
           ++iter;
        }
        ++i1;
      }
      i1=0;
      j=0;
      int count=0;
      while(i1<7)
      {
         j=0;
         while(j<new_s1.length())
         {
            if(arr1[j][i1]==1)
            {
               ++count;
            }
            ++j;
         }
         
         if(count%2!=0)
         {
            arr1[new_s1.length()][i1]=1;
         }
         else
         {
            arr1[new_s1.length()][i1]=0;
         }
         count=0;
         ++i1;
      }
      StringBuilder sb2=new StringBuilder();
      for(i1=0;i1<=new_s1.length();i1++)
      {
         for(j=0;j<7;j++)
         {
            sb2.append(arr1[i1][j]);
         }
         sb2.append('.');
      }
      System.out.print(sb2);
      for(i1=0;i1<sb2.length();i1++)
      {
         obj.write(sb2.charAt(i1));
      }
      




   }
   public static void main(String[] args)throws IOException
   {
      LRC_Transmitter obj=new LRC_Transmitter();
      Scanner rs=new Scanner(System.in);
      System.out.print("Enter the string:\n");
      String s=rs.next();
      obj.Convert_LRC(s);
   }
}