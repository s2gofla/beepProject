package beepbeep.cs.model;

import java.io.Serializable;
import java.util.Date;

public class Cs_AskDTO implements Serializable {
	private int qna_seq;
	private int qtype_seq;
	private int qna_reply_seq;
	private String id;
	private String title;
	private String attach_file;
	private String contents;
	private String condition;
	private Date dates;
	private int first;
	private String q_name;
	
	
	public String getQ_name() {
		return q_name;
	}
	public void setQ_name(String q_name) {
		this.q_name = q_name;
	}
	public int getQna_reply_seq() {
		return qna_reply_seq;
	}
	public void setQna_reply_seq(int qna_reply_seq) {
		this.qna_reply_seq = qna_reply_seq;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getQna_seq() {
		return qna_seq;
	}
	public void setQna_seq(int qna_seq) {
		this.qna_seq = qna_seq;
	}
	public int getQtype_seq() {
		return qtype_seq;
	}
	public void setQtype_seq(int qtype_seq) {
		this.qtype_seq = qtype_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAttach_file() {
		return attach_file;
	}
	public void setAttach_file(String attach_file) {
		this.attach_file = attach_file;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public Cs_AskDTO(int qna_seq, int qtype_seq, String id, String title, String attach_file, String contents,String condition ,Date dates
			,int qna_reply_seq,int first,String q_name) {
		super();
		this.qna_seq = qna_seq;
		this.qtype_seq = qtype_seq;
		this.id = id;
		this.title = title;
		this.attach_file = attach_file;
		this.contents = contents;
		this.condition= condition;
		this.dates = dates;
		this.qna_reply_seq = qna_reply_seq;
		this.first = first;
		this.q_name = q_name;
	}
	public Cs_AskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
