package beepbeep.community.dto;

import java.util.Date;
import java.util.List;

public class DiseaseContentDTO {
	private int dtip_seq;
	private int m_sub_seq;
	private int m_sub_name;
	private String id;
	private String title;
	private int likes;
	private Date dates;
	private String sdates;
	private int ref;
	private int step;
	private int views;

	private String hashtag;
	private String[] tagArr;
	
	private String contents;
	
	private int seq; // 댓글 기본키
	
	private int direply_seq;
	private List<DiseaseContentDTO> ccoList;

	private String photo;
	private int id_alikes;
	
	
	
	
	public int getM_sub_name() {
		return m_sub_name;
	}

	public void setM_sub_name(int m_sub_name) {
		this.m_sub_name = m_sub_name;
	}

	public int getDireply_seq() {
		return direply_seq;
	}

	public void setDireply_seq(int direply_seq) {
		this.direply_seq = direply_seq;
	}

	public List<DiseaseContentDTO> getCcoList() {
		return ccoList;
	}

	public void setCcoList(List<DiseaseContentDTO> ccoList) {
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

	public int getDtip_seq() {
		return dtip_seq;
	}

	public void setDtip_seq(int dtip_seq) {
		this.dtip_seq = dtip_seq;
	}

	public int getM_sub_seq() {
		return m_sub_seq;
	}

	public void setM_sub_seq(int m_sub_seq) {
		this.m_sub_seq = m_sub_seq;
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

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String[] getTagArr() {
		return tagArr;
	}

	public void setTagArr(String[] tagArr) {
		this.tagArr = tagArr;
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

	
	
}
