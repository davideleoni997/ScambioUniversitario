package controller;

import java.io.File;

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
		return UtenteDao.newStudent(nome, cognome, username, password, company, matricola);
		
	}

	public boolean registraSocieta(String nome,String username,String password,Boolean company,File logo) {
		
		return UtenteDao.newCompany(nome, username, password, company, logo);
			
			
		
	}
}
