package bean;

import javafx.scene.image.Image;

public class UserBean {
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private Boolean company;
	private Integer id;
	private Image logo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserBean() {
		this.username = "";
		this.password = "";
	}
	
	public UserBean(String username, String password, String nome, String cognome, Boolean company, Integer id,
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

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	

}
