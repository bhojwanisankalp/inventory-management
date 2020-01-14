package com.chatbot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chatbot.model.Agents;
import com.chatbot.model.User;

public class AgentDaoImpl implements AgentDao{
	
	private static final Logger logger = LoggerFactory.getLogger(AgentDaoImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	public void addAgent(Agents p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Agent saved successfully, Agent Details=" + p);
		
	}

	@Override
	public void updateAgent(Agents p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
	}

	@Override
	public List<Agents> listAgents() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Agents.class);
		criteria.add(Restrictions.eq("deleted", false));
		List<Agents> personsList = criteria.list();
		return personsList;
	}

	@Override
	public Agents getAgentById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Agents p = (Agents) session.load(Agents.class, new Integer(id));
		logger.info("Person loaded successfully, Person details=" + p);
		return p;
	}

	@Override
	public void removeAgent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Agents p = (Agents) session.load(Agents.class, new Integer(id));
		p.setDeleted(true);
		if (null != p) {
			session.update(p);
		}	
	}
	@Override
	public boolean isEmailExist(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Agents c WHERE c.email=:email");
		query.setParameter("email", email);
		Agents p = (Agents) query.uniqueResult();
		if (p != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isContactExist(String contact) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Agents c WHERE c.contact=:contact");
		query.setParameter("contact", contact);
		Agents p = (Agents) query.uniqueResult();
		if (p != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public long totalPageCount() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Agents.class);
		criteriaCount.setProjection(Projections.rowCount());
		criteriaCount.add(Restrictions.eq("deleted", false));
		return (Long) criteriaCount.uniqueResult();
	}
	
	@Override
	public List<Agents> listPerPage(int start, int limit) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Agents.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("name"));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		List<Agents> personsList = criteria.list();
		return personsList;
	}
	
}
