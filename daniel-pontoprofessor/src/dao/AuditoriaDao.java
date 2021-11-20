/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Auditoria;
import entidades.Cargos;
import entidades.Pontos;
import entidades.Funcionarios;
import entidades.Users;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author carleandro
 */
public class AuditoriaDao {
     
    private String textAuditoria(Object object, String acao){
        String texto=acao;
       
        if(object instanceof Users){
            Users u = (Users) object;
            texto += " Tabela users  ID:"+u.getId()+" nome:"+u.getNome()+" Email:"+u.getEmail()+" status:"+u.getStatus()+" password:"+
                    u.getPassword();
        }else if(object instanceof Funcionarios){
            Funcionarios p = (Funcionarios) object;
            texto += " Tabela funcionarios  ID:"+p.getId()+" nome:"+p.getNome()+" Email:"+p.getEmail()+" status:"+p.getStatus()
                    +" CPF:"+p.getCpf()+" tipo:"+p.getTipo();
        }else if(object instanceof Pontos){
            DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
            Pontos p = (Pontos) object;
            texto += " Tabela pontos  ID:"+p.getId()+" datacadastro:"+format.format(p.getDatacadastro())+" hora:"+p.getHora()+" imagem:"+p.getImagem()
                    +" professors_id:"+p.getFuncionarios().getId();
        }else if(object instanceof Cargos){
            Cargos p = (Cargos) object;
            texto += " Tabela cargos  ID:"+p.getId()+" nome:"+p.getNome();
        }
        
        return texto;
    }
    
    
    public boolean save(Object object, String acao){
        Date data = new Date();
        String texto = textAuditoria(object, acao);
        Users user = Util.usuarioLogado;
        if(user == null){
            user = new Users();
            user.setId(0);
            user.setNome("Funcionário ponto");
        }
        Auditoria auditoria = new Auditoria(user.getId(), user.getNome(), texto, data);
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(auditoria);
        s.getTransaction().commit();
      
        return true;
    }
}
