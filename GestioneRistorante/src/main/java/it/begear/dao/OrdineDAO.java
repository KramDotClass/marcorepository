package it.begear.dao;

import java.util.List;

import it.begear.model.Cameriere;
import it.begear.model.Ordine;
import it.begear.model.Prodotto;

public interface OrdineDAO {

	public boolean nuovoOrdine(Cameriere cameriere, int numTavolo, int numCoperti, List<Prodotto> prodotti, double totale, int codOrdine);

	public Ordine getOrdine(int codOrdine);

	//public boolean updateOrdine(Ordine ordine);

	public boolean deleteOrdine(int codOrdine);
	
	public List<Ordine> listaOrdini();

}
