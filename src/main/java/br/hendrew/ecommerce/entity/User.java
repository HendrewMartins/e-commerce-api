package br.hendrew.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * User entity class for persisting user data
 * If you plan on using this in production, please add some requirements
 * to the password field and restrictions to the username. 
 */

 /**
  * @author hendrewmartins
  */

@Getter
@Setter
@Entity
@Table(name = "usuario") 
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="email" ,unique = true)
    @NotNull
    @Size(min = 3,max = 30)
    private String email;

    @Column(name="nome" )
    @NotNull
    @Size(min = 3,max = 100)
    private String nome;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="roles")
    @Enumerated (EnumType.STRING) 
    private Role roles;
}
