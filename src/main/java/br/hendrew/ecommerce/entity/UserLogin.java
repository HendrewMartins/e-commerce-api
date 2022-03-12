package br.hendrew.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hendrewmartins
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@ToString
public class UserLogin {

    private String email;
    private String senha;

}
