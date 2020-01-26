package logic;

public class Filters {
	
	public enum Distance{
		FAR,
		NEAR
	}
	
	public enum Date{
		OLD,
		NEW
	}
	
	
	
	private Date date;
	private String university;
	private Distance distance;
	private String position;
	private Boolean book;
	private Boolean notes;
	private String city;
	private String subject;

	public Filters() {
		date = Date.NEW;
		distance = Distance.NEAR;
		book = true;
		notes = true;
	}

	public Filters(Date date, String university, Distance distance, String position, Boolean book, Boolean notes) {
		super();
		this.date = date;
		this.university = university;
		this.distance = distance;
		this.position = position;
	
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	public Boolean getBook() {
		return book;
	}

	public void setBook(Boolean book) {
		this.book = book;
	}

	public Boolean getNotes() {
		return notes;
	}

	public void setNotes(Boolean notes) {
		this.notes = notes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
