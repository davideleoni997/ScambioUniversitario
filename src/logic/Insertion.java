package logic;

import java.io.File;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import dao.InsertionDao;

public class Insertion {
	private BasicInformations basic;
	private Integer id;
	private List<File> images;
	private Integer seller;
	private Boolean sold;
	private Filters filter;

	public Filters getFilter() {
		return filter;
	}

	public void setFilter(Filters filter) {
		this.filter = filter;
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
	
	public Insertion(Integer id, BasicInformations basic, List<File> images, Integer seller, Filters filter) {
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

	public List<File> getImages() {
		return images;
	}

	public void setImages(List<File> images) {
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

	public void newInsertion() throws ClassNotFoundException, SQLException, IOException {
		InsertionDao.newInsertion(basic, images, seller, filter);
		
	}
	
	public InsertionBean getDetail() {
		try {
			return InsertionDao.getDetail(this.id);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getDetail", e);
		
		}
		return new InsertionBean();
	}
}
