package beepbeep.main.dto;

import java.util.List;

public class MSubnameDTO {
	private List<String> list;
	private int cnt;
	private String m_sub_name;
	
	public String getM_sub_name() {
		return m_sub_name;
	}
	public void setM_sub_name(String m_sub_name) {
		this.m_sub_name = m_sub_name;
	}
	public MSubnameDTO(List<String> list, int cnt) {
		this.list = list;
		this.cnt = cnt;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
