package beepbeep.member.dto;

public class MypageDTO {
	

	private String grade;
	
	private String hBookSeq;
	private String mBookSeq;
	
	private String hReviewSeq;
	private String mReviewSeq;
	
	private String qnaWSeq;
	private String qnaCSeq;
	
	private String tipWSeq;
	private String tipCSeq;
	
	private String donationWSeq;
	private String donationCSeq;
	
	private String freeWSeq;
	private String freeCSeq;

	private String reportWSeq;
	private String reportCSeq;

	//문의하기
	private String questionWSeq;
	private String questionCSeq;


	
	
	
	
	public MypageDTO() {
	
	}

	public MypageDTO(  String grade , String hBookSeq, String mBookSeq, String hReviewSeq, String mReviewSeq, String qnaWSeq,
			String qnaCSeq, String tipWSeq, String tipCSeq, String donationWSeq, String donationCSeq, String freeWSeq,
			String freeCSeq, String reportWSeq, String reportCSeq, String questionWSeq, String questionCSeq) {
	
	
		this.hBookSeq = hBookSeq;
		this.mBookSeq = mBookSeq;
		this.hReviewSeq = hReviewSeq;
		this.mReviewSeq = mReviewSeq;
		this.qnaWSeq = qnaWSeq;
		this.qnaCSeq = qnaCSeq;
		this.tipWSeq = tipWSeq;
		this.tipCSeq = tipCSeq;
		this.donationWSeq = donationWSeq;
		this.donationCSeq = donationCSeq;
		this.freeWSeq = freeWSeq;
		this.freeCSeq = freeCSeq;
		this.reportWSeq = reportWSeq;
		this.reportCSeq = reportCSeq;
		this.questionWSeq = questionWSeq;
		this.questionCSeq = questionCSeq;
	}

	public String gethBookSeq() {
		return hBookSeq;
	}
	public void sethBookSeq(String hBookSeq) {
		this.hBookSeq = hBookSeq;
	}
	public String getmBookSeq() {
		return mBookSeq;
	}
	public void setmBookSeq(String mBookSeq) {
		this.mBookSeq = mBookSeq;
	}
	public String gethReviewSeq() {
		return hReviewSeq;
	}
	public void sethReviewSeq(String hReviewSeq) {
		this.hReviewSeq = hReviewSeq;
	}
	public String getmReviewSeq() {
		return mReviewSeq;
	}
	public void setmReviewSeq(String mReviewSeq) {
		this.mReviewSeq = mReviewSeq;
	}
	public String getQnaWSeq() {
		return qnaWSeq;
	}
	public void setQnaWSeq(String qnaWSeq) {
		this.qnaWSeq = qnaWSeq;
	}
	public String getQnaCSeq() {
		return qnaCSeq;
	}
	public void setQnaCSeq(String qnaCSeq) {
		this.qnaCSeq = qnaCSeq;
	}
	public String getTipWSeq() {
		return tipWSeq;
	}
	public void setTipWSeq(String tipWSeq) {
		this.tipWSeq = tipWSeq;
	}
	public String getTipCSeq() {
		return tipCSeq;
	}
	public void setTipCSeq(String tipCSeq) {
		this.tipCSeq = tipCSeq;
	}
	public String getDonationWSeq() {
		return donationWSeq;
	}
	public void setDonationWSeq(String donationWSeq) {
		this.donationWSeq = donationWSeq;
	}
	public String getDonationCSeq() {
		return donationCSeq;
	}
	public void setDonationCSeq(String donationCSeq) {
		this.donationCSeq = donationCSeq;
	}
	public String getFreeWSeq() {
		return freeWSeq;
	}
	public void setFreeWSeq(String freeWSeq) {
		this.freeWSeq = freeWSeq;
	}
	public String getFreeCSeq() {
		return freeCSeq;
	}
	public void setFreeCSeq(String freeCSeq) {
		this.freeCSeq = freeCSeq;
	}
	public String getReportWSeq() {
		return reportWSeq;
	}
	public void setReportWSeq(String reportWSeq) {
		this.reportWSeq = reportWSeq;
	}
	public String getReportCSeq() {
		return reportCSeq;
	}
	public void setReportCSeq(String reportCSeq) {
		this.reportCSeq = reportCSeq;
	}
	public String getQuestionWSeq() {
		return questionWSeq;
	}
	public void setQuestionWSeq(String questionWSeq) {
		this.questionWSeq = questionWSeq;
	}
	public String getQuestionCSeq() {
		return questionCSeq;
	}
	public void setQuestionCSeq(String questionCSeq) {
		this.questionCSeq = questionCSeq;
	}
	
	

}
