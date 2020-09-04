package beepbeep.information.dto;

import java.util.List;

public class TTPicDTO {
	private List<String> list;
	private String title;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TTPicDTO(List<String> list, String title) {
		this.list = list;
		this.title = title;
	}
	
	
}
