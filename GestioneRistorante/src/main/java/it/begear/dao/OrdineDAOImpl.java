package it.begear.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import it.begear.pojo.Cameriere;
import it.begear.pojo.Ordine;
import it.begear.pojo.Prodotto;

public class OrdineDAOImpl implements OrdineDAO {

	@Autowired
	Ordine order;

	public boolean nuovoOrdine(Cameriere cameriere, int numTavolo, int numCoperti, List<Prodotto> prodotti) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			order.setNumCoperti(numCoperti);
			order.setNumTavolo(numTavolo);
			order.setCameriere(cameriere);
			order.setListaProdotti(prodotti);
			session.persist(order);
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

	public Ordine getOrdine(int codOrdine) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Ordine ordine = (Ordine) session.load(Ordine.class, codOrdine);
			return ordine;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public boolean deleteOrdine(int codOrdine) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Ordine ordine = (Ordine) session.load(Ordine.class, codOrdine);
		if (null != ordine) {
			session.delete(ordine);
		}
		return true;
	}

	public boolean updateOrdine(Ordine ordine) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		try {
			session.update(ordine);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public List<Ordine> listaOrdini() {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Ordine> lista = ((List<Ordine>) session.createCriteria(Ordine.class).list());

			return lista;
		} catch (HibernateException e) {

			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
