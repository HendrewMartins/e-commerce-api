package br.hendrew.ecommerce.services;

import javax.ws.rs.core.Response;

import br.hendrew.ecommerce.entity.User;
import br.hendrew.ecommerce.entity.UserLogin;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;

public interface UserServices {

    User saveUser(User user);

    User getUserById(long id) throws MenssageNotFoundException;
    
    boolean userExists(String username);

    Response generateToken(UserLogin user) throws Exception;

}
