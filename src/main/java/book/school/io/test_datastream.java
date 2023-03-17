package book.school.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class test_datastream {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try{
			FileOutputStream out = new FileOutputStream("data.txt");
			FileInputStream in = new FileInputStream("data.txt");
			DataInputStream dataInputStream = new DataInputStream(in);
			DataOutputStream dataOutputStream = new DataOutputStream(out);
			//
			dataOutputStream.writeInt(123);
			dataOutputStream.writeInt(500);
			System.out.println(dataInputStream.readInt());
			System.out.println(dataInputStream.readInt());
			
			
		}catch(FileNotFoundException e)
		{System.out.println(e);}
		catch(IOException e)
		{System.out.println(e);}
		catch(Exception e)
		{System.out.println(e);}
		
	}

}
