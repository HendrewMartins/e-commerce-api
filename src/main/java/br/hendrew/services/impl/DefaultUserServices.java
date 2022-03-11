package br.hendrew.services.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.google.inject.Inject;

import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

import org.wildfly.security.password.Password;



import br.hendrew.entity.Token;
import br.hendrew.entity.User;
import br.hendrew.entity.UserLogin;
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

        if(!userExists(user.getEmail())){
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
    public boolean userExists(String nome){
        return (userRepository.count("nome", nome) > 0);
    }

    @Override
    public Token generateJWT(UserLogin user){
        Token token = new Token();
        User foundUser = userRepository.find("email", user.getEmail()).firstResult();
       
        try {
            if(verifyBCryptPassword(foundUser.getPassword(), user.getSenha())){
                token.setToken(TokenGenerator.generate(user.getEmail(), foundUser.getRoles()));
                return token;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private static void addRole(User user, String role){
        user.setRoles( user.getRoles() + SEPARATOR + role);
    }

    @Transactional
    @Override
    public void addAdminRole(Long userId){
        User user = userRepository.findById(userId);
        if(userExists(user.getEmail())){
            addRole(user, ADMINROLE);
            userRepository.persistAndFlush(user);
        }
    }

    @Transactional
    public void addCustomRole(Long userId, String role){
        User user = userRepository.findById(userId);
        if(userExists(user.getEmail())){
            addRole(user, role);
            userRepository.persistAndFlush(user);
        }
    }

    public boolean verifyBCryptPassword(String bCryptPasswordHash, String passwordToVerify) throws Exception {

        WildFlyElytronPasswordProvider provider = new WildFlyElytronPasswordProvider();

        // 1. Create a BCrypt Password Factory
        PasswordFactory passwordFactory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, provider);

        // 2. Decode the hashed user password
        Password userPasswordDecoded = ModularCrypt.decode(bCryptPasswordHash);

        // 3. Translate the decoded user password object to one which is consumable by this factory.
        Password userPasswordRestored = passwordFactory.translate(userPasswordDecoded);

        // Verify existing user password you want to verify
        return passwordFactory.verify(userPasswordRestored, passwordToVerify.toCharArray());

    }

}
