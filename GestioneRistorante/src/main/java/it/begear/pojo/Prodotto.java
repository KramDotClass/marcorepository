package it.begear.pojo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prodotto")
public class Prodotto {

	@Id
	@Column(name = "nome")
	private String nome;

	@Column(name = "tipologia")
	private String tipologia;

	@Column(name = "prezzo")
	private Double prezzo;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "prodotti")
	private List<Ordine> ordini;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

}
