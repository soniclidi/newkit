package cc.codehub.newkit.dao;

import cc.codehub.newkit.model.AccessRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccessRoleMapRepository extends JpaRepository<AccessRoleMap, Integer> {
    List<AccessRoleMap> findByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);
}
