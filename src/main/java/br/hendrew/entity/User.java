package br.hendrew.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.Getter;
import lombok.Setter;

/**
 * User entity class for persisting user data
 * If you plan on using this in production, please add some requirements
 * to the password field and restrictions to the username. 
 */

@Getter
@Setter
@Entity
@Table(name = "usuario")
@UserDefinition 
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="username" ,unique = true)
    @Username 
    @NotNull
    @Size(min = 3,max = 20)
    private String username;

    @Column(name="email" ,unique = true)
    @Username 
    @NotNull
    @Size(min = 3,max = 20)
    private String email;

    @Password 
    @Column(name="password")
    private String password;

    // Comma-separated list of roles
    @Roles
    private String roles;
}
