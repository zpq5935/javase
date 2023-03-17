package book.fengkuang.unit06_oo2;

public class EnumTest {
	public void judge(SeasonEnum s){
		switch(s){
			case SPRING:
				System.out.println("春暖花开！");
				break;
			case SUMER:
				System.out.println("夏日炎炎！");
				break;
			case FALL:
				System.out.println("硕果累累！");
				break;
			case WINTER:
				System.out.println("千里雪峰！");
				break;
		}
	}
	public static void main(String[] args) {
		System.out.println(SeasonEnum.SPRING.toString()+"\n-----");
		for(SeasonEnum s:SeasonEnum.values()){
			System.out.println(s);
		}
		new EnumTest().judge(SeasonEnum.SPRING);
	}
}
