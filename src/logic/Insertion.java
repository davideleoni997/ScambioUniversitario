package logic;

import java.sql.Blob;

public class Insertion {
	private BasicInformations basic;
	private Integer id;
	private Blob[] images;
	private Integer seller;
	private Boolean sold;
	private Filters filter;

	public Filters getFilter() {
		return filter;
	}

	public void setFilter(Filters filter) {
		this.filter = filter;
	}

	public Boolean getSold() {
		return sold;
	}

	public Insertion() {
		super();
	}
	
	public Insertion(BasicInformations basic) {
		super();
		this.basic = basic;
	}
	
	public Insertion(Integer id, BasicInformations basic) {
		super();
		this.id  = id;
		this.basic = basic;
	}
	
	public Insertion(Integer id, BasicInformations basic, Blob[] images, Integer seller, Filters filter) {
		super();
		this.id  = id;
		this.basic = basic;
		this.images=images;
		this.seller = seller;
		this.filter = filter;
	}
	
	public BasicInformations getBasic() {
		return basic;
	}

	public void setBasic(BasicInformations basic) {
		this.basic = basic;
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
