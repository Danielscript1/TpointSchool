/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entidades.Cargos;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author carleandro
 */
public class CargoDao {
    public boolean save(Cargos cargo){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(cargo);
        s.getTransaction().commit();
      
        return true;
    }
    
    public boolean update(Cargos cargo){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(cargo);
        s.getTransaction().commit();
        return true;
    }
    
    public boolean delete(Cargos cargo){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(cargo);
        s.getTransaction().commit();
        return true;
    }
    
    public List<Cargos> lista(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Cargos> lista = s.createQuery("from Cargos as p  order by p.nome ASC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public List<Cargos> lista(String texto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Cargos> lista = s.createQuery("from Cargos as u where u.nome LIKE '%"+texto+"%'  order by u.nome ASC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public Cargos getCargo(int id){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Cargos cargo = (Cargos) s.createQuery("from Cargos u where u.id="+id).list().get(0);
        s.getTransaction().commit();
        return cargo;
    }
    
    public Cargos getCargo(String nome){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Cargos> listCargos = (List<Cargos>) s.createQuery("from Cargos u where u.nome='"+nome+"'").list();
        s.getTransaction().commit();
        if(listCargos.size()!= 0){
            return listCargos.get(0);
        }
        return null;
    }
}
