package book.school.thread;

/**
 * wait()
 * notify()
 * notifyAll()
 * @author zcp
 *
 */

public class test_wait {

	public static void main(String[] args) {
		Tickets tickets = new Tickets(10);
//		new Producer(tickets).start();
//		new Consumer(tickets).start();
		/****************/
		new Thread(new Producer(tickets)).start();
		new Thread(new Consumer(tickets)).start();
	}
}
class Tickets{
	protected int size;
	int number = 0;
	boolean available = false;
	public Tickets(int size){
		this.size = size;
	}
	public synchronized void put(){
		if(available)
			try{wait();}
				catch(Exception e){e.printStackTrace();}
		System.out.println("存入第【"+(++number)+"】号票");
		available = true;
		notify();
	}
	public synchronized void sell(){
		if(!available)
			try{wait();}
			catch (Exception e) {
				e.printStackTrace();
			}
		
		System.out.println("售出第【"+number+"】好票");
		available = false;
		notify();
		if( number == size) number = size+1;
	}
}
class Producer implements Runnable{
	Tickets tickets =null;
	public Producer(Tickets tickets) {
		this.tickets = tickets;
	}
	@Override
	public void run() {
		while(tickets.number<tickets.size)
			tickets.put();
	}
	
}
class Consumer implements Runnable{
	Tickets tickets =null;
	public Consumer(Tickets tickets) {
		this.tickets = tickets;
	}
	@Override
	public void run() {
		while(tickets.number<=tickets.size)
			tickets.sell();
	}
	
}