package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bean.OrderBean;

public class TestOrderBean {

	@Test
	public void testGetOrders() {
		OrderBean oB = new OrderBean();
		assertEquals("<li>\nLibro</li>\n",oB.getOrders());
	}

	
	
}
