package nl.fontys.jee.workshop.service;

import javax.ejb.Stateless;
import nl.fontys.jee.workshop.model.User;

/**
 * Service class which is responsible for registering users.
 *
 * @author Sebastiaan Heijmann
 */
@Stateless
public class UserRegistrator {

  public String register(User user) {
    return user.getUsername() + " has been successfully registered";
  }

}
