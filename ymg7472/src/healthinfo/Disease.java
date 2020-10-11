package healthinfo; 

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.log4j.Logger;

/**
 * <pre>
 * healthinfo 
 * Disease.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 9. 13.
 * @author : ymg74
 * @version : v1.0
 */
@Entity
@Table(name="H_diseaseCode")

public class Disease implements Serializable{
	
	public Disease() {
		
	}


	

	@Id
	@Column(name="diseaseCode")
	private int code;
	
	@Column(name="diseaseName")
	private String name;

	@Override
	public String toString() {
		return "Disease [code=" + code + ", name=" + name + "]";
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
