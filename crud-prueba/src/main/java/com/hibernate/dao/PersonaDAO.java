package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Persona;
import com.hibernate.util.HibernateUtil;

public class PersonaDAO {
	/*
	 * INSERTAR PERSONA
	 */
	public void insertPersona(Persona p) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	/*
	 * ACTUALIZAR PERSONA
	 */
	public void updatePersona(Persona p) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}
	
	/*
	 * BORRAR PERSONA
	 */
	public void deletePersona(int id) {
		Transaction transaction = null;
		Persona p = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			p = session.get(Persona.class, id);
			session.remove(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	/*
	 * SELECCION SIMPLE
	 */
	public Persona selectPersonaById(int id) {
		Transaction transaction = null;
		Persona p = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			p = session.get(Persona.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return p;
	}
	
	/*
	 * SELECCION MULTIPLE
	 */
	public List<Persona> selectAllPerson() {
		Transaction transaction = null;
		List<Persona> personas = null;
		Persona p = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			personas = session.createQuery("FROM Persona", Persona.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return personas;

	}
	
}
