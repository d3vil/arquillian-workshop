package nl.fontys.jee.workshop.service;

import javax.ejb.Stateless;

/**
 * Service class which is responsible for registering users.
 *
 * @author Sebastiaan Heijmann
 */
@Stateless
public class UserRegistrator {

  public String register(String username) {
    return username + " has been successfully registered";
  }

}
