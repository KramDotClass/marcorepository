package it.begear.daoTest;

import static org.junit.Assert.*;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans-application-context.xml" })
@Transactional
public class CameriereDAOTest {

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
	public void creaCameriereTest() {
		assertTrue(camDao.creaCameriere("Mario", "Bros"));
	}

	@Test
	public void creaCameriereTestNull() {
		assertFalse(camDao.creaCameriere(null, null));
	}

	@Test
	public void getCameriereTest() {
		Cameriere cam = camDao.getCameriere(1);
		assertNotNull(cam);
	}

	@Test
	public void getCameriereTestNull() {
		Cameriere cam = camDao.getCameriere(0);
		assertNull(cam);
	}
	
	@Test
	public void deleteCameriereTest(){
		assertTrue(camDao.deleteCameriere(3));
	}
	
	@Test
	public void getCamerieriTest(){
		camDao.getCamerieri();
	}
}
