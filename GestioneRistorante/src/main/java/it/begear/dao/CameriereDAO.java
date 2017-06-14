package it.begear.dao;

import it.begear.pojo.Cameriere;

public interface CameriereDAO {

	public boolean creaCameriere(String nome, String cognome);

	public Cameriere getCameriere(int codCameriere);

	public boolean updateCameriere(Cameriere cameriere);

	public boolean deleteCameriere(int codCameriere);

}
