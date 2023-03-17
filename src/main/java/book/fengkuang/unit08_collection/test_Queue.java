package book.fengkuang.unit08_collection;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

public class test_Queue {

	/**
	 * 测试PriorityQueue
	 */
	@Test
	public void test01(){
		Queue<Integer> queue = new PriorityQueue<>();
		queue.add(6);
		queue.add(-9);
		queue.add(45);
		queue.add(65);
		System.out.println(queue);
		System.out.println(queue.poll());
	}
	
	/**
	 * 测试Deque
	 */
	@Test
	public void test02(){
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.add(6);
		deque.add(-9);
		deque.add(45);
		deque.add(65);
		System.out.println(deque);
		deque.addFirst(100);
		System.out.println(deque);
		deque.addLast(1000);
		System.out.println(deque);
	}
}
