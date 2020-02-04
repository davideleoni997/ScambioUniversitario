package controller;


import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import dao.AdminDao;
import dao.UtenteDao;
import logic.Utente;


         
public class LoginController {

    private static LoginController instance;

    public static LoginController getInstance() {
        if (instance == null)
            instance = new LoginController();
        return instance;
    }

    private LoginController() {
    }
    
    public boolean validate(String tipoLogin,UserBean lb){
		
		// Controllo sintattico
		if (lb.getUsername().equals("") || lb.getPassword().equals("")) {
			return false;
		}

		if(tipoLogin.equals("user")) {
			Utente found = login(lb.getUsername(), lb.getPassword());
			if(found!= null) {
				lb.setNome(found.getNome());
				lb.setCognome(found.getCognome());
				lb.setCompany(found.isCompany());
				lb.setId(found.getId());
				lb.setLogo(found.getLogo());}
			return  (found != null);} 
		else
			try {
				return  AdminDao.adminLogin(lb.getUsername(), lb.getPassword());
			} catch (Exception e) {
				
				Logger.getGlobal().log(Level.WARNING, "validate", e);
				return false;
			}
	}
    
    public UserBean getUserFromId(Integer id) {
    	try {
			return UtenteDao.userFromId(id);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getUserFromId", e);
			return null;
		}
    }
    
    public Integer getIdFromUsername(String user) {
    	try {
			return UtenteDao.getIdByUsername(user);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getIdFromUser", e);
			return 0;
		}
    }
    
    public boolean updateInfo(UserBean lb) {
    	try {
			return dao.UtenteDao.update(lb.getId(), lb.getNome(), lb.getCognome(), lb.getUsername(), lb.getPassword());
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "update", e);
			return false;
		}
    }
    
    /**
     * Carica l'utente corrispondente alla coppia username/password in input
     *
     * @param username username
     * @param password password
     * @return l'utente loggato oppure null se nessun utente corrisponde alla coppia username/password
     */
    public Utente login(String username, String password) {
        try {
			return UtenteDao.findByNameAndPassword(username, password);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getMessage", e);
			return null;
		}
       
        
    }
}
