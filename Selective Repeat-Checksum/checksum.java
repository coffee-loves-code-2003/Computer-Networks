import java.util.*;
public class checksum {
    public static void main(String[] args) {
        Scanner rs = new Scanner(System.in);
        String number;
        System.out.print("Enter the number\n");
        number = rs.next();
        String num = Integer.toBinaryString(Integer.parseInt(number));
        int len = num.length();
        StringBuilder sb1 = new StringBuilder();
        if(len!=32)
        {

        for (int i = 1; i <= 32 - len; i++) {
            sb1.append(0);
        }
        }

        sb1.append(num);
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        while (i < 32) {
            sb2.append(sb1.charAt(i));
            ++i;
            if (i % 8 == 0 && i != 32) {
                sb2.append('.');
            }
        }
        System.out.print(sb2+"\n");
        checksum obj=new checksum();
        String s1[] = sb2.toString().split("\\.");
        int sum=0;
        
        for (int j = 0; j < 4; j++) {
            sum=sum+obj.binarytodecimal(s1[j]);
        }
        System.out.print(Integer.toBinaryString(sum)+"\n");
        int sum1=0;
        
        if(Integer.toBinaryString(sum).length()>8)
        {
           sum1=obj.binarytodecimal(Integer.toBinaryString(sum).substring(0, Integer.toBinaryString(sum).length()-8));
           
           sum1=sum1+obj.binarytodecimal(Integer.toBinaryString(sum).substring(Integer.toBinaryString(sum).length()-7,Integer.toBinaryString(sum).length()));
            
        }
        sb2.append('.');
        int sum2=~(sum1);
        System.out.print(sum1+"\n");
        System.out.print(Integer.toBinaryString(sum1)+"\n");
        String s=Integer.toBinaryString(sum2);
        System.out.print("\n"+s+"\n");
        int j=s.length()-Integer.toBinaryString(sum1).length();
        while(j<32)
        {
            
            sb2.append(s.charAt(j));
            ++j;
        }
        
        System.out.print(sb2);
        
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

}
