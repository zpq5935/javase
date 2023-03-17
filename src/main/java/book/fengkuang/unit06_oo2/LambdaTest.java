package book.fengkuang.unit06_oo2;

import org.junit.Test;

interface A{
	void fna();
}
interface B{
	void fnb(String string);
}
interface C{
	int fnc(int a, int b);
}
public class LambdaTest {
	public static void main(String[] args) {
		LambdaTest mainApp = new LambdaTest();
		mainApp.to_a(() -> System.out.println("调用接口A的fna方法"));
		mainApp.to_b( string->
					System.out.println("调用接口B的fnb方法,参数为"+string) );
		mainApp.to_c( (a,b)->{
			System.out.println("调用接口C的fnc方法,参数为"+a+","+b+";和为:"+(a+b));
			return (a+b);
		});
	}
	public void to_a(A a){
		a.fna();
	}
	public void to_b(B b){
		b.fnb("填空");
	}
	public void to_c(C c){
		c.fnc(12, 9);
	}
	
	
	
	@Test
	public void test01(){
		/*Object object = ()->{
			for(int i=0; i<10; i++)
				System.out.println(i);
		};*/
	}
}
