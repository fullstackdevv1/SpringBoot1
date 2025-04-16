package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//need to inject the sessionfactory
	@Autowired
	private SessionFactory sessionFactory;

	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer",Customer.class);
		
		//excute the query and get results
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	public void saveCustomer(Customer customer) {

		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(customer);
	}

	public Customer getCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Customer customer = currentSession.get(Customer.class,theId);
		
		return customer;
	}

	public void deleteCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery =currentSession.createQuery("delete from Customer where id=:customerId" );
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		
		
	}

	

}
