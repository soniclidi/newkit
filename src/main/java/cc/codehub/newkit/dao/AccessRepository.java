package cc.codehub.newkit.dao;

import cc.codehub.newkit.model.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AccessRepository extends JpaRepository<Access, Integer> {

    @Query(value="select a.url from access_role_map ar inner join "
            + "access a on a.access_id=ar.access_id where ar.role_id=:roleId", nativeQuery=true)
    List<String> findUrlsByRoleId(@Param("roleId")Integer roleId);
}
