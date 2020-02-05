package controller;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.UtenteDao;
import external.MockupUniDB;

public class RegistrationController {

	//Singleton of instance controller, has responsability on the use case Registration
	 private static RegistrationController instance;

	    public static RegistrationController getInstance() {
	        if (instance == null)
	            instance = new RegistrationController();
	        return instance;
	    }

	    private RegistrationController() {
	    	//constructor
	    }
	
	
	
	public boolean registraUtente(String nome,String cognome,String username,String password,Boolean company,String matricola) {
		//Method to register a user of the kind "student"
		try {
			//Method representing an external DB checking if the enrollment number exists
			//In this case it is a black box
			if(MockupUniDB.isUserInDB(matricola, nome, cognome)) {
				//If everything goes fine returns true otherwise false
				return UtenteDao.newStudent(nome, cognome, username, password, company, matricola);
			}
			else
				return false;
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "registraUser", e);
			return false;
		}
		
	}

	public boolean registraSocieta(String nome,String username,String password,Boolean company,File logo) {
		//method to register a user of the kind "company"
		try {
			//No mockup method since a company has no enrollment number
			return UtenteDao.newCompany(nome, username, password, company, logo);
		} catch (Exception e) {
			
			
			Logger.getGlobal().log(Level.WARNING, "registraSoc", e);
			return false;
		}
			
			
		
	}
}
