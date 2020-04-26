package mask.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionRegistry;
import org.hibernate.mapping.Selectable;



@Entity
@Table(name="maskstores")
public class Stores implements Serializable{

	
	@Column(name="addr")
	String addr = "missing";
	
						
	@Id
	@Column(name="code")
	String code;
	
	@Column(name="lat")
	String lat = "missing";
	
	@Column(name="lng")
	String lng = "missing";
	
	@Column(name="name")
	String name = "missing";
	
	@Column(name="type")
	String type = "missing";
	
	@Override
	public String toString() {
		return "Stores [code=" + code + ", name=" + name + ", addr=" + addr + ", type=" + type + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
}
