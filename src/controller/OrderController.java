package controller;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.OrderDao;
import logic.Order;

public class OrderController {
	
	public OrderController() {
		//Constructor OrderController
	}
	
	public void payOrder(Integer id) {
		try {
			OrderDao.payOrder(id);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "payOrder", e);
		
		}
	}
	
	public List<Order> getOrdersInfo(String user) {
			
			
			try {
				return OrderDao.orderListFromDB(user);
			} catch (Exception e) {
				
				Logger.getGlobal().log(Level.WARNING, "getOrders", e);
			
			}
			
			return new LinkedList<>();
			
			
			
			
		}
	
	public List<Order> MyOrders(String user) {
		
		
		try {
			return OrderDao.myOrderFromDB(user);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getOrders", e);
		
		}
		
		return new LinkedList<>();
		
		
		
		
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
