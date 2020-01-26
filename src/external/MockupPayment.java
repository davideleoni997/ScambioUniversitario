package external;

import java.util.Random;

public class MockupPayment {

	private MockupPayment() {
		throw new IllegalStateException("Mockup external class");
	}
	
	public static boolean payment() {
		
		
		Random r = new Random();
		Integer res = r.nextInt(99);
		
		return (res > 50) ;
	}
}
