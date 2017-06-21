package it.begear.daoTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.begear.dao.CameriereDAOImpl;
import it.begear.dao.OrdineDAOImpl;
import it.begear.dao.ProdottoDAOImpl;
import it.begear.model.Cameriere;
import it.begear.model.Prodotto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans-application-context.xml" })
@Transactional
public class OrdineDAOTest {

	@Autowired
	OrdineDAOImpl ordDao;

	@Autowired
	ProdottoDAOImpl prodDao;

	@Autowired
	CameriereDAOImpl camDao;

	@Before
	public void startTest() {
		System.out.println("Inizio test unitario...");
	}

	@After
	public void endTest() {
		System.out.println("Fine test unitario...");
	}

	@Test
	public void nuovoOrdineInserimentoTest() {
		List<Prodotto> prodotti = prodDao.listaProdotti();
		Cameriere cameriere = camDao.getCameriere(1);
		assertTrue(ordDao.nuovoOrdine(cameriere, 4, 3, prodotti, 100, 0));
	}

	@Test
	public void nuovoOrdineInserimentoTestNull1() {
		List<Prodotto> prodotti = null;
		Cameriere cameriere = null;
		assertFalse(ordDao.nuovoOrdine(cameriere, 4, 3, prodotti, 100, 0));
	}

	@Test
	public void nuovoOrdineInserimentoTestNull2() {
		List<Prodotto> prodotti = prodDao.listaProdotti();
		Cameriere cameriere = null;
		assertFalse(ordDao.nuovoOrdine(cameriere, 4, 3, prodotti, 100, 0));
	}

	@Test
	public void nuovoOrdineInserimentoTestNull3() {
		List<Prodotto> prodotti = null;
		Cameriere cameriere = camDao.getCameriere(1);
		ordDao.nuovoOrdine(cameriere, 4, 3, prodotti, 100, 0);
	}

	@Test
	public void nuovoOrdineInserimentoTestUpdate() {
		List<Prodotto> prodotti = prodDao.listaProdotti();
		Cameriere cameriere = camDao.getCameriere(1);
		assertTrue(ordDao.nuovoOrdine(cameriere, 4, 3, prodotti, 100, 16));
	}

	@Test
	public void getOrdineTest() {
		ordDao.getOrdine(16);
	}

	@Test
	public void getOrdineTestNull() {
		assertNull(ordDao.getOrdine(0));
	}

	@Test
	public void deleteOrdineTest() {
		ordDao.deleteOrdine(58);
	}

	@Test(expected = NullPointerException.class)
	public void deleteOrdineTestNull() {
		assertFalse(ordDao.deleteOrdine(0));
	}
	
	@Test
	public void getListaOrdiniTest(){
		ordDao.listaOrdini();
	}
}
