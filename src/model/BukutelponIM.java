/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Bukutelpon;
import interfaces.BukutelponIF;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Minami
 */
public class BukutelponIM implements BukutelponIF{

    Session s;
    Transaction t;
    
    public BukutelponIM() {
    }
    
    

    @Override
    public void save(Bukutelpon b) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        s=HibernateUtil.getSessionFactory().openSession();
        t=s.beginTransaction();
        s.save(b);
        t.commit();
        s.close();
        
    }

    @Override
    public void update(Bukutelpon b) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        s=HibernateUtil.getSessionFactory().openSession();
        t=s.beginTransaction();
        s.update(b);
        t.commit();
        s.close();
    }

    @Override
    public void delete(Bukutelpon b) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        s=HibernateUtil.getSessionFactory().openSession();
        t=s.beginTransaction();
        s.delete(b);
        t.commit();
        s.close();
    }

    @Override
    public List<Bukutelpon> read() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        s=HibernateUtil.getSessionFactory().openSession();
        Query q=s.createQuery("FROM Bukutelpon");
        List<Bukutelpon> ls=q.list();
        return ls;
        
    }

    @Override
    public List<Bukutelpon> find(String key) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        s=HibernateUtil.getSessionFactory().openSession();
        Query q=s.createQuery("FROM Bukutelpon WHERE nama LIKE :nama");
        q.setString("nama", "%"+key+"%");
        List<Bukutelpon> ls=q.list();
        return ls;
    }
    
}
