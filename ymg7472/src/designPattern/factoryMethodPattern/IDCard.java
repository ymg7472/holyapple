package designPattern.factoryMethodPattern;

/**
 * <pre>
 * kr.co.swh.lecture.engineering.scene3.factory 
 * IDCard.java
 *
 * 설명 :메소드 use()를 구현하고 있는 클래스
 * </pre>
 * 
 * @since : 2017. 10. 3.
 * @author : tobby48
 * @version : v1.0
 */
public class IDCard extends Product {
	private String owner;

	public IDCard(String owner){
		System.out.println(owner + "의 카드를 만듭니다.");
		this.owner = owner;
	}
	public void use(){
		System.out.println(owner + "의 카드를 사용합니다.");
	}
	public String getOwner(){
		return owner;
	}
}