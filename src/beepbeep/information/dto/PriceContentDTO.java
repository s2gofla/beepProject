package beepbeep.information.dto;

public class PriceContentDTO {
	
	private int min_price;
	private int avg_price;
	private int max_price;
	private int pinfo_code;
	private String pinfo_treatment;

	public int getPinfo_code() {
		return pinfo_code;
	}
	public void setPinfo_code(int pinfo_code) {
		this.pinfo_code = pinfo_code;
	}
	public String getPinfo_treatment() {
		return pinfo_treatment;
	}
	public void setPinfo_treatment(String pinfo_treatment) {
		this.pinfo_treatment = pinfo_treatment;
	}
	public int getMin_price() {
		return min_price;
	}
	public void setMin_price(int min_price) {
		this.min_price = min_price;
	}
	public int getAvg_price() {
		return avg_price;
	}
	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}
	public int getMax_price() {
		return max_price;
	}
	public void setMax_price(int max_price) {
		this.max_price = max_price;
	}
	
}
 