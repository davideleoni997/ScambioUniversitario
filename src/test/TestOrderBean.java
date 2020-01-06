package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bean.OrderBean;
import controller.OrderController;

public class TestOrderBean {

	@Test
	public void testGetOrders() {
		OrderBean oB = new OrderBean("kew");
		assertEquals("<li>\nLibro</li>\n",oB.getOrders("kew"));
	}

	
	
}
