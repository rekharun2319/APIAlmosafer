package pojo;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class PriceField {	


	private Map<String, Object> dynamicDate = new HashMap<>();
	private Map<String, Object> dynamicDate1 = new HashMap<>();
	private Map<String, Object> dynamicDate2 = new HashMap<>();
	
	
	public Map<String, Object> getDynamicDate1() {
		return dynamicDate1;
	}
	@JsonAnySetter
	public void setDynamicDate1(Map<String, Object> dynamicDate1) {
		this.dynamicDate1 = dynamicDate1;
	}
	public Map<String, Object> getDynamicDate2() {
		return dynamicDate2;
	}
	@JsonAnySetter
	public void setDynamicDate2(Map<String, Object> dynamicDate2) {
		this.dynamicDate2 = dynamicDate2;
	}
	public Map<String, Object> getDynamicDate() {
		return dynamicDate;
	}
	@JsonAnySetter
	public void setDynamicDate(Map<String, Object> dynamicDate) {
		this.dynamicDate = dynamicDate;
	}

}



