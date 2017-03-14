package cc.codehub.newkit.service;

import cc.codehub.newkit.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    User getUserByUsername(String username);

    User getUserByMobile(String mobile);

    User getUserByIdno(String idno);

    User getUserByEmail(String email);

    User getUserByName(String name);

    User getUserByUsernameOrMobileOrEmail(String username, String mobile, String email);

    Page<User> getAllUsers(Pageable pageable);

    long usersCount();

    User authorize(String username, String password);

    User encryptPassword(User user, String password);
}
