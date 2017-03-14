package cc.codehub.newkit.service;

import cc.codehub.newkit.model.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by Li Di on 2017/3/9.
 */
public interface AccessAndRoleService {

    List<Access> getAccessList();

    List<Access> getAllAccesses(Pageable pageable);

    Access createAccess(Access access);

    Access updateAccess(Access access);

    void deleteAccess(Integer id);

    boolean existsAccessName(String name);

    boolean existsAccessUrl(String url);

    Access getAccess(Integer id);

    List<String> findAccessUrlByRoleId(Integer roleId);


    Role addRole(Role role);

    void delRole(Integer id);

    Role updateRole(Role role);

    List<Role> getAllRoles(Pageable pageable);

    Role getRoleByRoleId(Integer id);

    List<String> getRoleNamesByAccessId(Integer accessId);

    boolean existsRoleName(String name);


    List<AccessRoleMap> getAllAccessRoleMapsByRoleId(Integer roleId);

    AccessRoleMap createAccessRoleMap(AccessRoleMap accessRoleMap);

    void deleteAccessRoleMapByRoleId(Integer roleId);

    void deleteAccessRoleMapByMapId(Integer mapId);



    Set<String> findRoleNamesByUser(User user);

    List<RoleUserMap> getAllUserRole(int userId);

    RoleUserMap addUserRoleMap(RoleUserMap roleUserMap);

    void deleteUserRoleByUserId(int userId);

    void deleteUserRoleByUserIdAndRoleId(int userId, int roleId);

    List<String> findAllRoleNameByUsername(String account);
}
