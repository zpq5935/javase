package book.school.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class test_buffered {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("data02.txt");
            FileReader fileReader = new FileReader("data02.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            char[] str1 = {'5', '2', '1'};
            char[] str2 = {'4', '3', '8'};
            String s1 = "31";
            String s2 = "15";

            bufferedWriter.write(s1);
            bufferedWriter.write('@');
            bufferedWriter.write(s2);
            bufferedWriter.flush();

            String aLine = bufferedReader.readLine();
            System.out.println("原文件内容为：" + aLine);
            String[] str2s = aLine.split("@");
            System.out.println("得到数字有:");
            for (String str : str2s) {
                int num = Integer.parseInt(str);
                System.out.print(num + " ");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
