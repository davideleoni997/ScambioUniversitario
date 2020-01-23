package bean;


import java.io.File;
import java.util.Date;
import java.util.LinkedList;

import javafx.scene.image.Image;

public class InsertionBean {

	private String title;
	private String desc;
	private Date date;
	private Integer price;
	private Integer id;
	private LinkedList<Image> images;
	private String seller;
	private Integer sellerId;
	

	private Boolean sold;
	
	public InsertionBean() {
		
	}
	
	public InsertionBean(String title, String desc, Date date, Integer price, Integer id, LinkedList<Image> images, String seller, Integer sellerId, Boolean sold) {
		super();
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.price = price;
		this.id = id;
		this.images = images;
		this.seller = seller;
		this.sellerId = sellerId;
		this.sold = sold;
	}
	
	public InsertionBean(String title, String desc, Date date, Integer price, Integer id, LinkedList<Image> images, Integer sellerId,
			Boolean sold) {
		super();
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.price = price;
		this.id = id;
		this.images = images;
		this.sellerId = sellerId;
		this.sold = sold;
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

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	public LinkedList<Image> getImages() {
		return images;
	}

	public void setImages(LinkedList<Image> images) {
		this.images = images;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	
	
}
