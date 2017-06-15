package it.begear.dao;

import it.begear.pojo.Cameriere;
import it.begear.pojo.Ordine;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class CameriereDAOImpl implements CameriereDAO {

	@Autowired
	Cameriere cameriere;

	public boolean creaCameriere(String nome, String cognome) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
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
			return false;
		} finally {
			session.close();
		}

	}

	public Cameriere getCameriere(int codCameriere) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Cameriere cameriere = (Cameriere) session.load(Cameriere.class, codCameriere);
			return cameriere;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public boolean updateCameriere(Cameriere cameriere) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		try {
			session.update(cameriere);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean deleteCameriere(int codCameriere) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		cameriere = (Cameriere) session.load(Cameriere.class, new Integer(codCameriere));
		if (null != cameriere) {
			session.delete(cameriere);
		}
		return true;
	}

	public List<Cameriere> getCamerieri() {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Cameriere> camerieri = ((List<Cameriere>) session.createCriteria(Cameriere.class).list());
			return camerieri;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
