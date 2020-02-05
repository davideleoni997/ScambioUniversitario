package controller;


import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import dao.AdminDao;
import dao.UtenteDao;
import logic.Utente;


         
public class LoginController {
	//Singleton controller with the responsability of interacting with the user entity
    private static LoginController instance;

    public static LoginController getInstance() {
        if (instance == null)
            instance = new LoginController();
        return instance;
    }

    private LoginController() {
    	//Constructor
    }
    
    public boolean validate(String tipoLogin,UserBean lb){
		//Validate if the login information is correct
    	//returns true if correct
		// Control
		if (lb.getUsername().equals("") || lb.getPassword().equals("")) {
			return false;
		}
		//Login is of user kind
		if(tipoLogin.equals("user")) {
			Utente found = login(lb.getUsername(), lb.getPassword());
			//Create a user entity using the username and password
			if(found!= null) {//Username and password match the ones in the DB
				lb.setNome(found.getNome());
				lb.setCognome(found.getCognome());
				lb.setCompany(found.isCompany());
				lb.setId(found.getId());
				lb.setLogo(found.getLogo());}
			return  (found != null);} 
		else //Login is of admin kind
			try {
				return  AdminDao.adminLogin(lb.getUsername(), lb.getPassword());
			} catch (Exception e) {
				
				Logger.getGlobal().log(Level.WARNING, "validate", e);
				return false;
			}
	}
    
    public UserBean getUserFromId(Integer id) {
    	//Get username of a user using its id
    	try {
			return UtenteDao.userFromId(id);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getUserFromId", e);
			return null;
		}
    }
    
    public Integer getIdFromUsername(String user) {
    	//get the Id of a user using its username
    	//returns 0 or -1 if there's any error
    	try {
			return UtenteDao.getIdByUsername(user);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getIdFromUser", e);
			return 0;
		}
    }
    
    public boolean updateInfo(UserBean lb) {
    	//Update a user info using it's representation in a bean
    	try {
			return dao.UtenteDao.update(lb.getId(), lb.getNome(), lb.getCognome(), lb.getUsername(), lb.getPassword());
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "update", e);
			return false;
		}
    }
    
    
    public Utente login(String username, String password) {
    	//Method that calls the dao to retrieve the user entity
        try {
			return UtenteDao.findByNameAndPassword(username, password);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getMessage", e);
			return null;
		}
       
        
    }
}
