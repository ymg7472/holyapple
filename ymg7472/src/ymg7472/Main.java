package ymg7472;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class Main{
	static void menu(){
		System.out.println("###############");
		System.out.println("1.사용자 추가");
		System.out.println("2.사용자 수정");
		System.out.println("3.보기");
		System.out.println("4.나가기");
		System.out.println("5.저장");
		System.out.println("6.불러오기");
		System.out.println("###############");
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int i = 0;
		User[] user = new User[10];
		while(true) {
			menu();
			Scanner s = new Scanner(System.in);
			int a = s.nextInt();
			
			if(a==1) {
				Scanner s1 = new Scanner(System.in);
				User u = new User();
				System.out.println("이름:");
				u.setName(s.next());
				System.out.println("나이:");
				u.setAge(s.nextInt());
				user[i] = u;
				i++;
				
			}
			
			if(a==2) {
				System.out.println("사용자 이름:");
				String f = s.next();
				for(int r=0; r<10; r++)
				{
					if (user[r] != null && user[r].getName().equals(f)) {
						Scanner s2 = new Scanner(System.in);
						User u = new User();
						System.out.println("이름:");
						u.setName(s.next());
						System.out.println("나이:");
						u.setAge(s.nextInt());
						user[r] = u;
						s2.close();
					}
				}
			}
			
			if(a==3) {
				Scanner s3 = new Scanner(System.in);
				System.out.println("사용자 이름:");
				String j = s3.next();
				for(int r=0; r<10; r++)
				{
					if (user[r] != null && user[r].getName().equals(j) ) {
						System.out.println("사용자의 이름:" + user[r].getName() + ",사용자의 나이:" + user[r].getAge());
					}
				}
			}
			
			if(a==4) {
				System.out.println("종료합니다.");
				break;
			}
			if(a==5 ) {
				FileOutputStream output = new FileOutputStream("C:\\Users\\swh\\userslist.txt");
				for(int r=0; r<10; r++)
				{
					if (user[r] != null) {
						String d = user[r].getName() +","+ user[r].getAge()+"\n";
						output.write(d.getBytes());
					}
				}
				output.close();
			}
			if(a==6) {
				FileInputStream input = new FileInputStream("C:\\Users\\swh\\userslist.txt");
		        byte[] data = new byte[1024];  // 파일 내용의 길이가 1024바이트보다 크면 좋지 못한 방법
		        input.read(data);
		        System.out.println(new String(data));
		        input.close();
			}
		}	
		
	}
}