/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import dao.UserDao;
import entidades.Users;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author carleandro
 */
public class Sistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Users u = new Users();
        u.setNome("Danielsousa1");
        u.setEmail("danielsousa@gmail.com");
        new UserDao().save(u);
    }
    
}
