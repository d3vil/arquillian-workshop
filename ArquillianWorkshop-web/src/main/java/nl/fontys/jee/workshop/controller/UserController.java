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

  @Produces
  @RequestScoped
  public FacesContext getFacesContext() {
    return FacesContext.getCurrentInstance();
  }

  @Inject
  private FacesContext facesContext;

  @Inject
  private UserRepository repository;

  @Produces
  @Named
  public User getNewUser() {
    return currentUser;
  }

  public String register() throws Exception {
      repository.persist(currentUser);
      registered = true;
      facesContext.addMessage(null,
              new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      return "home.xhtml";
  }

  @PostConstruct
  public void initNewMember() {
    currentUser = new User();
  }

  private String getRootErrorMessage(Exception e) {
    String errorMessage = "Registration failed. See server log for more information";
    if (e == null) {
      return errorMessage;
    }

    Throwable t = e;
    while (t != null) {
      errorMessage = t.getLocalizedMessage();
      t = t.getCause();
    }
    return errorMessage;
  }
}
