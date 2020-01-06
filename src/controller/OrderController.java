package controller;

import dao.OrderDao;
import logic.Order;

public class OrderController {
	private Integer Length;
	private Order list[];
	
	public Order[] getList() {
		return list;
	}

	public void setList(Order[] list) {
		this.list = list;
	}

	public OrderController(Order[] list) {
		super();
		Length = list.length;
		this.list = list;
	}

	public OrderController(String user) {
		this.Length = 0;
		this.getOrdersInfo(user);
	}
	
	public Integer getLength() {
		return Length;
	}

	public void setLength(Integer length) {
		Length = length;
	}
	
	//Prendere info da un DAO
		public Order[] getOrdersInfo(String user) {
			
			
			this.setList(OrderDao.OrderListFromDB(user));
			
			if(list!=null)
			this.setLength(this.list.length);
			else
			this.setLength(0);
			
			return this.list;
			
		}
		
		//Ok
		public Order[] getOrderList(String user) {
			getOrderList(user);
			return this.list;
			}
	
	
		public static Order getOrderDetail(Integer id) {
			Order order = null;
			order = OrderDao.getOrderInfo(id);
			return order;
		}
		
		public static boolean newOrder(int buyer,int seller,String oggetto, int prezzo) {
			return OrderDao.newOrder(buyer, seller, oggetto, prezzo);
		}
}
