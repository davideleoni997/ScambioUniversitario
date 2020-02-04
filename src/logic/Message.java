package logic;

import java.sql.Date;

public class Message {
	private Integer sender;
	private Integer to;
	private String desc;
	private Date date;
	private String senderName;
	
	public Message(Integer from, Integer to, String desc, Date date) {
		super();
		this.sender = from;
		this.to = to;
		this.desc = desc;
		this.date = date;
	}


	public void setFrom(Integer from) {
		this.sender = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	
	
}
