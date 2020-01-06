package logic;

public class Report {
	private Integer id;
	private Integer user_id;
	private String desc;
	
	public Report(Integer id, Integer user_id, String desc) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
