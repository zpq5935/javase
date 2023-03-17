package book.school.collection;

import java.util.HashMap;
import java.util.Map;

public class test_map02 {
	
	public static void main(String[] args) {
		
		HashMap<Integer,Integer> hp = new HashMap<>();
		
		int num = 0;
		for(int i=0; i<10000; i++){
			num = (int)(Math.random()*100);
			if( hp.isEmpty() ){
				hp.put(num, 1);
			}
			else{
				if( hp.get(num)== null){
					hp.put(num, 1);
				}
				else {
					int count = hp.get(num);
					hp.put(num, ++count);
				}
			}
		}
		
		//��ӡ
		int n =0;
		for(int i:hp.keySet()){
			n++;
			System.out.print(i+"	");
			if(n%20 == 0)
				System.out.println();
		}
		
		System.out.println("\n�������ּ�����Ƶ�����£�");
		for (Map.Entry<Integer, Integer> entry : hp.entrySet()) { 
			  System.out.println("���� = " + entry.getKey() + ", ���� = " + entry.getValue()); 
			}
		
	}
	
}
