/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Users;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author carleandro
 */
public class UserDao {
    
    public boolean save(Users users){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(users);
        s.getTransaction().commit();
      
        return true;
    }
    
    public boolean update(Users users){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(users);
        s.getTransaction().commit();
        return true;
    }
    
    public boolean delete(Users users){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(users);
        s.getTransaction().commit();
        return true;
    }
    
    public List<Users> lista(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Users> lista = s.createQuery("from Users as u order by u.nome ASC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public List<Users> lista(String texto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Users> lista = s.createQuery("from Users as u where u.nome LIKE '%"+texto+"%' order by u.nome ASC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public Users login(String email, String password){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Users> lista = s.createQuery("from Users as u where u.email='"+email+"' AND u.password='"+password+"'").list();
        s.getTransaction().commit();
        if(lista.size()!= 0){
            return lista.get(0);
        }
        return null;
    }
    
    public Users getUser(String email){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Users> lista = s.createQuery("from Users as u where u.email='"+email+"'").list();
        s.getTransaction().commit();
        if(lista.size()!= 0){
            return lista.get(0);
        }
        return null;
    }
    
}
