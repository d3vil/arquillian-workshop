package nl.fontys.jee.workshop.repository;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import nl.fontys.jee.workshop.model.User;

/**
 * Repository for {@link nl.fontys.jee.workshop.arquillian.model.User}
 *
 * @author Sebastiaan Heijmann
 */
@Stateless
public class UserRepository {

  @PersistenceContext
  private EntityManager em;

  @Inject
  private Event<User> userEventSrc;

  public User findById(long id) {
    return em.find(User.class, id);
  }

  public User findByUsername(String username) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<User> criteria = cb.createQuery(User.class);
    Root<User> member = criteria.from(User.class);
    criteria.select(member).where(cb.equal(member.get("username"), username));
    return em.createQuery(criteria).getSingleResult();
  }

  public User persist(User user) {
    em.persist(user);
    userEventSrc.fire(user);
    return user;
  }

}
