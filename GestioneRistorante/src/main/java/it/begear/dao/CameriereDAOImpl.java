package it.begear.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import it.begear.model.Cameriere;

public class CameriereDAOImpl implements CameriereDAO {

	@Autowired
	Cameriere cameriere;
	
	Logger logger = Logger.getAnonymousLogger();

	public boolean creaCameriere(String nome, String cognome) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;
		try {
			if (nome == null || cognome == null)
				throw new HibernateException("");
			tx = session.beginTransaction();
			cameriere.setNome(nome);
			cameriere.setCognome(cognome);
			session.persist(cameriere);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage(), e);
			return false;
		} finally {
			session.close();
		}

	}

	public Cameriere getCameriere(int codCameriere) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Cameriere cameriere = (Cameriere) session.get(Cameriere.class, codCameriere);
			if (cameriere == null)
				throw new HibernateException("");
			return cameriere;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage(), e);
			return null;
		} finally {
			session.close();
		}
	}

	/*
	 * public boolean updateCameriere(Cameriere cameriere) { Session session =
	 * new Configuration().configure().buildSessionFactory().openSession(); try
	 * { session.update(cameriere); return true; } catch (HibernateException e)
	 * { e.printStackTrace(); return false; } finally { session.close(); } }
	 */

	public boolean deleteCameriere(int codCameriere) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		cameriere = (Cameriere) session.get(Cameriere.class, codCameriere);
		if (null != cameriere) {
			session.delete(cameriere);
		}
		return true;
	}

	public List<Cameriere> getCamerieri() {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Cameriere> camerieri = ((List<Cameriere>) session.createCriteria(Cameriere.class).list());
		session.close();
		return camerieri;

	}

}
