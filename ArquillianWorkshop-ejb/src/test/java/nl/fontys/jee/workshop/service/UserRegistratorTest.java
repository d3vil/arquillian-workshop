package nl.fontys.jee.workshop.service;

import javax.inject.Inject;
import nl.fontys.jee.workshop.model.User;
import nl.fontys.jee.workshop.repository.UserRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 * First test with arquillian.
 * <p>
 * Tests for:
 * <ul>
 * <li>Context Dependency Injection properly setup.
 * <li>Registering a user.
 * </ul>
 *
 * @author Sebastiaan Heijmann
 */
@RunWith(Arquillian.class)
public class UserRegistratorTest {

  @Deployment
  public static Archive<?> createTestArchive() {
    return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(User.class, UserRegistrator.class ,UserRepository.class)
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
  }

  @Inject
  UserRegistrator registrator;

  @Test
  public void should_inject_a_user_registrator() {
    assertNotNull("should inject a UserRegistrator instance", registrator);
  }

  //TODO
  @Test
  public void should_register_a_user() {
    User user = new User();
    user.setUsername("User");
    assertEquals("should return success message",
            "User has been successfully registered",
            registrator.register(user));
  }

}
