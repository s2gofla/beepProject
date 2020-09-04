package beepbeep.cs.model;

import java.io.Serializable;


public class Cs_FaqDTO implements Serializable {
	private int faq_list_seq;
	private String title;
	private String contents;
	

	

	public int getFaq_list_seq() {
		return faq_list_seq;
	}
	public void setFaq_list_seq(int faq_list_seq) {
		this.faq_list_seq = faq_list_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public Cs_FaqDTO(int faq_list_seq, String title, String contents) {
		super();
		this.faq_list_seq = faq_list_seq;
		this.title = title;
		this.contents = contents;
		
	}
	public Cs_FaqDTO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
