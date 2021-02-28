package designPattern.compositePattern;
/**
 * <pre>
 * kr.co.swh.lecture.engineering.scene3.composite
 * FileTreatmentException.java
 *
 * 설명 :파일에 Entry를 추가하려고 할 때 발생하는 예외 클래스
 * </pre>
 * 
 * @since : 2017. 10. 3.
 * @author : tobby48
 * @version : v1.0
 */
public class FileTreatmentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileTreatmentException(){

	}
	public FileTreatmentException(String msg){
		super(msg);
	}
}