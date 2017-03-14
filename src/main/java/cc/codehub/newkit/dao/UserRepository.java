package cc.codehub.newkit.dao;

import cc.codehub.newkit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);

    User findByMobile(String mobile);

    User findByEmail(String email);

    User findByName(String name);

    User findByIdNo(String idNo);

    User findByUsernameOrMobileOrEmail(String username, String mobile, String email);
}
