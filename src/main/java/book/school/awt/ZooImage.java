package book.school.awt;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ZooImage {

	private final int WIDTH = 80;
	private final int HEIGHT = 60;
	private BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
	Graphics graphics = bufferedImage.getGraphics();

	public void zoom() throws Exception {
		Image img = ImageIO.read(new File("D:\\User\\360浏览器\\360安全浏览器下载\\图片\\39_150915110106_1.jpg"));
		graphics.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		ImageIO.write(bufferedImage, "jpeg",
				new File("D:\\User\\360浏览器\\360安全浏览器下载\\图片\\" + System.currentTimeMillis() + ".jpg"));
	}

	public static void main(String[] args) throws Exception {
		new ZooImage().zoom();
	}
}
