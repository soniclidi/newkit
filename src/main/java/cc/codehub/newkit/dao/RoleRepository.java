package cc.codehub.newkit.dao;

import cc.codehub.newkit.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value="select r.name from access_role_map ar inner join "
            + "role r on r.role_id = ar.role_id where ar.access_id=:accessId", nativeQuery=true)
    List<String> findRoleNamesByAccessId(@Param("accessId")Integer accessId);
}
