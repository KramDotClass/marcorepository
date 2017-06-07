package it.begear.dao;

import it.begear.pojo.Cameriere;

public interface CameriereDAO {

	public boolean creaCameriere(String nome, String cognome);

	public Cameriere getCameriere(int codCameriere);

	public boolean updateCameriere(int codCameriere);

	public boolean deleteCameriere(int codCameriere);

}
