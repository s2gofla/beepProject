package beepbeep.review.model;

import java.util.ArrayList;

import beepbeep.command.CategoryDTO;

public class HinfoDTO {
	
	private int h_code;
	private String h_name;
	private String h_address;
	private String h_tel;
	private String h_link;
	private ArrayList<SpecialDTO> special;
	private ArrayList<CategoryDTO> sub;
	private HtimeDTO htime;
	
	private int reviewer;
	private double star_score;
	private int bookmark_count;
	private int isBookMark;
	
	
	
	public int getIsBookMark() {
		return isBookMark;
	}
	public void setIsBookMark(int isBookMark) {
		this.isBookMark = isBookMark;
	}
	public HtimeDTO getHtime() {
		return htime;
	}
	public void setHtime(HtimeDTO htime) {
		this.htime = htime;
	}
	public String getH_link() {
		return h_link;
	}
	public void setH_link(String h_link) {
		this.h_link = h_link;
	}
	public ArrayList<SpecialDTO> getSpecial() {
		return special;
	}
	public void setSpecial(ArrayList<SpecialDTO> special) {
		this.special = special;
	}
	public ArrayList<CategoryDTO> getSub() {
		return sub;
	}
	public void setSub(ArrayList<CategoryDTO> sub) {
		this.sub = sub;
	}

	public int getReviewer() {
		return reviewer;
	}
	public void setReviewer(int reviewer) {
		this.reviewer = reviewer;
	}
	public int getBookmark_count() {
		return bookmark_count;
	}
	public void setBookmark_count(int bookmark_count) {
		this.bookmark_count = bookmark_count;
	}
	public int getH_code() {
		return h_code;
	}
	public void setH_code(int h_code) {
		this.h_code = h_code;
	}
	public String getH_name() {
		return h_name;
	}
	public void setH_name(String h_name) {
		this.h_name = h_name;
	}
	public String getH_address() {
		return h_address;
	}
	public void setH_address(String h_address) {
		this.h_address = h_address;
	}
	public String getH_tel() {
		return h_tel;
	}
	public void setH_tel(String h_tel) {
		this.h_tel = h_tel;
	}

	public double getStar_score() {
		return star_score;
	}
	public void setStar_score(double star_score) {
		this.star_score = star_score;
	}




}
