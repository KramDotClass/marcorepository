package it.begear.dao;

import java.util.List;

import it.begear.model.Prodotto;

public interface ProdottoDAO {

	public boolean creaProdotto(String nome, String tipologia, double prezzo);

	public Prodotto getProdotto(String nome);

	//public boolean updateProdotto(Prodotto prodotto);

	public boolean deleteProdotto(String nome);
	
	public List<Prodotto> listaProdotti();

}
