package book.fengkuang.unit17_netCode.multiThreadDown;

public class DownUtilTest {
	public static void main(String[] args) {
		final DownUtil downUtil = new DownUtil("https://static.cnblogs.com/images/adminlogo.gif", "down.gif", 4);
		try {

			downUtil.download();
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		new Thread(() -> {
			double complementPercent = 0.0;
			while ((complementPercent = downUtil.getComplementPercent()) < 1) {
				System.out.println("已完成：" + complementPercent);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}).start();
	}
}
