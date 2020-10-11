package healthinfo; 

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.log4j.Logger;

/**
 * <pre>
 * healthinfo 
 * healthFinal.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 9. 13.
 * @author : ymg74
 * @version : v1.0
 */
@Entity
public class healthFinal implements Serializable{
	@Id
	private int id;
	private String nalzza;
	private String bigCity;
	private String smallCity;
	private String diseaseName;
	private int how_many;
	
	@Override
	public String toString() {
		return "healthFinal [nalzza=" + nalzza + ", bigCity=" + bigCity + ", smallCity=" + smallCity + ", diseaseName="
				+ diseaseName + ", how_many=" + how_many + "]";
	}
	public String getNalzza() {
		return nalzza;
	}
	public void setNalzza(String nalzza) {
		this.nalzza = nalzza;
	}
	public String getBigCity() {
		return bigCity;
	}
	public void setBigCity(String bigCity) {
		this.bigCity = bigCity;
	}
	public String getSmallCity() {
		return smallCity;
	}
	public void setSmallCity(String smallCity) {
		this.smallCity = smallCity;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public int getHow_may() {
		return how_many;
	}
	public void setHow_may(int case_num) {
		this.how_many = case_num;
	}

}
