package com.sample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.sample.model.Client;
import com.sample.model.Order;
import com.sample.model.Stock;
import com.sample.model.StockDetail;

public class HibernateTest {
	public static void main(String[] args) {
		//1. Create Session Factory
		SessionFactory  sf = createSessionFactory();
		
		//2. Using the SessionFactory create Session
		Session session = sf.openSession();
		session.beginTransaction();
		
		
		Order order = new Order();
		//order.setOrderId(1);
		order.setFirstName("Rakesh");
		order.setLastName("Tom");
		order.setPrice(400);
		order.setQuantity(10);
		
		System.out.println("Object to be save - "+order.getOrderId());
		//3. Use session to save object
		session.saveOrUpdate(order);
		
		System.out.println("Object saved - "+order.getOrderId());
		/*
		
		Order order = (Order)session.load(Order.class, 5);
		System.out.println("Name: "+order.getFirstName());
		order.setFirstName("Sam");
		session.saveOrUpdate(order);
		
		session.delete(order);
		*/	
		session.getTransaction().commit();
		
		//4. close session
		session.close();
		sf.close();
	}

	public static SessionFactory createSessionFactory() {
	    Configuration config = new Configuration();
		config.configure();
		config.addAnnotatedClass(Order.class);
		config.addAnnotatedClass(Stock.class);
		config.addAnnotatedClass(StockDetail.class);
		config.addAnnotatedClass(Client.class);
		
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(config.getProperties());
	    ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
	    
	    SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
}
