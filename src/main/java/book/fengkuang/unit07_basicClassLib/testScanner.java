package book.fengkuang.unit07_basicClassLib;

import java.util.Scanner;

public class testScanner {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			String line = in.nextLine();
			if(line.equals("/over")) break;
			System.out.println(line);
		}
		
		in.close();
	}
}
