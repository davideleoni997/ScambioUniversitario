package controller;

import java.io.File;

import dao.UtenteDao;

public class RegistrationController {

	public RegistrationController() {
		
	}
	
	public boolean registraUtente(String nome,String cognome,String username,String password,Boolean company,String matricola) {
		return UtenteDao.newStudent(nome, cognome, username, password, company, matricola);
		
	}

	public boolean registraSocieta(String nome,String username,String password,Boolean company,File logo) {
		
		return UtenteDao.newCompany(nome, username, password, company, logo);
			
			
		
	}
}
