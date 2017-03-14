package cc.codehub.newkit.service;

import cc.codehub.newkit.model.User;
import org.springframework.data.domain.Pageable;

/**
 * Created by Li Di on 2017/3/9.
 */
public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    User getUserByUsername(String username);

    User getUserByMobile(String mobile);

    User getUserByIdno(String idno);

    Iterable<User> getAllUsers(Pageable pageable);

    long usersCount();

    User authorize(String username, String password);

    User encryptPassword(User user, String password);
}
