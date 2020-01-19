package thread;

public class ThreadBasic3 extends Thread {
	private int index;
	
	public ThreadBasic3(int index) {
		this.index = index;
	}
	
	public void run() {
		System.out.println(index + "번째 쓰레드가 동작합니다.");
		try {
			Thread.sleep(1000);
		}catch(Exception e) {

		}
		System.out.println(index + "번째 쓰레드가 종료합니다.");
	}

	public static void main(String[] args) {
		for(int i=0; i<15; i++) {
			Thread t = new ThreadBasic3(i);
			t.start();
		}
		System.out.println("메인함수가 종료합니다.");
	}
}
