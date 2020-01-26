package logic;

public class Report {
	private Integer id;
	private Integer insId;
	private String desc;
	private Integer reporter;
	
	public Report(Integer id, Integer userId, String desc,Integer reporter) {
		super();
		this.id = id;
		this.insId = userId;
		this.desc = desc;
		this.reporter = reporter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInsId() {
		return insId;
	}

	public void setInsId(Integer userId) {
		this.insId = userId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getReporter() {
		return reporter;
	}

	public void setReporter(Integer reporter) {
		this.reporter = reporter;
	}
	
	
}
