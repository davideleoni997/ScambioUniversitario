package logic;

public class Item {
	
	private String Nome;
	private Integer Prezzo;
	private Integer quantita;
	
	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Item(String nome,Integer prezzo) {
		super();
		Nome = nome;
		Prezzo = prezzo;
	}

	public Integer getPrezzo() {
		return Prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		Prezzo = prezzo;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
}
