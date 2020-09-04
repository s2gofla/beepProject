package beepbeep.community.dto;

import java.util.Date;
import java.util.List;

public class DonationContentDTO {
	private int dseq;
	private String header;
	private String id;
	private String title;
	private int likes;
	private Date dates;
	private String sdates;
	private int ref;
	private int step;
	private int views;
	
	private String contents;
	
	private int seq; // 댓글 기본키
	
	private int doreply_seq;
	private List<DonationContentDTO> ccoList;

	private String photo;
	private int id_alikes;
	
	private int dpic_seq;
	private String pic;
	
	
	

	public DonationContentDTO() {
		super();
	}
	public DonationContentDTO(String header, String id, String title, String contents, String pic) {
		super();
		this.header = header;
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.pic = pic;
	}
	public int getDpic_seq() {
		return dpic_seq;
	}
	public void setDpic_seq(int dpic_seq) {
		this.dpic_seq = dpic_seq;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getDseq() {
		return dseq;
	}
	public void setDseq(int dseq) {
		this.dseq = dseq;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
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
	public String getSdates() {
		return sdates;
	}
	public void setSdates(String sdates) {
		this.sdates = sdates;
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
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getDoreply_seq() {
		return doreply_seq;
	}
	public void setDoreply_seq(int doreply_seq) {
		this.doreply_seq = doreply_seq;
	}
	public List<DonationContentDTO> getCcoList() {
		return ccoList;
	}
	public void setCcoList(List<DonationContentDTO> ccoList) {
		this.ccoList = ccoList;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getId_alikes() {
		return id_alikes;
	}
	public void setId_alikes(int id_alikes) {
		this.id_alikes = id_alikes;
	}
	
	
	
}
