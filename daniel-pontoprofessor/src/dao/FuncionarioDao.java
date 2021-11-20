/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Funcionarios;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author carleandro
 */
public class FuncionarioDao {
     public boolean save(Funcionarios professors){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(professors);
        s.getTransaction().commit();
      
        return true;
    }
    
    public boolean update(Funcionarios professors){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(professors);
        s.getTransaction().commit();
        return true;
    }
    
    public boolean delete(Funcionarios professors){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(professors);
        s.getTransaction().commit();
        return true;
    }
    
    public List<Funcionarios> lista(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Funcionarios> lista = s.createQuery("from Funcionarios as p  JOIN p.cargos as cargos  order by p.nome ASC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public List<Object[]> listaObject(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Object[]> lista = s.createQuery("from Funcionarios as p  JOIN p.cargos as cargos  order by p.nome ASC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public List<Object[]> listaObject(String texto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Object[]> lista = s.createQuery("from Funcionarios as u  JOIN u.cargos as cargos where u.nome LIKE '%"+texto+"%'  order by u.nome ASC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public List<Funcionarios> lista(String texto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Funcionarios> lista = s.createQuery("from Funcionarios as u  JOIN u.cargos as cargos where u.nome LIKE '%"+texto+"%'  order by u.nome ASC").list();
        s.getTransaction().commit();
        return lista;
    }
    
    public Funcionarios getFuncionario(int id){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Funcionarios funcionario = (Funcionarios) s.createQuery("from Funcionarios u  JOIN u.cargos as cargos where u.id="+id).list().get(0);
        s.getTransaction().commit();
        return funcionario;
    }
    
    public Funcionarios getFuncionario(String cpf){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Object[]>  list = s.createQuery("from Funcionarios u  JOIN u.cargos as cargos where u.cpf='"+cpf+"'").list();
        s.getTransaction().commit();
        if(list.size()!= 0){
            Funcionarios u =(Funcionarios) list.get(0)[0];
            return u;
        }
        return null;
    }
    
    public Funcionarios getFuncionario(String email, String cpf){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Object[]> list =  s.createQuery("from Funcionarios u  JOIN u.cargos as cargos where u.cpf='"+cpf+"' OR u.email='"+email+"'").list();
        s.getTransaction().commit();
        if(list.size()!= 0){
            Funcionarios u =(Funcionarios) list.get(0)[0];
            return u;
        }
        return null;
    }
    
}
