package nl.fontys.jee.workshop.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import nl.fontys.jee.workshop.model.User;
import nl.fontys.jee.workshop.repository.UserRepository;

/**
 * Service class which is responsible for registering users.
 *
 * @author Sebastiaan Heijmann
 */
@Stateless
public class UserRegistrator {

  @Inject
  UserRepository repository;

  public String register(User user) {
    if(repository.findByUsername(user.getUsername()) == null){
      repository.persist(user);
      return user.getUsername() + " has been successfully registered";
    }
    return user.getUsername() + " already exists";
  }

}
