package pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "cameriere")
public class Cameriere {

	@Id
	@Column(name = "codCameriere")
	@GeneratedValue
	private int codCameriere;

	@Column(name = "name")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "codCameriere")
	@IndexColumn(name = "codCameriere")
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

}
