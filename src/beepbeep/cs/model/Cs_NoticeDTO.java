package beepbeep.cs.model;

import java.io.Serializable;
import java.util.Date;

public class Cs_NoticeDTO implements Serializable{

	private int notice_seq;
	private String id;
	private String title;
	private Date dates;
	private int views;
	private String contents;
	private boolean newmark;
	private int ref;



	public int getRef() {
		return ref;
	}


	public void setRef(int ref) {
		this.ref = ref;
	}


	public boolean isNewmark() {
		return newmark;
	}


	public int getNotice_seq() {
		return notice_seq;
	}


	public void setNotice_seq(int notice_seq) {
		this.notice_seq = notice_seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getDates() {
		return dates;
	}


	public void setDates(Date dates) {
		this.dates = dates;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public void setNewmark(boolean newmark) {
		this.newmark = newmark;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}

	public Cs_NoticeDTO(int notice_seq, String id, String title, Date dates, int views, String contents ,int ref) {
		super();
		this.notice_seq = notice_seq;
		this.id = id;
		this.title = title;
		this.dates = dates;
		this.views = views;
		this.contents = contents;
		this.ref = ref;
	}
	public Cs_NoticeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


}
