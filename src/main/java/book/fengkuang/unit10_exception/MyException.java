package book.fengkuang.unit10_exception;

public class MyException extends Exception {
	public MyException() {
	}

	public MyException(String message) {
		super(message);
	}
	/**
	 * 对应职责链模式
	 * @param throwable
	 */
	public MyException(Throwable throwable){
		super(throwable);
	}
}
