package com.sample;


import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.sample.model.Client;
import com.sample.model.Stock;
import com.sample.model.StockDetail;

public class HQLSample {
	public static void main(String[] args) {
		System.out.println("Hibernate one to one (Annotation)");
		SessionFactory sf = new AnnotationConfiguration().configure()
				.addAnnotatedClass(Stock.class)
				.addAnnotatedClass(StockDetail.class)
				.addAnnotatedClass(Client.class).buildSessionFactory();

		Session session = sf.openSession();
		session.beginTransaction();
		
		//String hql = "select s from Stock as s left join s.details as sd where sd.compName=:queryParamName";
		String hql = "select s from Stock as s where s.stockName = :queryParamName";
		
		System.out.println("HQL Formed is "+hql);
		
		Query query =	session.createQuery(hql);
		//query.setFirstResult(0);
		//query.setMaxResults(5);
		
		
		//set the query values
		query.setParameter("queryParamName", "Soap");
		
		//get the result
		List<Stock> stocksFromDB =	query.list();
		
		System.out.println("Results Size:"+stocksFromDB.size());
		for (Stock stock : stocksFromDB) {
			System.out.println(stock);
			//List<StockDetail> details = stock.getDetails();
			//System.out.println("This stock has "+details.size()+" childeren");
			
			session.delete(stock);
		}
		
		
		session.getTransaction().commit();
		 
		System.out.println("Done");
	}
}
