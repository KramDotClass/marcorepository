package it.begear.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import it.begear.model.Prodotto;

public class ProdottoDAOImpl implements ProdottoDAO {

	@Autowired
	Prodotto prodotto;

	Logger logger = Logger.getAnonymousLogger();

	public boolean creaProdotto(String nome, String tipologia, double prezzo) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;
		try {
			if (nome == null || tipologia == null || prezzo <= 0)
				throw new HibernateException("");
			tx = session.beginTransaction();
			prodotto.setNome(nome);
			prodotto.setTipologia(tipologia);
			prodotto.setPrezzo(prezzo);
			session.persist(prodotto);
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

	public Prodotto getProdotto(String nome) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Prodotto prodotto = (Prodotto) session.get(Prodotto.class, nome);
			if (prodotto == null)
				throw new HibernateException("");
			return prodotto;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.log(Level.SEVERE, e.getMessage(), e);
			return null;
		} finally {
			session.close();
		}

	}

	/*
	 * public boolean updateProdotto(Prodotto prodotto) { Session session = new
	 * Configuration().configure().buildSessionFactory().openSession(); try {
	 * session.update(prodotto); return true; } catch (HibernateException e) {
	 * e.printStackTrace(); return false; } finally { session.close(); } }
	 */

	public boolean deleteProdotto(String nome) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Prodotto p = (Prodotto) session.load(Prodotto.class, nome);
		if (null != p) {
			session.delete(p);
		}
		return true;
	}

	public List<Prodotto> listaProdotti() {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Prodotto> productList = session.createQuery("from Prodotto").list();
		session.close();
		return productList;
	}

}
