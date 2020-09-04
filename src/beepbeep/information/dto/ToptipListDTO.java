package beepbeep.information.dto;

public class ToptipListDTO {
	private int tt_code;
	private String pic;
	private String title;
	private String dates;
	private int views;
	
	public int getTt_code() {
		return tt_code;
	}
	public void setTt_code(int tt_code) {
		this.tt_code = tt_code;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
}
