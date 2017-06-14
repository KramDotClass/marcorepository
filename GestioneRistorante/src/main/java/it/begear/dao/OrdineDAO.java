package it.begear.dao;

import java.util.List;

import it.begear.pojo.Cameriere;
import it.begear.pojo.Ordine;
import it.begear.pojo.Prodotto;

public interface OrdineDAO {

	public boolean nuovoOrdine(Cameriere cameriere, int numTavolo, int numCoperti, List<Prodotto> prodotti);

	public Ordine getOrdine(int codOrdine);

	public boolean updateOrdine(Ordine ordine);

	public boolean deleteOrdine(int codOrdine);
	
	public List<Ordine> listaOrdini();

}
