package it.begear.dao;

import it.begear.pojo.Cameriere;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class CameriereDAOImpl implements CameriereDAO {

	public boolean creaCameriere(String nome, String cognome) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Cameriere cameriere=new Cameriere(); 
			cameriere.setNome(nome);
			cameriere.setCognome(cognome);
			session.persist(cameriere);
			tx.commit();
			return true;
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return false;
		}finally {
			session.close(); 
		}
	}

	public Cameriere getCameriere(int codCameriere) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Cameriere cameriere = (Cameriere) session.load(Cameriere.class, codCameriere);
			return cameriere;
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return null;
		}finally {
			session.close(); 
		}
	}

	public boolean updateCameriere(Cameriere cameriere) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		try{
		session.update(cameriere);
		return true;
		}catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			session.close();
		}
	}

	public boolean deleteCameriere(int codCameriere) {
		Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
		Cameriere c = (Cameriere) session.load(Cameriere.class, new Integer(codCameriere));
		if(null != c){
			session.delete(c);
		}
		return true;
	}

}
