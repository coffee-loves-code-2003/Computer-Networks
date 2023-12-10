import java.io.*;

public class Bit_Stuffing_2 {
    public void receiver() throws IOException {
        FileReader f1 = new FileReader("Intermediate.txt");
        BufferedReader b1 = new BufferedReader(f1);
        OutputStream o1 = new FileOutputStream("Receiver.txt");
        String ascii;
        int i = 0;
        StringBuilder s1 = new StringBuilder();
        while ((ascii = b1.readLine()) != null) {
            
            s1.append(ascii.substring(8, ascii.length()-8));
            s1.append('\n');
        }
        System.out.print(s1);
        int count = 0;
        char c;
        int j = 0;
        while (j < s1.length()) {
            if (count == 5) {
                System.out.print(s1.charAt(j));
                ++j;
                count = 0;
                continue;
            } else if (s1.charAt(j) == '1') {
                ++count;
                c = s1.charAt(j);
                o1.write(c);
            } else {
                count = 0;
                c = s1.charAt(j);
                o1.write(c);
            }
            if(s1.charAt(i)=='\n')
            {
                count=0;
            }
            ++j;

        }
    }
    

    public static void main(String[] args) throws IOException {
        Bit_Stuffing_2 obj = new Bit_Stuffing_2();
        obj.receiver();
    }
}