package it.begear.pojo;

import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "ordine")
public class Ordine {

	@Id
	@Column(name = "codOrdine")
	@GeneratedValue
	private int codOrdine;

	@ManyToOne
	@JoinColumn(name = "codCameriere", referencedColumnName = "codOrdine", insertable = false, updatable = false, nullable = false)
	private Cameriere cameriere;

	@Column(name = "numTavolo")
	private Integer numTavolo;

	@Column(name = "numCoperti")
	private Integer numCoperti;

	@Column(name = "totale")
	private Double totale;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ordine_prodotto", joinColumns = { @JoinColumn(name = "codOrdine") }, inverseJoinColumns = {
			@JoinColumn(name = "nome") })
	private List<Prodotto> prodotti;
	

	public void setListaProdotti(List<Prodotto> listProdotti) {
		this.prodotti = listProdotti;
	}

	public int getCodOrdine() {
		return codOrdine;
	}

	public void setCodOrdine(int codOrdine) {
		this.codOrdine = codOrdine;
	}

	public Integer getNumTavolo() {
		return numTavolo;
	}

	public void setNumTavolo(Integer numTavolo) {
		this.numTavolo = numTavolo;
	}

	public Integer getNumCoperti() {
		return numCoperti;
	}

	public void setNumCoperti(Integer numCoperti) {
		this.numCoperti = numCoperti;
	}

	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}

	public void setCameriere(Cameriere cameriere) {
		this.cameriere = cameriere;
	}

}
