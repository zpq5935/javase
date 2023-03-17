package book.school.collection;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class test_map {

	/*
	 * HashMap�������ݵķ�ʽ�ǡ��� ��ֵ�� 
	 */
	@Test
	public void test01(){
		HashMap<String,String> dictionary = new HashMap<>();
        dictionary.put("adc", "����Ӣ��");
        dictionary.put("apc", "ħ��Ӣ��");
        dictionary.put("t", "̹��");
         
        System.out.println(dictionary.get("t"));
	}
	
	/*
	 * ����HashMap���ԣ�key��Ψһ�ģ��������ظ��ġ�
		���ԣ�����ͬ��key �Ѳ�ͬ��value���뵽 Map�лᵼ�¾�Ԫ�ر����ǣ�ֻ�����������Ԫ�ء�
		������ͬһ�����������Ϊֵ���뵽map�У�ֻҪ��Ӧ��key��һ�� 
	 */
	@Test
	public void test02(){
		HashMap<String,String> dictionary = new HashMap<>();
        dictionary.put("adc", "¬����");
        dictionary.put("adc", "vn");
        dictionary.put("�е�", "vn");
        dictionary.put("top", "����");
         
        System.out.println(dictionary);
	}
	
	/**��String
	 * 	==���Ƚϵ�ַ
	 * equal���Ƚ�����
	 * 
	 */
	@Test
	public void test03(){
		String s1 = "adc";
		String s2 = new String("adc");
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
	}
	
	
	/**
	 * 
	 */
	@Test
	public void test04(){
		List<Hero> hs =new ArrayList<>();
        System.out.println("��ʼ����ʼ");
        for (int i = 0; i < 3000000; i++) {
            Hero h = new Hero(   "hero-" + random());
            hs.add(h);
        }
        //������Ϊkey
        //������ͬ��hero������һ��List�У���Ϊvalue

        System.out.println("��ʼ������");
        System.out.println("��ʼ����");
        findByIteration(hs);
        findByMap(hs);
	}
	private static List<Hero> findByMap(List<Hero> hs) {
        long start =System.currentTimeMillis();
        HashMap<String,List<Hero>> heroMap =new HashMap<String, List<Hero>>();
        for (Hero h : hs) {
            List<Hero> list= heroMap.get( h.name);
            if(list==null){
                list = new ArrayList<>();
                heroMap.put(h.name, list);
            }
            list.add(h);
        }
        List <Hero>result= heroMap.get("hero-5555");
        long end =System.currentTimeMillis();
        System.out.printf("ͨ��map���ң�һ���ҵ�%d��Ӣ�ۣ���ʱ%d ����%n",result.size(),end-start);
        return result;
    }
    private static List<Hero> findByIteration (List<Hero> hs) {
        long start =System.currentTimeMillis();
        List<Hero> result =new ArrayList<>();
        for (Hero h : hs) {
            if(h.name.equals("hero-5555")){
                result.add(h);
            }
        }
        long end =System.currentTimeMillis();
        System.out.printf("ͨ��for���ң�һ���ҵ�%d��Ӣ�ۣ���ʱ%d ����%n", result.size(),end-start);
        return result;
    }
    public static int random(){
        return ((int)(Math.random()*9000)+1000);
    }
    class Hero{
    	String name;
    	public Hero(String name){
    		this.name = name;
    	}
    }
    
    
    
    /**
     * 4�б�����ʽ
     * 1.
     * 2.
     * 3.
     * 4.
     * @throws IOException 
     */
    @SuppressWarnings("resource")
	@Test
    public void test004() {
    	Map<String, String> map = new HashMap<>();
    	double Start,End;
    	//BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("time.txt"));
    	FileOutputStream fOutputStream;
		try {
			fOutputStream = new FileOutputStream("time.txt");

			DataOutputStream bufferedWriter = new DataOutputStream(fOutputStream);

			for (int i = 0; i < 10000; i++)
				map.put(i + "��", "�̳���" + i);
			//
			System.out.println(map);
			// 1.map��entrySet��getKey()��getValue()
			Start = System.currentTimeMillis();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			}
			End = System.currentTimeMillis();
			System.out.println("----------------------------------ʱ�䣺" + (End - Start));
			bufferedWriter.writeDouble(End - Start);
			// 2. ��for-eachѭ���б���keys��values
			Start = System.currentTimeMillis();
			for (String key : map.keySet())
				System.out.print(key + " ");
			System.out.println();
			for (String value : map.values())
				System.out.print(value + " ");
			End = System.currentTimeMillis();
			System.out.println("----------------------------------ʱ�䣺" + (End - Start));
			bufferedWriter.writeDouble(End - Start);
			// 3.ʹ��Iterator����
			Start = System.currentTimeMillis();
			Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				System.out.println("Key:" + entry.getKey() + ",Value:" + entry.getValue());
			}
			End = System.currentTimeMillis();
			System.out.println("----------------------------------ʱ�䣺" + (End - Start));
			bufferedWriter.writeDouble(End - Start);
			// 4.ͨ������ֵ������Ч�ʵͣ�
			Start = System.currentTimeMillis();
			Set<String> set = map.keySet();
			for (String s : set) {
				System.out.print("Key:" + s);
				String value = map.get(s);
				System.out.println("Value" + value);
			}
			End = System.currentTimeMillis();
			System.out.println("----------------------------------ʱ�䣺" + (End - Start));
			bufferedWriter.writeDouble(End - Start);
		}
    	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @SuppressWarnings("resource")
	@Test
    public void test009(){
    	try {
    	FileOutputStream fOutputStream;
    	FileInputStream fInputStream = new FileInputStream("time.txt");
    	
			fOutputStream = new FileOutputStream("time.txt");

			DataOutputStream bufferedWriter = new DataOutputStream(fOutputStream);
			DataInputStream dataInputStream = new DataInputStream(fInputStream);
			bufferedWriter.writeDouble(2.1245);
			bufferedWriter.writeChars("你好啊");
			
			System.out.println(dataInputStream.readDouble());
			while(dataInputStream.available()>0)
				System.out.print(dataInputStream.readChar());
				
			
		}
    	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

    }
}
