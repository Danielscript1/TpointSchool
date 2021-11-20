/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Pontos;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author carleandro
 */
public class PontoDao {
    
     public boolean save(Pontos ponto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(ponto);
        s.getTransaction().commit();
      
        return true;
    }
    
    public boolean update(Pontos ponto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(ponto);
        s.getTransaction().commit();
        return true;
    }
    
    public boolean delete(Pontos ponto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Pontos ent = (Pontos) s.load(Pontos.class, ponto.getId());
        s.delete(ent);
        s.getTransaction().commit();
        return true;
    }
    
    public List<Object[]> lista(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Object[]> lista = s.createQuery("from Pontos u JOIN u.funcionarios order by u.id DESC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public List<Object[]> lista(String texto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Object[]>  lista = s.createQuery("from Pontos u JOIN u.funcionarios as funcionario where funcionario.nome LIKE '%"+texto+"%'  order by u.id DESC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public List<Object[]> listaData(String texto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Object[]>  lista = s.createQuery("from Pontos u JOIN u.funcionarios as funcionario JOIN funcionario.cargos as cargos where "+texto+" order by u.id DESC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public Object[] getPonto(int id){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Object[]>  lista = s.createQuery("from Pontos u JOIN u.funcionarios as funcionario where u.id="+id).list();
        s.getTransaction().commit();
        if(lista != null){
           return lista.get(0);
        }
        return null;
    }
    
}
