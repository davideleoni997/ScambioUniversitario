package bean;

import controller.LoginController;
import logic.Utente;

public class LoginBean {
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private Boolean company;

	public LoginBean() {
		this.username = "";
		this.password = "";
	}

	public void setUsername(String user) {
		this.username = user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getPassword() {
		return this.password;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setCognome(String cogn) {
		this.cognome = cogn;
	}

	public String getCognome() {
		return this.cognome;
	}

	public Boolean getCompany() {
		return company;
	}

	public void setCompany(Boolean company) {
		this.company = company;
	}

	public boolean validate(String tipoLogin) {
		System.out.println("Tipo login: " + tipoLogin);
		// Controllo sintattico
		if (this.username.equals("") || this.password.equals("")) {
			return false;
		}

		LoginController controller = LoginController.getInstance();
		Utente found = controller.login(this.username, this.password);
		if(found!= null) {
		this.nome = found.getNome();
		this.cognome = found.getCognome();
		this.company = found.isCompany();}
		return  (found != null);
	}

}
