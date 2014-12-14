package nl.fontys.jee.workshop.integrationtest;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration test for user registration.
 * <p>
 * Tests for:
 * <ul>
 * <li>Persisting a {@link nl.fontys.jee.workshop.arquillian.model.User}.
 * <li>Finding a {@link nl.fontys.jee.workshop.arquillian.model.User} by id.
 * <li>Finding a {@link nl.fontys.jee.workshop.arquillian.model.User} by
 * username.
 * </ul>
 *
 * @author Sebastiaan Heijmann
 */
public class TestUserRegistration {

  // TODO
  @Test
  public void should_inject_registrator() {
    assertFalse("Todo", true);
  }

  // TODO
  @Test
  public void should_register_user() {
    assertFalse("Todo", true);
  }

  // TODO
  @Test
  public void should_not_persist_user_when_username_exists() {
    assertFalse("Todo", true);
  }

}
