package entidades;
// Generated 17/03/2019 07:41:02 by Hibernate Tools 4.3.1

import java.util.Date;




/**
 * Auditoria generated by hbm2java
 */
public class Cargos  implements java.io.Serializable {


     private Integer id;
     private String nome;

    public Cargos() {
    }

    public Cargos(String nome) {
       this.nome = nome;

    }

   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



}


