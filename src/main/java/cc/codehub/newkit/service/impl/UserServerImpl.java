package cc.codehub.newkit.service.impl;

import cc.codehub.newkit.common.UserStatus;
import cc.codehub.newkit.dao.UserRepository;
import cc.codehub.newkit.model.User;
import cc.codehub.newkit.service.UserService;
import cc.codehub.newkit.util.PasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;


@Service
public class UserServerImpl implements UserService{

    private static final int PRIVATE_SALT_LENGTH = 12;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setUid(null);
        user.setStatus(UserStatus.NORMAL.getValue());
        encryptPassword(user, user.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        user.setUsername(null);//不允许修改账号
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        user.setStatus(UserStatus.DELETE.getValue());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findOne(id);
        user.setStatus(UserStatus.DELETE.getValue());

        userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    @Override
    public User getUserByIdno(String idno) {
        return userRepository.findByIdNo(idno);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getUserByUsernameOrMobileOrEmail(String username, String mobile, String email) {
        return userRepository.findByUsernameOrMobileOrEmail(username, mobile, email);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        Page<User> pageData = userRepository.findAll(new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> status = root.get("status");
                query.where(cb.notEqual(status, 1));
                return null;
            }
        }, pageable);

        return pageData;
    }

    @Override
    public long usersCount() {
        return userRepository.count();
    }

    @Override
    public User authorize(String username, String password) {
        User user = userRepository.findByUsernameOrMobileOrEmail(username, username, username);
        if (user == null) {
            return null;
        }

        String salt = user.getSalt();
        String encryptedPassword = user.getPassword();
        if (encryptedPassword.equals(PasswordEncoder.encryptPassword(password, salt))) {
            return user;
        }

        return null;
    }

    @Override
    public User encryptPassword(User user, String password) {
        String salt = RandomStringUtils.randomAlphanumeric(PRIVATE_SALT_LENGTH);
        user.setSalt(salt);
        user.setPassword(PasswordEncoder.encryptPassword(password, salt));

        return user;
    }
}
