package br.hendrew.services.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.google.inject.Inject;

import br.hendrew.entity.Token;
import br.hendrew.entity.User;
import br.hendrew.exception.MenssageNotFoundException;
import br.hendrew.repository.UserRepository;
import br.hendrew.services.UserServices;
import br.hendrew.token.TokenGenerator;
import io.quarkus.elytron.security.common.BcryptUtil;

@ApplicationScoped
public class DefaultUserServices implements UserServices {

    final static String USERROLE = "user";
    final static String ADMINROLE = "admin";
    final static String SEPARATOR = ",";

    @Inject
    private final UserRepository userRepository;

    public DefaultUserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User saveUser(User user) {

        if(!userExists(user.getUsername())){
            user.setRoles(USERROLE);
            user.setPassword(BcryptUtil.bcryptHash(user.getPassword()));
            userRepository.persistAndFlush(user);
            return user;
        }

        return null;
    }

    @Override
    public User getUserById(long id) throws MenssageNotFoundException {
        return userRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There User doesn't exist"));
    }

    @Override
    public boolean userExists(String username){
        return (userRepository.count("username", username) > 0);
    }

    @Override
    public Token generateJWT(String email){
        Token token = new Token();
        User foundUser = userRepository.find("email", email).firstResult();
        token.setToken(TokenGenerator.generate(email, foundUser.getRoles()));
        return token;
    }

    private static void addRole(User user, String role){
        user.setRoles( user.getRoles() + SEPARATOR + role);
    }

    @Transactional
    @Override
    public void addAdminRole(Long userId){
        User user = userRepository.findById(userId);
        if(userExists(user.getUsername())){
            addRole(user, ADMINROLE);
            userRepository.persistAndFlush(user);
        }
    }

    @Transactional
    public void addCustomRole(Long userId, String role){
        User user = userRepository.findById(userId);
        if(userExists(user.getUsername())){
            addRole(user, role);
            userRepository.persistAndFlush(user);
        }
    }

}
