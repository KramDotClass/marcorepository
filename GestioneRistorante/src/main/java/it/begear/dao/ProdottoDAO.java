package it.begear.dao;

import it.begear.pojo.Prodotto;

public interface ProdottoDAO {

	public boolean creaProdotto(String nome, String tipologia, double prezzo);

	public Prodotto getProdotto(String nome);

	public boolean updateProdotto(String nome);

	public boolean deleteProdotto(String nome);

}
