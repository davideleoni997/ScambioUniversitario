package controller;

import bean.UserBean;
import dao.UtendeDao;
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
    
    public boolean validate(String tipoLogin,UserBean lb) {
		System.out.println("Tipo login: " + tipoLogin);
		// Controllo sintattico
		if (lb.getUsername().equals("") || lb.getPassword().equals("")) {
			return false;
		}

		
		Utente found = login(lb.getUsername(), lb.getPassword());
		if(found!= null) {
		lb.setNome(found.getNome());
		lb.setCognome(found.getCognome());
		lb.setCompany(found.isCompany());
		lb.setId(found.getId());}
		return  (found != null);
	}
	
    
    public boolean updateInfo(UserBean lb) {
    	if(dao.UtendeDao.update(lb.getId(), lb.getNome(), lb.getCognome(), lb.getUsername(), lb.getPassword()))
    	return true;
    	 
    	else return false;
    	
    }
    
    /**
     * Carica l'utente corrispondente alla coppia username/password in input
     *
     * @param username username
     * @param password password
     * @return l'utente loggato oppure null se nessun utente corrisponde alla coppia username/password
     */
    public Utente login(String username, String password) {
        Utente u = UtendeDao.findByNameAndPassword(username, password);
        //Utente u = UtendeDao.findByNameAndPasswordMockup(username, password);
        return u;
    }
}
