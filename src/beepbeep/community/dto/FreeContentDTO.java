package beepbeep.community.dto;

import java.util.Date;

public class FreeContentDTO {
	//private String title;
	private int fcoment_seq;
	private String id;
	private int fboard_seq;
	private int likes;
	private Date dates;
	private String sdates;
	private int ref;
	private int step;
	private String com_contents; // 댓글 내용
	//
	
	
	
	public int getFcoment_seq() {
		return fcoment_seq;
	}
	public String getSdates() {
		return sdates;
	}
	public void setSdates(String sdates) {
		this.sdates = sdates;
	}
	public String getCom_contents() {
		return com_contents;
	}
	public void setCom_contents(String com_contents) {
		this.com_contents = com_contents;
	}
	public void setFcoment_seq(int fcoment_seq) {
		this.fcoment_seq = fcoment_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFboard_seq() {
		return fboard_seq;
	}
	public void setFboard_seq(int fboard_seq) {
		this.fboard_seq = fboard_seq;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}

	
	
	
}
