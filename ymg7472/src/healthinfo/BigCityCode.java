package healthinfo; 

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 * <pre>
 * healthinfo 
 * SmallCityCode.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 8. 30.
 * @author : ymg74
 * @version : v1.0
 */
@Entity
@Table(name="H_bigcityCode")
public class BigCityCode implements Serializable{
	public BigCityCode() {}
	@Id
	@Column(name="code", columnDefinition = "char(2)")
	String code;
	
	@OneToMany(mappedBy="bcc")
    private transient List<SmallCityCode> items;
	
	@Column(name="location")
	String location;

	@Override
	public String toString() {
		return "BigCityCode [code=" + code + ", location=" + location + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<SmallCityCode> getItems() {
		return items;
	}

	public void setItems(List<SmallCityCode> items) {
		this.items = items;
	}

	
	
	
}
