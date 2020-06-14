package algorithm; 

class BB extends AA{
	public void y(){
		System.out.println("자식 y");
	}
	@Override
	public void x() {
		// TODO Auto-generated method stub
	}
}
class CC extends AA{
	public void y(){
		System.out.println("자식 y");
	}
	@Override
	public void x() {
		// TODO Auto-generated method stub
	}
}
public class Polymorphism2 {
	public void play(AA k){
		k.x();
	}
	public void play(AA[] k){
		for(AA a : k){
			a.x();
		}
	}
	public static void main(String[] args){
//		AA a1 = new AA();
		BB a2 = new BB();
		CC a3 = new CC();
		
		AA[] list = new AA[2];
		list[0] = new BB();
		list[1] = new CC();
		
		Polymorphism2 p = new Polymorphism2();
		p.play(a2);
		p.play(a3);
		p.play(list);
	}
}
