package mask.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="masksales")
public class Sales implements Serializable{
	@Id
	@Column(name="code")
	String code;
	
	@Column(name="created_at")
	String created_at = "missing";
	
	@Column(name="remain_stat")
	String remain_stat = "missing";
	
	@Column(name="stock_at")
	String stock_at = "missing";
	
	@Override
	public String toString() {
		return "Stores [code=" + code + ", created_at=" + created_at + ", remain_stat=" + remain_stat + ", stock_at="
				+ stock_at + "]";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getRemain_stat() {
		return remain_stat;
	}
	public void setRemain_stat(String remain_stat) {
		this.remain_stat = remain_stat;
	}
	public String getStock_at() {
		return stock_at;
	}
	public void setStock_at(String stock_at) {
		this.stock_at = stock_at;
	}
}