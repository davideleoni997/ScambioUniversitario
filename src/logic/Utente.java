package logic;

public class Utente {

    private String username;
    private String password;
    private String nome;
    private String cognome;
    private boolean company;
    private Integer id;

    public Utente(String username, String password, String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
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

	public boolean update() {
		return dao.UtendeDao.update(this.id,this.nome,this.cognome,this.username,this.password);
	}
}
