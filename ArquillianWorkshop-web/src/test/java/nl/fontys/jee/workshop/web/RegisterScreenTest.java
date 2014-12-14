 package nl.fontys.jee.workshop.web;

import java.io.File;
import java.net.URL;
import nl.fontys.jee.workshop.arquillian.model.User;
import nl.fontys.jee.workshop.arquillian.repository.UserRepository;
import nl.fontys.jee.workshop.controller.UserController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Functional test for registering a user.
 *
 * @author Sebastiaan Heijmann
 */
@RunWith(Arquillian.class)
public class RegisterScreenTest {

  private static final String WEBAPP_SRC = "src/main/webapp";

  @Deployment(testable = false)
  public static WebArchive createDeployment() {
    WebArchive war = ShrinkWrap.create(WebArchive.class, "login.war")
            .addClasses(User.class, UserRepository.class, UserController.class)
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            .addAsResource(EmptyAsset.INSTANCE, "META-INF/beans.xml")
            .addAsWebResource(new File(WEBAPP_SRC, "home.xhtml"))
            .addAsWebResource(new File(WEBAPP_SRC, "register.xhtml"))
            .addAsWebInfResource(
                    new StringAsset("<faces-config version=\"2.0\"/>"),
                    "faces-config.xml");

    System.out.println(war.toString(true));
    return war;
  }

  @Drone
  private WebDriver browser;

  @ArquillianResource
  private URL deploymentUrl;

  @FindBy(id = "username")
  private WebElement username;

  @FindBy(id = "register")
  private WebElement registerButton;

  @FindBy(tagName = "li")
  private WebElement facesMessage;

  @Test
  public void should_register_user() throws InterruptedException {
    browser.get(deploymentUrl.toExternalForm() + "register.jsf");

    username.sendKeys("user");
    registerButton.click();
    assertEquals("Registered!", facesMessage.getText().trim());

  }
}
