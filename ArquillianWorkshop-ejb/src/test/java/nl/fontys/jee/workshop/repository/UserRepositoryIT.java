package nl.fontys.jee.workshop.repository;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import nl.fontys.jee.workshop.model.User;
import nl.fontys.jee.workshop.repository.UserRepository;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import javax.inject.Inject;

/**
 * Integration test for user repository.
 * <p>
 * Tests for:
 * <ul>
 * <li>Proper injection of UserRepository.
 * <li>Persisting a User.
 * <li>Finding a User by id.
 * <li>Finding a User by username.
 * </ul>
 *
 * @author Sebastiaan Heijmann
 */
@RunWith(Arquillian.class)
public class UserRepositoryIT {

  @Deployment
  public static Archive<?> createTestArchive() {
    return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(User.class, UserRepository.class)
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
  }

  @Inject
  UserRepository repository;

  @Test
  public void should_inject_repository() {
    assertNotNull("should inject repository", repository);
  }

  @Test
  public void should_persist_user() {
    User user = new User();
    user.setUsername("new user");
    repository.persist(user);

    assertNotNull("should set id", user.getId());
  }

  @Test
  public void should_find_user_by_id() {
    User user = new User();
    user.setUsername("new user2");
    repository.persist(user);

    user = repository.findById(1);
    assertNotNull("should find user with id: 1", user);
  }

  @Test
  public void should_find_user_by_username() {
    User user = new User();
    user.setUsername("new user3");
    repository.persist(user);

    user = repository.findByUsername("new user3");
    assertNotNull("should find user with username: 'new user3'", user);
  }
}
