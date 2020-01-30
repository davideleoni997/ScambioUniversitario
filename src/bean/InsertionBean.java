package bean;



import java.util.List;

import javafx.scene.image.Image;
import logic.BasicInformations;
import logic.Filters;

public class InsertionBean {

	private BasicInformations basic;
	private Integer id;
	private List<Image> images;
	private String seller;
	private Integer sellerId;
	private Filters filter;

	private Boolean sold;
	
	public InsertionBean() {
		
	}
	
	public InsertionBean(BasicInformations basic, Integer id, List<Image> images, String seller, Integer sellerId, Boolean sold,Filters filter) {
		super();
		this.basic = basic;
		this.id = id;
		this.images = images;
		this.seller = seller;
		this.sellerId = sellerId;
		this.sold = sold;
		this.filter = filter;
	}
	
	public Filters getFilter() {
		return filter;
	}

	public void setFilter(Filters filter) {
		this.filter = filter;
	}

	public InsertionBean(BasicInformations basic, Integer id, List<Image> images, Integer sellerId,
			Boolean sold) {
		super();
		this.basic = basic;
		this.id = id;
		this.images = images;
		this.sellerId = sellerId;
		this.sold = sold;
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
}
