package cc.codehub.newkit.dao;

import cc.codehub.newkit.model.RoleUserMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoleUserMapRepository extends JpaRepository<RoleUserMap, Integer> {

    @Query(value="select r.name from role_user_map ru join role r on ru.role_id=r.role_id where ru.uid=:userId",
            nativeQuery=true)
    List<String> findRoleNamesByUserId(@Param("userId")Integer userId);

    List<RoleUserMap> findByUid(Integer uid);

    List<RoleUserMap> findByRoleId(Integer roleId);

    void deleteByUid(Integer uid);

    void deleteByUidAndRoleId(Integer uid, Integer roleId);
}
