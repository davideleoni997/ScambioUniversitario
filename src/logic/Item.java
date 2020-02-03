package logic;

public class Item {
	
	private String nome;
	private Integer prezzo;
	
	public Item(String nome,Integer prezzo) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
