package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.Utente;

public class TestUtente {

	@Test
	public void testPass() {
		Utente x = new Utente("a","b","c","d");
		x.setPassword("b");
		assertEquals("b",x.getPassword());
	}

	@Test
	public void testUser() {
		Utente x = new Utente("a","b","c","d");
		
		x.setUsername("f");
		assertEquals("f",x.getUsername());
	}
	
	@Test
	public void testCognome() {
		Utente x = new Utente("a","b","c","d");
		
		x.setCognome("x");
		assertEquals("x",x.getCognome());
	}
	
	@Test
	public void testNome() {
		Utente x = new Utente("a","b","c","d");
		
		x.setNome("u");
		assertEquals("u",x.getNome());
	}
	
	@Test
	public void testCompany() {
		Utente x = new Utente("a","b","c","d");
		
		x.setCompany(true);
		assertEquals(true,x.isCompany());
	}
}	
