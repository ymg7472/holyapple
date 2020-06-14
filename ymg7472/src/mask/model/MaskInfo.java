package mask.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaskInfo {
	private String code;
	private String name;
	private String addr;
	private String type;
	private String lat;
	private String lng;
	private String created_at;
	private String remain_stat;
	private String stock_at;
	@Override
	public String toString() {
		return "Maskinfo [code=" + code + ", name=" + name + ", addr=" + addr + ", type=" + type + ", lat=" + lat
				+ ", lng=" + lng + ", created_at=" + created_at + ", remain_stat=" + remain_stat + ", stock_at="
				+ stock_at + "]";
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
	public void setAll(ResultSet rs) throws SQLException{
		this.code = rs.getString("code");
		this.name = rs.getString("name");
		this.addr = rs.getString("addr");
		this.type = rs.getString("type");
		this.lat = rs.getString("lat");
		this.lng = rs.getString("lng");
		this.created_at = rs.getString("created_at");
		this.remain_stat = rs.getString("remain_stat");
		this.stock_at = rs.getString("stock_at");

	}
}
