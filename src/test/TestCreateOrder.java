package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import bean.InsertionBean;
import controller.InsertionController;
import controller.OrderController;

import logic.Filters;


class TestCreateOrder {

	@Test
	void testCreateOrder() {
		//Test of the function that creates a new order for an item, only if this has not already bought,
		//If the item has been already bought the function will return false and the item that we were trying to buy will
		//have the parameter sold set to true; if the function goes through and a new order is created that means that the insertion
		//sold parameter was not true before.
		//Creator : Davide Leoni
		final Integer INS_NUMBER = 3;
		
		InsertionController ic = InsertionController.getInstance();
		Filters filters = new Filters();
		List<InsertionBean> list = ic.getResearchResults("", filters);
		
		if(OrderController.newOrder(1, list.get(INS_NUMBER).getSellerId(), list.get(INS_NUMBER).getBasic().getTitle(), list.get(INS_NUMBER).getId(), list.get(INS_NUMBER).getBasic().getPrice()))
			assertFalse(list.get(INS_NUMBER).getSold());
		else
			assertTrue(list.get(INS_NUMBER).getSold());
		
	}

}
