package healthinfo; 

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.log4j.Logger;

/**
 * <pre>
 * healthinfo 
 * FluStat.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 8. 30.
 * @author : ymg74
 * @version : v1.0
 */
@Entity
@Table(name="H_FluStat")

public class FluStat implements Serializable{
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	int Id;
	
	
	@ManyToOne
    @JoinColumn(name="diseaseCode", nullable=false)
	Disease dis;
	
	@ManyToOne
    @JoinColumn(name="smallCity", nullable=true, columnDefinition = "char(5)")
	SmallCityCode scc;
	
	
	@Column(name="sd", columnDefinition="char(8)")
	String date;
	
	@Column(name="how_many")
	int case1;

	;

	public SmallCityCode getScc() {
		return scc;
	}

	public void setScc(SmallCityCode scc) {
		this.scc = scc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCase1() {
		return case1;
	}

	public void setCase1(int case1) {
		this.case1 = case1;
	}


//toString 에 없었네요 아하
	public Disease getDis() {
		return dis;
	}

	public void setDis(Disease dis) {
		this.dis = dis;
	}

	@Override
	public String toString() {
		return "FluStat [Id=" + Id + ", dis=" + dis + ", scc=" + scc + ", date=" + date + ", case1=" + case1 + "]";
	}




}
