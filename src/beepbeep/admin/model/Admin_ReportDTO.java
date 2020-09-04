package beepbeep.admin.model;


import java.util.Date;

public class Admin_ReportDTO {
	private int report_seq;
	private int report_type;
	private String report_name;
	private String contents;
	private String id;
	private Date dates;
	private int condition; 
	private String name;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getReport_type() {
		return report_type;
	}
	public void setReport_type(int report_type) {
		this.report_type = report_type;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getReport_seq() {
		return report_seq;
	}
	public void setReport_seq(int report_seq) {
		this.report_seq = report_seq;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Admin_ReportDTO(int report_seq, int report_type, String report_name, String contents, String id, Date dates,
			int condition, String name) {
		super();
		this.report_seq = report_seq;
		this.report_type = report_type;
		this.report_name = report_name;
		this.contents = contents;
		this.id = id;
		this.dates = dates;
		this.condition = condition;
		this.name=name;
	}
	public Admin_ReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
