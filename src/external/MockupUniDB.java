package external;

public class MockupUniDB {

	private MockupUniDB() {
		throw new IllegalStateException("Mockup external class");
	}
	
	public static Boolean isUserInDB(String matricola, String nome, String cognome) {
			String test = matricola + nome + cognome;
			return test.charAt(1) == '1';
			
	}
}
