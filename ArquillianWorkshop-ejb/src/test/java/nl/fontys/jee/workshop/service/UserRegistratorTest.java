package nl.fontys.jee.workshop.service;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
            .addClasses(UserRegistrator.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
    assertEquals("should return success message",
            "User has been successfully registered",
            registrator.register("User"));
  }

}
