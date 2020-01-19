package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadBasic6 {
	
	public void excute() {
		int threadSize = 80;
		List<Thread> threadList = new ArrayList<Thread>();
		CountDownLatch latch = new CountDownLatch(threadSize);
		for(int i=0; i<threadSize; i++) {
			threadList.add(new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("쓰레드가 동작합니다.");
					try {
						Thread.sleep(1000);
					}catch(Exception e) {

					}
					System.out.println("쓰레드가 종료합니다.");
					latch.countDown();
				}
			}));
		}
		for(Thread t : threadList) {
			t.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ThreadBasic6 ex = new ThreadBasic6();
		ex.excute();
		System.out.println("메인함수가 종료합니다.");
	}
}