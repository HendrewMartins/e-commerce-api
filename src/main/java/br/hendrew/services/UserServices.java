package br.hendrew.services;

import br.hendrew.entity.Token;
import br.hendrew.entity.User;
import br.hendrew.entity.UserLogin;
import br.hendrew.exception.MenssageNotFoundException;

public interface UserServices {

    User saveUser(User user);

    User getUserById(long id) throws MenssageNotFoundException;
    
    boolean userExists(String username);

    Token generateJWT(UserLogin user);

    void addAdminRole(Long userId);
}
