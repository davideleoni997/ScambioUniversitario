package controller;

import java.sql.SQLException;

import bean.UserBean;
import dao.AdminDao;
import dao.UtenteDao;
import logic.Utente;

/*
         * Per aprire la pagina di login
		 * 1) avviare il server
		 * 2) fare il deploy del progetto sul server (senza
		 * dimenticare di includere il driver mysql nel build
		 * path e renderlo esportabile durante il deploy).
		 * 3) visitare il seguente link (eventualmente cambiando
		 * il numero di porta dell'application server, il nome
		 * del progetto ed il nome della pagina):
		 * http://localhost:8080/LoginExample/LoginPage.jsp
		 *
		 * Per effettuare il deploy del progetto, occorre
		 * installare un application server (e.g. Tomcat),
		 * crearne un'istanza dentro eclipse e selezionare
		 * quali progetti dovranno girare su quella istanza
		 * di server.
		 * Per rendere esportabile il driver mysql, dopo averlo
		 * aggiunto alla build path del progetto, selezionare il
		 * tab "order and export" (sempre nella finestra del build
		 * path) e spuntare il jar del driver.
		 *
		 * Se questo non dovesse funzionare (ovvero il caricamento
		 * della classe del driver dovesse fallire): Project ->
		 * Properties -> Deployment Assembly -> Add -> Project e
		 * selezionate il jar.
		 *
		 * Non dimenticate di creare il database, la tabella e di
		 * aggiungere una entry per fare il test (nel progetto
		 * trovate lo script sql). Verificare username, password ed
		 * URL del db.
		 *
		 */
public class LoginController {

    private static LoginController instance;

    public static LoginController getInstance() {
        if (instance == null)
            instance = new LoginController();
        return instance;
    }

    private LoginController() {
    }
    
    public boolean validate(String tipoLogin,UserBean lb) throws ClassNotFoundException, SQLException {
		
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
			return  AdminDao.adminLogin(lb.getUsername(), lb.getPassword());
	}
    
    public UserBean getUserFromId(Integer id) {
    	return UtenteDao.userFromId(id);
    }
    
    public Integer getIdFromUsername(String user) {
    	return UtenteDao.getIdByUsername(user);
    }
    
    public boolean updateInfo(UserBean lb) {
    	return dao.UtenteDao.update(lb.getId(), lb.getNome(), lb.getCognome(), lb.getUsername(), lb.getPassword());
    }
    
    /**
     * Carica l'utente corrispondente alla coppia username/password in input
     *
     * @param username username
     * @param password password
     * @return l'utente loggato oppure null se nessun utente corrisponde alla coppia username/password
     */
    public Utente login(String username, String password) {
        return UtenteDao.findByNameAndPassword(username, password);
       
        
    }
}
