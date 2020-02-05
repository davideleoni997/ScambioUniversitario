package controller;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.OrderDao;
import logic.Order;

public class OrderController {
	//Singleton controller with responsability over the entity Order
	private static OrderController instance;

    public static OrderController getInstance() {
        if (instance == null)
            instance = new OrderController();
        return instance;
    }
	
	private OrderController() {
		//Constructor OrderController
	}
	
	public void payOrder(Integer id) {
		//Method used to pay an order with a corresponding id
		try {
			OrderDao.payOrder(id);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "payOrder", e);
		
		}
	}
	
	public List<Order> getOrdersInfo(String user) {
			
			//Get a list of orders using a username
			try {
				return OrderDao.orderListFromDB(user);
			} catch (Exception e) {
				
				Logger.getGlobal().log(Level.WARNING, "getOrders", e);
			
			}
			
			return new LinkedList<>();
			
			
			
			
		}
	

	
	
	public static Order getOrderDetail(Integer id) {
		//Get an order object using its id
			Order order = null;
			try {
				order = OrderDao.getOrderInfo(id);
			} catch (Exception e) {
				
				Logger.getGlobal().log(Level.WARNING, "getMessage", e);
			
			}
			return order;
		}
		
		public static boolean newOrder(int buyer,int seller,String oggetto,Integer inserzione, int prezzo) {
			//method to create a newOrder in the DB
			return OrderDao.buyBook(buyer, seller,inserzione, oggetto, prezzo);
		}
}
