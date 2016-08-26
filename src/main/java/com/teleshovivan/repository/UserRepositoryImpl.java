/*
package repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

*/
/**
 * Created by Jager on 25.08.2016.
 *//*

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User get(int id) {
        System.out.println(entityManager);
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }

    @Override
    public boolean delete(int id) {
        return entityManager.createNamedQuery(User.DELETE).setParameter("id", id).executeUpdate() != 0;

    }
}
*/
