package beepbeep.community.dto;

import java.util.Date;
import java.util.List;

public class FreeCommentDTO {
	//private String title;
	private int fcoment_seq;
	private String id;
	private int fboard_seq;
	private int likes;
	private Date dates;
	private String sdates;
	private int ref;
	private int step;
	private String contents; // 댓글 내용
	private int freply_seq;
	private List<FreeCommentDTO> ccoList;
	//
	private String photo;
	private String date_ajax;
	
	private int id_alikes;
	
	
	
	
	public int getId_alikes() {
		return id_alikes;
	}
	public void setId_alikes(int id_alikes) {
		this.id_alikes = id_alikes;
	}
	public List<FreeCommentDTO> getCcoList() {
		return ccoList;
	}
	public void setCcoList(List<FreeCommentDTO> ccoList) {
		this.ccoList = ccoList;
	}
	public int getFreply_seq() {
		return freply_seq;
	}
	public void setFreply_seq(int freply_seq) {
		this.freply_seq = freply_seq;
	}
	public String getSdates() {
		return sdates;
	}
	public void setSdates(String sdates) {
		this.sdates = sdates;
	}
	public String getDate_ajax() {
		return date_ajax;
	}
	public void setDate_ajax(String date_ajax) {
		this.date_ajax = date_ajax;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getFcoment_seq() {
		return fcoment_seq;
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
