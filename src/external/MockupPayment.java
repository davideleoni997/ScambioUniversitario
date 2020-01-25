package external;

import java.util.Random;

public class MockupPayment {

	public static boolean Payment() {
		
		
		Random r = new Random();
		Integer res = r.nextInt(99);
		
		if(res > 50)
		return true;
		else
		return false;
	}
}
