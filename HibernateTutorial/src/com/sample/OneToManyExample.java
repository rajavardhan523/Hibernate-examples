package com.sample;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.sample.model.Client;
import com.sample.model.Order;
import com.sample.model.Stock;
import com.sample.model.StockDetail;

public class OneToManyExample {
	public static void main(String[] args) {
		System.out.println("Hibernate one to one (Annotation)");
		//1. Create Session Factory
		SessionFactory  sf = new AnnotationConfiguration()
		.configure()
		.addAnnotatedClass(Stock.class)
		.addAnnotatedClass(StockDetail.class)
		.addAnnotatedClass(Client.class)
		.buildSessionFactory();
		
		//2.Using the SessionFactory create Session
		Session session = sf.openSession();
		session.beginTransaction();
		
		Client client = new Client();
		client.setClientName("Trader Joe");
 
		//session.save(client);

		Stock stock = new Stock();

		stock.setClient(client);
		stock.setStockCode("11111");
		stock.setStockName("Soap");
 
		StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("Dove");
		stockDetail.setCompDesc("one stop shopping");
		stockDetail.setRemark("vinci vinci");
		stockDetail.setListedDate(new Date());
 
		stock.getDetails().add(stockDetail);
		
		StockDetail stockDetail2 = new StockDetail();
		stockDetail2.setCompName("Crackers");
		stockDetail2.setCompDesc("one stop shopping");
		stockDetail2.setRemark("wholesale");
		stockDetail2.setListedDate(new Date());
 
		stock.getDetails().add(stockDetail2);
		
		StockDetail stockDetail3 = new StockDetail();
		stockDetail3.setCompName("33333");
		stockDetail3.setCompDesc("one stop shopping");
		stockDetail3.setRemark("wholesale");
		stockDetail3.setListedDate(new Date());
 
		stock.getDetails().add(stockDetail3);
		
		
		//3. Use session to save object
		session.saveOrUpdate(stock);
		session.getTransaction().commit();
		
		//4. close session
		session.close();
 
		System.out.println("Done");
	}
}
