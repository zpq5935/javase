package book.school.awt;

import javax.imageio.ImageIO;

public class ImageIOTest {

	public static void main(String[] args) {
		String[] readFormat = ImageIO.getReaderFileSuffixes();
		System.out.println("------所有能读的格式：------");
		for(String s:readFormat){
			System.out.print(s+" ");
		}
		String[] writeFormat = ImageIO.getWriterFileSuffixes();
		System.out.println("\n------所有能写的格式：------");
		for(String s:writeFormat){
			System.out.print(s+" ");
		}
	}
}
