package beepbeep.qna.dto;

import java.util.List;

public class QnaContentView {
	private List<QnaCommentDTO> commentList;
	private QnaListDTO contentDTO;
	private int id_like;
	
	
	
	
	public int getId_like() {
		return id_like;
	}
	public void setId_like(int id_like) {
		this.id_like = id_like;
	}
	public List<QnaCommentDTO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<QnaCommentDTO> commentList) {
		this.commentList = commentList;
	}
	public QnaListDTO getContentDTO() {
		return contentDTO;
	}
	public void setContentDTO(QnaListDTO contentDTO) {
		this.contentDTO = contentDTO;
	}
	
}
