package it.begear.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import it.begear.pojo.Prodotto;

public class ProdottoDAOImpl implements ProdottoDAO {

	@Autowired
	Prodotto prodotto;

	public boolean creaProdotto(String nome, String tipologia, double prezzo) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
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
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public Prodotto getProdotto(String nome) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Prodotto prodotto = (Prodotto) session.load(Prodotto.class, nome);
			return prodotto;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	public boolean updateProdotto(Prodotto prodotto) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		try {
			session.update(prodotto);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean deleteProdotto(String nome) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Prodotto p = (Prodotto) session.load(Prodotto.class, nome);
		if (null != p) {
			session.delete(p);
		}
		return true;
	}

	public List<Prodotto> listaProdotti() {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Prodotto> productList = session.createQuery("from Prodotto").list();
		session.close();
		return productList;
	}

}
