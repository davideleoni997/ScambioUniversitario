package logic;

import java.sql.Date;

public class Order {
	
	private Item item;
	private Integer id;
	private String seller;
	public String getSeller() {
		return seller;
	}


	public void setSeller(String seller) {
		this.seller = seller;
	}


	public String getBuyer() {
		return buyer;
	}


	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

	private String buyer;
	private Date data;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Item getItem() {
		return this.item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public Order(Item item) {
		super();
		this.item = item;
	}

	public Order() {
	
		this.item = null;
	}

	
}