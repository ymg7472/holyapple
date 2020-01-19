package thread;

import java.util.LinkedList;
public class ThreadBasic4 extends Thread {
	private int index;

	public ThreadBasic4(int index) {
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
		LinkedList<Thread> threadList = new LinkedList<Thread>();
		for(int i=0; i<15; i++) {
			Thread t = new ThreadBasic4(i);
			t.start();
			threadList.add(t);
		}
		for(int i=0; i<threadList.size(); i++) {
			Thread t = threadList.get(i);
			try {
				t.join();		//	해당 쓰레드가 종료될 때 까지 기다린다. 메인함수가 제일 마지막에 호출될 수 있게 변경
			}catch(Exception e) {
			}
		}
		System.out.println("메인함수가 종료합니다.");
	}
}