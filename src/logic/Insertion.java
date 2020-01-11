package logic;

import java.sql.Blob;
import java.util.Date;

public class Insertion {
	private String title;
	private String desc;
	private Date date;
	private Integer price;
	private Integer id;
	private Blob[] images;
	private Integer seller;
	private Boolean sold;

	public Insertion() {
		super();
	}
	
	public Insertion(String title, String desc, Date date, Integer price) {
		super();
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.price = price;
	}
	
	public Insertion(Integer id, String title, String desc, Date date, Integer price) {
		super();
		this.id  = id;
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.price = price;
	}
	
	public Insertion(Integer id, String title, String desc, Date date, Integer price,Blob[] images, Integer seller) {
		super();
		this.id  = id;
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.price = price;
		this.images=images;
		this.seller = seller;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Blob[] getImages() {
		return images;
	}

	public void setImages(Blob[] images) {
		this.images = images;
	}

	public Integer getSeller() {
		return seller;
	}

	public void setSeller(Integer seller) {
		this.seller = seller;
	}

	public Boolean isSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}
	
	
}
