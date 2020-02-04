package bean;

import java.util.List;

import controller.OrderController;
import logic.Order;


//Rivedere per singolo ordine, non lista
public class OrderBean {
	private Integer length;
	private List<Order> list;
	private String user;
	private Integer id;
	
	public List<Order> getList() {
		return list;
	}

	public void setList(List<Order> list) {
		this.list = list;
		
			this.setLength(this.getLength());
	
	}


	public OrderBean() {
		this.length = 0;
		this.list = null;
		
	}

	public Integer getLength() {
		return length;
	}
	
	private void setLength(Integer length) {
		this.length= length;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		OrderController oC = OrderController.getInstance();
		this.setList(oC.getOrdersInfo(this.user));
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
