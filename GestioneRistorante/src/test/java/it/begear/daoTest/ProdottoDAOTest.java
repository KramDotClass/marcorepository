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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans-application-context.xml" })
@Transactional
public class ProdottoDAOTest {

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
	public void creaProdottoTest(){
		assertTrue(prodDao.creaProdotto("Margherita", "pizza", 3.5));
	}
	
	@Test
	public void creaProdottoTestNull(){
		assertFalse(prodDao.creaProdotto("Margherita", null, 3.5));
	}
	
	

}
