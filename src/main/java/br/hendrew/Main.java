package br.hendrew;

import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;

import br.hendrew.entity.User;
import br.hendrew.services.UserServices;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;


/**
 * Main method for intercepting command line arguments
 * @param userdata The initial admin user in username:password format
 */
@QuarkusMain
public class Main {

    
    public final UserServices userServices;

    @Inject
    public Main(UserServices userServices){
      this.userServices = userServices;
    }

    public static void main(String... args ) {
        Quarkus.run(SimpleLogin.class, args);
    }

    public static class SimpleLogin implements QuarkusApplication {

      @Override
      @ActivateRequestContext
      public int run(String... args) throws Exception {
        
        if (args.length>0) {
            String[] userData = args[0].split(":");
            addAdmin(userData[0], userData[1], userData[2]);
            }
          Quarkus.waitForExit();
          return 0;
      }

      private void addAdmin(String email, String password, String username){
        User admin = new User();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(password);
        Main main = new Main(null);
        main.userServices.saveUser(admin);
        main.userServices.addAdminRole(admin.getId());
      }
    }
  }
 