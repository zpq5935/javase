package book.school.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/* add()
* contains()
* 
*/
public class test_list {

	public static void main(String[] args) {
		List<Integer> l;
		l = new ArrayList<>();
		insertFirst(l, "ArrayList");

		l = new LinkedList<>();
		insertFirst(l, "LinkedList");

	}

	private static void insertFirst(List<Integer> l, String type) {
		int total = 1000 * 100;
		final int number = 5;
		long start = System.currentTimeMillis();
		for (int i = 0; i < total; i++) {
			// ??????????????λ???????????
			l.add(l.size() / 2, number);
		}
		long end = System.currentTimeMillis();
		System.out.printf("??%s ???м????%d????????????? %d ???? %n", type, total, end - start);
	}

	// ArrayList:?????List????????List?????

	@Test
	public void test01() {
		ArrayList<String> list1 = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			list1.add("??" + i);

		// 1.for????
		for (int i = 0; i < list1.size(); i++)
			System.out.print(list1.get(i) + " ");
		// 2.??????iterator????
		System.out.println();
		Iterator<String> iterator = list1.iterator();
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		// 3.???for???
		System.out.println();
		for (String str : list1)
			System.out.print(str + " ");
		//
		System.out.println();
		System.out.println(list1);
	}

	/* LinkedList???????????List???????????????????Deque??????????????β???????????? */

	@Test
	public void test02() {
		LinkedList<String> linkedList = new LinkedList<>();
		for (int i = 0; i < 10; i++)
			linkedList.add("??" + i);
		//
		System.out.println(linkedList);

		linkedList.addFirst("First???");
		linkedList.addLast("Last???");
		System.out.println(linkedList);
	}

	/*
	 * LinkedList?????Queue???(????)?? offer ??????????? poll ??????????? peek ??????????
	 */

	@Test
	public void test03() {
		LinkedList<String> linkedList = new LinkedList<>();
		for (int i = 0; i < 10; i++)
			linkedList.add("??" + i);
		//
		System.out.println(linkedList);

		linkedList.offer("Last");
		System.out.println(linkedList);

		System.out.println(linkedList.poll());
		System.out.println(linkedList);

		System.out.println(linkedList.peek());
		System.out.println(linkedList);
	}

}
