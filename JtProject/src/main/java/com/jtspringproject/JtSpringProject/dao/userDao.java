package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.sound.midi.Soundbank;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.User;


@Repository
public class userDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
   @Transactional
    public List<User> getAllUser() {
        Session session = this.sessionFactory.getCurrentSession();
		List<User>  userList = session.createQuery("from CUSTOMER").list();
        return userList;
    }
    
    @Transactional
	public User saveUser(User user) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		System.out.println("User added" + user.getId());
        return user;
	}
    
//    public User checkLogin() {
//    	this.sessionFactory.getCurrentSession().
//    }


	@Transactional
	public boolean isUserExists(String username) 
	{
		Query<Long> query = sessionFactory.getCurrentSession()
				.createQuery("select count(*) from CUSTOMER where username = :username", Long.class);
		query.setParameter("username", username);

		long count = query.getSingleResult();
		return count > 0;
	}

	@Transactional
	public boolean isEmailExists(String email) {
		Query<Long> query = sessionFactory.getCurrentSession()
				.createQuery("select count(*) from CUSTOMER where email = :email", Long.class);
		query.setParameter("email", email);

		long count = query.getSingleResult();
		return count > 0;
	}

	@Transactional
	public User getUserByUsername(String username) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery("FROM CUSTOMER WHERE username = :username", User.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException e) {
			// Handle the case where no user with the given username is found
			return null;
		}
	}
	
  //   @Transactional
  //   public User getUser(String username,String password) {
  //   	Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
  //   	query.setParameter("username",username);
    	
  //   	try {
		// 	User user = (User) query.getSingleResult();
		// 	System.out.println(user.getPassword());
		// 	if(password.equals(user.getPassword())) {
		// 		return user;
		// 	}else {		
		// 		return new User();
		// 	}
		// }catch(Exception e){
		// 	System.out.println(e.getMessage());
		// 	User user = new User();
		// 	return user;
		// }
    	
  //   }
	@Transactional
	public User saveUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		return user;
	}

@Transactional
	public void deleteUser(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		session.delete(user);
	}

	@Transactional
	public User getUserById(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(User.class, userId);
	}



	@Transactional
	public User getUser(String username, String password) {
		Query<User> query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username", User.class);
		query.setParameter("username", username);

		try {
			return query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new User();
		}
	}
}
