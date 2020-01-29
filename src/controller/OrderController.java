package controller;


import java.util.logging.Level;
import java.util.logging.Logger;

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
		list = new Order[0];
	}
	
	public void payOrder(Integer id) {
		try {
			OrderDao.payOrder(id);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "payOrder", e);
		
		}
	}
	
	public Order[] getOrdersInfo(String user) {
			
			
			try {
				this.setList(OrderDao.orderListFromDB(user));
			} catch (Exception e) {
				
				Logger.getGlobal().log(Level.WARNING, "getOrders", e);
			
			}
			
			if(list!=null)
				this.setLength(this.list.length);
			else
				this.setLength(0);
			
			return this.list;
			
		}
	
	
	public static Order getOrderDetail(Integer id) {
			Order order = null;
			try {
				order = OrderDao.getOrderInfo(id);
			} catch (Exception e) {
				
				Logger.getGlobal().log(Level.WARNING, "getMessage", e);
			
			}
			return order;
		}
		
		public static boolean newOrder(int buyer,int seller,String oggetto,Integer inserzione, int prezzo) {
			return OrderDao.buyBook(buyer, seller,inserzione, oggetto, prezzo);
		}
}
