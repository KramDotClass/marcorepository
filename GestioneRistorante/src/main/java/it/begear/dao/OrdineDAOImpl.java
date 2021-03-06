package it.begear.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import it.begear.model.Cameriere;
import it.begear.model.Ordine;
import it.begear.model.Prodotto;

public class OrdineDAOImpl implements OrdineDAO {

	@Autowired
	Ordine order;
	
	Logger logger = Logger.getAnonymousLogger();

	public boolean nuovoOrdine(Cameriere cameriere, int numTavolo, int numCoperti, List<Prodotto> prodotti,
			double totale, int codOrdine) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			if (session.get(Ordine.class, codOrdine) != null)
				order = (Ordine) session.get(Ordine.class, codOrdine);
			order.setNumCoperti(numCoperti);
			order.setNumTavolo(numTavolo);
			order.setCameriere(cameriere);
			order.setListaProdotti(prodotti);
			order.setTotale(totale);
			session.save(order);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.log(Level.SEVERE, e.getMessage(), e);
			return false;
		} finally {
			session.close();

		}
	}

	public Ordine getOrdine(int codOrdine) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			order = (Ordine) session.get(Ordine.class, codOrdine);
			if (order == null)
				throw new HibernateException("");
			Hibernate.initialize(order.getProdotti());
			return order;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.log(Level.SEVERE, e.getMessage(), e);
			return null;
		} finally {
			session.close();
		}
	}

	public boolean deleteOrdine(int codOrdine) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		order = (Ordine) session.get(Ordine.class, codOrdine);
		Transaction tx = null;
		try {
			if (order == null)
				throw new HibernateException("");
			else {
				tx = session.beginTransaction();
				session.delete(order);
				tx.commit();
				return true;
			}
		} catch (HibernateException | NullPointerException x) {
			if(tx == null)
				throw new NullPointerException();
			tx.rollback();			
			logger.log(Level.SEVERE, x.getMessage(), x);
			return false;
		} finally {
			session.close();
		}
	}

	/*
	 * public boolean updateOrdine(Ordine ordine) { Session session = new
	 * Configuration().configure().buildSessionFactory().openSession(); try {
	 * session.update(ordine); return true; } catch (HibernateException e) {
	 * e.printStackTrace(); return false; } finally { session.close(); } }
	 */

	public List<Ordine> listaOrdini() {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Ordine> lista = ((List<Ordine>) session.createCriteria(Ordine.class).list());
		session.close();
		return lista;

	}

}
