package nl.fontys.jee.workshop.controller;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import nl.fontys.jee.workshop.arquillian.model.User;
import javax.annotation.PostConstruct;
import nl.fontys.jee.workshop.arquillian.repository.UserRepository;

/**
 * Controller for registering users.
 *
 * @author Sebastiaan Heijmann
 */
@Model
public class UserController {

  private boolean registered;

  private User currentUser;

  @Produces
  @Named
  public User getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(User currentUser) {
    this.currentUser = currentUser;
  }

  public boolean isRegistered() {
    return registered;
  }

  public void setRegistered(boolean registered) {
    this.registered = registered;
  }

  @Inject
  private FacesContext facesContext;

  @Inject
  private UserRepository repository;

  @Produces
  @RequestScoped
  public FacesContext getFacesContext() {
    return FacesContext.getCurrentInstance();
  }

  @PostConstruct
  public void initCurrentUser() {
    currentUser = new User();
  }

  public String register() throws Exception {
    repository.persist(currentUser);
    registered = true;
    facesContext.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
    return "home.xhtml";
  }
}
