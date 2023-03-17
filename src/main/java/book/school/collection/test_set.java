package book.school.collection;

import java.util.*;

import org.junit.Test;

public class test_set {
	
	/*
	 * Set�е�Ԫ�أ������ظ� 
	 */
	@Test
	public void test01(){
		HashSet<String> names = new HashSet<String>();
        
        names.add("gareen");
         
        System.out.println(names);
         
        //�ڶ��β���ͬ�������ݣ��ǲ岻��ȥ�ģ�������ֻ�ᱣ��һ��
        names.add("gareen");
        System.out.println(names);
	}
	
	/*
	 * Set�е�Ԫ�أ�û��˳��
		�ϸ��˵����û�а���Ԫ�صĲ���˳������
		HashSet�ľ���˳�򣬼Ȳ��ǰ��ղ���˳��Ҳ���ǰ���hashcode��˳��
	 */
	@Test
	public void test02(){
		HashSet<Integer> numbers = new HashSet<Integer>();		 
        numbers.add(9);
        numbers.add(5);
        numbers.add(1);
 
        HashSet<Integer> numbers2 = new HashSet<Integer>();
        numbers2.add(9);
        numbers2.add(5);
        numbers2.add(1);
        // Set�е�Ԫ�����У����ǰ��ղ���˳��
        System.out.println(numbers);
        System.out.println(numbers2);
	}
	
	/*
	 * Set���ṩget()����ȡָ��λ�õ�Ԫ��
		���Ա�����Ҫ�õ���������������ǿ��forѭ�� 
	 */
	@Test
	public void test03(){
		HashSet<String> hashSet = new HashSet<>();
		for(int i=0; i<10; i++)
			hashSet.add("Person"+i);
		System.out.println(hashSet);
		//1.������Iterator����
		Iterator<String> iterator = hashSet.iterator();
		while(iterator.hasNext())
			System.out.print(iterator.next()+" ");
		//2.��ǿforѭ��
		System.out.println();
		for(String string: hashSet)
			System.out.print(string+" ");
		
	}
	/*
	 * ͨ���۲�HashSet��Դ���루��β鿴Դ���룩
		���Է���HashSet����û�ж�����ʵ�֣������������װ��һ��Map.
	 */
	/***********************/
	@Test
	public void test_set(){
		HashSet<String> hashSet = new HashSet<>();
		HashSet<String> hashSet2 = new HashSet<>();
		int number = 0;
		String string = null;
		
		for (int i = 0; i < 50; i++) {
			string = randomString(2);
			if( (i+1) %10 == 0)
				System.out.println(string);
			else
				System.out.print(string+" ");
			
			if (!hashSet.add(string)) {
				if (hashSet2.add(string)) {
					number++;
				}
			}

		}
		
		System.out.println("�ظ�:"+number+"��");
		System.out.print("�ֱ��ǣ�"+hashSet2);
	}
	public String randomString(int length){
		String pool = "";
		for(char c='0';c<'9';c++)
			pool+=(char)c;
		for(char c='a';c<'z';c++)
			pool+=(char)c;
		for(char c='A';c<'A';c++)
			pool+=(char)c;
		char[] cs = new char[length];
		for(int i=0; i<length; i++){
			int index = (int)(Math.random() * pool.length());
			cs[i] = pool.charAt(index);
		}
		String returnString = new String(cs);
		
		return returnString;
	}
}
