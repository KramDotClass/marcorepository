package it.begear.dao;

import java.util.List;

import it.begear.model.Cameriere;

public interface CameriereDAO {

	public boolean creaCameriere(String nome, String cognome);

	public Cameriere getCameriere(int codCameriere);

	public List<Cameriere> getCamerieri();

	//public boolean updateCameriere(Cameriere cameriere);

	public boolean deleteCameriere(int codCameriere);

}
