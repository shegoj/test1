package com.rso.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class GetDataObject {
		
    Session session = HibernateUtil.getSessionFactory().openSession();

    
    public List retrieveData (String queryString) 
    {
    	 List results = null;
         Query query = session.createQuery(queryString);
         results = query.list();
         
         return results;	 
    }
    
    
    public static void main (String args[]) {
    	
    	GetDataObject db = new GetDataObject();
    	System.out.println("Maven + Hibernate + MySQL");
    	List <BandwidthUtil> result = (List <BandwidthUtil>) db.retrieveData("from BandwidthUtil");
    	
    	for (int i = 0; i < result.size(); ++i) {
    		
    		System.out.println (result.get(i).getServerName() + " Result: " + result.get(i).getBandwithUsed() );
    	}	
    }
    
    public Session getSession()
    {
    	return session;
    }

}
