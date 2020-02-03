package logic;

import java.sql.SQLException;

import bean.UserBean;
import javafx.scene.image.Image;

public class Utente {

    private String username;
    private String password;
    private String nome;
    private String cognome;
    private boolean company;
    private Integer id;
    private Image logo;

    

	public Utente(String username, String password, String nome, String cognome, boolean company, Integer id,
			Image logo) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.company = company;
		this.id = id;
		this.logo = logo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public boolean isCompany() {
		return company;
	}

	public void setCompany(boolean company) {
		this.company = company;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public boolean update() throws ClassNotFoundException, SQLException {
		return dao.UtenteDao.update(this.id,this.nome,this.cognome,this.username,this.password);
	}
	
	public UserBean tobean() {
		return new UserBean(this.username,this.password,this.nome,this.cognome,this.company,this.id,this.logo);
	}
}
