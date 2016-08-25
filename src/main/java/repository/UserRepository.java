package repository;

import model.User;

import java.util.List;

/**
 * Created by Jager on 25.08.2016.
 */
public interface UserRepository {
    User save(User user);

    User get(int id);

    List<User> getAll();

    boolean delete(int id);
}
