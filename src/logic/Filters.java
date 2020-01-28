package logic;

public class Filters {
	
	
	
	public enum Date{
		OLD,
		NEW
	}
	
	
	
	private Date date;
	private String university;
	
	private Boolean book;
	private Boolean notes;
	private String city;
	private String subject;

	public Filters() {
		date = Date.NEW;
		
		book = true;
		notes = true;
		university = "";
		city = "";
		subject = "";
	}

	public Filters(Date date, String university) {
		super();
		this.date = date;
		this.university = university;
	
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

}
