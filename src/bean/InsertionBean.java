package bean;


import java.util.Date;

import javafx.scene.image.Image;

public class InsertionBean {

	private String title;
	private String desc;
	private Date date;
	private Integer price;
	private Integer id;
	private Image image1;
	private Image image2;
	private Image image3;
	private String seller;
	private Integer sellerId;
	

	private Boolean sold;
	
	public InsertionBean() {
		
	}
	
	public InsertionBean(String title, String desc, Date date, Integer price, Integer id, Image image1, Image image2,
			Image image3, String seller, Integer sellerId, Boolean sold) {
		super();
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.price = price;
		this.id = id;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.seller = seller;
		this.sellerId = sellerId;
		this.sold = sold;
	}
	
	public InsertionBean(String title, String desc, Date date, Integer price, Integer id, Image image1,Image image2,Image image3, Integer sellerId,
			Boolean sold) {
		super();
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.price = price;
		this.id = id;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
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

	public Image getImage1() {
		return image1;
	}

	public void setImage1(Image image1) {
		this.image1 = image1;
	}

	public Image getImage2() {
		return image2;
	}

	public void setImage2(Image image2) {
		this.image2 = image2;
	}

	public Image getImage3() {
		return image3;
	}

	public void setImage3(Image image3) {
		this.image3 = image3;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	
	
}
