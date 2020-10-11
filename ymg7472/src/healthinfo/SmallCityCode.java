package healthinfo; 

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="H_smallcityCode")
public class SmallCityCode implements Serializable{
	public SmallCityCode() {}
		
	@ManyToOne
    @JoinColumn(name="code", nullable=false, columnDefinition = "char(2)")
	BigCityCode bcc;
	

	@OneToMany(mappedBy="scc")
    private transient List<FluStat> items;
	
	@Id
	@Column(name="smallCity")
	String smallCityCode;
	
	@Column(name="location")
	String location;





	public String getSmallCityCode() {
		return smallCityCode;
	}

	public void setSmallCityCode(String smallCityCode) {
		this.smallCityCode = smallCityCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigCityCode getBcc() {
		return bcc;
	}

	public void setBcc(BigCityCode bcc) {
		this.bcc = bcc;
	}

	@Override
	public String toString() {
		return "SmallCityCode [bcc=" + bcc + ", smallCityCode=" + smallCityCode + ", location=" + location + "]";
	}

//	public List<FluStat> getItems() {
//		return items;
//	}
//
//	public void setItems(List<FluStat> items) {
//		this.items = items;
//	}

	
	
	
	
}
