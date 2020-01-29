package controller;

import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

import dao.UtenteDao;

public class RegistrationController {

	
	 private static RegistrationController instance;

	    public static RegistrationController getInstance() {
	        if (instance == null)
	            instance = new RegistrationController();
	        return instance;
	    }

	    private RegistrationController() {
	    	//Istanziare controller
	    }
	
	
	
	public boolean registraUtente(String nome,String cognome,String username,String password,Boolean company,String matricola) {
		try {
			return UtenteDao.newStudent(nome, cognome, username, password, company, matricola);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "registra", e);
			return false;
		}
		
	}

	public boolean registraSocieta(String nome,String username,String password,Boolean company,File logo) {
		
		return UtenteDao.newCompany(nome, username, password, company, logo);
			
			
		
	}
}
