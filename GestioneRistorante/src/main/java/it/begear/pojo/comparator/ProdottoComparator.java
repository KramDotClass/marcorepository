package it.begear.pojo.comparator;

import java.util.Comparator;

import it.begear.pojo.Prodotto;

public class ProdottoComparator implements Comparator<Prodotto> {

	public int compare(Prodotto o1, Prodotto o2) {
		
		return o1.compareTo(o2);
	}

}
