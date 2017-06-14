package it.begear.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "cameriere")
public class Cameriere {

	@Id
	@Column(name = "codCameriere")
	@GeneratedValue
	private int codCameriere;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "codCameriere")
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private List<Ordine> ordini;

	public int getCodCameriere() {
		return codCameriere;
	}

	public void setCodCameriere(int codCameriere) {
		this.codCameriere = codCameriere;
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

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

}
