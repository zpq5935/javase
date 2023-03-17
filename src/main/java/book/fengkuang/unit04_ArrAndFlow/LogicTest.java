package book.fengkuang.unit04_ArrAndFlow;

import java.util.Arrays;

import org.junit.Test;

public class LogicTest {

	public void test_switch01(String string) {
		switch (string) {
		case "春天": {
			System.out.println("现在是春天");
			break;
		}
		case "夏天": {
			System.out.println("现在是夏天");
			break;
		}
		case "秋天": {
			System.out.println("现在是秋天");
			break;
		}
		case "东天": {
			System.out.println("现在是冬天");
			break;
		}
		default :{
			System.out.println("此时满是虚无！");
			break;
		}
		}
	}

	@Test
	public void test01() {
		test_switch01("ads");
		test_switch01("春天");
	}
	
	
	@Test
	public void test03(){
		String[] strArr = new String[4];
//		for(String s:strArr){
//			System.out.println(s);
//		}
		
		int[][] arr2 = new int[4][];
		for(int i=0; i<arr2.length; i++)
			System.out.println(arr2[i]);
		
		Arrays.fill(strArr, "zpq");
		for(String s:strArr){
		System.out.println(s);
	}
	}
}
