package controller;

import dao.OrderDao;
import logic.Order;

public class OrderController {
	private Integer length;
	private Order[] list;
	
	public Order[] getList() {
		return list;
	}

	public void setList(Order[] list) {
		this.list = list;
	}

	public OrderController(Order[] list) {
		super();
		length = list.length;
		this.list = list;
	}

	public OrderController(String user) {
		this.length = 0;
		this.getOrdersInfo(user);
	}
	
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	public OrderController() {
		
	}
	
	public void payOrder(Integer id) {
		OrderDao.payOrder(id);
	}
	
	public Order[] getOrdersInfo(String user) {
			
			
			this.setList(OrderDao.orderListFromDB(user));
			
			if(list!=null)
				this.setLength(this.list.length);
			else
				this.setLength(0);
			
			return this.list;
			
		}
	
	
	public static Order getOrderDetail(Integer id) {
			Order order = null;
			order = OrderDao.getOrderInfo(id);
			return order;
		}
		
		public static boolean newOrder(int buyer,int seller,String oggetto,Integer inserzione, int prezzo) {
			return OrderDao.buyBook(buyer, seller,inserzione, oggetto, prezzo);
		}
}
