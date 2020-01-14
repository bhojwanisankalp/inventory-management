package com.chatbot.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.stereotype.Repository;

import com.chatbot.model.Encryption;
import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.User;
import com.chatbot.util.UserUtil;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("User saved successfully, User Details=" + p);
	}

	@Override
	public void updateUser(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details=" + p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("deleted", false));
		List<User> personsList = criteria.list();
		for (User p : personsList) {
			logger.info("User List::" + p);
		}
		return personsList;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User p = (User) session.load(User.class, new Integer(id));
		logger.info("Person loaded successfully, Person details=" + p);
		return p;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User p = (User) session.load(User.class, new Integer(id));
//		Query query = session.createQuery("UPDATE User set delete=1 WHERE id=:id");
//		query.setParameter("id", id);
		p.setDeleted(true);
		if (null != p) {
			session.update(p);
		}
		logger.info("Person deleted successfully, person details=" + p);
	}

	@Override
	public boolean authenticate(String email, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("FROM User c WHERE c.email=:email");
			query.setParameter("email", email);
			User p = (User) query.uniqueResult();
			if (p.getPassword().equals(Encryption.getMd5(password))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User c WHERE c.email=:email");
		query.setParameter("email", email);
		User p = (User) query.uniqueResult();
		return p;
	}

	@Override
	public boolean isEmailExist(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User c WHERE c.email=:email");
		query.setParameter("email", email);
		User p = (User) query.uniqueResult();
		if (p != null) {

			return true;

		} else {
			return false;
		}

	}

	@Override
	public boolean isContactExist(String contact) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User c WHERE c.contact=:contact");
		query.setParameter("contact", contact);
		User p = (User) query.uniqueResult();
		if (p != null) {

			return true;

		} else {
			return false;
		}

	}

	@Override
	public List<User> listUsersForAdmin() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("role", "user"));
		criteria.add(Restrictions.eq("deleted", false));
		return criteria.list();
	}

	@Override
	public List<User> listPerPage(int start, int limit) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("name"));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		List<User> persinsList = criteria.list();
		for (User p : persinsList) {
			logger.info("User List::" + p);
		}
		return persinsList;
	}

	@Override
	public long totalPageCount() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(User.class);
		criteriaCount.setProjection(Projections.rowCount());
		criteriaCount.add(Restrictions.eq("deleted", false));
		return (Long) criteriaCount.uniqueResult();
	}

	@Override
	public long totalPageCountForAdmin() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(User.class);
		criteriaCount.add(Restrictions.eq("role", "user"));
		criteriaCount.add(Restrictions.eq("deleted", false));
		criteriaCount.setProjection(Projections.rowCount());
		return (Long) criteriaCount.uniqueResult();
	}

	@Override
	public List<User> listPerPageUsersForAdmin(int start, int limit) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("role", "user"));
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("name"));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}

	@Override
	public List<User> searchFilter(String nameOrEmailOrContact) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.disjunction().add(Restrictions.like("name", nameOrEmailOrContact + "%"))
				.add(Restrictions.like("email", nameOrEmailOrContact + "%"))
				.add(Restrictions.like("contact", nameOrEmailOrContact + "%")));
		criteria.add(Restrictions.eq("deleted", false));
		return criteria.list();
	}

	@Override
	public long newUserCount() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dateobj = new Date();
		try {
			dateobj = df.parse(df.format(UserUtil.today()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		criteria.add(Restrictions.ge("createdAt", dateobj));
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}

	@Override
	public long newUserCountForAdmin() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dateobj = new Date();
		try {
			dateobj = df.parse(df.format(UserUtil.today()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		criteria.add(Restrictions.eq("role", "user"));
		criteria.add(Restrictions.ge("createdAt", dateobj));
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}

	@Override
	public List<User> listOfNewUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dateobj = new Date();
		try {
			dateobj = df.parse(df.format(UserUtil.today()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		criteria.add(Restrictions.ge("createdAt", dateobj));
		return criteria.list();
	}
	@Override
	public List<User> listOfNewUsersForAdmin() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dateobj = new Date();
		try {
			dateobj = df.parse(df.format(UserUtil.today()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		criteria.add(Restrictions.eq("role", "user"));
		criteria.add(Restrictions.ge("createdAt", dateobj));
		return criteria.list();
	}
	@Override
	public List<User> serachforAdmin(String nameOrEmailOrContact){
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("role", "user"));
		criteria.add(Restrictions.disjunction().add(Restrictions.like("name", nameOrEmailOrContact + "%"))
				.add(Restrictions.like("email", nameOrEmailOrContact + "%"))
				.add(Restrictions.like("contact", nameOrEmailOrContact + "%")));
		return criteria.list();
	}
	@Override
	public long paymentPending() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Orders.class);
//		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
//		Date dateobj = new Date();
//		try {
//			dateobj = df.parse(df.format(UserUtil.today()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		criteria.add(Restrictions.eq("orderStatus", 1));
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}
	@Override
	public long newOrders() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Orders.class);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dateobj = new Date();
		try {
			dateobj = df.parse(df.format(UserUtil.yesterday()));
			criteria.add(Restrictions.ge("createdAt", dateobj));
		} catch (Exception e) {
			e.printStackTrace();
		}
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}
	@Override
	public long productsOutOfStock() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);	
		criteria.add(Restrictions.ge("isActive", 1));
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}
}