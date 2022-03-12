package br.hendrew.ecommerce.services.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;


import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.hendrew.ecommerce.entity.Role;
import br.hendrew.ecommerce.entity.Token;
import br.hendrew.ecommerce.entity.User;
import br.hendrew.ecommerce.entity.UserLogin;
import br.hendrew.ecommerce.exception.MenssageNotFoundException;
import br.hendrew.ecommerce.repository.UserRepository;
import br.hendrew.ecommerce.services.UserServices;
import br.hendrew.ecommerce.util.PBKDF2Encoder;
import br.hendrew.ecommerce.util.TokenUtils;

@ApplicationScoped
public class DefaultUserServices implements UserServices {

    final static Role USERROLE = Role.USER;
    final static Role ADMINROLE = Role.ADMIN;

    @ConfigProperty(name = "br.hendrew.ecommerce.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

    @Inject
    private final UserRepository userRepository;

    @Inject 
    private final PBKDF2Encoder encoder;

    public DefaultUserServices(UserRepository userRepository, PBKDF2Encoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public User saveUser(User user) {

        if(!userExists(user.getEmail())){
            user.setRoles(ADMINROLE);
            user.setPassword(encoder.encode(user.getPassword()));
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
    public Response generateToken(UserLogin userLogin) throws Exception{

        User user = userRepository.find("email", userLogin.getEmail()).firstResult();
       
        try {
            if (user != null && verifyCryptPassword(user, userLogin)){
                try {
                    return Response.ok(new Token(TokenUtils.generateToken(user.getEmail(), user.getRoles(), duration, issuer))).build();
                } catch (Exception e) {
                    return Response.status(Status.UNAUTHORIZED).build();
                }
            } else {
                return Response.status(Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

   



    public boolean verifyCryptPassword(User user, UserLogin login){
        return user.getPassword().equals(encoder.encode(login.getSenha()));
    }

}
