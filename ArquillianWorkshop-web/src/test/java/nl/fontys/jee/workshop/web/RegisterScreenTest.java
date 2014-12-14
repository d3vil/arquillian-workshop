 package nl.fontys.jee.workshop.web;

import java.net.URL;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Functional test for registering a user.
 *
 * @author Sebastiaan Heijmann
 */
public class RegisterScreenTest {

  private static final String WEBAPP_SRC = "src/main/webapp";

  @ArquillianResource
  private URL deploymentUrl;

  //TODO Create deployment

  //TODO Inject WebDriver

  //TODO Inject WebElement inputfield

  //TODO Inject WebElement button

  //TODO Inject WebElement facesMessage

  //TODO Implement functional testcase
  @Test
  public void should_register_user() throws InterruptedException {
     assertFalse("TODO", true);
  }
}
